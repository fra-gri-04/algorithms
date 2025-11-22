package AstronomicalSystem;

/**
 * A star is a fixed in space {@link CelestialBody}, which cannot change its
 * velocity or position.
 */
public class Star extends CelestialBody {

    /* CONSTRUCTORS */

    /**
     * Constructs a star
     * with position = 0,0,0 and velocity = 0,0,0
     * 
     * @param name body's name
     */
    public Star(String name) {
        super(name);
    }

    /**
     * Constructs a star
     * with position = position and velocity = 0,0,0
     * 
     * @param name     body's name
     * @param position three dimensional position
     */
    public Star(String name, Vector3 position) {
        super(name, position);
    }

    /* METHODS */
    /**
     * A star cannot change its position, so it can't update its velocity. It has to
     * remain equal to 0.
     */
    @Override
    public void updateVelocity(Vector3 direction) {
        // adds 0.
        // velocity.add(new Vector3());
    }

    @Override
    public String toString() {
        return "Star, name: " + name() + ", pos: " + position();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Star other))
            throw new IllegalArgumentException();
        if (other.name() != this.name())
            return false;

        return hashCode() == other.hashCode();
    }

}
