import java.util.Objects;
import java.util.Scanner;
import java.util.stream.StreamSupport;

public class Menu {
    private final Scanner SCANNER = new Scanner(System.in);
    private int record;

    Menu(){
        record = 0;
    }
    private void greeting() {
        System.out.println("Welcome to The Reverse! \nHope you know the rules, so I'll just remind a few moments according to my game.\n" +
                "There are three kinds of players: person, easy computer and hard computer.\n" +
                "You can make all the enemies-combinations, for example:\n" +
                "person vs person, person vs easy computer and also easy computer vs hard computer.\n" +
                "You can make a backup by entering number -999.\n");
    }

    public void start() {
        greeting();
        while (true) {
            System.out.print("Do you want to start a new game?(Yes or No) - ");
            String cmd = SCANNER.nextLine();
            switch (cmd) {
                case "Yes":
                    startNewGame();
                    break;
                case "No":
                    return;
                default:
                    System.out.println("Incorrect input");
                    break;
            }
        }
    }

    private void startNewGame() {
        Player player1 = chooseFirstPlayer();
        Player player2 = chooseSecondPlayer();
        System.out.printf("The game %s(1) vs %s(2) is starting...\n", player1, player2);
        Game game = new Game(player1, player2);
        int result = game.start();
        if(result > record){
            System.out.printf("New record - %d! The previous was %d..%n", result, record);
            record = result;
        } else {
            System.out.printf("Best score for today - %d%n", record);
        }
    }

    private Player chooseFirstPlayer() {
        Player player1 = null;
        boolean isCorrect = false;
        String name;
        while (!isCorrect) {
            System.out.print("Choose the first player:\n" +
                    "Person\n" +
                    "EasyComputer\n" +
                    "HardComputer\n" +
                    "Enter type of player as it is written upper: ");
            isCorrect = true;
            String player = SCANNER.nextLine();
            name = getName();
            switch (player) {
                case "Person":
                    if (name == "") {
                        player1 = new Person();
                    } else {
                        player1 = new Person(name);
                    }
                    break;
                case "EasyComputer":
                    if (name == "") {
                        player1 = new EasyComputer();
                    } else {
                        player1 = new EasyComputer(name);
                    }
                    break;
                case "HardComputer":
                    if (name == "") {
                        player1 = new HardComputer();
                    } else {
                        player1 = new HardComputer(name);
                    }
                    break;
                default:
                    isCorrect = false;
                    System.out.println("Incorrect input");
            }
        }
        return player1;
    }

    private String getName() {
        System.out.print("Enter the name of the player: ");
        return SCANNER.nextLine();
    }

    private Player chooseSecondPlayer() {
        Player player2 = null;
        boolean isCorrect = false;
        String name;
        while (!isCorrect) {
            System.out.print("Choose the second player:\n" +
                    "Person\n" +
                    "EasyComputer\n" +
                    "HardComputer\n" +
                    "Enter type of player as it is written upper: ");
            isCorrect = true;
            String player = SCANNER.nextLine();
            name = getName();
            switch (player) {
                case "Person":
                    if (name == "") {
                        player2 = new Person();
                    } else {
                        player2 = new Person(name);
                    }
                    break;
                case "EasyComputer":
                    if (name == "") {
                        player2 = new EasyComputer();
                    } else {
                        player2 = new EasyComputer(name);
                    }
                    break;
                case "HardComputer":
                    if (name == "") {
                        player2 = new HardComputer();
                    } else {
                        player2 = new HardComputer(name);
                    }
                    break;
                default:
                    isCorrect = false;
                    System.out.println("Incorrect input");
            }
        }
        return player2;
    }
}
