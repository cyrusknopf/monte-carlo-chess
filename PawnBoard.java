public class PawnBoard extends Bitboard {
    private long init = 0x000000000000FF00L;

    public PawnBoard() {
        this.empty = 0x0L;
    }

    public long initialiseBoard(boolean isBlack) {
        if (isBlack) {
            super.board = Long.reverse(init);
        } else {
            super.board = init;
        }
        return super.board;
    }
    
    public long checkMoves() {
        return 0;
    }
}
