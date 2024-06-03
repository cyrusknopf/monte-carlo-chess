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

    private CastleBoard whiteCastle;
    private CastleBoard blackCastle;

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

        this.whiteCastle = new CastleBoard(this, true);
        this.blackCastle = new CastleBoard(this, false);
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

    public void setKing(long state, boolean colour) {
        if (colour) {
            whiteKing.state = state;
        } else {
            blackKing.state = state;
        }
    }

    public void setCastle(long state, boolean colour) {
        if (colour) {
            whiteCastle.state = state;
        } else {
            blackCastle.state = state;
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

}
