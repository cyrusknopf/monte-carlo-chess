import java.util.ArrayList;

public class KingBoard extends Bitboard {
    private long init = 0x8L;
    final private Chess game;
    final private boolean colour;

    public KingBoard(Chess game, boolean colour) {
        this.game = game;
        this.colour = colour;
    }

    public KingBoard(KingBoard other) {
        this.init = other.init;
        this.game = other.game;
        this.colour = other.colour;
    }

    @Override
    public KingBoard copy() {
        return new KingBoard(this);
    }

    public ArrayList<KingBoard> getPseudoLegalMoves() {
        KingBoard[] tempMoveList = new KingBoard[8];
        ArrayList<KingBoard> pseudoLegalMoves = new ArrayList<>();
        
        for (int i = 0; i < tempMoveList.length; i++) {
            tempMoveList[i] = new KingBoard(this);
            System.out.println(this.state);
            System.out.println(tempMoveList[i].state);
        }

        
        tempMoveList[0].slideNorth();
        tempMoveList[1].slideNorthEast();
        tempMoveList[2].slideEast();
        tempMoveList[3].slideSouthEast();
        tempMoveList[4].slideSouth();
        tempMoveList[5].slideSouthWest();
        tempMoveList[6].slideWest();
        tempMoveList[7].slideNorthWest();

        for (KingBoard move : tempMoveList) {
            // System.out.println(move);
            if ((move.state & game.getGameState(this.colour)) != 0) {
                continue;
            }
            if ((move.state & game.getGameState(!this.colour)) % 2 == 0) {
                pseudoLegalMoves.add(move);
            }
        }
        
        
        return pseudoLegalMoves;
    }

    public long initialiseBoard() {
        if (!this.colour) {
            super.state = Long.reverse(init);
        } else {
            super.state = init;
        }
        return super.state;
    }
}
