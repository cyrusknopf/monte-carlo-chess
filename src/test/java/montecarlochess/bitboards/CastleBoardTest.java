package montecarlochess.bitboards;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import montecarlochess.Chess;

public class CastleBoardTest {
    private Chess game;
    private CastleBoard castle;

    @BeforeEach
    public void setUp() {
        game = new Chess();
        castle = new CastleBoard(game, false);
    }

    @Test
    public void pseudoLegalMovesE4() {
        castle.state = 0x0000000008000000;

        long[] moves = castle.getPseudoLegalMoves();

        assertEquals(14, moves.length);

        long allMovesState = 0;

        for (long move : moves) {
            allMovesState |= move;
        }

        long correctAllMovesState = 0x08080808F7080808L;

        // The board state which contains every legal move should be:
        // 0 0 0 0 1 0 0 0
        // 0 0 0 0 1 0 0 0
        // 0 0 0 0 1 0 0 0
        // 0 0 0 0 1 0 0 0
        // 1 1 1 1 0 1 1 1
        // 0 0 0 0 1 0 0 0
        // 0 0 0 0 1 0 0 0
        // 0 0 0 0 1 0 0 0

        assertEquals(correctAllMovesState, allMovesState);
    }

    @Test
    public void pseudoLegalMovesA8() {
        castle.state = 0x8000000000000000L;

        long[] moves = castle.getPseudoLegalMoves();

        assertEquals(14, moves.length);

        long allMovesState = 0;

        for (long move : moves) {
            allMovesState |= move;
        }

        long correctAllMovesState = 0x7F80808080808080L;

        // The board state which contains every legal move should be:
        // 0 1 1 1 1 1 1 1
        // 1 0 0 0 0 0 0 0
        // 1 0 0 0 0 0 0 0
        // 1 0 0 0 0 0 0 0
        // 1 0 0 0 0 0 0 0
        // 1 0 0 0 0 0 0 0
        // 1 0 0 0 0 0 0 0
        // 1 0 0 0 0 0 0 0

        assertEquals(correctAllMovesState, allMovesState);
    }

    @Test
    public void pseudoLegalMovesH7Blocked() {
        castle.state = 0x0001000000000000L;

        long pawns = 0x0102010000000000L;

        game.setPawns(pawns, false);
        game.setHorses(castle.state, false);

        long[] moves = castle.getPseudoLegalMoves();

        assertEquals(0, moves.length);

        long allMovesState = 0;

        for (long move : moves) {
            allMovesState |= move;
        }

        long correctAllMovesState = 0x0L;

        // The board state which contains every legal move should be:
        // 0 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 0 0

        assertEquals(correctAllMovesState, allMovesState);
    }

    @Test
    public void pseudoLegalMovesH8PartiallyBlocked() {
        castle.state = 0x0100000000000000L;

        long pawns = 0x0200000000000000L;

        game.setPawns(pawns, false);
        game.setHorses(castle.state, false);

        long[] moves = castle.getPseudoLegalMoves();

        assertEquals(7, moves.length);

        long allMovesState = 0;

        for (long move : moves) {
            allMovesState |= move;
        }

        long correctAllMovesState = 0x0001010101010101L;

        // The board state which contains every legal move should be:
        // 0 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 0 1
        // 0 0 0 0 0 0 0 1
        // 0 0 0 0 0 0 0 1
        // 0 0 0 0 0 0 0 1
        // 0 0 0 0 0 0 0 1
        // 0 0 0 0 0 0 0 1
        // 0 0 0 0 0 0 0 1

        assertEquals(correctAllMovesState, allMovesState);
    }
}
