package FloorTiles;

import java.util.Objects;

/**
 * Abstract class for tiles of different shapes used to cover floors.
 * Implements partially FloorCovering interface, providing a getter method for
 * the cost.
 * All dimensions of the children of this class must be described as centimeters (CM)
 */
public abstract class Tile implements FloorCovering {
    /*
     * AF:
     * Describes the cost as a integer.
     * 
     * RI:
     * cost >= 0
     * 
     */

    /** Cost of the single tile */
    private final int cost;

    /**
     * Creates a new tile setting the cost.
     * 
     * @param cost of the tile.
     * @throws IllegalArgumentException if cost is negative.
     */
    public Tile(int cost) {
        if (cost < 0)
            throw new IllegalArgumentException("Cost must be non negative.");
        this.cost = cost;
    }

    /**
     * Returns the cost of the tile.
     * 
     * @return cost.
     */
    public int cost() {
        return cost;
    }

    /**
     * Return the surface of the single tile.
     * 
     * @return an integer corresponding to the surface of the single tile.
     */
    public abstract int surface();

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        Objects.requireNonNull(obj);
        if (obj instanceof Tile other)
            if (other.cost != cost) return false;
        return true;
    }
}
