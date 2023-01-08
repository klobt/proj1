package agh.ics.proj1;

import java.util.Random;

public class Config {
    public final Random random;
    public final int mapWidth, mapHeight;
    public final int grassEnergy;
    public final int animalInitialEnergy;
    public final int animalMoveEnergyCost;
    public final int genomeLength;

    public Config(Random random, int mapWidth, int mapHeight, int grassEnergy, int animalInitialEnergy, int animalMoveEnergyCost, int genomeLength) {
        this.random = random;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.grassEnergy = grassEnergy;
        this.animalInitialEnergy = animalInitialEnergy;
        this.animalMoveEnergyCost = animalMoveEnergyCost;
        this.genomeLength = genomeLength;
    }

    public Config(int mapWidth, int mapHeight, int grassEnergy, int animalInitialEnergy, int animalMoveEnergyCost, int genomeLength) {
        this(new Random(), mapWidth, mapHeight, grassEnergy, animalInitialEnergy, animalMoveEnergyCost, genomeLength);
    }
}