package agh.ics.proj1;

public class Animal {
    private int orientation;
    public Tile tile;

    public Animal(int _orientation) {
        orientation = _orientation;
    }

    public int getOrientation() {
        return orientation;
    }

    public void move() {
        tile.moveForward(this);
    }

    public void turnBack() {
        orientation = (orientation + 4) % 8;
    }
}
