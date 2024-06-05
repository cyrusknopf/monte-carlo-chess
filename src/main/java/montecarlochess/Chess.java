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

    private PawnBoard whitePawns;
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

        this.whiteCastles = new CastleBoard(this, WHITE);
        this.blackCastles = new CastleBoard(this, BLACK);
    }

    public void initGame() {
        initPawns();
        initQueens();
        initHorses();
        initKings();
        initBishops();
        initCastles();
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

    public void initCastles() {
        whiteCastles.state = whiteCastles.init;
        blackCastles.state = Long.reverse(whiteCastles.init);
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

    public long getGameState() {
        return getGameState(true) ^ getGameState(false);
    }

    public long getGameState(boolean colour) {
        if (colour) {
            return whitePawns.state ^
                    whiteHorses.state ^
                    whiteQueen.state ^
                    whiteHorses.state ^
                    whiteBishops.state ^
                    whiteCastles.state ^
                    whiteKing.state;
        } else {
            return blackPawns.state ^
                    blackHorses.state ^
                    blackQueen.state ^
                    blackHorses.state ^
                    blackBishops.state ^
                    blackCastles.state ^
                    blackKing.state;
        }
    }

    // Takes a coordinate string (e.g. a4) and produces a board with a 1 in that
    // location only
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
        // Create a board with co ordiante keys, and each square with its coordinate
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

        // For each piece, replace the square coordinate with the symbol of that piece

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
        for (long horse : wHorses) {
            char file = (char) (Bitboard.getFile(horse) + 'a' - 1);
            int rank = Bitboard.getRank(horse);
            b = b.replace("" + file + rank, WHORSE);
        }

        long[] bHorses = blackHorses.getAllPieceStates();
        for (long horse : bHorses) {
            char file = (char) (Bitboard.getFile(horse) + 'a' - 1);
            int rank = Bitboard.getRank(horse);
            b = b.replace("" + file + rank, BHORSE);
        }

        long[] wBishops = whiteBishops.getAllPieceStates();
        for (long bishop : wBishops) {
            char file = (char) (Bitboard.getFile(bishop) + 'a' - 1);
            int rank = Bitboard.getRank(bishop);
            b = b.replace("" + file + rank, WBISHOP);
        }
        long[] bBishops = blackBishops.getAllPieceStates();
        for (long bishop : bBishops) {
            char file = (char) (Bitboard.getFile(bishop) + 'a' - 1);
            int rank = Bitboard.getRank(bishop);
            b = b.replace("" + file + rank, BBISHOP);
        }

        long[] wCastles = whiteCastles.getAllPieceStates();
        for (long castle : wCastles) {
            char file = (char) (Bitboard.getFile(castle) + 'a' - 1);
            int rank = Bitboard.getRank(castle);
            b = b.replace("" + file + rank, WCASTLE);
        }
        long[] bCastles = blackCastles.getAllPieceStates();
        for (long castle : bCastles) {
            char file = (char) (Bitboard.getFile(castle) + 'a' - 1);
            int rank = Bitboard.getRank(castle);
            b = b.replace("" + file + rank, BCASTLE);
        }

        {
            char file = (char) (Bitboard.getFile(whiteKing.state) + 'a' - 1);
            int rank = Bitboard.getRank(whiteKing.state);
            b = b.replace("" + file + rank, WKING);
        }

        {
            char file = (char) (Bitboard.getFile(blackKing.state) + 'a' - 1);
            int rank = Bitboard.getRank(blackKing.state);
            b = b.replace("" + file + rank, BKING);
        }

        {
            char file = (char) (Bitboard.getFile(whiteQueen.state) + 'a' - 1);
            int rank = Bitboard.getRank(whiteQueen.state);
            b = b.replace("" + file + rank, WQUEEN);
        }

        {
            char file = (char) (Bitboard.getFile(blackQueen.state) + 'a' - 1);
            int rank = Bitboard.getRank(blackQueen.state);
            b = b.replace("" + file + rank, BQUEEN);
        }

        // Any squares remaining with a coordinate labeled is empty, replace with space
        b = b.replaceAll("[a-z][1-8]", " ");
        return b;
    }
}
