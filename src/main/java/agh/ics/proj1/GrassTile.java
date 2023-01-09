package agh.ics.proj1;

import java.util.*;

class PeckingOrder implements Comparator<Animal> {
    @Override
    public int compare(Animal a1, Animal a2) {
        int result = a1.getEnergy() - a2.getEnergy();
        if (result == 0) {
            result = a1.getMovesMade() - a2.getMovesMade();
        }
        if (result == 0) {
            result = a1.getChildrenBegotten() - a2.getChildrenBegotten();
        }
        return result;
    }
}

public class GrassTile extends MapTile {
    private final Config config;
    private final SortedSet<Animal> animals = new TreeSet<>(new PeckingOrder());
    public boolean preferred;
    private boolean hasGrass = false;
    private final Set<ChangeListener<GrassTile>> listeners = new HashSet<>();
    public int deaths = 0;

    public GrassTile(Config config, Vector2d position, boolean preffered) {
        super(position);
        this.config = config;
        this.preferred = preffered;
    }

    @Override
    public void place(Animal animal) {
        animals.add(animal);
        animal.tile = this;
        notifyOfChange();
    }

    public void takeTheDead(Animal animal) {
        animals.remove(animal);
        animal.tile = null;
        deaths++;
        notifyOfChange();
    }

    public boolean feed() {
        if (hasGrass && !animals.isEmpty()) {
            animals.last().addEnergy(config.grassEnergy);
            hasGrass = false;
            notifyOfChange();
            return true;
        }
        return false;
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
            notifyOfChange();
        }
    }

    public boolean growGrass() {
        if (!hasGrass && config.random.nextDouble() < (preferred ? 0.8 : 0.2)) {
            hasGrass = true;
            notifyOfChange();
            return true;
        }
        return false;
    }

    public Animal breed() {
        if (animals.size() >= 2) {
            Animal a1 = animals.last();
            animals.remove(a1);
            Animal a2 = animals.last();
            animals.add(a1);
            if (a1.getEnergy() >= config.energyHealthy && a2.getEnergy() >= config.energyHealthy) {
                Animal a3 = new Animal(config, a1, a2);
                place(a3);
                notifyOfChange();
                return a3;
            }
        }
        return null;
    }

    public void notifyOfChange() {
        for (ChangeListener<GrassTile> listener : listeners) {
            listener.onChange(this);
        }
    }

    public void addListener(ChangeListener<GrassTile> listener) {
        listeners.add(listener);
    }

    public SortedSet<Animal> getAnimals() {
        return animals;
    }

    public boolean hasGrass() {
        return hasGrass;
    }
}
