package agh.ics.proj1;

public class MapTile implements Tile {
    protected final Tile[] neighbours = new Tile[8];

    public void connect(Tile neighbour, int direction) {
        neighbours[direction] = neighbour;
    }

    public Tile getNeighbour(int direction) {
        return neighbours[direction];
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
