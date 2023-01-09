package agh.ics.proj1;

import java.util.*;

public class GlobeMap implements Iterable<GrassTile> {
    private final Config config;
    private final Vector2d bounds;
    private final ArrayList<MapTile> tiles = new ArrayList<>();

    public GlobeMap(Config config) {
        this.config = config;
        bounds = new Vector2d(config.mapWidth, config.mapHeight);
        for (int x = 0; x < bounds.getIntX(); x++) {
            tiles.add(new PolarTile(new Vector2d(x, -1)));
        }
        for (int y = 0; y < bounds.getIntY(); y++) {
            for (int x = 0; x < bounds.getIntX(); x++) {
                tiles.add(new GrassTile(config, new Vector2d(x, y), false));
            }
        }
        for (int x = 0; x < bounds.getIntX(); x++) {
            tiles.add(new PolarTile(new Vector2d(x, bounds.getIntX())));
        }
        for (int y = 0; y < bounds.getIntY(); y++) {
            for (int x = 0; x < bounds.getIntX(); x++) {
                int left = (x - 1 + bounds.getIntX()) % bounds.getIntX();
                int right = (x + 1 + bounds.getIntX()) % bounds.getIntX();
                int down = y - 1;
                int up = y + 1;
                Vector2d[] positions = {
                        new Vector2d(x, up),
                        new Vector2d(right, up),
                        new Vector2d(right, y),
                        new Vector2d(right, down),
                        new Vector2d(x, down),
                        new Vector2d(left, down),
                        new Vector2d(left, y),
                        new Vector2d(left, up),
                };
                for (int orientation = 0; orientation < 8; orientation++) {
                    at(x, y).connect(at(positions[orientation]), orientation);
                }
            }
        }
        switch (config.mapVariant) {
            case GLOBE -> {
                for (int x = 0; x < bounds.getIntX(); x++) {
                    int left = (x - 1 + bounds.getIntX()) % bounds.getIntX();
                    int right = (x + 1 + bounds.getIntX()) % bounds.getIntX();
                    at(x, -1).connect(at(left, 0), 7);
                    at(x, -1).connect(at(x, 0), 0);
                    at(x, -1).connect(at(right, 0), 1);
                    at(x, bounds.getIntY()).connect(at(left, bounds.getIntY() - 1), 5);
                    at(x, bounds.getIntY()).connect(at(x, bounds.getIntY() - 1), 4);
                    at(x, bounds.getIntY()).connect(at(right, bounds.getIntY() - 1), 3);
                }
            }
            case HELL_BORDER -> {
                HellTile hellTile = new HellTile(this);
                for (int x = 0; x < bounds.getIntX(); x++) {
                    at(x, 0).connect(hellTile, 0);
                    at(x, bounds.getIntY() - 1).connect(hellTile, 4);
                }
                for (int y = 0; y < bounds.getIntY(); y++) {
                    at(0, y).connect(hellTile, 6);
                    at(bounds.getIntX() - 1, y).connect(hellTile, 2);
                }
            }
        }
        switch (config.grassVariant) {
            case GREEN_EQUATOR -> {
                int h = (int) Math.ceil(bounds.y * 0.2);
                int y0 = (int) ((bounds.y - h) / 2);
                int preferredN = 0;
                for (int y = y0; y < y0 + h; y++) {
                    for (int x = 0; x < bounds.getIntX(); x++) {
                        GrassTile tile = (GrassTile) at(x, y);
                        tile.preferred = true;
                        preferredN++;
                        if (preferredN >= (int) (bounds.x * bounds.y * 0.2)) {
                            break;
                        }
                    }
                    if (preferredN >= (int) (bounds.x * bounds.y * 0.2)) {
                        break;
                    }
                }
            }
            case TOXIC_CORPSES -> {
            }
        }
    }

    public MapTile at(int x, int y) {
        return tiles.get((y + 1) * bounds.getIntX() + x);
    }

    public MapTile at(Vector2d position) {
        return at(position.getIntX(), position.getIntY());
    }

    public void randomPlace(Animal animal) {
        int x = config.random.nextInt(bounds.getIntX());
        int y = config.random.nextInt(bounds.getIntY());
        at(x, y).place(animal);
    }

    @Override
    public Iterator<GrassTile> iterator() {
        return new Iterator<>() {
            int x = 0, y = 0;

            @Override
            public boolean hasNext() {
                return y < bounds.getIntY();
            }

            @Override
            public GrassTile next() {
                GrassTile tile = (GrassTile) at(x, y);
                if (x == bounds.getIntX() - 1) {
                    x = 0;
                    y++;
                } else {
                    x++;
                }
                return tile;
            }
        };
    }
}
