package montecarlochess.bitboards;

import montecarlochess.Chess;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

public class PawnBoardTest {

    private Chess game;
    private PawnBoard pawn;

    @BeforeEach
    public void setUp() {
        game = new Chess();
        pawn = new PawnBoard(game, false);
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

    @Test
    public void pseudoLegalMovesStart() {
        pawn.state = 0x000000000000FF00L;

        long[] moves = pawn.getPseudoLegalMoves(pawn.state, pawn.colour);
    }
}
