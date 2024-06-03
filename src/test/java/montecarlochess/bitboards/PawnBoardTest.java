package montecarlochess.bitboards;

import montecarlochess.Chess;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnBoardTest {

    private Chess game;
    private PawnBoard pawn;

    @BeforeEach
    public void setUp() {
        game = new Chess();
        pawn = new PawnBoard(game, true);
    }

    @Test
    public void testInitialiseBoardWhite() {
        PawnBoard whiteBoard = new PawnBoard(game, true);
        long expected = 0x000000000000FF00L;
        assertEquals(expected, whiteBoard.initialiseBoard());
    }

    @Test
    public void testInitialiseBoardBlack() {
        PawnBoard blackBoard = new PawnBoard(game, false);
        long expected = Long.reverse(0x000000000000FF00L);
        assertEquals(expected, blackBoard.initialiseBoard());
    }

    @Test
    public void pseudoLegalMovesE4() {
        pawn.state = 0x0000000008000000;

        long[] moves = pawn.getPseudoLegalMoves(pawn.colour);

        assertEquals(1, moves.length);

        long allMovesState = 0;

        for (long move : moves) {
            allMovesState |= move;
        }

        long correctAllMovesState = 0x0000000800000000L;

        // The board state which contains every legal move should be:
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 0 0 1 0 0 0 | 8 0
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 0 0 0 0 0 0 | 0 0

        assertEquals(correctAllMovesState, allMovesState);
    }

    @Test
    public void pseudoLegalMovesWhiteStart() {
        pawn.state = 0x000000000000FF00L;

        long[] moves = pawn.getPseudoLegalMoves(pawn.colour);

        assertEquals(16, moves.length);

        long allMovesState = 0;

        for (long move : moves) {
            allMovesState |= move;
        }

        long correctAllMovesState = 0x00000000FFFF0000L;

        // The board state which contains every legal move should be:
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 0 0 0 0 0 0 | 0 0
        // 1 1 1 1 1 1 1 1 | F F
        // 1 1 1 1 1 1 1 1 | F F
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 0 0 0 0 0 0 | 0 0

        assertEquals(correctAllMovesState, allMovesState);
    }

    @Test
    public void pseudoLegalDoubleCaptureD1() {
        pawn.state = 0x0000000000000010L;

        long pawns = 0x0000000000002800L;

        game.setPawns(pawn.state, true);
        game.setPawns(pawns, false);

        long[] moves = pawn.getPseudoLegalMoves(pawn.colour);

        assertEquals(3, moves.length);

        long allMovesState = 0;

        for (long move : moves) {
            allMovesState |= move;
        }

        long correctAllMovesState = 0x0000000000003800L;

        // The board state which contains every legal move should be:
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 0 0 0 0 0 0 | 0 0
        // 0 0 1 1 1 0 0 0 | 3 8
        // 0 0 0 0 0 0 0 0 | 0 0

        assertEquals(correctAllMovesState, allMovesState);

    }

    @Test
    public void pseudoLegalWhiteJumpWhite() {
        pawn.state = 0x0000000000000100L;

        long queen = 0x0000000000010000L;

        game.setPawns(pawn.state, true);
        game.setQueen(queen, true);

        long[] moves = pawn.getPseudoLegalMoves(pawn.colour);

        assertEquals(0, moves.length);
    }

    @Test
    public void pseudoLegalWhiteJumpBlack() {
        pawn.state = 0x0000000000000100L;

        long queen = 0x0000000000010000L;

        game.setPawns(pawn.state, true);
        game.setQueen(queen, false);

        long[] moves = pawn.getPseudoLegalMoves(pawn.colour);

        assertEquals(0, moves.length);
    }

    @Test
    public void pseudoLegalBlackJumpBlack() {
        pawn = new PawnBoard(game, false);
        pawn.state = 0x0001000000000000L; // Black pawn at row 7

        long queen = 0x0000010000000000L; // Black queen blocking the pawn

        game.setPawns(pawn.state, false);
        game.setQueen(queen, false);

        long[] moves = pawn.getPseudoLegalMoves(pawn.colour);

        assertEquals(0, moves.length);
    }

    @Test
    public void pseudoLegalBlackJumpWhite() {
        pawn = new PawnBoard(game, false);
        pawn.state = 0x0001000000000000L; // Black pawn at row 7

        long queen = 0x0000010000000000L; // Black queen blocking the pawn

        game.setPawns(pawn.state, false);
        game.setQueen(queen, true);

        long[] moves = pawn.getPseudoLegalMoves(pawn.colour);

        assertEquals(0, moves.length);
    }

}
