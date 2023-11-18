public class CastleBoard extends Bitboard{
    private long init = 0x81L;

    public long initialiseBoard(boolean isBlack) {
        if (isBlack) {
            super.state = Long.reverse(init);
        } else {
            super.state = init;
        }
        return super.state;
    }
}
