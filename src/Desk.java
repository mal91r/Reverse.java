import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Desk {
    public int[][] map;
    private final Desk previousDesk;
    private final int[] score;


    public Desk() {
        previousDesk = null;
        map = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 1, 0, 0, 0},
                {0, 0, 0, 1, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        score = new int[]{2, 2};
    }

    public Desk(Desk desk, int[][] map) {
        previousDesk = desk;
        this.map = map;
        score = new int[]{0, 0};
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (map[i][j] == 1) {
                    score[0]++;
                }
                if (map[i][j] == 2) {
                    score[1]++;
                }
            }
        }
    }

    public Desk getPreviousDesk() {
        return previousDesk;
    }

    public int getFirstPlayerScore() {
        return score[0];
    }

    public int getSecondPlayerScore() {
        return score[1];
    }

    public void Print(Set<Point> points) {
        System.out.println("   1  2  3  4  5  6  7  8\n" +
                "   ----------------------");
        for (int i = 0; i < 8; i++) {
            System.out.printf("%d| ", i + 1);
            for (int j = 0; j < 8; j++) {
                if (myContains(points, new Point(i, j))) {
                    System.out.print("X  ");
                } else {
                    System.out.printf("%d  ", map[i][j]);
                }
            }
            System.out.println();
        }
    }

    private boolean myContains(Set<Point> points, Point point) {
        for (Point p : points) {
            if (p.equals(point)) {
                return true;
            }
        }
        return false;
    }
}
