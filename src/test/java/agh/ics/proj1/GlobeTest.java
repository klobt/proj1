package agh.ics.proj1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class GlobeTest {
    @Test
    public void normalTest() {
        assertGlobeMap(
                new GlobeMap(100, 100),
                new Vector2d[] {
                        new Vector2d(20, 0),
                        new Vector2d(20, 0),
                },
                new int[] {6, 1},
                10,
                new Vector2d[] {
                        new Vector2d(10, 0),
                        new Vector2d(30, 10),
                }
        );
    }

    @Test
    public void poleTest() {
        assertGlobeMap(
                new GlobeMap(100, 100),
                new Vector2d[] {
                       new Vector2d(40, 0),
                       new Vector2d(60, 99),
                },
                new int[] {4, 0},
                20,
                new Vector2d[] {
                        new Vector2d(40, 19),
                        new Vector2d(60, 80),
                }
        );
    }

    @Test
    public void wrapTest() {
        assertGlobeMap(
                new GlobeMap(100, 100),
                new Vector2d[] {
                        new Vector2d(5, 50),
                        new Vector2d(95, 50),
                },
                new int[] {6, 2},
                10,
                new Vector2d[] {
                        new Vector2d(95, 50),
                        new Vector2d(5, 50),
                }
        );
    }

    private void assertGlobeMap(GlobeMap globeMap,
                                Vector2d[] initialPositions,
                                int[] orientations,
                                int steps,
                                Vector2d[] finalPositions) {
        int n = orientations.length;
        Animal[] animals = Arrays.stream(orientations).mapToObj(Animal::new).toArray(Animal[]::new);
        for (int i = 0; i < n; i++) {
            globeMap.at(initialPositions[i]).place(animals[i]);
        }
        for (int i = 0; i < steps; i++) {
            for (int j = 0; j < n; j++) {
                animals[j].move();
            }
        }
        for (int i = 0; i < n; i++) {
            Tile tile = globeMap.at(finalPositions[i]);
            assert(tile.has(animals[i]));
        }
    }
}
