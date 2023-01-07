package agh.ics.proj1;

public class Animal {
    private int orientation;
    public Tile tile;
    private double energy;

    public Animal(Config config, int _orientation) {
        orientation = _orientation;
        energy = config.animalInitialEnergy;
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

    public double getEnergy() {
        return energy;
    }

    public void addEnergy(double change) {
        energy += change;
    }
}
