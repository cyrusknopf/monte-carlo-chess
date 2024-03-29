package montecarlochess.bitboards;

import montecarlochess.Chess;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HorseBoardTest {

    private Chess game;
    private HorseBoard horse;

    @BeforeEach
    public void setUp() {
        game = new Chess();
        horse = new HorseBoard(game, false);
    }

    @Test
    public void legalMovesFromCentre() {
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
        horse.state = 0x0000000008000000;

        ArrayList<Long> moves = horse.getPseudoLegalMoves();

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
