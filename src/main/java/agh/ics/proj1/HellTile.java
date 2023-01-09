package agh.ics.proj1;

public class HellTile implements Tile {
    private final GlobeMap globeMap;

    public HellTile(GlobeMap globeMap) {
        this.globeMap = globeMap;
    }

    @Override
    public void place(Animal animal) {
        globeMap.randomPlace(animal);
    }

    @Override
    public boolean has(Animal animal) {
        return false;
    }

    @Override
    public void moveForward(Animal animal) {
    }
}
