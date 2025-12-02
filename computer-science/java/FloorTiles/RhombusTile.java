package FloorTiles;

import java.util.Objects;

/**
 * Implements the abstract class Tile as a rhombus shaped tile, described by
 * the diagonal1 and diagonal2 sizes.
 */
public class RhombusTile extends Tile {
    /*
     * AF:
     * Implements a rhombus shaped tile, whom area is equals to half of the product
     * of its diagonal2 and diagonal1.
     * 
     * RI:
     * diagonal2 > 0
     * diagonal1 > 0
     * area = diagonal2 * diagonal1 / 2
     */

    /** Height of the rhombus */
    public final int diagonal1;

    /** Base of the rhombus */
    public final int diagonal2;

    /**
     * Creates a rhombusTile described by the cost, the diagonal1 and diagonal2 size.
     * 
     * @param cost   of the tile.
     * @param diagonal2   of the rhombus.
     * @param diagonal1 of the rhombus.
     * @throws IllegalArgumentException if diagonal2 or diagonal1 are non positive.
     */
    public RhombusTile(int cost, int diagonal2, int diagonal1) {
        super(cost);
        if (diagonal2 <= 0 || diagonal1 <= 0)
            throw new IllegalArgumentException("RhombusTile's dimensions must be positive.");
        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;
    }

    @Override
    public int surface() {
        return diagonal1 * diagonal2 / 2;
    }

    @Override
    public int hashCode() {
        return Objects.hash("Rhombus",cost(), diagonal1, diagonal2);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj))
            if (obj instanceof RhombusTile other)
                return other.diagonal1 == diagonal1 && other.diagonal2 == diagonal2;
        return false;
    }

    @Override
    public String toString() {
        return "RhombusTile: cost : {"+cost()+"}, diagonal1 : {"+diagonal1+"}, diagonal2 : {"+diagonal2+"}, surface : {"+surface()+"}";
    }
}
