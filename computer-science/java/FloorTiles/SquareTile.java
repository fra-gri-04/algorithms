package FloorTiles;

import java.util.Objects;

/**
 * Implements the abstract class Tile as a square shaped tile, described by the
 * side size.
 */
public class SquareTile extends Tile {
    /*
     * AF:
     * Implements a square shaped tile, whom area is equals to the side squared
     * 
     * RI:
     * side > 0
     * area = side*side
     * 
     */

    /** Side of the square */
    public final int side;

    /**
     * Creates a SquareTile described by the cost and the side size.
     * 
     * @param cost of the tile.
     * @param side of the square.
     * @throws IllegalArgumentException if side is non positive.
     */
    public SquareTile(int cost, int side) {
        super(cost);
        if (side <= 0)
            throw new IllegalArgumentException("SquareTile's side must be positive.");
        this.side = side;
    }

    @Override
    public int surface() {
        return side * side;
    }

    @Override
    public int hashCode() {
        return Objects.hash("Square",cost(), side);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj))
            if (obj instanceof SquareTile other)
                return other.side == side;
        return false;
    }

    @Override
    public String toString() {
        return "SquareTile: cost : {"+cost()+"}, side : {"+side+"}, surface : {"+surface()+"}";
    }
}
