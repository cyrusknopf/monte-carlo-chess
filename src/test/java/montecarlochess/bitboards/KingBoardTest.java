package montecarlochess.bitboards;

import montecarlochess.Chess;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KingBoardTest {
    private Chess game;
    private KingBoard king;

    @BeforeEach
    public void setUp() {
        game = new Chess();
        king = new KingBoard(game, true);
    }

    @Test
    public void pseudoLegalMovesE4() {
        king.state = 0x0000000008000000;

        long[] moves = king.getPseudoLegalMoves();

        assertEquals(8, moves.length);

        long allMovesState = 0;

        for (long move : moves) {
            allMovesState |= move;
        }

        long correctAllMovesState = 0x0000001C141C0000L;

        // The board state which contains every legal move should be:
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 0 1 1 1 0 0 | 1 C
        // 0 0 0 1 0 1 0 0 | 1 4
        // 0 0 0 1 1 1 0 0 | 1 C
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 0 0 0 0 0 0 | 0 0

        assertEquals(correctAllMovesState, allMovesState);
    }
}
