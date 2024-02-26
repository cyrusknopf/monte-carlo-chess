package montecarlochess.bitboards;

import montecarlochess.Chess;

import java.util.ArrayList;

public class HorseBoard extends Bitboard{
    private long init = 0x42L;
    private Chess game;
    private boolean colour;

    public HorseBoard(Chess game, boolean colour) {
        this.empty = 0x0L;
        this.game = game;
        this.colour = colour;
    }

    public HorseBoard(HorseBoard other) {
        this.init = other.init;
        this.game = other.game;
        this.colour = other.colour;
    }

    public long initialiseBoard() {
        if (!this.colour) {
            super.state = Long.reverse(init);
        } else {
            super.state = init;
        }
        return super.state;
    }

    // Returns an array of boards containg just one of the knights
    public ArrayList<HorseBoard> getAllBoards() {
        ArrayList<HorseBoard> hoarseBoards = new ArrayList<>();
        for (long state : this.getAllPieceStates()) {
            HorseBoard currentBoard = new HorseBoard(this.game, this.colour);
            currentBoard.state = state;
            hoarseBoards.add(currentBoard);
        }
        return hoarseBoards;
    }

    
    // Need to implement checking legal moves for both horses using above method
    public ArrayList<HorseBoard> getPseudoLegalMoves() {
        ArrayList<HorseBoard> pseudoLegalMoves = new ArrayList<>();
        // 8 Possible moves so initialise array
        HorseBoard[] tempMoveList = new HorseBoard[8];
        
        // Initialise each move as a HorseBoard with copy constructor
        for (int i = 0; i < tempMoveList.length; i++) {
            tempMoveList[i] = new HorseBoard(this);
        }

        // Carry out the moves, done in clockwise direction
        tempMoveList[0].slideNorth();
        tempMoveList[0].slideNorthEast();

        tempMoveList[1].slideEast();
        tempMoveList[1].slideNorthEast();

        tempMoveList[2].slideEast();
        tempMoveList[2].slideSouthEast();

        tempMoveList[3].slideSouth();
        tempMoveList[3].slideSouthEast();       

        tempMoveList[4].slideSouth();
        tempMoveList[4].slideSouthWest();

        tempMoveList[5].slideWest();
        tempMoveList[5].slideSouthWest();

        tempMoveList[6].slideWest();
        tempMoveList[6].slideNorthWest();

        tempMoveList[7].slideNorth();
        tempMoveList[7].slideNorthWest();
        
        return null;
    }
}
