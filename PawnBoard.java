import java.util.ArrayList;

public class PawnBoard extends Bitboard {
    private long init = 0x000000000000FF00L;
    private Chess game;
    private boolean colour;

    public PawnBoard(Chess game, boolean colour) {
        this.empty = 0x0L;
        this.game = game;
        this.colour = colour;
    }

    public PawnBoard(PawnBoard other) {
        this.init = other.init;
        this.game = other.game;
        this.colour = other.colour;

    }

    @Override
    public PawnBoard copy() {
        return new PawnBoard(this);
    }

    public long initialiseBoard() {
        if (!this.colour) {
            super.state = Long.reverse(init);
        } else {
            super.state = init;
        }
        return super.state;
    }

    // Returns an array of boards containing just each piece of the board.
    public ArrayList<PawnBoard> getAllBoards() {
        ArrayList<PawnBoard> pawnBoards = new ArrayList<>();
        for (long state: this.getAllPieceStates()) {
            PawnBoard currentBoard = new PawnBoard(this.game, this.colour);
            currentBoard.state = state;
            pawnBoards.add(currentBoard);
        }
        return pawnBoards;
    }


    public ArrayList<PawnBoard> getPseudoLegalPushes() {
        PawnBoard tempMovedBoard = this.copy();
        tempMovedBoard.state = this.state;
        ArrayList<PawnBoard> pseudoLegalMoves = new ArrayList<>();

        if (colour) {
            tempMovedBoard.slideNorth();
            // Checking for collisions
            if ((this.game.getGameState() & tempMovedBoard.state) == 0) {
                pseudoLegalMoves.add(tempMovedBoard);
            }
        }
        else {
            tempMovedBoard.slideSouth();
            // Checking for collisions
            if ((this.game.getGameState() & tempMovedBoard.state) == 0) {
                pseudoLegalMoves.add(tempMovedBoard);
            }
        }
        return pseudoLegalMoves;
    }

    public ArrayList<PawnBoard> getPseudoLegalCaptures() {
        PawnBoard tempMovedBoard;
        ArrayList<PawnBoard> pseudoLegalMoves = new ArrayList<>();

        if (colour) {
            tempMovedBoard = this.copy();
            tempMovedBoard.slideNorthEast();
            // Checks if there are no colisions with other white pieces
            if ((this.game.getGameState(colour) & tempMovedBoard.state) == 0) {
                // Checks that there is at least one collision with a black piece ie a capture
                if ((this.game.getGameState(!colour) & tempMovedBoard.state) > 0) {
                    pseudoLegalMoves.add(tempMovedBoard);
                }
            }
            tempMovedBoard = this.copy();
            tempMovedBoard.slideNorthWest();
            // Checks if there are no colisions with other white pieces
            if ((this.game.getGameState(colour) & tempMovedBoard.state) == 0) {
                // Checks that there is at least one collision with a black piece ie a capture
                if ((this.game.getGameState(!colour) & tempMovedBoard.state) > 0) {
                    pseudoLegalMoves.add(tempMovedBoard);
                }
            }

        }
        else {
            tempMovedBoard = this.copy();
            tempMovedBoard.slideSouthEast();
            // Checks there are no collisions wiht other black pieces
            if ((this.game.getGameState(!colour) & tempMovedBoard.state) == 0) {
                // Checks that there is at least one collision with a white piece ie a capture
                if ((this.game.getGameState(colour) & tempMovedBoard.state) > 0) {
                    pseudoLegalMoves.add(tempMovedBoard);
                }
            }
            tempMovedBoard = this.copy();
            tempMovedBoard.slideSouthWest();
            if ((this.game.getGameState(!colour) & tempMovedBoard.state) == 0) {
                // Checks that there is at least one collision with a white piece ie a capture
                if ((this.game.getGameState(colour) & tempMovedBoard.state) > 0) {
                    pseudoLegalMoves.add(tempMovedBoard);
                }
            }
        }
        return pseudoLegalMoves;
    }

}
