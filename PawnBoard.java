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

    public long primitiveMove(int rank, int file) {
        
        long mask = 1L << (rank + file*8); // 1 = rank 1*8 = file
        this.board = this.board ^ mask;
        // System.out.println("\n XOR'd pawnboard:\n");
        // System.out.println(p);
        mask = mask << 8;
        this.board |= mask;
        return this.board;
    }



    public long checkMoves() {
        return 0;
    }
}
