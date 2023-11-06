public class HorseBoard extends Bitboard{
    private long init = 0x42L;

    public long initialiseBoard(boolean isBlack) {
        if (isBlack) {
            super.state = Long.reverse(init);
        } else {
            super.state = init;
        }
        return super.state;
    }
}
