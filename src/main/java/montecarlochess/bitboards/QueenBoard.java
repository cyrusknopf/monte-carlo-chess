package montecarlochess.bitboards;

import montecarlochess.Chess;

public class QueenBoard extends Bitboard {
    private long init = 0x10L;
    private Chess game;
    private boolean colour;

    public QueenBoard(Chess game, boolean colour) {
        this.empty = 0x0L;
        this.game = game;
        this.colour = colour;
    }

    public long initialiseBoard(boolean isBlack) {
        if (isBlack) {
            super.state = Long.reverse(init);
        } else {
            super.state = init;
        }
        return super.state;
    }

    public long[] makeMoves() {
        int file = getFile(state);
        long[] moves = new long[28];
        int ptr = 0;

        // Slide north
        long slide_north = state;
        while (slide_north << 8 != 0) {
            slide_north <<= 8;
            // If the move is onto a piece of the same colour, stop
            if ((slide_north & game.getGameState(this.colour)) != 0) {
                break;
            }
            moves[ptr] = slide_north;
            ptr++;
            // If move onto enemy piece, stop, cannot go further
            if ((slide_north & game.getGameState(!this.colour)) != 0) {
                break;
            }
        }

        // Slide south
        long slide_south = state;
        while (slide_south >> 8 != 0) {
            slide_south >>= 8;
            // If the move is onto a piece of the same colour, stop
            if ((slide_south & game.getGameState(this.colour)) != 0) {
                break;
            }
            moves[ptr] = slide_south;
            ptr++;
            // If move onto enemy piece, stop, cannot go further
            if ((slide_south & game.getGameState(!this.colour)) != 0) {
                break;
            }
        }

        // Slide east
        long slide_east = state;
        long slide_east_file = file;
        while (slide_east_file < 8) {
            slide_east >>= 1;
            slide_east_file++;

            // If the move is onto a piece of the same colour, stop
            if ((slide_east & game.getGameState(this.colour)) != 0) {
                break;
            }

            moves[ptr] = slide_east;
            ptr++;

            // If move onto enemy piece, stop, cannot go further
            if ((slide_east & game.getGameState(!this.colour)) != 0) {
                break;
            }

        }

        // Slide west
        long slide_west = state;
        long slide_west_file = file;
        while (slide_west_file > 1) {
            slide_west <<= 1;
            slide_west_file--;

            // If the move is onto a piece of the same colour, stop
            if ((slide_west & game.getGameState(this.colour)) != 0) {
                break;
            }

            moves[ptr] = slide_west;
            ptr++;

            // If move onto enemy piece, stop, cannot go further
            if ((slide_west & game.getGameState(!this.colour)) != 0) {
                break;
            }

        }

        long[] res = new long[ptr];
        for (int i = 0; i < ptr; i++) {
            res[i] = moves[i];
        }

        return res;
    }

}
