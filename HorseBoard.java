public class HorseBoard extends Bitboard{
    private long init = 0x42L;

    public long initialiseBoard(boolean isBlack) {
        if (isBlack) {
            super.board = Long.reverse(init);
        } else {
            super.board = init;
        }
        return super.board;
    }
}
