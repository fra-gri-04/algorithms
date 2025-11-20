package AstronomicalSystem;

/**
 * A star is a CelestialBody which has a velocity != 0
  */
public class Star extends CelestialBody {

    /* CONSTRUCTORS */

    /**
     * Constructs a star 
     * with position = 0,0,0 and velocity = 0,0,0
     * @param name body's name
     */
    public Star(String name){
        super(name);
    }
    /**
     * Constructs a star
     * with position = position and velocity = 0,0,0
     * @param name body's name
     * @param position three dimensional position
     */
    public Star(String name, Vector3 position){
        super(name, position);
    }

    /* METHODS */

    // block the setters
    @Override
    public void velocity(Vector3 velocity) {
        throw new IllegalArgumentException("You can't update a star's velocity");
    }
    @Override
    public void position(Vector3 position) {
        throw new IllegalArgumentException("You can't update a star's position");
    }


    @Override
    public String toString() {
        return "Star, name: "+name()+", pos: "+position()+", vel: "+velocity();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Star other)) throw new IllegalArgumentException();
        if (other.name() != this.name()) return false;

        return hashCode() == other.hashCode();
    }
}
