package agh.ics.proj1;

public class Animal {
    private final Config config;
    private int orientation;
    public Tile tile;
    private int energy;

    public Animal(Config config, int orientation) {
        this.config = config;
        this.orientation = orientation;
        energy = config.animalInitialEnergy;
    }

    public int getOrientation() {
        return orientation;
    }

    public void move() {
        tile.moveForward(this);
        energy -= config.animalMoveEnergyCost;
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
