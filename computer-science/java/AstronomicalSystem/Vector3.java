package AstronomicalSystem;

import java.util.Objects;

/**
 * Object with three coordinates
 * 
 */
public class Vector3 {
    /*
    * RI:
    * 
    * AF:
    * Uses ints to describe an axys value
    * The norm is given by the sum of the three values
    * 
    */
    
    
    public int x;
    public int y;
    public int z;

    /** Sets the three values to 0 */
    public Vector3(){
        x = 0;
        y = 0;
        z = 0;
    }

    /** Constructs vector of three dimensions */
    public Vector3(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Calculates the norm of the three coordinates
     * @return x+y+z
     */
    public int norm(){
        return Math.abs(x)+Math.abs(y)+Math.abs(z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y,z);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if(!(obj instanceof Vector3 other)) throw new IllegalArgumentException();

        return this.hashCode() == other.hashCode();
    }

    @Override
    public String toString() {
        return "("+x+", "+y+", "+z+")";
    }
}
