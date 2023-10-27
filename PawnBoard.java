public class PawnBoard extends Bitboard {
    private boolean colour;
    private long init;
    private long board;
    
    public PawnBoard() {
        super();
        this.init = 0x000000000000FF00L;
        this.board = super.getBoard();
    }

    public long initialiseBoard(boolean colour) {
        board = init;
        return board;
    }
}
