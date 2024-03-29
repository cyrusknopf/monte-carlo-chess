
package montecarlochess.bitboards;

import montecarlochess.Chess;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HorseBoardTest {

    private Chess game;
    private HorseBoard hb;

    @BeforeEach
    public void setUp() {
        game = new Chess();
        hb = new HorseBoard(game, false);
    }

    @Test
    public void getBoardsSingle() {
        hb.state = 1L;
        assertEquals(1, hb.getAllBoards().size());
        assertEquals(1L, hb.getAllBoards().get(0).state);
    }

    @Test
    public void getBoardsDouble() {
        hb.state = 0x8001L;
        assertEquals(2, hb.getAllBoards().size());

        HorseBoard b1 = hb.getAllBoards().get(0);

        HorseBoard b2 = hb.getAllBoards().get(1);

        assertEquals(0x0001, b1.state);
        assertEquals(0x8000, b2.state);
    }

    @Test
    public void legalMovesFromCentre() {
        HorseBoard horse = new HorseBoard(game, false);
        horse.state = 0x0000000008000000;

        ArrayList<Long> moves = horse.getPseudoLegalMoves();

        assertEquals(8, moves.size());

        long allMovesState = 0;

        for (long move : moves) {
            allMovesState |= move;
        }

        horse.state = allMovesState;

        long correctAllMovesState = 0x0000142200221400L;

        assertEquals(correctAllMovesState, allMovesState);
    }

    @Test
    public void pseudoLegalMovesFromCentre() {
        HorseBoard hb = new HorseBoard(game, false);
        hb.state = 0x0000000008000000;

        ArrayList<Long> moves = hb.getPseudoLegalMoves();

        assertEquals(8, moves.size());

        long allMovesState = 0;

        for (long move : moves) {
            allMovesState |= move;
        }

        long correctAllMovesState = 0x0000142200221400L;

        // The board state which contains every legal move should be:
        // 0 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 0 0
        // 0 0 0 1 0 1 0 0
        // 0 0 1 0 0 0 1 0
        // 0 0 0 0 0 0 0 0
        // 0 0 1 0 0 0 1 0
        // 0 0 0 1 0 1 0 0
        // 0 0 0 0 0 0 0 0

        assertEquals(correctAllMovesState, allMovesState);
    }

    @Test
    public void pseudoLegalMovesFile8() {
        HorseBoard horse = new HorseBoard(game, false);
        horse.state = 0x0000010000000000L;

        ArrayList<Long> moves = horse.getPseudoLegalMoves();

        assertEquals(4, moves.size());

        long allMovesState = 0;

        for (long move : moves) {
            allMovesState |= move;
        }

        HorseBoard temp = new HorseBoard(game, false);
        temp.state = allMovesState;

        // Board should look like this:
        // 0 0 0 0 0 0 1 0
        // 0 0 0 0 0 1 0 0
        // 0 0 0 0 0 0 0 0
        // 0 0 0 0 0 1 0 0
        // 0 0 0 0 0 0 1 0
        // 0 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 0 0

        long correctAllMovesState = 0x0204000402000000L;

        assertEquals(correctAllMovesState, allMovesState);

    }

    // TODO add tests for file 1, top and bottom ranks with file 1 and 8

}
