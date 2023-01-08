package agh.ics.proj1;

import java.util.Arrays;

public class Animal {
    private final Config config;
    private int orientation;
    public Tile tile;
    private int energy;
    private final int[] genome;
    private int active = 0;
    private int childrenBegotten = 0;
    private int movesMade = 0;

    public Animal(Config config, int orientation, int[] genome) {
        this.config = config;
        this.orientation = orientation;
        energy = config.animalInitialEnergy;
        this.genome = Arrays.copyOf(genome, config.genomeLength);
    }

    public Animal(Config config, int orientation) {
        this(config, orientation, new int[config.genomeLength]);
    }

    public int getOrientation() {
        return orientation;
    }

    public void move() {
        orientation = (orientation + genome[active]) % 8;
        tile.moveForward(this);
        energy -= config.animalMoveEnergyCost;
        active = (active + 1) % genome.length;
        movesMade++;
    }

    public void turnBack() {
        orientation = (orientation + 4) % 8;
    }

    public int getEnergy() {
        return energy;
    }

    public void addEnergy(double change) {
        energy += change;
    }

    public int getChildrenBegotten() {
        return childrenBegotten;
    }

    public int getMovesMade() {
        return movesMade;
    }

    public void setStats(int energy, int childrenBegotten, int movesMade) {
        this.energy = energy;
        this.childrenBegotten = childrenBegotten;
        this.movesMade = movesMade;
    }
}
