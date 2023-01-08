package agh.ics.proj1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Simulation {
    private final GlobeMap globeMap;
    private final List<Animal> animals = new ArrayList<>();
    private final List<Animal> deadAnimals = new ArrayList<>();

    Simulation(Config config) {
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
        Map<Boolean, List<Animal>> aliveOrDead =
                animals.stream().collect(Collectors.groupingBy(a -> a.getEnergy() > 0));
        animals.clear();
        animals.addAll(aliveOrDead.get(true));
        deadAnimals.addAll(aliveOrDead.get(false));

        // skręt i przemieszczenie każdego zwierzęcia,
        for (Animal animal : animals) {
            animal.move();
        }

        // konsumpcja roślin na których pola weszły zwierzęta,
        for (GrassTile tile : globeMap) {
            tile.feed();
        }

        // rozmnażanie się najedzonych zwierząt znajdujących się na tym samym polu,
        for (GrassTile tile : globeMap) {
            tile.breed();
        }

        // wzrastanie nowych roślin na wybranych polach mapy.
        for (GrassTile tile : globeMap) {
            tile.growGrass();
        }
    }
}
