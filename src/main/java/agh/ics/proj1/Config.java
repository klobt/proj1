package agh.ics.proj1;

import java.util.Random;

public class Config {
    public final Random random;
    public final int mapWidth, mapHeight;
    public final int grassEnergy;
    public final int animalInitialEnergy;
    public final int animalMoveEnergyCost;
    public final int genomeLength;
    public final int energyToBreed;

    public Config(Random random, int mapWidth, int mapHeight, int grassEnergy, int animalInitialEnergy, int animalMoveEnergyCost, int genomeLength, int energyToBreed) {
        this.random = random;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.grassEnergy = grassEnergy;
        this.animalInitialEnergy = animalInitialEnergy;
        this.animalMoveEnergyCost = animalMoveEnergyCost;
        this.genomeLength = genomeLength;
        this.energyToBreed = energyToBreed;
    }

    public Config(int mapWidth, int mapHeight, int grassEnergy, int animalInitialEnergy, int animalMoveEnergyCost, int genomeLength, int energyToBreed) {
        this(new Random(), mapWidth, mapHeight, grassEnergy, animalInitialEnergy, animalMoveEnergyCost, genomeLength, energyToBreed);
    }
}