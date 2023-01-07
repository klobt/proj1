package agh.ics.proj1;

public class Config {
    public final int mapWidth, mapHeight;
    public final double grassEnergy;
    public final double animalInitialEnergy;

    public Config(int _mapWidth, int _mapHeight, double _grassEnergy, double _animalInitialEnergy) {
        mapWidth = _mapWidth;
        mapHeight = _mapHeight;
        grassEnergy = _grassEnergy;
        animalInitialEnergy = _animalInitialEnergy;
    }
}
