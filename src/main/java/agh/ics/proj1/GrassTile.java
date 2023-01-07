package agh.ics.proj1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GrassTile extends MapTile {
    private final Set<Animal> animals = new HashSet<>();
    public boolean preferred;
    private boolean hasGrass = false;

    public GrassTile(boolean _preffered) {
        this.preferred = _preffered;
    }

    @Override
    public void place(Animal animal) {
        animals.add(animal);
        animal.tile = this;
        if (hasGrass) {
            animal.addEnergy(10);
            hasGrass = false;
        }
    }

    @Override
    public boolean has(Animal animal) {
        return animals.contains(animal);
    }

    @Override
    public void moveForward(Animal animal) {
        Tile nextTile = neighbours[animal.getOrientation()];
        if (nextTile != null) {
            animals.remove(animal);
            nextTile.place(animal);
        }
    }

    public boolean growGrass(Random random) {
        if (!hasGrass && random.nextDouble() < (preferred ? 0.8 : 0.2)) {
            hasGrass = true;
            return true;
        }
        return false;
    }
}