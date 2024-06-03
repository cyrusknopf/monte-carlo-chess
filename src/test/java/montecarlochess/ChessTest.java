package montecarlochess;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChessTest {
    @Test
    public void e4ToState() {
        long actualE4 = 0x0000000008000000;
        assertEquals(actualE4, Chess.coordinateToState("e4"));
    }
}
