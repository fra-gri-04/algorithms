package AstronomicalSystem;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Tuple of three coordinates
 */
public class Vector3 {
    /*
     * RI:
     * 
     * AF:
     * Uses ints to describe an axys value
     * The norm is given by the sum of absolute of the three values
     * 
     */

    public int x;
    public int y;
    public int z;

    /** Sets the three values to 0 */
    public Vector3() {
        x = 0;
        y = 0;
        z = 0;
    }

    /** Constructs vector of three dimensions */
    public Vector3(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Calculates the norm of the three coordinates
     * 
     * @return x+y+z
     */
    public int norm() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z);
    }

    /**
     * Sums a vector3 to this vector3 by summing the respective coordinates.
     * 
     * @param other the vector3 to add
     * @throws NoSuchElementException if other is null
     */
    public void add(Vector3 other) throws NoSuchElementException {
        Objects.requireNonNull(other);
        x += other.x;
        y += other.y;
        z += other.z;
    }

    /**
     * Sums two vector3s by summing the respective coordinates.
     * 
     * @param first  to add
     * @param second to add
     * @return the sum of the two
     * @throws NoSuchElementException if first or second is null
     */
    public Vector3 add(Vector3 first, Vector3 second) throws NoSuchElementException {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        return new Vector3(
                first.x + second.x,
                first.y + second.y,
                first.z + second.z);
    }

    /**
     * Multiplies each coordinate by a scalar number
     * 
     * @param multiplier multiplier of each coordinate
     * @return vector3 with each coordinate multiplied by multiplier
     */
    public Vector3 multiply(int multiplier) {
        int new_x = x * multiplier;
        int new_y = y * multiplier;
        int new_z = z * multiplier;
        return new Vector3(new_x, new_y, new_z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Vector3 other))
            throw new IllegalArgumentException();

        return this.hashCode() == other.hashCode();
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
