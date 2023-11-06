public class Bitboard {
    protected long board;
    protected long empty;

    public long getBoard() {
        return this.board;
    }

    public long slideNorth(int rank, int file) {
        long mask = 1L << (rank + file*8); // 1 = rank 1*8 = file
        this.board = this.board ^ mask;
         mask = mask << 8;
        this.board |= mask;
        return this.board;
    }

    public long slideNorthEast(int rank, int file) {
        long mask = 1L << (rank + file*8); // 1 = rank 1*8 = file
        this.board = this.board ^ mask;
         mask = mask << 9;
        this.board |= mask;
        return this.board;
    }
    
    public long slideEast(int rank, int file) {
        long mask = 1L << (rank + file*8); // 1 = rank 1*8 = file
        this.board = this.board ^ mask;
         mask = mask << 1;
        this.board |= mask;
        return this.board;
    }
    
    public long slideSouthEast(int rank, int file) {
        long mask = 1L << (rank + file*8); // 1 = rank 1*8 = file
        this.board = this.board ^ mask;
            mask = mask >> 9;
        this.board |= mask;
        return this.board;
    }

    public long slideSouth(int rank, int file) {
        long mask = 1L << (rank + file*8); // 1 = rank 1*8 = file
        this.board = this.board ^ mask;
        mask = mask >> 8;
        this.board |= mask;
        return this.board;
    }

    public long slideSouthWest(int rank, int file) {
        long mask = 1L << (rank + file*8); // 1 = rank 1*8 = file
        this.board = this.board ^ mask;
        mask = mask >> 7;
        this.board |= mask;
        return this.board;
    }
    
    public long slideWest(int rank, int file) {
        long mask = 1L << (rank + file*8); // 1 = rank 1*8 = file
        this.board = this.board ^ mask;
         mask = mask >> 1;
        this.board |= mask;
        return this.board;
    }

    public long slideNorthWest(int rank, int file) {
        long mask = 1L << (rank + file*8); // 1 = rank 1*8 = file
        this.board = this.board ^ mask;
         mask = mask << 7;
        this.board |= mask;
        return this.board;
    }



    @Override
    public String toString() {
        StringBuilder boardBuilder = new StringBuilder();

        for (int i = 63; i >= 0; i--) {
            long bit = (this.board >> i) & 1L;
            boardBuilder.append(bit).append(" ");

            if (i % 8 == 0) {
                int row = i / 8;
                boardBuilder.append("|" + (row + 1));
                boardBuilder.append("\n");
            }
        }
        boardBuilder.append("---------------\n");
        boardBuilder.append("a b c d e f g h");
        return boardBuilder.toString();
    }
}