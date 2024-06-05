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

    public String getMove() {
        Scanner input = new Scanner(System.in);
        String move = "";
        while (!move.matches("[a-h][1-8]")) {
            clearScreen();
            System.out.println(this.game);
            if (move != "")
                System.out.println("Invalid move");
            else
                System.out.println();
            move = input.nextLine(); // Read user input
        }
        clearScreen();
        input.close();
        return move;
    }

    public static void main(String[] args) {
        GameDriver driver = new GameDriver();
        driver.printStart();
        driver.initialiseGame();
        driver.getMove();
    }
}
