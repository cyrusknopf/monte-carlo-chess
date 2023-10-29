public class PawnBoard extends Bitboard {
    private long init = 0x000000000000FF00L;

    public long initialiseBoard(boolean isBlack) {
        if (isBlack) {
            super.board = Long.reverse(init);
        } else {
            long reversedInit = init;
            super.board = reversedInit;
        }
        return super.board;
    }
}