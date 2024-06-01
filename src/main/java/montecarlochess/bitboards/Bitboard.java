package montecarlochess.bitboards;

public class Bitboard {
    public long state;
    public long empty;

    public Bitboard() {
    }

    // Copy contructor
    public Bitboard(Bitboard other) {
        this.state = other.state;
        this.empty = other.empty;
    }

    public Bitboard copy() {
        return new Bitboard(this);
    }

    public long getBoard() {
        return this.state;
    }

    // Prototype, not sure works, just spitballing. Captor would remain the same.
    // The idea is that the captor has a piece which is on the same square as the
    // victim board. The captor board remains the same, the victim simply removes
    // the piece on the square where there is a piece on both states.
    public long capture(long captor, long victim) {
        return victim ^ captor;
    }

    /*
     * Used to get boards of each isolated piece
     *
     * @return ArrayList of board states each with a single piece
     */
    public long[] getAllPieceStates() {
        long initState = this.state;
        long[] pieces = new long[8];
        int ptr = 0;
        while (initState != 0) {
            long thisPiece = initState & -initState;
            pieces[ptr] = thisPiece;
            ptr++;
            initState &= initState - 1;
        }

        int i = 0;
        long[] filtered = new long[ptr];
        for (long piece : pieces) {
            if (piece != 0) {
                filtered[i] = piece;
                i++;
            }
        }

        return filtered;
    }

    /*
     * Finds the file of a piece from a bitboard. Should only be called
     * on a bitboard containing a single piece.
     * 
     * @return the file of the piece, from 1 to 8
     */
    public static int getFile(long state) {
        /*
         * Since file is independent of rank, we
         * shift the piece into rank 1 for simplicity
         */
        long tempState = state;
        while (tempState > 128) {
            tempState = tempState >> 8;
        }

        /*
         * Find the log2 of the state. Since the state is guaranteed
         * to be a binary number with a single 1, this is correct.
         */
        int exp = 0;
        while ((tempState & 0b1) == 0) {
            tempState = tempState >> 1;
            exp++;
        }

        int file = 8 - exp;
        return file;
    }

    public static int getRank(long state) {
        int rank = 0;
        long tempState = state;
        while (tempState != 0) {
            tempState >>>= 8;
            rank++;
        }
        return rank;
    }

    public static long slideNorth(long state) {
        state = state << 8;
        return state;
    }

    public static long slideNorthEast(long state) {
        state = state << 7;
        return state;
    }

    public static long slideEast(long state) {
        state = state >> 1;
        return state;
    }

    public static long slideSouthEast(long state) {
        state = state >> 9;
        return state;
    }

    public static long slideSouth(long state) {
        state = state >> 8;
        return state;
    }

    public static long slideSouthWest(long state) {
        state = state >> 7;
        return state;
    }

    public static long slideWest(long state) {
        state = state << 1;
        return state;
    }

    public static long slideNorthWest(long state) {
        state = state << 9;
        return state;
    }

    @Override
    public String toString() {
        StringBuilder stateBuilder = new StringBuilder();

        for (int i = 63; i >= 0; i--) {
            long bit = (this.state >> i) & 1L;
            stateBuilder.append(bit).append(" ");

            if (i % 8 == 0) {
                int row = i / 8;
                stateBuilder.append("|" + (row + 1));
                stateBuilder.append("\n");
            }
        }
        stateBuilder.append("---------------\n");
        stateBuilder.append("a b c d e f g h");
        return stateBuilder.toString();
    }
}
