package AstronomicalSystem;

/**
 * A planet is a {@link CelestialBody} which can have a velocity != 0 and update
 * its position.
 */
public class Planet extends CelestialBody {
    /*
     * RI:
     * name must be non null
     * 
     * AF:
     * the constant offset for a velocity update is VELOCITY_OFFSET
     */

    /** Value of constant offset for the change of velocity */
    private final int VELOCITY_OFFSET = 1;

    /* CONSTRUCTORS */

    /**
     * Constructs a planet
     * with position = 0,0,0 and velocity = 0,0,0
     * 
     * @param name body's name
     */
    public Planet(String name) {
        super(name);
    }

    /**
     * Constructs a planet
     * with position = position and velocity = 0,0,0
     * 
     * @param name     body's name
     * @param position three dimensional position
     */
    public Planet(String name, Vector3 position) {
        super(name, position);
    }

    /* METHODS */
    /**
     * A planet is able to move so it will add the direction * VELOCITY_OFFSET to
     * the current velocity.
     * Due to the fact that the gravitational attraction is constant and based upon
     * the {@link VELOCITY_OFFSET} value,
     * the direction is multiplied by the offset.
     * Then the direction vector with module offset is added to the velocity.
     */
    @Override
    public void updateVelocity(Vector3 direction) {
        direction.multiply(VELOCITY_OFFSET);
        velocity.add(direction);
    }

    /**
     * Update planet's position based on the velocity's values.
     * It will add the velocity values to the position's axys.
     */
    public void updatePosition() {
        position.add(velocity);
    }

    @Override
    public String toString() {
        return "Planet, name: " + name() + ", pos: " + position() + ", vel: " + velocity();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Planet other))
            throw new IllegalArgumentException();
        if (other.name() != this.name())
            return false;

        return hashCode() == other.hashCode();
    }
}
