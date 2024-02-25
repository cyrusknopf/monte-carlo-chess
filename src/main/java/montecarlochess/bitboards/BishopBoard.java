package montecarlochess.bitboards;
public class BishopBoard extends Bitboard {
    private long init = 0x24L;

    public long initialiseBoard(boolean isBlack) {
        if (isBlack) {
            super.state = Long.reverse(init);
        } else {
            super.state = init;
        }
        return super.state;
    }
    
}
