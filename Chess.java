public class Chess {
    public static void main(String[] args) {
        PawnBoard p = new PawnBoard();
        p.initialiseBoard(false);
        p.primitiveMove(5, 1);
        System.out.println(p);

    }
}