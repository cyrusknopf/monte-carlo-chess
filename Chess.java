public class Chess {
    public static void main(String[] args) {
        PawnBoard p = new PawnBoard();
        p.initialiseBoard(false);

        Bitboard w = new Bitboard();
        w.board = 1L << (1 + 1*8);
        System.out.println("\n w:\n");
        System.out.println(w);

        p.board = p.board ^ w.board;
        System.out.println("\n XOR'd pawnboard:\n");
        System.out.println(p);
        w.board = w.board << 8;
        p.board |= w.board;
        System.out.println(p);


    }
}