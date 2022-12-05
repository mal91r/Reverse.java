import java.util.ArrayList;

public class EasyComputer extends Player {
    private final ArrayList<Point> newList = new ArrayList<Point>();

    public EasyComputer() {
        this.name = super.toString();
    }

    public EasyComputer(String name) {
        this.name = name;
    }

    final double[][] S = new double[][]{
            {2, 2, 2, 2, 2, 2, 2, 2},
            {2, 1, 1, 1, 1, 1, 1, 2},
            {2, 1, 1, 1, 1, 1, 1, 2},
            {2, 1, 1, 1, 1, 1, 1, 2},
            {2, 1, 1, 1, 1, 1, 1, 2},
            {2, 1, 1, 1, 1, 1, 1, 2},
            {2, 1, 1, 1, 1, 1, 1, 2},
            {2, 2, 2, 2, 2, 2, 2, 2}
    };
    final double[][] SS = new double[][]{
            {0.8, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.8},
            {0.4, 0, 0, 0, 0, 0, 0, 0.4},
            {0.4, 0, 0, 0, 0, 0, 0, 0.4},
            {0.4, 0, 0, 0, 0, 0, 0, 0.4},
            {0.4, 0, 0, 0, 0, 0, 0, 0.4},
            {0.4, 0, 0, 0, 0, 0, 0, 0.4},
            {0.4, 0, 0, 0, 0, 0, 0, 0.4},
            {0.8, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.8}
    };

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void printInfo() {
        System.out.printf("%s is making his choice!%n%n", this);
    }

    @Override
    public void checkPoint(int x, int y) {
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
            } else if (map[i][j] != game.players.get(game.currentPlayer)) {
                //System.out.println(map[i][j]);
                newList.add(new Point(i, j));
            } else if (map[i][j] == game.players.get(game.currentPlayer)) {
                //System.out.println(map[i][j]);
                //if (i != y + dy && j != x + dx) {
                return newList;
                //}
                //return null;
            }
        }
        return null;
    }

    @Override
    public int makeStep() {
        getPossibleSteps();
        game.print();
        Point lastChangedPoint = chooseStep();
        if (lastChangedPoint == null) {
            return 0;
        }
        return makeChange(lastChangedPoint);
    }

    private double R(Point point) {
        double rez = SS[point.getY()][point.getX()];
        for (Point p : steps.get(point)) {
            rez += S[p.getY()][p.getX()];
        }
        return rez;
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
        System.out.printf("%s colored %s!%n", this, point);
        int[][] newArr = deepCopy(game.desk.map);
        newArr[point.getY()][point.getX()] = color;
        for (Point p : game.currentPlayer.steps.keySet()) {
            if (p.equals(point)) {
                point = p;
            }
        }
        int cnt = 0;
        for (Point colored : steps.get(point)) {
            newArr[colored.getY()][colored.getX()] = color;
            cnt++;
        }
        game.desk = new Desk(game.desk, newArr);
        //game.desk.Print();
        //game.printScore();
        return cnt;
    }

    private Point chooseStep() {
        Point ans = null;
        double r, maxR = 0;
        for (Point p : steps.keySet()) {
            r = R(p);
            if (r > maxR) {
                maxR = r;
                ans = p;
            }
        }
        return ans;
    }

    public double chooseStepEasy() {
        double r, maxR = 0;
        for (Point p : steps.keySet()) {
            r = R(p);
            if (r > maxR) {
                maxR = r;
            }
        }
        return maxR;
    }

    public void getPossibleSteps() {
        steps.clear();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                checkPoint(i, j);
            }
        }
    }
}
