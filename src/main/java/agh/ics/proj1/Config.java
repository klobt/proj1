package agh.ics.proj1;

import java.util.Random;

public class Config {
    public Random random = new Random();
    public int mapWidth = 1, mapHeight = 1;
    public int grassEnergy = 0;
    public int animalInitialEnergy = 0;
    public int animalMoveEnergyCost = 0;
    public int genomeLength = 1;
    public int energyToBreed = 0;
    public int startingGrass = 0;
    public int startingAnimals = 0;
    public int dailyGrass = 0;

    public Config random(Random random) {
        this.random = random;
        return this;
    }

    public Config mapDimensions(int width, int height) {
        mapWidth = width;
        mapHeight = height;
        return this;
    }

    public Config grassEnergy(int grassEnergy) {
        this.grassEnergy = grassEnergy;
        return this;
    }

    public Config animalInitialEnergy(int animalInitialEnergy) {
        this.animalInitialEnergy = animalInitialEnergy;
        return this;
    }

    public Config animalMoveEnergyCost(int animalMoveEnergyCost) {
        this.animalMoveEnergyCost = animalMoveEnergyCost;
        return this;
    }

    public Config genomeLength(int genomeLength) {
        this.genomeLength = genomeLength;
        return this;
    }

    public Config energyToBreed(int energyToBreed) {
        this.energyToBreed = energyToBreed;
        return this;
    }

    public Config startingGrass(int startingGrass) {
        this.startingGrass = startingGrass;
        return this;
    }

    public Config startingAnimals(int startingAnimals) {
        this.startingAnimals = startingAnimals;
        return this;
    }

    public Config dailyGrass(int dailyGrass) {
        this.dailyGrass = dailyGrass;
        return this;
    }
}