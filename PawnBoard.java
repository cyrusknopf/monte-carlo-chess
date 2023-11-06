public class PawnBoard extends Bitboard {
    private long init = 0x000000000000FF00L;
    private Chess game;

    public PawnBoard(Chess game) {
        this.empty = 0x0L;
        this.game = game;
    }

    public long initialiseBoard(boolean isBlack) {
        if (isBlack) {
            super.state = Long.reverse(init);
        } else {
            super.state = init;
        }
        return super.state;
    }
    
    public long checkMoves() {
        return 0;
    }
}
