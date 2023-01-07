package agh.ics.proj1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class GlobeMap {
    private final Vector2d bounds;
    private final ArrayList<MapTile> tiles = new ArrayList<>();

    public GlobeMap(int width, int height) {
        bounds = new Vector2d(width, height);
        for (int x = 0; x < width; x++) {
            tiles.add(new PolarTile());
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles.add(new GrassTile(false));
            }
        }
        for (int x = 0; x < width; x++) {
            tiles.add(new PolarTile());
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int left = (x - 1 + width) % width;
                int right = (x + 1 + width) % width;
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
        for (int x = 0; x < width; x++) {
            int left = (x - 1 + width) % width;
            int right = (x + 1 + width) % width;
            at(x, -1).connect(at(left, 0), 7);
            at(x, -1).connect(at(x, 0), 0);
            at(x, -1).connect(at(right, 0), 1);
            at(x, height).connect(at(left, height - 1), 5);
            at(x, height).connect(at(x, height - 1), 4);
            at(x, height).connect(at(right, height - 1), 3);
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
                if (preferredN >= width * height * 0.2) {
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
}
