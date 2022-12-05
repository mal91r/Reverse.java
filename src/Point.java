public class Point {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point(int y, int x) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("<%d;%d>", y + 1, x + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point that = (Point) o;
        return x == that.x &&
                y == that.y;
    }
}
