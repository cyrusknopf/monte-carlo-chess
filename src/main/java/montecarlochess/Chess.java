package montecarlochess;

import montecarlochess.bitboards.*;

public class Chess {
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

        this.whitePawns = new PawnBoard(this, true);
        this.blackPawns = new PawnBoard(this, false);

        this.whiteHorses = new HorseBoard(this, true);
        this.blackHorses = new HorseBoard(this, false);

        this.whiteKing = new KingBoard(this, true);
        this.blackKing = new KingBoard(this, false);
        this.whiteQueen = new QueenBoard(this, true);

        this.blackQueen = new QueenBoard(this, false);
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

    // expand to all pieces
    public long getGameState() {
        long gameState = whitePawns.state ^
                blackQueen.state;
        return gameState;
    }

    public long getGameState(boolean colour) {
        if (colour) {
            return whitePawns.state ^ whiteHorses.state;
        } else {
            return blackPawns.state ^ blackHorses.state;
        }
    }

}
