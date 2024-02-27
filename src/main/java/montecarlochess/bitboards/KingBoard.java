package montecarlochess.bitboards;

import montecarlochess.Chess;
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
        /* There are eight possible moves the king can make,
         one in each direction, so we initialse an array to
        temporarily store these possible moves    
        */
        KingBoard[] tempMoveList = new KingBoard[8];
        ArrayList<KingBoard> pseudoLegalMoves = new ArrayList<>();
        
        // Initialise each move as a KingBoard with copy constructor
        for (int i = 0; i < tempMoveList.length; i++) {
            tempMoveList[i] = new KingBoard(this);
        }

        // Carry out the moves
        tempMoveList[0].slideNorth();
        tempMoveList[1].slideNorthEast();
        tempMoveList[2].slideEast();
        tempMoveList[3].slideSouthEast();
        tempMoveList[4].slideSouth();
        tempMoveList[5].slideSouthWest();
        tempMoveList[6].slideWest();
        tempMoveList[7].slideNorthWest();

        // For each of the preset moves above...
        for (KingBoard move : tempMoveList) {
            /*
            Checks if the king has moved to a position where
            a piece of the same colour already exists. If so
            continue to checking next move and do not add this
            move to legal moves.
            */
            if ((move.state & game.getGameState(this.colour)) != 0) {
                continue;
            }
            // Otherwise, move is pseudo legal
            pseudoLegalMoves.add(move);
            // I can't remember what this does but I think it works
             // if ((move.state & game.getGameState(!this.colour)) % 2 == 0) {
                // pseudoLegalMoves.add(move);
            // }
        }
        
        
        return pseudoLegalMoves;
    }

    // TODO Can we add this to the BitBoard class instead of implementing it in every
    // subclass?
    public long initialiseBoard() {
        if (!this.colour) {
            super.state = Long.reverse(init);
        } else {
            super.state = init;
        }
        return super.state;
    }
}
