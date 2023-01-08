package agh.ics.proj1;

public class Config {
    public final int mapWidth, mapHeight;
    public final int grassEnergy;
    public final int animalInitialEnergy;

    public Config(int mapWidth, int mapHeight, int grassEnergy, int animalInitialEnergy) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.grassEnergy = grassEnergy;
        this.animalInitialEnergy = animalInitialEnergy;
    }
}
