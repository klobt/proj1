package agh.ics.proj1;

import java.util.*;

public class GlobeMap implements Iterable<GrassTile> {
    private final Vector2d bounds;
    private final ArrayList<MapTile> tiles = new ArrayList<>();

    public GlobeMap(Config config) {
        bounds = new Vector2d(config.mapWidth, config.mapHeight);
        for (int x = 0; x < bounds.getIntX(); x++) {
            tiles.add(new PolarTile());
        }
        for (int y = 0; y < bounds.getIntY(); y++) {
            for (int x = 0; x < bounds.getIntX(); x++) {
                tiles.add(new GrassTile(config, false));
            }
        }
        for (int x = 0; x < bounds.getIntX(); x++) {
            tiles.add(new PolarTile());
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
        int preferredN = 0;
        Set<GrassTile> visitedTiles = new HashSet<>();
        Stack<GrassTile> tileStack = new Stack<>();
        GrassTile centerTile = (GrassTile) at(bounds.times(0.5));
        if (centerTile != null) {
            tileStack.push(centerTile);
            while (!tileStack.empty()) {
                GrassTile tile = tileStack.pop();
                tile.preferred = true;
                preferredN++;
                if (preferredN >= bounds.getIntX() * bounds.getIntY() * 0.2) {
                    break;
                }
                visitedTiles.add(tile);
                for (int direction = 0; direction < 8; direction++) {
                    if (tile.getNeighbour(direction) instanceof GrassTile nextTile) {
                        if (!visitedTiles.contains(nextTile)) {
                            tileStack.push(nextTile);
                        }
                    }
                }
            }
        }
    }

    public MapTile at(int x, int y) {
        return tiles.get((y + 1) * bounds.getIntX() + x);
    }

    public MapTile at(Vector2d position) {
        return at(position.getIntX(), position.getIntY());
    }


    @Override
    public Iterator<GrassTile> iterator() {
        return new Iterator<>() {
            int x = 0, y = 0;

            @Override
            public boolean hasNext() {
                return x < bounds.getIntX() || y < bounds.getIntY();
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
