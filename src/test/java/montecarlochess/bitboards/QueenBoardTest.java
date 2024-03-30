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
    public void pseudoLegalMovesFromCentre() {
        queen.state = 0x0000000008000000L;

        long[] moves = queen.makeMoves();

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
    public void pseudoLegalMovesFromCorner() {
        queen.state = 0x8000000000000000L;

        long[] moves = queen.makeMoves();

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
}
