package agh.ics.proj1;

public interface Tile {
    void place(Animal animal);
    boolean has(Animal animal);
    void moveForward(Animal animal);
}
