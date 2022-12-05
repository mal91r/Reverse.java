import java.util.ArrayList;
import java.util.Set;

public class Person extends Player {
    private final ArrayList<Point> newList = new ArrayList<Point>();

    public Person() {
        this.name = super.toString();
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
    @Override
    public void printInfo() {
        System.out.printf("%s, that's your turn, hurry up!%n%n", this);
    }

    @Override
    public void checkPoint(int y, int x) {
        int[][] map = game.desk.map;
        ArrayList<Point> listOfPoints;
        if (map[y][x] == 0) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i != 0 || j != 0) {
                        listOfPoints = checkLine(y, x, j, i);
                        if (listOfPoints != null) {
                            //System.out.println(1);
                            points.addAll(listOfPoints);
                        }
                    }
                }
            }
            if (!points.isEmpty()) {
                steps.put(new Point(y, x), new ArrayList<Point>(points));
                points.clear();
            }
        }
    }

    @Override
    public ArrayList<Point> checkLine(int y, int x, int dy, int dx) {
        int[][] map = game.desk.map;
        newList.clear();
        for (int i = y + dy, j = x + dx; i >= 0 && i <= 7 && j >= 0 && j <= 7; i += dy, j += dx) {
            if (map[i][j] == 0) {
                return null;
            } else if (map[i][j] != game.players.get(this)) {
                //System.out.println(map[i][j]);
                newList.add(new Point(i, j));
            } else if (map[i][j] == game.players.get(this)) {
                //System.out.println(map[i][j]);
                //if (i != y + dy && j != x + dx) {
                return newList;
                //}
                //return null;
            }
        }
        return null;
    }

    public int makeStep() {
        int cnt = getPossibleSteps();
        if (cnt == 0) {
            return 0;
        }
        game.print();
        printPossibleSteps();
        Point point = chooseStep();
        if (point == null) {
            return -1;
        }
        return makeChange(point);
    }

    private static int[][] deepCopy(int[][] original) {
        if (original == null) return null;

        int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = original[i].clone();
        }
        return result;
    }

    private int makeChange(Point point) {
        int color = game.players.get(game.currentPlayer);
        int[][] newArr = deepCopy(game.desk.map);
        newArr[point.getY()][point.getX()] = color;
        for (Point p : game.currentPlayer.steps.keySet()) {
            if (p.equals(point)) {
                point = p;
            }
        }
        int cnt = 0;
        for (Point colored : game.currentPlayer.steps.get(point)) {
            newArr[colored.getY()][colored.getX()] = color;
            cnt++;
        }
        game.desk = new Desk(game.desk, newArr);
        return cnt;
    }

    private Point chooseStep() {
        int x = 0, y = 0;
        while (true) {
            try {
                System.out.print("Enter y: ");
                y = game.scanner.nextInt();
                if (y >= 1 && y <= 8) {
                    break;
                } else if (y == -999) {
                    return null;
                } else {
                    System.out.println("Number needs to be from 1 to 8");
                }

            } catch (Exception e) {
                System.out.println("Incorrect input");
                game.scanner.next();
            }
        }
        while (true) {
            try {
                System.out.print("Enter x: ");
                x = game.scanner.nextInt();
                if (x >= 1 && x <= 8) {
                    break;
                } else if (x == -999) {
                    return null;
                } else {
                    System.out.println("Number needs to be from 1 to 8");
                }
            } catch (Exception e) {
                System.out.println("Incorrect input");
                game.scanner.next();
            }
        }

        Point point = new Point(y - 1, x - 1);
        for (Point p : game.currentPlayer.steps.keySet()) {
            if (p.equals(point)) {
                point = p;
                System.out.println("That's it!");
                return point;
            }
        }
        System.out.println("You can't make a step to this field");
        return chooseStep();
    }

    private int getPossibleSteps() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                game.currentPlayer.checkPoint(i, j);
            }
        }
        return game.currentPlayer.steps.size();
    }

    private void printPossibleSteps() {
        System.out.print("List of possible steps:\n");
        Set<Point> possibleSteps = game.currentPlayer.steps.keySet();
        for (Point point : possibleSteps) {
            System.out.println(point);
        }
    }
}
