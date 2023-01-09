package agh.ics.proj1;

import java.util.*;

public class Simulation {
    public final Config config;
    public final GlobeMap globeMap;
    public final List<Animal> animals = new ArrayList<>();
    public final List<Animal> deadAnimals = new ArrayList<>();

     public int statDead = 0;
     public int statBorn = 0;
     public int statGrassGrown = 0;
     public int statMovesMade = 0;
     public int statGrassEaten = 0;

    public Simulation(Config config) {
        this.config = config;
        globeMap = new GlobeMap(config);
        int grassN = 0;
        while (grassN < config.startingGrass) {
            int x = config.random.nextInt(config.mapWidth);
            int y = config.random.nextInt(config.mapHeight);
            GrassTile tile = (GrassTile) globeMap.at(x, y);
            if (tile.growGrass()) {
                grassN++;
            }
        }
        for (int i = 0; i < config.startingAnimals; i++) {
            int x = config.random.nextInt(config.mapWidth);
            int y = config.random.nextInt(config.mapHeight);
            int[] genome = new int[config.genomeLength];
            for (int j = 0; j < genome.length; j++) {
                genome[j] = config.random.nextInt(8);
            }
            Animal animal = new Animal(config, 0, genome);
            animals.add(animal);
            globeMap.at(x, y).place(animal);
        }
    }

    public void step() {
        // usunięcie martwych zwierząt z mapy,
        Set<Animal> forRemoval = new HashSet<>();
        for (Animal animal : animals) {
            if (animal.getEnergy() == 0) {
                forRemoval.add(animal);
            }
        }
        for (Animal animal : forRemoval) {
            animals.remove(animal);
            deadAnimals.add(animal);
            ((GrassTile) animal.tile).takeTheDead(animal);
            statDead++;
        }
        if (config.grassVariant == GrassVariant.TOXIC_CORPSES) {
            SortedSet<GrassTile> tilesByDeadliness = new TreeSet<>(Comparator.comparingInt(tile -> tile.deaths));
            for (GrassTile tile : globeMap) {
                tile.preferred = false;
                tilesByDeadliness.add(tile);
            }
            for (int i = 0; i <= (int) (config.mapWidth * config.mapHeight * 0.2); i++) {
                GrassTile tile = tilesByDeadliness.first();
                tile.preferred = true;
                tilesByDeadliness.remove(tile);
            }
        }

        // skręt i przemieszczenie każdego zwierzęcia,
        for (Animal animal : animals) {
            animal.move();
            statMovesMade++;
        }

        // konsumpcja roślin na których pola weszły zwierzęta,
        for (GrassTile tile : globeMap) {
            if (tile.feed()) {
                statGrassEaten++;
            }
        }

        // rozmnażanie się najedzonych zwierząt znajdujących się na tym samym polu,
        for (GrassTile tile : globeMap) {
            Animal newAnimal = tile.breed();
            if (newAnimal != null) {
                animals.add(newAnimal);
                statBorn++;
            }
        }

        // wzrastanie nowych roślin na wybranych polach mapy.
        int grassN = 0;
        for (GrassTile tile : globeMap) {
            if (tile.growGrass()) {
                grassN++;
                if (grassN == config.dailyGrass) {
                    break;
                }
            }
        }
        statGrassGrown += grassN;
    }
}
