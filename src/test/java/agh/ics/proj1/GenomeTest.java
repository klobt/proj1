package agh.ics.proj1;

import org.junit.jupiter.api.Test;

public class GenomeTest {
    @Test
    public void squareMovement() {
        Config config = new Config(4, 4, 0, 0, 0, 1);
        GlobeMap globeMap = new GlobeMap(config);
        Animal animal = new Animal(config, 2, new int[] {2});
        globeMap.at(0, 0).place(animal);
        for (int i = 0; i < 400; i++) {
            animal.move();
        }
        assert(globeMap.at(0, 0).has(animal));
    }
}
