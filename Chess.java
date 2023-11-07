public class Chess {
    private PawnBoard white_pawns;
    private PawnBoard black_pawns;

    public Chess() {
        this.white_pawns = new PawnBoard(this, true);
        this.black_pawns = new PawnBoard(this, false);
    }
    public static void main(String[] args) {
        Chess game = new Chess();
        game.white_pawns.initialiseBoard();

        for (PawnBoard board: game.white_pawns.getAllBoards()) {
            board.slideNorth();
            System.out.println(board);
        }
    }
}