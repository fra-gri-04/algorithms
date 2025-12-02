package AstronomicalSystem;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Visualizes the celestial bodies present in the system on the x, y axys present in the ranges of 
 * [-X_SIZE/2, X_SIZE/2] and [-Y_SIZE/2, Y_SIZE/2].
 * Due to the terminal bidimensional nature of this representation, only two axys are shown.
 */
public abstract class CelestialVisualizer {
    private static int delay = 200; 

    private static int X_SIZE = 61;
    private static int Y_SIZE = 25;
    
    public static char[][] map;

    private final static int GRID_SIZE = 3;
    
    /** Space character to draw empty void  */
    private final static char space = ' ';
    /** Dot character to draw a grid */
    private final static char dot = '\u00B7';
    /** Border character */
    private final static char border = '#';

    /**
     * Cycles through the astronomical system's state and saves each body position.
     * inserts body at the right position, keeping in mind that 0,0 is in the center of the map
     * to do the division, it ceils at the rounds on the upper side (5 / 2 = 3) so there can be a symmetrical representation of a body present in 0,0
     * A / B => (A + B -1) / B
     * 
     * @param state list of celestial bodies present in the system.
     * @throws IllegalArgumentException if the collection of celestial bodies of the system is empty
     */
    public static void updateMap(List<CelestialBody> state){
        if (state.isEmpty()) throw new IllegalArgumentException("This system does not contain celestial bodies.");

        map = new char[X_SIZE][Y_SIZE];
        for (CelestialBody body : state){
            int bodyX = body.position().x;
            int bodyY = body.position().y;

            int currentX = (X_SIZE + 1) / 2 + bodyX;
            int currentY = (Y_SIZE + 1) / 2 + bodyY;

            if (currentX >= 0 && currentX < X_SIZE)
                if (currentY >= 0 && currentY < Y_SIZE)
                    map[currentX][currentY] = body.name().charAt(0);
        }
    }

    /**
     * Print a rectangle of X_SIZE x Y_SIZE. Called xmax, ymax later
     * Bodies present in the range body.x ∈ [-xmax/2, xmax/2] and body.y ∈ [-ymax/2, ymax/2]
     * The space is represented with character space.
     * Every GRID_SIZE spaces there will a character dot 
     */
    public static void showMap(){       
        String result = "";
        
        result += String.valueOf(border+" ").repeat(X_SIZE+2)+"\n";

        for (int y = 0; y < Y_SIZE; y++){
            for (int x = 0; x < X_SIZE; x++){
                if (x == 0)
                    result += border + " ";
                // Decide wether to have space or the grid dot.
                char thisPos; 
                if ((x % GRID_SIZE == 0) && (y % GRID_SIZE == 0)) thisPos = dot; else thisPos = space;
                if (map[x][y] != 0) thisPos = map[x][y];

                result += thisPos+" ";
                
                if (x == X_SIZE-1)
                    result += border;
            }
            result += '\n';
        }
        result += String.valueOf(border+" ").repeat(X_SIZE+2)+"\n";
        System.out.println(result);        
    }
    /**
     * Visualize the system in a map of X_SIZE x Y_SIZE dimensions
     * @param system to visualize
     * @param timeUnits * delay = total visualization duration
     * @throws NoSuchElementException if system is null
     * @throws IllegalArgumentException if the collection of celestial bodies of the system is empty or the timeUnits are negative
     */
    public static void visualize(AstronomicalSystem system, int timeUnits) throws NoSuchElementException{
        Objects.requireNonNull(system);
        if (system.state().isEmpty()) throw new IllegalArgumentException("This system does not contain celestial bodies.");

        for (int i = 0; i < timeUnits; i++){
            system.updateState();
            CelestialVisualizer.updateMap(system.state());
            clearScreen();
            CelestialVisualizer.showMap();
            try{
                Thread.sleep(delay);
            }catch(InterruptedException e){
                System.out.println("Interrupted the sleep!");
            }
        }
    }

    /** clears the screen */
    private static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Updates map size
     * @param sizeX
     * @param sizeY
     * @throws IllegalArgumentException size must be positive
     */
    public static void size(int sizeX, int sizeY){
        if (sizeX <= 0 || sizeY <= 0)
            throw new IllegalArgumentException("Map size must be positive");
        X_SIZE = sizeX;
        Y_SIZE = sizeY;
    }
    /**
     * 
     * @return length of map
     */
    public int x_size(){
        return X_SIZE;
    }
    /**
     * 
     * @return height of map
     */
    public int y_size(){
        return Y_SIZE;
    }

    /**
     * Sets new map size for x axys
     * @param new_size
     * @throws IllegalArgumentException size must be positive
     */
    public static void x_size(int new_size){
        if (new_size <= 0)
            throw new IllegalArgumentException("Map size must be positive");
        X_SIZE = new_size;
    }
    /**
     * Sets new map size for y axys
     * @param new_size
     * @throws IllegalArgumentException size must be positive
     */
    public static void y_size(int new_size){
        if (new_size <= 0)
            throw new IllegalArgumentException("Map size must be positive");
        Y_SIZE = new_size;
    }

    /**
     * @return delay
     */
    public int delay(){
        return delay;
    }
}