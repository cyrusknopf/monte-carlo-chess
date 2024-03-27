package montecarlochess.bitboards;

import java.util.ArrayList;

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

    /*
     * Used to get boards of each isolated piece
     *
     * @return ArrayList of board states each with a single piece
     */
    public ArrayList<Long> getAllPieceStates() {
        long initState = this.state;
        ArrayList<Long> longs = new ArrayList<>();
        while (initState != 0) {
            long thisPiece = initState & -initState;
            longs.add(thisPiece);
            initState &= initState - 1;
        }
        return longs;
    }

    /*
     * Finds the file of a piece from a bitboard. Should only be called
     * on a bitboard containing a single piece.
     * 
     * @return the file of the piece, from 1 to 8
     */
    public int getFile() {
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

    public long slideNorth() {
        this.state = this.state << 8;
        return this.state;
    }

    public long slideNorthEast() {
        this.state = this.state << 7;
        return this.state;
    }

    public long slideEast() {
        this.state = this.state >> 1;
        return this.state;
    }

    public long slideSouthEast() {
        this.state = this.state >> 9;
        return this.state;
    }

    public long slideSouth() {
        this.state = this.state >> 8;
        return this.state;
    }

    public long slideSouthWest() {
        this.state = state >> 7;
        return this.state;
    }

    public long slideWest() {
        this.state = this.state << 1;
        return this.state;
    }

    public long slideNorthWest() {
        this.state = this.state << 9;
        return this.state;
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
