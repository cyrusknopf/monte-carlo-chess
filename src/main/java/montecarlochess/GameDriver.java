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

    public void printStart() {
        System.out.println("MONTE CARLO CHESS");
    }

    public void getMove() {
        Scanner input = new Scanner(System.in);
    }

    public static void main(String[] args) {
        GameDriver driver = new GameDriver();
        driver.printStart();
        driver.initialiseGame();
        System.out.println(driver.game);
    }
}
