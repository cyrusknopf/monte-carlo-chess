public class HorseBoard extends Bitboard{
    private long init = 0x42L;
    private Chess game;
    private boolean colour;


    public HorseBoard(Chess game, boolean colour) {
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
}
