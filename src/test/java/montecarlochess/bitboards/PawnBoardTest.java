package montecarlochess.bitboards;

import montecarlochess.Chess;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;


public class PawnBoardTest {

    private Chess game;

    @BeforeEach
    public void setUp() {
        game = new Chess(); // Assuming Chess class has a default constructor
    }

    @Test
    public void testInitialiseBoardWhite() {
        PawnBoard whiteBoard = new PawnBoard(game, true);
        long expected = 0x000000000000FF00L;
        Assertions.assertEquals(expected, whiteBoard.initialiseBoard());
    }

    @Test
    public void testInitialiseBoardBlack() {
        PawnBoard blackBoard = new PawnBoard(game, false);
        long expected = Long.reverse(0x000000000000FF00L);
        Assertions.assertEquals(expected, blackBoard.initialiseBoard());
    }
}
