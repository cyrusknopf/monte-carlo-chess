package montecarlochess.bitboards;

import montecarlochess.Chess;

public class KingBoard extends Bitboard {
    private long init = 0x8L;
    final private Chess game;
    final protected boolean colour;

    public KingBoard(Chess game, boolean colour) {
        this.game = game;
        this.colour = colour;
    }

    public KingBoard(KingBoard other) {
        this.init = other.init;
        this.game = other.game;
        this.colour = other.colour;
    }

    @Override
    public KingBoard copy() {
        return new KingBoard(this);
    }

    public long[] getPseudoLegalMoves() {
        long[] tempMoves = new long[8];
        long[] moves = new long[8];

        // Attempt all possible moves, preemptively
        tempMoves[0] = Bitboard.slideNorth(state);
        if (Bitboard.getFile(this.state) < 8) {
            tempMoves[1] = Bitboard.slideNorthEast(state);
            tempMoves[2] = Bitboard.slideEast(state);
            tempMoves[3] = Bitboard.slideSouthEast(state);
        }
        tempMoves[4] = Bitboard.slideSouth(state);
        if (Bitboard.getFile(this.state) > 1) {
            tempMoves[5] = Bitboard.slideSouthWest(state);
            tempMoves[6] = Bitboard.slideWest(state);
            tempMoves[7] = Bitboard.slideNorthWest(state);
        }

        // For each of the preset moves above...
        int ptr = 0;
        for (long move : tempMoves) {
            // Check if the move results in the piece going off the top or bottom of board
            if (move == 0) {
                continue;
            }
            // Checks if the king has moved to a position where a piece of the same colour
            // already exists
            if ((move & game.getGameState(this.colour)) != 0) {
                continue;
            }
            moves[ptr] = move;
            ptr++;
        }
        // `moves` array has empty space at the end. We want to return an array
        // containing only the moves. `ptr` holds how many psuedo legal moves exists:
        // create an array of this size and fill it with the moves.
        long[] filtered = new long[ptr];
        for (int i = 0; i < ptr; i++) {
            filtered[i] = moves[i];
        }
        return filtered;
    }

    // TODO Can we add this to the BitBoard class instead of implementing it in
    // every
    // subclass?
    public long initialiseBoard() {
        if (!this.colour) {
            super.state = Long.reverse(init);
        } else {
            super.state = init;
        }
        return super.state;
    }
}
