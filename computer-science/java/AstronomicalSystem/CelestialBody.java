package AstronomicalSystem;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A celestial body is characterized by a name and a position,
 * described by a three-dimensional point with integer coordinates; the
 * norm of a three-dimensional point is the sum of the absolute values of its components
 * (also known as the ℓ-1 norm). 
 */
public abstract class CelestialBody {
    /*
    * RI:
    * - name must be non empty
    * 
    * AF:
    * name described by a string
    * position and velocity are three dimensional, in order to describe the three axys, two vector3 are used.
    * 
    */
    
    /** Celestial body's name */
    private final String name;
    /** Celestial body's three-dimensional position */
    protected Vector3 position;
    /** Celestial body's three-dimensional velocity */
    protected Vector3 velocity;

    /* CONSTRUCTORS */

    /**
     * Constructs a celestial body 
     * with position = 0,0,0 and velocity = 0,0,0
     * @param name body's name
     * @throws IllegalArgumentException if name is empty
     */
    public CelestialBody(String name){
        if(Objects.requireNonNull(name).isEmpty()) throw new IllegalArgumentException("Name must be non empty"); 
        this.name = name;
        
        this.position = new Vector3();
        this.velocity = new Vector3();
    }
    /**
     * Constructs a celestial body
     * with position = position and velocity = 0,0,0
     * @param name body's name
     * @param position three dimensional position
     * @throws IllegalArgumentException if name is empty
     * @throws NoSuchElementException if position is null
     */
    public CelestialBody(String name, Vector3 position) throws NoSuchElementException{
        if(Objects.requireNonNull(name).isEmpty()) throw new IllegalArgumentException("Name must be non empty"); 
        Objects.requireNonNull(position,"Position must be non null");
        this.name = name;
        
        this.position = new Vector3(position.x, position.y, position.z);
        this.velocity = new Vector3();
    }

    // GETTERS:
    /**
     * Get position
     * @return position
     */
    public Vector3 position(){
        return position;
    }
    
    /**
     * Get velocity
     * @return velocity
     */
    public Vector3 velocity(){
        return velocity;
    }
    
    // METHODS
    /**
     * Calculates the direction of attraction of this body between its position and the position of a bodyB, 
     * by compairing each coordinate in order to attract this body to bodyB
     * So for example with:
     * this.position: {5, 0, 2} and bodyB.position: {1, 0, 4}
     * booleanDifference returns {-1, 0, 1} because 
     * 5 < 2? false -> -1, 
     * 0 < 0? 0 -> 0,
     * 2 < 4? true -> 1.
     * So this means this body should add the resulting vector in order to move towards the given position
     * Note: 
     * this is just a direction calculation. In order to have a rule of proper gravitational attraction, more data would be needed.
     * 
     * In order to have a 1 if x < y, Integer.signum is used. It returns
     * 1 if x > 0 
     * 0 if x == 0  
     * -1 if x < 0
     * So by calculating the Integer.signum of y - x is equal to have x < y.
     * 
     * @param position of a celestial body B
     * @return the direction of the velocity positionA should follow
     * @throws NoSuchElementException if position is null
     */
    public Vector3 booleanDifference(Vector3 position) throws NoSuchElementException{
        Objects.requireNonNull(position);
        return new Vector3(
            Integer.signum(position.x -this.position.x), 
            Integer.signum(position.y -this.position.y), 
            Integer.signum(position.z -this.position.z) 
        );
    }

    /**
     * Updates the velocity of two bodies by calculating their {@link booleanDifference} and updating bodyA's velocity 
     * using its result and to bodyB's velocity using -result.
     * Note: 
     * this is just a simplified calculation of attraction. In order to have a proper rule of gravitational attraction, more data would be needed.
     * @param bodyA a celestial body attracted to body B
     * @param bodyB a celestial body attracted to body A
     * @throws NoSuchElementException if bodyA or bodyB is null
     */
    public static void applyAttraction(CelestialBody bodyA, CelestialBody bodyB) throws NoSuchElementException{
        Objects.requireNonNull(bodyA, "Bodies must be non null");
        Objects.requireNonNull(bodyB, "Bodies must be non null");

        Vector3 direction = bodyA.booleanDifference(bodyB.position());

        bodyA.updateVelocity(direction);
        bodyB.updateVelocity(new Vector3(-direction.x, -direction.y, -direction.z));
    }

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
     * Describes how the velocity of the celestial body is updated based upon the given direction.
     * @param direction of the velocity.
     */
    public abstract void updateVelocity(Vector3 direction);


    /* @Override
    public String toString() {
        return "CelestialBody, name: "+name+", pos: "+position+", vel: "+velocity;
    } */

    @Override
    public int hashCode() {
        return Objects.hash(name, position.hashCode(), velocity.hashCode());
    }
}
