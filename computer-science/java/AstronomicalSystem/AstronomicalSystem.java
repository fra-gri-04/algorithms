package AstronomicalSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * An astronomical system is a collection of planets and fixed stars. It is
 * characterized by a state that evolves in discrete time.
 */
public class AstronomicalSystem implements Iterable<CelestialBody> {
    private List<CelestialBody> state;

    /**
     * Constructs an empty astronomical system
     */
    public AstronomicalSystem(){
        state = new ArrayList<CelestialBody>();
    }

    /**
     * Adds a CelestialBody to the state in alphabetical order
     * @param body celestialBody to add to the state
     */
    public void addBody(CelestialBody body){
        int stateSize = state.size();
        for (int i = 0; i < stateSize; i++)
            // if state.get(i).name () < body.name()
            if (state.get(i).name().compareTo(body.name()) < 0)
                state.add(i, body);

        state.add(body);
    }

    /**
     * Updates state of system for 1 unit of time.
     * First, each planet modifies its velocity based on the attraction towards all
     * other celestial bodies: given the planet p and the celestial body c,
     * independently for each of the three coordinates, the velocity of p changes by +1 or
     * -1 depending on whether that coordinate is, respectively, less than or greater than that of c;
     * once the new velocity for all planets has been calculated, each planet modifies its
     * position by adding the value of its velocity to that of its position (as if the planet
     * were subject to uniform motion for one unit of time).
     */
    public void updateState(){
        int stateSize = state.size();
        // update velocity of planets
        for (int i = 0; i < stateSize; i++)
            // if it's a planet
            if (state.get(i) instanceof Planet planetA)
                // cycle through the other bodies
                for (int j = 0; j < stateSize; j++)
                    // prevent recalculating velocity updates by excluding bodies already seen
                    // and check if it's a planet again
                    if ((state.get(j) instanceof Planet planetB) && (i < j))
                        // calculate difference in coordinates and update velocity
                        updateVelocities(planetA, planetB);

        // update position of planets
        for (CelestialBody body : state)
            // if it's a planet
            if (body instanceof Planet planet)
                planet.updatePosition();
    }
    /**
     * Updates the velocity of planets a and b based upon their mutual position.
     * @param a first planet
     * @param b second planet
     */
    private void updateVelocities(Planet a, Planet b){
        if (a.position().x < b.position().x){
            a.incVelocityX();
            b.decVelocityX();
        }else if (a.position().x > b.position().x){
            a.decVelocityX();
            b.incVelocityX();
        }
        if (a.position().y < b.position().y){
            a.incVelocityY();
            b.decVelocityY();
        }else if (a.position().y > b.position().y){
            a.decVelocityY();
            b.incVelocityY();
        }
        if (a.position().z < b.position().z){
            a.incVelocityZ();
            b.decVelocityZ();
        }else if (a.position().z > b.position().z){
            a.decVelocityZ();
            b.incVelocityZ();
        }
        
    }

    /**
     * Returns the total energy of the system, given by the sum of the energy of each celestial body.
     * @return
     */
    public long totalEnergy(){
        long total=0;
        for (CelestialBody body : state)
            total += body.energy();
        return total;
    }

    public Iterator<CelestialBody> iterator(){
        return Collections.unmodifiableCollection(state).iterator();
    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof AstronomicalSystem other)) throw new IllegalArgumentException();
        return hashCode() == other.hashCode();
    }

    @Override
    public String toString() {
        String res = "";
        for (CelestialBody body : state) res += body.toString()+"\n";
        
        res += "Total Energy: "+totalEnergy();
        return res;
    }    

}
