import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Game {
    Player player1;
    Player player2;
    Map<Player, Integer> players = new HashMap<Player, Integer>();
    Desk desk = new Desk();
    Player currentPlayer;
    Scanner scanner = new Scanner(System.in);

    Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = player1;
        player1.game = this;
        player2.game = this;
        players.put(player1, 1);
        players.put(player2, 2);
    }

    void printScore() {
        System.out.printf("%s(1) %d - %d %s(2) %n%n", player1, desk.getFirstPlayerScore(), desk.getSecondPlayerScore(), player2);
        currentPlayer.printInfo();
    }

    public int start() {
        int status;
        do {
            currentPlayer.steps.clear();
            status = currentPlayer.makeStep();
            if (status == -1) {
                goBack();
            }
            if (player1 == currentPlayer) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }

        } while (!player1.steps.isEmpty() || !player2.steps.isEmpty());
        System.out.print("The game is over!\n");
        return callWinner();
    }

    public int callWinner() {
        if (desk.getFirstPlayerScore() > desk.getSecondPlayerScore()) {
            System.out.printf("%s won! Score - %d^n", player1, desk.getFirstPlayerScore());
            if (player1 instanceof Person) {
                return desk.getFirstPlayerScore();
            } else {
                return 0;
            }
        } else {
            System.out.printf("%s won! Score - %d%n", player2, desk.getSecondPlayerScore());
            if (player1 instanceof Person) {
                return desk.getSecondPlayerScore();
            } else {
                return 0;
            }
        }
    }

    public void print() {
        desk.Print(currentPlayer.steps.keySet());
        printScore();
    }

    void goBack() {
        if (desk.getPreviousDesk() != null && desk.getPreviousDesk().getPreviousDesk() != null) {
            desk = desk.getPreviousDesk().getPreviousDesk();
        }
        if (player1 == currentPlayer) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    void backUp() {
        if (desk.getPreviousDesk() != null) {
            desk = desk.getPreviousDesk();
        }
        if (player1 == currentPlayer) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }
}
