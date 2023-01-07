package agh.ics.proj1;

public class Vector2d {
    public double x, y;

    public Vector2d(double _x, double _y) {
        x = _x;
        y = _y;
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
