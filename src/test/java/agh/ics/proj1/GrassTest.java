package agh.ics.proj1;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class GrassTest {
    @Test
    public void paretoTest() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int width = random.nextInt(1000);
            int height = random.nextInt(1000);
            GlobeMap globeMap = new GlobeMap(width, height);
            int preferredN = 0;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    GrassTile tile = (GrassTile) globeMap.at(x, y);
                    assert(tile != null);
                    if (tile.preferred) {
                        preferredN++;
                    }
                }
            }
            assert(Math.abs((double) preferredN / (width * height) - 0.2) <= 1e-3);
        }
    }

    @Test
    public void eatingTest() {
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            int n = random.nextInt(1000);
            GrassTile[] tiles = new GrassTile[n];
            int grassN = 0;
            for (int j = 0; j < n; j++) {
                tiles[j] = new GrassTile(random.nextBoolean());
                if (tiles[j].growGrass(random)) {
                    grassN++;
                }
            }
            for (int j = 0; j < n; j++) {
                if (j < n - 1) {
                    tiles[j].connect(tiles[j + 1], 2);
                }
            }
            Animal animal = new Animal(2);
            tiles[0].place(animal);
            for (int j = 0; j < n - 1; j++) {
                animal.move();
            }
            assert(tiles[n - 1].has(animal));
            assert(animal.getEnergy() == 1 + grassN * 10);
        }
    }
}
