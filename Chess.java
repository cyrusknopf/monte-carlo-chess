public class Chess {
    public static void main(String[] args) {
        KingBoard p = new KingBoard();
        p.initialiseBoard(true);
        System.out.println("Board in chess:\n" + p);
    }
}