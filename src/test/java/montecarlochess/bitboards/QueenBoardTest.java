package montecarlochess.bitboards;

import montecarlochess.Chess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueenBoardTest {

    Chess game;
    QueenBoard queen;

    @BeforeEach
    public void setUp() {
        game = new Chess();
        queen = new QueenBoard(game, false);
    }

    @Test
    public void pseudoLegalMovesE4() {
        queen.state = 0x0000000008000000L;

        long[] moves = queen.getPseudoLegalMoves();

        assertEquals(27, moves.length);

        long allMoves = 0;
        for (long move : moves) {
            allMoves |= move;
        }

        // The board state which contains every legal move should be:
        // 1 0 0 0 1 0 0 0 | 8 8
        // 0 1 0 0 1 0 0 1 | 4 9
        // 0 0 1 0 1 0 1 0 | 2 A
        // 0 0 0 1 1 1 0 0 | 1 C
        // 1 1 1 1 0 1 1 1 | F 7
        // 0 0 0 1 1 1 0 0 | 1 C
        // 0 0 1 0 1 0 1 0 | 2 A
        // 0 1 0 0 1 0 0 1 | 4 9

        long allCorrectMoves = 0x88492A1CF71C2A49L;

        assertEquals(allCorrectMoves, allMoves);

    }

    @Test
    public void pseudoLegalMovesA8() {
        queen.state = 0x8000000000000000L;

        long[] moves = queen.getPseudoLegalMoves();

        assertEquals(21, moves.length);

        long allMoves = 0;
        for (long move : moves) {
            allMoves |= move;
        }

        // The board state which contains every legal move should be:
        // 0 1 1 1 1 1 1 1 | 7 F
        // 1 1 0 0 0 0 0 0 | C 0
        // 1 0 1 0 0 0 0 0 | A 0
        // 1 0 0 1 0 0 0 0 | 9 0
        // 1 0 0 0 1 0 0 0 | 8 8
        // 1 0 0 0 0 1 0 0 | 8 4
        // 1 0 0 0 0 0 1 0 | 8 2
        // 1 0 0 0 0 0 0 1 | 8 1

        long allCorrectMoves = 0x7FC0A09088848281l;
        assertEquals(allCorrectMoves, allMoves);
    }

    @Test
    public void pseudoLegalMovesB7() {
        queen.state = 0x0040000000000000L;

        long[] moves = queen.getPseudoLegalMoves();

        assertEquals(23, moves.length);

        long allMoves = 0;
        for (long move : moves) {
            allMoves |= move;
        }

        // The board state which contains every legal move should be:
        // 1 1 1 0 0 0 0 0 | E 0
        // 1 0 1 1 1 1 1 1 | B F
        // 1 1 1 0 0 0 0 0 | E 0
        // 0 1 0 1 0 0 0 0 | 5 0
        // 0 1 0 0 1 0 0 0 | 4 8
        // 0 1 0 0 0 1 0 0 | 4 4
        // 0 1 0 0 0 0 1 0 | 4 2
        // 0 1 0 0 0 0 0 1 | 4 1

        long allCorrectMoves = 0xE0BFE05048444241L;

        assertEquals(allCorrectMoves, allMoves);
    }

    @Test
    public void pseudoLegalMovesA3() {
        queen.state = 0x0000000000800000L;

        long[] moves = queen.getPseudoLegalMoves();

        assertEquals(21, moves.length);

        long allMoves = 0;
        for (long move : moves) {
            allMoves |= move;
        }

        // The board state which contains every legal move should be:
        // 1 0 0 0 0 1 0 0 | 8 4
        // 1 0 0 0 1 0 0 0 | 8 8
        // 1 0 0 1 0 0 0 0 | 9 0
        // 1 0 1 0 0 0 0 0 | A 0
        // 1 1 0 0 0 0 0 0 | C 0
        // 0 1 1 1 1 1 1 1 | 7 F
        // 1 1 0 0 0 0 0 0 | C 0
        // 1 0 1 0 0 0 0 0 | A 0

        long allCorrectMoves = 0x848890A0C07FC0A0L;
        assertEquals(allCorrectMoves, allMoves);
    }

    @Test
    public void pseudoLegalMovesB8() {
        queen.state = 0x4000000000000000L;

        long[] moves = queen.getPseudoLegalMoves();

        assertEquals(21, moves.length);

        long allMoves = 0;
        for (long move : moves) {
            allMoves |= move;
        }

        // The board state which contains every legal move should be:
        // 1 0 1 1 1 1 1 1 | B F
        // 1 1 1 0 0 0 0 0 | E 0
        // 0 1 0 1 0 0 0 0 | 5 0
        // 0 1 0 0 1 0 0 0 | 4 8
        // 0 1 0 0 0 1 0 0 | 4 4
        // 0 1 0 0 0 0 1 0 | 4 2
        // 0 1 0 0 0 0 0 1 | 4 1
        // 0 1 0 0 0 0 0 0 | 4 0

        long allCorrectMoves = 0xBFE0504844424140L;

        assertEquals(allCorrectMoves, allMoves);
    }

    @Test
    public void pseudoLegalMovesE4Surrounded() {
        queen.state = 0x0000000008000000L;

        // Four black pawns surrounding the queen
        long pawns = 0x0000000814080000L;

        game.setPawns(pawns, true);
        game.setQueen(queen.state, false);

        long expState = 0x000000081C080000L;

        long gameState = game.getGameState();

        assertEquals(expState, gameState);

        long[] moves = queen.getPseudoLegalMoves();

        assertEquals(17, moves.length);

        long allMoves = 0;
        for (long move : moves) {
            allMoves |= move;
        }

        // The board state which contains every legal move should be:
        // 1 0 0 0 0 0 0 0 | 8 0
        // 0 1 0 0 0 0 0 1 | 4 1
        // 0 0 1 0 0 0 1 0 | 2 2
        // 0 0 0 1 1 1 0 0 | 1 C
        // 0 0 0 1 0 1 0 0 | 1 4
        // 0 0 0 1 1 1 0 0 | 1 C
        // 0 0 1 0 0 0 1 0 | 2 2
        // 0 1 0 0 0 0 0 1 | 4 1

        long allCorrectMoves = 0x8041221C141C2241L;

        assertEquals(allCorrectMoves, allMoves);
    }

    @Test
    public void pseudoLegalMovesE4Stuck() {
        queen.state = 0x0000000008000000L;

        // Four black pawns surrounding the queen
        long pawns = 0x0000001C141C0000L;

        game.setPawns(pawns, false);
        game.setQueen(queen.state, false);

        long[] moves = queen.getPseudoLegalMoves();

        assertEquals(0, moves.length);

        long allMoves = 0;
        for (long move : moves) {
            allMoves |= move;
        }

        // The board state which contains every legal move should be:
        // 0 0 0 0 0 0 0 0 |
        // 0 0 0 0 0 0 0 0 |
        // 0 0 0 0 0 0 0 0 |
        // 0 0 0 0 0 0 0 0 |
        // 0 0 0 0 0 0 0 0 |
        // 0 0 0 0 0 0 0 0 |
        // 0 0 0 0 0 0 0 0 |
        // 0 0 0 0 0 0 0 0 |

        long allCorrectMoves = 0x0L;

        assertEquals(allCorrectMoves, allMoves);
    }
}
