package agh.ics.proj1;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class GenomeTest {
    @Test
    public void squareMovementTest() {
        Config config = new Config().mapDimensions(4, 4);
        GlobeMap globeMap = new GlobeMap(config);
        Animal animal = new Animal(config, 2, new int[] {2});
        globeMap.at(0, 0).place(animal);
        for (int i = 0; i < 400; i++) {
            animal.move();
        }
        assert(globeMap.at(0, 0).has(animal));
    }
    @Test
    public void breedingTest() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int genomeLength = random.nextInt(10) + 1;
            int initalEnergy = random.nextInt(100) + 1;
            int energyToBreed = random.nextInt(initalEnergy);
            Config config = new Config()
                    .random(random)
                    .animalInitialEnergy(initalEnergy)
                    .genomeLength(genomeLength)
                    .energyHealthy(energyToBreed)
                    .energyToBreed(energyToBreed);
            GrassTile tile = new GrassTile(config, new Vector2d(0, 0), true);
            int[] genome1 = new int[genomeLength];
            for (int j = 0; j < genomeLength; j++) {
                genome1[j] = config.random.nextInt(8);
            }
            Animal a1 = new Animal(config, 0, genome1);
            int[] genome2 = new int[genomeLength];
            for (int j = 0; j < genomeLength; j++) {
                genome2[j] = config.random.nextInt(8);
            }
            Animal a2 = new Animal(config, 0, genome2);
            a2.setStats(random.nextInt(100) + energyToBreed, 0, 0);
            tile.place(a1);
            tile.place(a2);
            int totalEnergy = a1.getEnergy() + a2.getEnergy();
            Animal a3 = tile.breed();
            assert(a3 != null && tile.has(a3) && Math.abs(a3.getEnergy() + a1.getEnergy() + a2.getEnergy() - totalEnergy) <= 1);
        }
    }
}
