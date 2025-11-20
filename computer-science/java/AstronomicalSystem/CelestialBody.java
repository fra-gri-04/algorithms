package AstronomicalSystem;

import java.util.Objects;

/**
 * A celestial body is characterized by a name and a position,
 * described by a three-dimensional point with integer coordinates; the
 * norm of a three-dimensional point is the sum of the absolute values of its components
 * (also known as the ℓ-1 norm)
 * 
  */
public abstract class CelestialBody {
    /*
    * RI:
    * - name must be non empty
    * 
    * AF:
    * 
    * 
    */
    
    /** Celestial body's name */
    private final String name;
    /** Celestial body's three-dimensional position */
    private Vector3 position;
    /** Celestial body's three-dimensional velocity */
    private Vector3 velocity;

    /* CONSTRUCTORS */

    /**
     * Constructs a celestial body 
     * with position = 0,0,0 and velocity = 0,0,0
     * @param name body's name
     */
    public CelestialBody(String name){
        if(Objects.requireNonNull(name).isEmpty()) throw new IllegalArgumentException(); 
        this.name = name;
        
        this.position = new Vector3();
        this.velocity = new Vector3();
    }
    /**
     * Constructs a celestial body
     * with position = position and velocity = 0,0,0
     * @param name body's name
     * @param position three dimensional position
     */
    public CelestialBody(String name, Vector3 position){
        if(Objects.requireNonNull(name).isEmpty()) throw new IllegalArgumentException(); 
        this.name = name;
        
        this.position = new Vector3(position.x, position.y, position.z);
        this.velocity = new Vector3();
    }
    
    /* METHODS */

    /** Celestial body's ℓ-1 norm, given by the sum of its position's values */
    public int norm(){
        return position.norm();
    }

    /** Celestial body's kineticEnergy, given by the sum of its velocity's values */
    public int kineticEnergy(){
        return velocity.norm();
    }
    
    /** Celestial body's energy, given by the product of norm and kineticEnergy */
    public long energy(){
        return norm() * kineticEnergy();
    }

    public String name(){
        return name;
    }

    /**
     * Setter for position
     * @return position
     */
    public void position(Vector3 position){
        this.position.x = position.x;
        this.position.y = position.y;
        this.position.z = position.z;
    }
    /**
     * Get position
     * @return position
     */
    public Vector3 position(){
        return position;
    }

    /**
     * Setter for velocity;
     * @param velocity 
     */
    public void velocity(Vector3 velocity){
        this.velocity.x = velocity.x;
        this.velocity.y = velocity.y;
        this.velocity.z = velocity.z;
    }
    /**
     * Get velocity
     * @return velocity
     */
    public Vector3 velocity(){
        return velocity;
    }

    @Override
    public String toString() {
        return "CelestialBody, name: "+name+", pos: "+position+", vel: "+velocity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position.hashCode(), velocity.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CelestialBody other)) throw new IllegalArgumentException();
        if (other.name != this.name) return false;

        return hashCode() == other.hashCode();
    }
}
