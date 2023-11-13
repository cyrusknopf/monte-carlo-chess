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

    public ArrayList<PawnBoard> canPseudoLegalPush() {
        PawnBoard tempCurrentBoard = this;
        PawnBoard tempMovedBoard = new PawnBoard(this.game, this.colour);
        tempMovedBoard.state = this.state;
        ArrayList<PawnBoard> pseudoLegalMoves = new ArrayList<>();

        if (this.colour) {
            tempMovedBoard.slideNorth();
        }
        else {
            tempMovedBoard.slideSouth();
        }
        
        if ((tempCurrentBoard.state & tempMovedBoard.state) == 0) {
            pseudoLegalMoves.add(tempMovedBoard);
        }
        return pseudoLegalMoves;
    }

    // public boolean canPseudoLegalCapture() {
    //     long tempState = this.game.getGameState();
    //     long madeMoveState;

    //     if (this.colour = true) {
    //         madeMoveState = this.slideEast();
    //     }
    // }

}
