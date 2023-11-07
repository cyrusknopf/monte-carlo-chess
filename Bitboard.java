import java.util.ArrayList;

public class Bitboard {
    protected long state;
    protected long empty;

    public long getBoard() {
        return this.state;
    }



    // Returns a list of bitboard longs that contain each piece of the board.
    public ArrayList<Long> getAllPieceStates() {
        long initState = this.state;
        ArrayList<Long> longs = new ArrayList<>();
        while (initState != 0) {
            long thisPiece = initState & -initState;
            longs.add(thisPiece);
            initState &= initState -1; 
        }
        return longs;
    }



    public long slideNorth(int rank, int file) {
        long mask = 1L << (rank + file*8); // 1 = rank 1*8 = file
        this.state = this.state ^ mask;
        mask = mask << 8;
        this.state |= mask;
        return this.state;
    }

    public long slideNorth() {
        this.state = this.state << 8;
        return this.state;
    }


    public long slideNorthEast(int rank, int file) {
        long mask = 1L << (rank + file*8); // 1 = rank 1*8 = file
        this.state = this.state ^ mask;
        mask = mask << 9;
        this.state |= mask;
        return this.state;
    }
    
    public long slideEast(int rank, int file) {
        long mask = 1L << (rank + file*8); // 1 = rank 1*8 = file
        this.state = this.state ^ mask;
        mask = mask << 1;
        this.state |= mask;
        return this.state;
    }
    
    public long slideSouthEast(int rank, int file) {
        long mask = 1L << (rank + file*8); // 1 = rank 1*8 = file
        this.state = this.state ^ mask;
        mask = mask >> 9;
        this.state |= mask;
        return this.state;
    }

    public long slideSouth(int rank, int file) {
        long mask = 1L << (rank + file*8); // 1 = rank 1*8 = file
        this.state = this.state ^ mask;
        mask = mask >> 8;
        this.state |= mask;
        return this.state;
    }

    public long slideSouthWest(int rank, int file) {
        long mask = 1L << (rank + file*8); // 1 = rank 1*8 = file
        this.state = this.state ^ mask;
        mask = mask >> 7;
        this.state |= mask;
        return this.state;
    }
    
    public long slideWest(int rank, int file) {
        long mask = 1L << (rank + file*8); // 1 = rank 1*8 = file
        this.state = this.state ^ mask;
        mask = mask >> 1;
        this.state |= mask;
        return this.state;
    }

    public long slideNorthWest(int rank, int file) {
        long mask = 1L << (rank + file*8); // 1 = rank 1*8 = file
        this.state = this.state ^ mask;
        mask = mask << 7;
        this.state |= mask;
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