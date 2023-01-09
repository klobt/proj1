package agh.ics.proj1;

public class MapTile implements Tile {
    protected final Vector2d position;
    protected final Tile[] neighbours = new Tile[8];

    public void connect(Tile neighbour, int direction) {
        neighbours[direction] = neighbour;
    }

    public Tile getNeighbour(int direction) {
        return neighbours[direction];
    }

    public MapTile(Vector2d position) {
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public void place(Animal animal) {
    }

    @Override
    public boolean has(Animal animal) {
        return false;
    }

    @Override
    public void moveForward(Animal animal) {
    }
}
