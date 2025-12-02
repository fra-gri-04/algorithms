package FloorTiles;

/**
 * Describes an element which covers the floor, by using surface and cost.
 */
public interface FloorCovering {
    /**
     * Return the surface value of the covering based upon the floor shape
     * 
     * @return an integer correspoding to the area of the covering
     */
    public int surface();

    /**
     * Return the total cost of the covering
     * 
     * @return an integer corresponding to the cost of the covering
     */
    public int cost();
}
