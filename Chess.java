public class Chess {
    public static void main(String[] args) {
        QueenBoard p = new QueenBoard();
        p.initialiseBoard(false);
        System.out.println("Board for white:\n" + p);
        p.initialiseBoard(true);
        System.out.println("\nBoard for black:\n" + p);
    }
}