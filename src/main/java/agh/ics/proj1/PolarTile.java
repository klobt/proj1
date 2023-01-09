package agh.ics.proj1;

public class PolarTile extends MapTile {
    public PolarTile(Vector2d position) {
        super(position);
    }

    @Override
    public void place(Animal animal) {
        animal.turnBack();
        neighbours[animal.getOrientation()].place(animal);
    }
}
