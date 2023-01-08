package agh.ics.proj1;

import java.util.*;

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

    public Animal(Config config, Animal parent1, Animal parent2) {
        this.config = config;
        orientation = 0;
        Animal domParent = parent1.energy >= parent2.energy ? parent1 : parent2;
        Animal subParent = parent1.energy < parent2.energy ? parent1 : parent2;
        double domShare = ((double) domParent.energy) / (double) (domParent.energy + subParent.energy);
        int domGenes = (int) (domShare * config.genomeLength);
        int subGenes = config.genomeLength - domGenes;
        energy = parent1.takeEnergy(config.energyToBreed) + parent2.takeEnergy(config.energyToBreed);

        genome = new int[config.genomeLength];
        int domGenomeOffset = 0;
        int subGenomeOffset = domGenes;
        if (config.random.nextBoolean()) {
            domGenomeOffset = subGenes;
            subGenomeOffset = 0;
        }
        System.arraycopy(domParent.genome, 0, genome, domGenomeOffset, domGenes);
        System.arraycopy(subParent.genome, 0, genome, subGenomeOffset, subGenes);
        int mutationN = config.random.nextInt(genome.length);
        List<Integer> unvisited = new ArrayList<>();
        for (int i = 0; i < genome.length; i++) {
            unvisited.add(i);
        }
        Collections.shuffle(unvisited, config.random);
        for (int i = 0; i < mutationN; i++) {
            genome[unvisited.get(i)] = config.random.nextInt(8);
        }
    }

    public int getOrientation() {
        return orientation;
    }

    public void move() {
        if (takeEnergy(config.animalMoveEnergyCost) == config.animalMoveEnergyCost) {
            orientation = (orientation + genome[active]) % 8;
            tile.moveForward(this);
            active = (active + 1) % genome.length;
            movesMade++;
        }
    }

    public void turnBack() {
        orientation = (orientation + 4) % 8;
    }

    public int getEnergy() {
        return energy;
    }

    public void addEnergy(int change) {
        energy += change;
    }

    public int takeEnergy(int taken) {
        taken = Math.min(taken, energy);
        energy -= taken;
        return taken;
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
