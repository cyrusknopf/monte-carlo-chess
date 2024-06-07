package montecarlochess;

import java.util.Scanner;

public class GameDriver {
    private Chess game;
    private boolean inProgress;
    private boolean turn;

    public GameDriver() {
        game = new Chess();
    }

    public void initialiseGame() {
        game.initGame();
        inProgress = true;
    }

    // https://stackoverflow.com/questions/2979383/how-to-clear-the-console-using-java
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void printStart() {
        System.out.println("MONTE CARLO CHESS");
    }

    public String getSquare() {
        Scanner input = new Scanner(System.in);
        String square = "";
        while (!square.matches("[a-h][1-8]")) {
            clearScreen();
            System.out.println(this.game);
            if (square != "")
                System.out.println("Invalid move");
            else
                System.out.println();
            square = input.nextLine(); // Read user input
        }
        clearScreen();
        return square;
    }

    public static void main(String[] args) {
        GameDriver driver = new GameDriver();
        driver.printStart();
        driver.initialiseGame();
        String from_str = driver.getSquare();
        long from = Chess.coordinateToState(from_str);
        String to_str = driver.getSquare();
        long to = Chess.coordinateToState(to_str);
    }
}
