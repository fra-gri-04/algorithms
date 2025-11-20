package AstronomicalSystem;

/**
 * A planet is a CelestialBody which has a velocity != 0
  */
public class Planet extends CelestialBody {
    /*
    * RI:
    *
    * AF:
    *  
    */

    private final int velocity_offset = 1;

    /* CONSTRUCTORS */

    /**
     * Constructs a planet 
     * with position = 0,0,0 and velocity = 0,0,0
     * @param name body's name
     */
    public Planet(String name){
        super(name);
    }
    /**
     * Constructs a planet
     * with position = position and velocity = 0,0,0
     * @param name body's name
     * @param position three dimensional position
     */
    public Planet(String name, Vector3 position){
        super(name, position);
    }

    /* METHODS */

    /**
     * Update planet's position based on the velocity's values
     */
    public void updatePosition(){
        Vector3 velocity = velocity();
        Vector3 position = position();
        position.x += velocity.x;
        position.y += velocity.y;
        position.z += velocity.z;
    }

    /* Methods to update velocity: */

    /** Increments velocity by the velocity_offset on the x axys. */
    public void incVelocityX(){
        Vector3 v = velocity();
        velocity(new Vector3(v.x + velocity_offset, v.y, v.z));
    }
    /** Increments velocity by the velocity_offset on the y axys. */
    public void incVelocityY(){
        Vector3 v = velocity();
        velocity(new Vector3(v.x, v.y + velocity_offset, v.z));
    }
    /** Increments velocity by the velocity_offset on the z axys. */
    public void incVelocityZ(){
        Vector3 v = velocity();
        velocity(new Vector3(v.x, v.y, v.z + velocity_offset));
    }
    /** Decrements velocity by the velocity_offset on the x axys. */
    public void decVelocityX(){
        Vector3 v = velocity();
        velocity(new Vector3(v.x - velocity_offset, v.y, v.z));
    }
    /** Decrements velocity by the velocity_offset on the y axys. */
    public void decVelocityY(){
        Vector3 v = velocity();
        velocity(new Vector3(v.x, v.y - velocity_offset, v.z));
    }
    /** Decrements velocity by the velocity_offset on the z axys. */
    public void decVelocityZ(){
        Vector3 v = velocity();
        velocity(new Vector3(v.x, v.y, v.z - velocity_offset));
    }
    /* ---- */

    @Override
    public String toString() {
        return "Planet, name: "+name()+", pos: "+position()+", vel: "+velocity();
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Planet other)) throw new IllegalArgumentException();
        if (other.name() != this.name()) return false;

        return hashCode() == other.hashCode();
    }
}
