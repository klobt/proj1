package agh.ics.proj1;

public class Vector2d {
    public int x, y;

    public Vector2d(int _x, int _y) {
        x = _x;
        y = _y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
