package montecarlochess;

import montecarlochess.bitboards.*;

public class Chess {
    private Bitboard game_board;
    private Bitboard white_game_board;
    private Bitboard black_game_board;

    private PawnBoard white_pawns;
    private PawnBoard black_pawns;

    private HorseBoard white_horses;
    private HorseBoard black_horses;

    private KingBoard white_king;
    private KingBoard black_king;

    public Chess() {
        this.game_board = new Bitboard();
        this.white_game_board = new Bitboard();
        this.black_game_board = new Bitboard();
        
        this.white_pawns = new PawnBoard(this, true);
        this.black_pawns = new PawnBoard(this, false);

        this.white_horses = new HorseBoard(this, true);
        this.black_horses = new HorseBoard(this, false);

        this.white_king = new KingBoard(this, true);
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

    public long getGameState() {
        return this.game_board.state;
    }

    public long getGameState(boolean colour) {
        if (colour) {
            return white_pawns.state ^ white_horses.state;
        }
        else {
            return black_pawns.state ^ black_horses.state;
        }
    }



    public static void main(String[] args) {
        Chess game = new Chess();
        game.white_pawns.initialiseBoard();
        for (PawnBoard board : game.white_pawns.getPseudoLegalMoves()) {
            System.out.println(board);
        }
            }
}
