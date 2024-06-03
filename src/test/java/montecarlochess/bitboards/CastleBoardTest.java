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
    public void pseudoLegalMovesA8(){
        castle.state = 0x8000000000000000L;

        long [] moves = castle.getPseudoLegalMoves();

        assertEquals(14, moves.length);

        long allMovesState = 0;

        for (long move : moves) {
            allMovesState |= move;
        }

        long correctAllMovesState = 0x7F80808080808080L;
        
        assertEquals(correctAllMovesState, allMovesState);
    }
}
