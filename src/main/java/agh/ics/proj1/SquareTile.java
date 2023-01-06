package agh.ics.proj1;

import java.util.HashSet;
import java.util.Set;

public class SquareTile extends MapTile {
    private final Set<Animal> animals = new HashSet<>();

    public SquareTile() {
    }

    @Override
    public void place(Animal animal) {
        animals.add(animal);
        animal.tile = this;
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
}
