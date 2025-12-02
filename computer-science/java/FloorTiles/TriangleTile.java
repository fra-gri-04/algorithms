package FloorTiles;

import java.util.Objects;

/**
 * Implements the abstract class Tile as a triangle shaped tile, described by
 * the height and base sizes.
 */
public class TriangleTile extends Tile {
    /*
     * AF:
     * Implements a triangle shaped tile, whom area is equals to half of the product
     * of its base and height.
     * 
     * RI:
     * base > 0
     * height > 0
     * area = base * height / 2
     */

    /** Height of the triangle */
    public final int height;

    /** Base of the triangle */
    public final int base;

    /**
     * Creates a triangleTile described by the cost, the height and base size.
     * 
     * @param cost   of the tile.
     * @param base   of the triangle.
     * @param height of the triangle.
     * @throws IllegalArgumentException if base or height are non positive.
     */
    public TriangleTile(int cost, int base, int height) {
        super(cost);
        if (base <= 0 || height <= 0)
            throw new IllegalArgumentException("TriangleTile's dimensions must be positive.");
        this.height = height;
        this.base = base;
    }

    @Override
    public int surface() {
        return height * base / 2;
    }

    @Override
    public int hashCode() {
        return Objects.hash("Triangle",cost(), base, height);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj))
            if (obj instanceof TriangleTile other)
                return other.base == base && other.height == height;
        return false;
    }

    @Override
    public String toString() {
        return "TriangleTile: cost : {"+cost()+"}, base : {"+base+"}, height : {"+height+"}, surface : {"+surface()+"}";
    }

}
