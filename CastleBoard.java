public class CastleBoard extends Bitboard{
    private long init = 0x81L;

    public long initialiseBoard(boolean isBlack) {
        if (isBlack) {
            super.board = Long.reverse(init);
        } else {
            super.board = init;
        }
        return super.board;
    }
}
