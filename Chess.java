public class Chess {
    public static void main(String[] args) {
        PawnBoard p = new PawnBoard();
        p.initialiseBoard(false);
        System.out.println(p.toString());
    }
}
