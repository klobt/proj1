package agh.ics.proj1;

public class Config {
    public final int mapWidth, mapHeight;
    public final int grassEnergy;
    public final int animalInitialEnergy;
    public final int animalMoveEnergyCost;
    public final int genomeLength;

    public Config(int mapWidth, int mapHeight, int grassEnergy, int animalInitialEnergy, int animalMoveEnergyCost, int genomeLength) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.grassEnergy = grassEnergy;
        this.animalInitialEnergy = animalInitialEnergy;
        this.animalMoveEnergyCost = animalMoveEnergyCost;
        this.genomeLength = genomeLength;
    }
}
