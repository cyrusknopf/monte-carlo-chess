import java.util.ArrayList;

public class Chess {
    private long white_pawn_state;
    private long white_horse_state;
    private long white_bishop_state;
    private long white_castle_state;
    private long white_queen_state;
    private long white_king_state;

    private long black_pawn_state;
    private long black_horse_state;
    private long black_bishop_state;
    private long black_castle_state;
    private long black_queen_state;
    private long black_king_state;

    public void initGame() {
        PawnBoard white_pawn_board = new PawnBoard(this);
        PawnBoard black_pawn_board = new PawnBoard(this);
        white_pawn_board.initialiseBoard(false);
        black_pawn_board.initialiseBoard(true);
        this.white_pawn_state = white_pawn_board.state;
        this.black_pawn_state = black_pawn_board.state;
        
        HorseBoard white_horse_board = new HorseBoard();
        HorseBoard black_horse_board = new HorseBoard();
        white_horse_board.initialiseBoard(false);
        black_horse_board.initialiseBoard(true);
        this.white_horse_state = white_horse_board.state;
        this.black_horse_state = black_horse_board.state;
        
        BishopBoard white_bishop_board = new BishopBoard();
        BishopBoard black_bishop_board = new BishopBoard();
        white_bishop_board.initialiseBoard(false);
        black_bishop_board.initialiseBoard(true);
        this.white_bishop_state = white_bishop_board.state;
        this.black_bishop_state = black_bishop_board.state;
        
        CastleBoard white_castle_board = new CastleBoard();
        CastleBoard black_castle_board = new CastleBoard();
        white_castle_board.initialiseBoard(false);
        black_castle_board.initialiseBoard(true);
        this.white_castle_state = white_castle_board.state;
        this.black_castle_state = black_castle_board.state;
        
        QueenBoard white_queen_board = new QueenBoard();
        QueenBoard black_queen_board = new QueenBoard();
        white_queen_board.initialiseBoard(false);
        black_queen_board.initialiseBoard(true);
        this.white_queen_state = white_queen_board.state;
        this.black_queen_state = black_queen_board.state;
        
        KingBoard white_king_board = new KingBoard();
        KingBoard black_king_board = new KingBoard();
        white_king_board.initialiseBoard(false);
        black_king_board.initialiseBoard(true);
        this.white_king_state = white_king_board.state;
        this.black_king_state = black_king_board.state;
    }

    public static void main(String[] args) {
        Chess game = new Chess();
        game.initGame();
        System.out.println(game.white_pawn_state);
    }
}