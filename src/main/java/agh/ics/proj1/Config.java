package agh.ics.proj1;

import java.util.Random;

public class Config {
    public Random random = new Random();
    public long simulationStepDelay = 1000;
    public int mapWidth = 1, mapHeight = 1;
    public int grassEnergy = 0;
    public int animalInitialEnergy = 0;
    public int animalMoveEnergyCost = 0;
    public int genomeLength = 1;
    public int minMutations = 0;
    public int maxMutations = 0;
    public int energyToBreed = 0;
    public int startingGrass = 0;
    public int startingAnimals = 0;
    public int dailyGrass = 0;
    public MapVariant mapVariant = MapVariant.GLOBE;
    public GrassVariant grassVariant = GrassVariant.GREEN_EQUATOR;
    public GeneticVariant geneticVariant = GeneticVariant.TOTAL_RANDOMNESS;
    public MoveVariant moveVariant = MoveVariant.TOTAL_DETERMINISM;

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

    public Config minMutations(int minMutations) {
        this.minMutations = minMutations;
        return this;
    }

    public Config maxMutations(int maxMutations) {
        this.maxMutations = maxMutations;
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

    public Config simulationStepDelay(long simulationStepDelay) {
        this.simulationStepDelay = simulationStepDelay;
        return this;
    }

    public Config mapVariant(MapVariant mapVariant) {
        this.mapVariant = mapVariant;
        return this;
    }

    public Config grassVariant(GrassVariant grassVariant) {
        this.grassVariant = grassVariant;
        return this;
    }

    public Config geneticVariant(GeneticVariant geneticVariant) {
        this.geneticVariant = geneticVariant;
        return this;
    }

    public Config moveVariant(MoveVariant moveVariant) {
        this.moveVariant = moveVariant;
        return this;
    }
}