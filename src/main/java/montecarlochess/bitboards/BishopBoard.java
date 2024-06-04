package montecarlochess.bitboards;

import montecarlochess.Chess;

public class BishopBoard extends Bitboard {
    final public long init = 0x24L;
    private Chess game;
    private boolean colour;

    public BishopBoard(Chess game, boolean colour) {
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

    public long[] getPseudoLegalMoves() {
        int file = getFile(state);
        long[] moves = new long[28];
        int ptr = 0;

        // Slide north west
        long slideNorthWest = state;
        int slideNorthWestFile = file;
        while (slideNorthWestFile > 1 && slideNorthWest << 9 != 0) {
            slideNorthWest <<= 9; // Move north west
            slideNorthWestFile--; // decrement file

            // If move onto piece of same colour, stop
            if ((slideNorthWest & game.getGameState(this.colour)) != 0) {
                break;
            }

            moves[ptr] = slideNorthWest;
            ptr++;

            // If move onto enemy piece, stop, cannot go further
            if ((slideNorthWest & game.getGameState(!this.colour)) != 0) {
                break;
            }
        }

        // Slide north east
        long slideNorthEast = state;
        int slideNorthEastFile = file;
        while (slideNorthEastFile < 8 && slideNorthEast << 7 != 0) {
            slideNorthEast <<= 7;
            slideNorthEastFile++;

            // If move onto piece of same colour, stop
            if ((slideNorthEast & game.getGameState(this.colour)) != 0) {
                break;
            }

            moves[ptr] = slideNorthEast;
            ptr++;

            // If move onto enemy piece, stop, cannot go further
            if ((slideNorthEast & game.getGameState(!this.colour)) != 0) {
                break;
            }
        }

        // Slide south west
        long slideSouthWest = state;
        int slideSouthWestFile = file;
        while (slideSouthWestFile > 1 && slideSouthWest >>> 7 != 0) {
            slideSouthWest >>>= 7; // Move north west
            slideSouthWestFile--; // decrement file

            // If move onto piece of same colour, stop
            if ((slideSouthWest & game.getGameState(this.colour)) != 0) {
                break;
            }

            moves[ptr] = slideSouthWest;
            ptr++;

            // If move onto enemy piece, stop, cannot go further
            if ((slideSouthWest & game.getGameState(!this.colour)) != 0) {
                break;
            }
        }

        // Slide south east
        long slideSouthEast = state;
        int slideSouthEastFile = file;
        while (slideSouthEastFile < 8 && slideSouthEast >>> 9 != 0) {
            slideSouthEast >>>= 9;
            slideSouthEastFile++;

            // If move onto piece of same colour, stop
            if ((slideSouthEast & game.getGameState(this.colour)) != 0) {
                break;
            }

            moves[ptr] = slideSouthEast;
            ptr++;

            // If move onto enemy piece, stop, cannot go further
            if ((slideSouthEast & game.getGameState(!this.colour)) != 0) {
                break;
            }
        }
        long[] filtered = new long[ptr];
        for (int i = 0; i < ptr; i++) {
            filtered[i] = moves[i];
        }

        return filtered;
    }
}
