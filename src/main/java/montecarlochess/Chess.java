package montecarlochess;

import montecarlochess.bitboards.*;

public class Chess {
    final boolean WHITE = true;
    final boolean BLACK = false;

    final String WPAWN = "♙";
    final String WCASTLE = "♖";
    final String WHORSE = "♘";
    final String WBISHOP = "♗";
    final String WQUEEN = "♕";
    final String WKING = "♔";

    final String BPAWN = "♟";
    final String BCASTLE = "♜";
    final String BHORSE = "♞";
    final String BBISHOP = "♝";
    final String BQUEEN = "♛";
    final String BKING = "♚";

    private Bitboard gameBoard;
    private Bitboard whiteBoard;
    private Bitboard blackBoard;

    protected PawnBoard whitePawns;
    private PawnBoard blackPawns;

    private HorseBoard whiteHorses;
    private HorseBoard blackHorses;

    private KingBoard whiteKing;
    private KingBoard blackKing;

    private QueenBoard whiteQueen;
    private QueenBoard blackQueen;

    private CastleBoard whiteCastles;
    private CastleBoard blackCastles;

    private BishopBoard whiteBishops;
    private BishopBoard blackBishops;

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

        this.whiteBishops = new BishopBoard(this, WHITE);
        this.blackBishops = new BishopBoard(this, BLACK);
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

    public void initGame() {
        initPawns();
        initQueens();
        initHorses();
        initKings();
        initBishops();
    }

    public void initPawns() {
        whitePawns.state = whitePawns.init;
        blackPawns.state = Long.reverse(whitePawns.init);
    }

    public void initQueens() {
        whiteQueen.state = whiteQueen.init;
        blackQueen.state = Long.reverse(whiteQueen.init);
    }

    public void initHorses() {
        whiteHorses.state = whiteHorses.init;
        blackHorses.state = Long.reverse(whiteHorses.init);
    }

    public void initKings() {
        whiteKing.state = whiteKing.init;
        blackKing.state = Long.reverse(whiteKing.init);
    }

    public void initBishops() {
        whiteBishops.state = whiteBishops.init;
        blackBishops.state = Long.reverse(whiteBishops.init);
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
            whiteCastles.state = state;
        } else {
            blackCastles.state = state;
        }
    }

    // TODO expand to all pieces
    public long getGameState() {
        long gameState = whitePawns.state ^
                blackPawns.state ^
                blackQueen.state ^
                whiteQueen.state ^
                whiteKing.state ^
                blackKing.state ^
                whiteHorses.state ^
                blackHorses.state;
        return gameState;
    }

    // TODO expand to all pieces
    public long getGameState(boolean colour) {
        if (colour) {
            return whitePawns.state ^
                    whiteHorses.state ^
                    whiteQueen.state ^
                    whiteHorses.state ^
                    whiteKing.state;
        } else {
            return blackPawns.state ^
                    blackHorses.state ^
                    blackQueen.state ^
                    blackHorses.state ^
                    blackKing.state;
        }
    }

    public static long coordinateToState(String coord) {
        int file = coord.charAt(0) - 'a';
        int rank = coord.charAt(1) - '1';

        long square = 1L;

        for (int i = 0; i < file; i++) {
            square = Bitboard.slideNorth(square);
        }
        for (int j = 0; j < 8 - rank; j++) {
            square = Bitboard.slideEast(square);
        }
        return square;
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        for (int rank = 8; rank > 0; rank--) {
            for (char file = 'a'; file < 'i'; file++) {
                board.append("" + file + rank + " ");
            }
            board.append("| " + rank + "\n");
        }
        board.append("---------------\n");
        board.append("a b c d e f g h");

        String b = board.toString();

        long[] wPawns = whitePawns.getAllPieceStates();
        for (long pawn : wPawns) {
            char file = (char) (Bitboard.getFile(pawn) + 'a' - 1);
            int rank = Bitboard.getRank(pawn);
            b = b.replace("" + file + rank, WPAWN);
        }

        long[] bPawns = blackPawns.getAllPieceStates();
        for (long pawn : bPawns) {
            char file = (char) (Bitboard.getFile(pawn) + 'a' - 1);
            int rank = Bitboard.getRank(pawn);
            b = b.replace("" + file + rank, BPAWN);
        }

        long[] wHorses = whiteHorses.getAllPieceStates();
        for (long pawn : wHorses) {
            char file = (char) (Bitboard.getFile(pawn) + 'a' - 1);
            int rank = Bitboard.getRank(pawn);
            b = b.replace("" + file + rank, WHORSE);
        }

        long[] bHorses = blackHorses.getAllPieceStates();
        for (long pawn : bHorses) {
            char file = (char) (Bitboard.getFile(pawn) + 'a' - 1);
            int rank = Bitboard.getRank(pawn);
            b = b.replace("" + file + rank, BHORSE);
        }
        return b;
    }
}
