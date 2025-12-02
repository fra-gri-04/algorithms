package FloorTiles;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Implements FloorCovering using a list of tiles.
 * You can add to the pavement single tiles or entire other pavements.
 */
public class Pavement implements FloorCovering {
    /**
     * AF:
     * List of tiles (Tile) expandable with other Pavements or single tiles
     * 
     * RI:
     * tiles must be non empty
     */

    /** List of tiles composing the pavement. */
    List<FloorCovering> components;

    /**
     * Creates an empty Pavement
     */
    public Pavement() {
        components = new ArrayList<FloorCovering>();
    }

    /**
     * Creates a Pavement using a non empty list of flooring components.
     * 
     * @param tiles composing the pavement.
     * @throws IllegalArgumentException if tiles' list is empty
     * @throws NoSuchElementException   if one of the tiles inside the list is null
     */
    public Pavement(List<FloorCovering> tiles) throws NoSuchElementException {
        if (tiles.isEmpty())
            throw new IllegalArgumentException("tiles list must be non empty.");
        for (FloorCovering t : tiles)
            Objects.requireNonNull(t, "All tiles must be non null");
        this.components = List.copyOf(tiles);
    }

    /**
     * Adds an instance of a FloorCovering implementation to this pavement
     * 
     * @param covering to add
     * @throws NoSuchElementException if covering is null
     */
    public void add(FloorCovering covering) throws NoSuchElementException {
        Objects.requireNonNull(covering, "Covering must be non null");

        this.components.add(covering);
    }

    /**
     * Adds N instances of FloorCovering implementation to this pavement
     * 
     * @param covering to add
     * @param N        number of elements to add
     * @throws NoSuchElementException if covering is null
     */
    public void add(FloorCovering covering, int N) throws NoSuchElementException {
        Objects.requireNonNull(covering, "Covering must be non null");

        while (N > 0) {
            components.add(covering);
            N--;
        }
    }

    /**
     * Getter for the pavement's components
     * 
     * @return components composing the entire pavement
     */
    public List<FloorCovering> components() {
        return components;
    }

    @Override
    public int cost() {
        int cost = 0;
        for (FloorCovering t : components)
            cost += t.cost();
        return cost;
    }

    @Override
    public int surface() {
        int surface = 0;
        for (FloorCovering t : components)
            surface += t.surface();
        return surface;
    }

    @Override
    public int hashCode() {
        return components.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof Pavement other)
            return components.equals(other.components());
        return false;
    }

    @Override
    public String toString() {
        return "Pavement: cost : {" + cost() + "}, surface : {" + surface() + "}";
    }
}
