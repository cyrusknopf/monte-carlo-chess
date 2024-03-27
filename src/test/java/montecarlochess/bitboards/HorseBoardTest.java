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
    public void pseudoLegalMovesFromCentre() {
        HorseBoard hb = new HorseBoard(game, false);
        hb.state = 0x0000000008000000;

        ArrayList<HorseBoard> moves = hb.getPseudoLegalMoves();

        assertEquals(8, moves.size());

        long allMovesState = 0;

        for (HorseBoard move : moves) {
            allMovesState |= move.state;
        }

        long correctAllMovesState = 0x0000142200221400L;

        // Board should look like this:
        // 0 0 0 0 0 0 0 0 |8
        // 0 0 0 0 0 0 0 0 |7
        // 0 0 0 1 0 1 0 0 |6
        // 0 0 1 0 0 0 1 0 |5
        // 0 0 0 0 0 0 0 0 |4
        // 0 0 1 0 0 0 1 0 |3
        // 0 0 0 1 0 1 0 0 |2
        // 0 0 0 0 0 0 0 0 |1

        assertEquals(correctAllMovesState, allMovesState);
    }
}
