package montecarlochess;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChessTest {
    @Test
    public void e4ToState() {
        long actualE4 = 0x0000000008000000;
        assertEquals(actualE4, Chess.coordinateToState("e4"));
    }

    @Test
    public void printBoardInitGame() {
        Chess c = new Chess();
        c.initGame();
        String correct = "♜ ♞ ♝ ♚ ♛ ♝ ♞ ♜ | 8\n" +
                "♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟ | 7\n" +
                "                | 6\n" +
                "                | 5\n" +
                "                | 4\n" +
                "                | 3\n" +
                "♙ ♙ ♙ ♙ ♙ ♙ ♙ ♙ | 2\n" +
                "♖ ♘ ♗ ♕ ♔ ♗ ♘ ♖ | 1\n" +
                "---------------\n" +
                "a b c d e f g h";

        assertEquals(correct, c.toString());
    }
}
