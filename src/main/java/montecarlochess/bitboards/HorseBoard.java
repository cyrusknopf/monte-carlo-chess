package montecarlochess.bitboards;

import montecarlochess.Chess;

import java.util.ArrayList;

public class HorseBoard extends Bitboard {
    private long init = 0x42L;
    private Chess game;
    private boolean colour;

    public HorseBoard(Chess game, boolean colour) {
        this.empty = 0x0L;
        this.game = game;
        this.colour = colour;
    }

    public HorseBoard(HorseBoard other) {
        this.game = other.game;
        this.colour = other.colour;
        this.state = other.state;
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

    public ArrayList<HorseBoard> makeMoves() {

        // 8 Possible moves so initialise array
        ArrayList<HorseBoard> tempMoveList = new ArrayList<HorseBoard>();

        if (this.getFile() < 8) {
            HorseBoard hb1 = new HorseBoard(this);
            hb1.slideNorth();
            hb1.slideNorthEast();
            tempMoveList.add(hb1);

            HorseBoard hb2 = new HorseBoard(this);
            hb2.slideSouth();
            hb2.slideSouthEast();
            tempMoveList.add(hb2);
        }

        if (this.getFile() < 7) {
            HorseBoard hb1 = new HorseBoard(this);
            hb1.slideEast();
            hb1.slideNorthEast();
            tempMoveList.add(hb1);

            HorseBoard hb2 = new HorseBoard(this);
            hb2.slideEast();
            hb2.slideSouthEast();
            tempMoveList.add(hb2);
        }

        if (this.getFile() > 1) {
            HorseBoard hb1 = new HorseBoard(this);
            hb1.slideSouth();
            hb1.slideSouthWest();
            tempMoveList.add(hb1);

            HorseBoard hb2 = new HorseBoard(this);
            hb2.slideNorth();
            hb2.slideNorthWest();
            tempMoveList.add(hb2);
        }

        if (this.getFile() > 2) {
            HorseBoard hb1 = new HorseBoard(this);
            hb1.slideWest();
            hb1.slideSouthWest();
            tempMoveList.add(hb1);

            HorseBoard hb2 = new HorseBoard(this);
            hb2.slideWest();
            hb2.slideNorthWest();
            tempMoveList.add(hb2);
        }

        return tempMoveList;
    }

    public ArrayList<HorseBoard> getPseudoLegalMoves() {
        ArrayList<HorseBoard> pseudoLegalMoves = new ArrayList<>();
        // For each horse...
        for (HorseBoard horse : this.getAllBoards()) {
            ArrayList<HorseBoard> moves = horse.makeMoves();
            // For each move that horse can make...
            for (HorseBoard move : moves) {
                if (move.state == 0) {
                    continue;
                }

                if ((move.state & game.getGameState(horse.colour)) != 0) {
                    continue;
                }

                pseudoLegalMoves.add(move);
            }
        }
        return pseudoLegalMoves;
    }

}
