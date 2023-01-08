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
            Config config = new Config(width, height, 0, 0, 0, 0, 0);
            GlobeMap globeMap = new GlobeMap(config);
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
            assert(Math.abs(preferredN - (width * height) * 0.2) <= 1);
        }
    }

    @Test
    public void eatingTest() {
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            int grassEnergy = random.nextInt(100);
            int initialEnergy = random.nextInt(100);
            int n = random.nextInt(1000);
            Config config = new Config(random, 0, 0, grassEnergy, initialEnergy, 0, 1, 0);
            GrassTile[] tiles = new GrassTile[n];
            int grassN = 0;
            for (int j = 0; j < n; j++) {
                tiles[j] = new GrassTile(config, random.nextBoolean());
                if (tiles[j].growGrass()) {
                    grassN++;
                }
            }
            for (int j = 0; j < n; j++) {
                if (j < n - 1) {
                    tiles[j].connect(tiles[j + 1], 2);
                }
            }
            Animal animal = new Animal(config, 2);
            tiles[0].place(animal);
            tiles[0].feed();
            for (int j = 0; j < n - 1; j++) {
                animal.move();
                tiles[j + 1].feed();
            }
            assert(tiles[n - 1].has(animal));
            assert(animal.getEnergy() == initialEnergy + grassN * grassEnergy);
        }
    }

    @Test
    public void peckingOrderTest() {
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int grassEnergy = random.nextInt(100);
            int initialEnergy = random.nextInt(100);
            int energyCost = random.nextInt(100);
            Config config = new Config(random, 0, 0, grassEnergy, initialEnergy, energyCost, 1, 0);

            int energyHi = random.nextInt(100) + 20;
            int energyLo = random.nextInt(20);
            int ageHi = random.nextInt(100) + 20;
            int ageLo = random.nextInt(20);
            int childrenHi = random.nextInt(100);

            {
                GrassTile tile = new GrassTile(config, true);

                Animal lowEnergyYoungNoChildren = new Animal(config, 0);
                lowEnergyYoungNoChildren.setStats(energyLo, 0, ageLo);
                tile.place(lowEnergyYoungNoChildren);

                while (!tile.growGrass());
                tile.feed();

                assert (lowEnergyYoungNoChildren.getEnergy() == energyLo + grassEnergy);
            }
            {
                GrassTile tile = new GrassTile(config, true);

                Animal lowEnergyYoungNoChildren = new Animal(config, 0);
                lowEnergyYoungNoChildren.setStats(energyLo, 0, ageLo);
                tile.place(lowEnergyYoungNoChildren);

                Animal highEnergyYoungNoChildren = new Animal(config, 0);
                highEnergyYoungNoChildren.setStats(energyHi, 0, ageLo);
                tile.place(highEnergyYoungNoChildren);

                while (!tile.growGrass());
                tile.feed();

                assert (highEnergyYoungNoChildren.getEnergy() == energyHi + grassEnergy);
            }
            {
                GrassTile tile = new GrassTile(config, true);

                Animal lowEnergyYoungNoChildren = new Animal(config, 0);
                lowEnergyYoungNoChildren.setStats(energyLo, 0, ageLo);
                tile.place(lowEnergyYoungNoChildren);

                Animal highEnergyYoungNoChildren = new Animal(config, 0);
                highEnergyYoungNoChildren.setStats(energyHi, 0, ageLo);
                tile.place(highEnergyYoungNoChildren);

                Animal highEnergyOldNoChildren = new Animal(config, 0);
                highEnergyOldNoChildren.setStats(energyHi, 0, ageHi);
                tile.place(highEnergyOldNoChildren);

                while (!tile.growGrass());
                tile.feed();

                assert (highEnergyOldNoChildren.getEnergy() == energyHi + grassEnergy);
            }
            {
                GrassTile tile = new GrassTile(config, true);

                Animal lowEnergyYoungNoChildren = new Animal(config, 0);
                lowEnergyYoungNoChildren.setStats(energyLo, 0, ageLo);
                tile.place(lowEnergyYoungNoChildren);

                Animal highEnergyYoungNoChildren = new Animal(config, 0);
                highEnergyYoungNoChildren.setStats(energyHi, 0, ageLo);
                tile.place(highEnergyYoungNoChildren);

                Animal highEnergyOldNoChildren = new Animal(config, 0);
                highEnergyOldNoChildren.setStats(energyHi, 0, ageHi);
                tile.place(highEnergyOldNoChildren);

                Animal highEnergyOldManyChildren = new Animal(config, 0);
                highEnergyOldManyChildren.setStats(energyHi, childrenHi, ageHi);
                tile.place(highEnergyOldManyChildren);

                while (!tile.growGrass());
                tile.feed();

                assert (highEnergyOldManyChildren.getEnergy() == energyHi + grassEnergy);
            }
        }
    }
}
