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
    public void makeMovesTest() {
        queen.state = 0x0000000008000000;

        long[] moves = queen.makeMoves();

        assertEquals(14, moves.length);

    }

}
