package montecarlochess;

import java.util.Scanner;

public class GameDriver {
    private Chess game;

    public GameDriver() {
        game = new Chess();
    }

    public void initialiseGame() {
        game.initGame();
    }

    // https://stackoverflow.com/questions/2979383/how-to-clear-the-console-using-java
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void printStart() {
        System.out.println("MONTE CARLO CHESS");
    }

    public void getMove() {
        System.out.println(this.game);
        Scanner input = new Scanner(System.in);
        String move = input.nextLine(); // Read user input
        while (!move.matches("[a-h][1-8]")) {
            clearScreen();
            System.out.println(this.game);
            System.out.println("Invalid move");
            input = new Scanner(System.in);
            move = input.nextLine(); // Read user input
        }
        clearScreen();
    }

    public static void main(String[] args) {
        GameDriver driver = new GameDriver();
        driver.printStart();
        driver.initialiseGame();
        while (true) {
            driver.getMove();
        }
    }
}
