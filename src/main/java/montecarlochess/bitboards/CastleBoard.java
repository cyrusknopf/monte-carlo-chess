package montecarlochess.bitboards;

import montecarlochess.Chess;

public class CastleBoard extends Bitboard{
    private long init = 0x81L;
    private Chess game;
    private boolean colour;

    public CastleBoard(Chess game, boolean colour) {
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

    public long[] getPseudoLegalMoves(){
        int file = getFile(state);
        long[] moves = new long[28];
        int ptr = 0;

        // Slide north
        long slideNorth = state;
        while (slideNorth << 8 != 0) {
            slideNorth <<= 8;
            // If the move is onto a piece of the same colour, stop
            if ((slideNorth & game.getGameState(this.colour)) != 0) {
                break;
            }
            moves[ptr] = slideNorth;
            ptr++;
            // If move onto enemy piece, stop, cannot go further
            if ((slideNorth & game.getGameState(!this.colour)) != 0) {
                break;
            }
        }

        // Slide south
        long slideSouth = state;
        while (slideSouth >>> 8 != 0) {
            slideSouth >>>= 8;
            // If the move is onto a piece of the same colour, stop
            if ((slideSouth & game.getGameState(this.colour)) != 0) {
                break;
            }
            moves[ptr] = slideSouth;
            ptr++;
            // If move onto enemy piece, stop, cannot go further
            if ((slideSouth & game.getGameState(!this.colour)) != 0) {
                break;
            }
        }

        // Slide east
        long slideEast = state;
        int slideEastFile = file;
        while (slideEastFile < 8) {
            slideEast >>>= 1;
            slideEastFile = Bitboard.getFile(slideEast);

            // If the move is onto a piece of the same colour, stop
            if ((slideEast & game.getGameState(this.colour)) != 0) {
                break;
            }

            moves[ptr] = slideEast;
            ptr++;

            // If move onto enemy piece, stop, cannot go further
            if ((slideEast & game.getGameState(!this.colour)) != 0) {
                break;
            }
        }

        // Slide west
        long slideWest = state;
        int slideWestFile = file;
        while (slideWestFile > 1) {
            slideWest <<= 1;
            slideWestFile--;

            // If the move is onto a piece of the same colour, stop
            if ((slideWest & game.getGameState(this.colour)) != 0) {
                break;
            }

            moves[ptr] = slideWest;
            ptr++;

            // If move onto enemy piece, stop, cannot go further
            if ((slideWest & game.getGameState(!this.colour)) != 0) {
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
