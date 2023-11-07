public class Chess {
    private Bitboard game_board;
    private Bitboard white_game_board;
    private Bitboard black_game_board;

    private PawnBoard white_pawns;
    private PawnBoard black_pawns;

    private HorseBoard white_horses;
    private HorseBoard black_horses;



    public Chess() {
        this.game_board = new Bitboard();
        this.white_game_board = new Bitboard();
        this.black_game_board = new Bitboard();
        
        this.white_pawns = new PawnBoard(this, true);
        this.black_pawns = new PawnBoard(this, false);

        this.white_horses = new HorseBoard(this, true);
        this.black_horses = new HorseBoard(this, false);
    }

    private void setGameState() {
        this.game_board.state = white_pawns.state ^ black_pawns.state ^ white_horses.state ^ black_horses.state;
    }

    private void setWhiteGameState() {
        this.white_game_board.state = white_pawns.state ^ white_horses.state;
    }

    private void setBlackGameState() {
        this.black_game_board.state = black_pawns.state ^ black_horses.state;
    }




    public static void main(String[] args) {
        Chess game = new Chess();

        game.white_pawns.initialiseBoard();
        game.black_pawns.initialiseBoard();

        game.white_horses.initialiseBoard();
        game.black_horses.initialiseBoard();

        game.setGameState();
        System.out.println(game.game_board);

        game.setWhiteGameState();
        System.out.println(game.white_game_board);

        game.setBlackGameState();
        System.out.println(game.black_game_board);
    }
}