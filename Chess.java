public class Chess {
    public static void main(String[] args) {
        PawnBoard p = new PawnBoard();
        p.initialiseBoard(true);
        System.out.println("Board in chess:\n" + p);
    }
}