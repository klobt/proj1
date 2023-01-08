package agh.ics.proj1;

public class Config {
    public final int mapWidth, mapHeight;
    public final double grassEnergy;
    public final double animalInitialEnergy;

    public Config(int mapWidth, int mapHeight, double grassEnergy, double animalInitialEnergy) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.grassEnergy = grassEnergy;
        this.animalInitialEnergy = animalInitialEnergy;
    }
}
