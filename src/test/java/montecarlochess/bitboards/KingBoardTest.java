package montecarlochess.bitboards;

import montecarlochess.Chess;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KingBoardTest {
    private Chess game;
    private KingBoard king;

    @BeforeEach
    public void setUp() {
        game = new Chess();
        king = new KingBoard(game, true);
    }

}
