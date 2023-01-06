package agh.ics.proj1;

import java.util.ArrayList;

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
                tiles.add(new SquareTile());
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
    }

    public MapTile at(int x, int y) {
        return tiles.get((y + 1) * bounds.x + x);
    }

    public MapTile at(Vector2d position) {
        return at(position.x, position.y);
    }
}
