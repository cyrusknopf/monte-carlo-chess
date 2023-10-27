public class Bitboard {
    private long board;

    public Bitboard() {
        this.board = 0x000000000000FF00L;
    }

    public long getBoard() {
        return this.board;
    }


    public String toString() {
        StringBuilder boardBuilder = new StringBuilder();

        for (int i = 63; i >= 0; i--) {
            long bit = (this.board >> i) & 1L;
            boardBuilder.append(bit).append(" ");

            if (i % 8 == 0) {
                int row = i / 8;
                boardBuilder.append("|" + row);
                boardBuilder.append("\n");
            }
        }
        boardBuilder.append("---------------\n");
        boardBuilder.append("A B C D E F G H");
        return boardBuilder.toString();
    }

}