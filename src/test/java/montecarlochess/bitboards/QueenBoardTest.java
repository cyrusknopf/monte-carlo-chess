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

        long allMoves = 0;
        for (long move : moves) {
            allMoves |= move;
        }

        assertEquals(28, moves.length);

        Bitboard temp = new Bitboard();
        temp.state = allMoves;
        System.out.println(temp);

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

}
