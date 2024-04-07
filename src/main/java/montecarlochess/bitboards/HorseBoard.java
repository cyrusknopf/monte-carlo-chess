package montecarlochess.bitboards;

import montecarlochess.Chess;

public class HorseBoard extends Bitboard {
    private long init = 0x42L;
    private Chess game;
    private boolean colour;

    public HorseBoard(Chess game, boolean colour) {
        this.empty = 0x0L;
        this.game = game;
        this.colour = colour;
    }

    public HorseBoard(HorseBoard other) {
        this.game = other.game;
        this.colour = other.colour;
        this.state = other.state;
    }

    public long initialiseBoard() {
        if (!this.colour) {
            super.state = Long.reverse(init);
        } else {
            super.state = init;
        }
        return super.state;
    }

    public long[] makeMoves(long state) {
        long[] moves = new long[8];

        int file = getFile(state);

        if (file < 8) {
            moves[0] = state;
            moves[0] <<= 8; // Move north
            moves[0] <<= 7; // Move north east

            moves[1] = state;
            moves[1] >>= 8; // Move south
            moves[1] >>= 9; // Move south east
        }

        if (file < 7) {
            moves[2] = state;
            moves[2] >>= 1; // Move east
            moves[2] <<= 7; // Move north east

            moves[3] = state;
            moves[3] >>= 1; // Move east
            moves[3] >>= 9; // Move south east
        }

        if (file > 1) {
            moves[4] = state;
            moves[4] >>= 8; // Move south
            moves[4] >>= 7; // Move south west

            moves[5] = state;
            moves[5] <<= 8; // Move north
            moves[5] <<= 9; // Move north west
        }

        if (file > 2) {
            moves[6] = state;
            moves[6] <<= 1; // Move west
            moves[6] >>= 7; // Move south west

            moves[7] = state;
            moves[7] <<= 1; // Move west
            moves[7] <<= 9; // Move north west
        }

        return moves;

    }

    public long[] getPseudoLegalMoves() {
        long[] moves = new long[8];
        int ptr = 0;
        for (long piece : this.getAllPieceStates()) {
            for (long move : makeMoves(piece)) {
                /*
                 * If the move is 0 it means either:
                 * 1. The piece moved off the board
                 * 2. The move was never updated after initialisating
                 * in the array (file check)
                 *
                 * In either case, don't add
                 */
                if (move == 0) {
                    continue;
                }

                // Check there are no collisions with own piece
                if ((move & game.getGameState(this.colour)) != 0) {
                    continue;
                }
                moves[ptr] = move;
                ptr++;
            }

        }

        long[] filtered = new long[ptr];
        int i = 0;

        for (long move : moves) {
            if (move != 0) {
                filtered[i] = move;
                i++;
            }
        }

        return filtered;
    }

}
