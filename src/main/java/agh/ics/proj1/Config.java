package agh.ics.proj1;

public class Config {
    public final int mapWidth, mapHeight;
    public final int grassEnergy;
    public final int animalInitialEnergy;
    public final int animalMoveEnergyCost;

    public Config(int mapWidth, int mapHeight, int grassEnergy, int animalInitialEnergy, int animalMoveEnergyCost) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.grassEnergy = grassEnergy;
        this.animalInitialEnergy = animalInitialEnergy;
        this.animalMoveEnergyCost = animalMoveEnergyCost;
    }
}
