package agh.ics.proj1;

public class PolarTile extends MapTile {
    @Override
    public void place(Animal animal) {
        animal.turnBack();
        neighbours[animal.getOrientation()].place(animal);
    }
}
