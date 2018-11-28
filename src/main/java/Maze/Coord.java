package Maze;

public class Coord
{
    private double x=0;
    private double y=0;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Coord(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coord() {
    }

    @Override
    public String toString() {
        return "Coord{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
