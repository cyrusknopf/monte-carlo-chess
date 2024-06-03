package montecarlochess.bitboards;

import montecarlochess.Chess;

import java.util.ArrayList;

public class PawnBoard extends Bitboard {
    final protected long init = 0x000000000000FF00L;
    final protected Chess game;
    final public boolean colour;

    public PawnBoard(Chess game, boolean colour) {
        this.empty = 0x0L;
        this.game = game;
        this.colour = colour;
    }

    public PawnBoard(PawnBoard other) {
        this.game = other.game;
        this.colour = other.colour;

    }

    @Override
    public PawnBoard copy() {
        return new PawnBoard(this);
    }

    public long initialiseBoard() {
        if (!this.colour) {
            super.state = Long.reverse(init);
        } else {
            super.state = init;
        }
        return super.state;
    }

    // Returns an array of boards containing just each piece of the board.
    public ArrayList<PawnBoard> getAllBoards() {
        ArrayList<PawnBoard> pawnBoards = new ArrayList<>();
        for (long state : this.getAllPieceStates()) {
            PawnBoard currentBoard = new PawnBoard(this.game, this.colour);
            currentBoard.state = state;
            pawnBoards.add(currentBoard);
        }
        return pawnBoards;
    }

    public long[] makeMoves(long state, boolean colour) {
        long moves[];
        long move;
        int ptr = 0;
        // White moves
        if (colour) {
            // Check if first move
            if (Bitboard.getRank(state) == 2) {
                moves = new long[4];
                // Check double push
                move = state << 8;
                // Check to ensure the pawn is jumping over a piece
                if ((move & game.getGameState()) == 0) {
                    move <<= 8;
                    if ((move & game.getGameState()) == 0) {
                        moves[ptr] = move;
                        ptr++;
                    }
                }
            } else {
                moves = new long[3];
            }
            // Check push
            move = state << 8;
            if ((move & game.getGameState()) == 0) {
                moves[ptr] = move;
                ptr++;
            }
            // Check captures
            move = state << 7;
            if ((move & game.getGameState(!colour)) != 0 && (move & game.getGameState(colour)) == 0) {
                moves[ptr] = move;
                ptr++;
            }
            // Check captures
            move = state << 9;
            if ((move & game.getGameState(!colour)) != 0 && (move & game.getGameState(colour)) == 0) {
                moves[ptr] = move;
                ptr++;
            }

            // Black moves
        } else {
            if (Bitboard.getRank(state) == 7) {
                moves = new long[4];
                move = state >>> 8;
                if ((move & game.getGameState()) == 0) {
                    move >>>= 8;
                    if ((move & game.getGameState()) == 0) {
                        moves[ptr] = move;
                        ptr++;
                    }
                }
            } else {
                moves = new long[3];
            }
            // Check push
            move = state >>> 8;
            if ((move & game.getGameState()) == 0) {
                moves[ptr] = move;
                ptr++;
            }
            // Check captures
            move = state >>> 7;
            if ((move & game.getGameState(!colour)) != 0 && (move & game.getGameState(colour)) == 0) {
                moves[ptr] = move;
                ptr++;
            }
            // Check captures
            move = state >>> 9;
            if ((move & game.getGameState(!colour)) != 0 && (move & game.getGameState(colour)) == 0) {
                moves[ptr] = move;
                ptr++;
            }

        }

        long[] filtered = new long[ptr];
        for (int i = 0; i < ptr; i++) {
            filtered[i] = moves[i];
        }
        return filtered;
    }

    public long[] getPseudoLegalMoves(boolean colour) {
        long[] moves = new long[32];
        int ptr = 0;
        for (long piece : this.getAllPieceStates()) {
            for (long move : makeMoves(piece, this.colour)) {
                moves[ptr] = move;
                ptr++;
            }
        }

        long[] filtered = new long[ptr];
        for (int i = 0; i < ptr; i++) {
            filtered[i] = moves[i];
        }
        return filtered;
    }
}
