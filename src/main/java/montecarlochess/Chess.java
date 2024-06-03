package montecarlochess;

import montecarlochess.bitboards.*;

public class Chess {
    final boolean WHITE = true;
    final boolean BLACK = false;

    private Bitboard gameBoard;
    private Bitboard whiteBoard;
    private Bitboard blackBoard;

    private PawnBoard whitePawns;
    private PawnBoard blackPawns;

    private HorseBoard whiteHorses;
    private HorseBoard blackHorses;

    private KingBoard whiteKing;
    private KingBoard blackKing;

    private QueenBoard whiteQueen;
    private QueenBoard blackQueen;

    public Chess() {
        this.gameBoard = new Bitboard();
        this.whiteBoard = new Bitboard();
        this.blackBoard = new Bitboard();

        this.whitePawns = new PawnBoard(this, WHITE);
        this.blackPawns = new PawnBoard(this, BLACK);

        this.whiteHorses = new HorseBoard(this, WHITE);
        this.blackHorses = new HorseBoard(this, BLACK);

        this.whiteKing = new KingBoard(this, WHITE);
        this.blackKing = new KingBoard(this, BLACK);

        this.whiteQueen = new QueenBoard(this, WHITE);
        this.blackQueen = new QueenBoard(this, BLACK);
    }

    private void setGameState() {
        long whiteState = getGameState(true);
        long blackState = getGameState(false);
        gameBoard.state = whiteState ^ blackState;

    }

    private void setWhiteGameState() {
        whiteBoard.state = whitePawns.state ^
                whiteHorses.state ^
                whiteKing.state ^
                whiteQueen.state;
    }

    private void setBlackGameState() {
        blackBoard.state = blackPawns.state ^
                blackHorses.state ^
                blackKing.state ^
                blackQueen.state;
    }

    public void setPawns(long state, boolean colour) {
        if (colour) {
            whitePawns.state = state;
        } else {
            blackPawns.state = state;
        }
    }

    public void setQueen(long state, boolean colour) {
        if (colour) {
            whiteQueen.state = state;
        } else {
            blackQueen.state = state;
        }
    }

    public void setHorses(long state, boolean colour) {
        if (colour) {
            whiteHorses.state = state;
        } else {
            blackHorses.state = state;
        }
    }

    // TODO expand to all pieces
    public long getGameState() {
        long gameState = whitePawns.state ^
                blackPawns.state ^
                blackQueen.state ^
                whiteQueen.state ^
                whiteHorses.state ^
                blackHorses.state;
        return gameState;
    }

    // TODO expand to all pieces
    public long getGameState(boolean colour) {
        if (colour) {
            return whitePawns.state ^ whiteHorses.state ^ whiteQueen.state ^ whiteHorses.state;
        } else {
            return blackPawns.state ^ blackHorses.state ^ blackQueen.state ^ blackHorses.state;
        }
    }

    public static long coordinateToState(String coord) {
        int file = coord.charAt(0) - 'a';
        int rank = coord.charAt(1) - '1';

        long square = 1L;

        for (int i = 0; i < file; i++) {
            Bitboard.slideNorth(square);
        }
        for (int j = 0; j < rank; j++) {
            Bitboard.slideEast(square);
        }
        return square;
    }
}
