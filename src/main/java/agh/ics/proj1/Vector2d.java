package agh.ics.proj1;

public class Vector2d {
    public double x, y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int getIntX() {
        return (int) x;
    }

    public int getIntY() {
        return (int) y;
    }

    public Vector2d times(double k) {
        return new Vector2d(x * k, y * k);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
