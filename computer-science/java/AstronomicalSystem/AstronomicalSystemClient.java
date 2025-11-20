package AstronomicalSystem;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Allows verification of the behavior of an astronomical system.
 *
 * <p>For more details, see the <em>overview</em> of this package.
 */
public class AstronomicalSystemClient {

    /** . */
    private AstronomicalSystemClient() {}

    /**
    * Simulates an astronomical system.
    *
    * <p>Reads the information of celestial bodies from standard input, and simulates the system for
    * the number of steps specified as the first argument on the command line; hence it emits on the
    * standard error the state of the system and the total energy.
    *
    * @param args the number of simulation steps.
    */
    public static void main(String[] args) {
        AstronomicalSystem system = new AstronomicalSystem();

        if (args.length < 1)
            throw new NoSuchElementException("No command line argument found.");
        
        int timeUnits = Integer.parseInt(args[0]);
        try (Scanner s = new Scanner(System.in)){
            while (s.hasNext()) {
                // user input
                char pOrS = s.next().charAt(0); // can be P or S
                String name = s.next();
                int x = s.nextInt();
                int y = s.nextInt();
                int z = s.nextInt();
                
                CelestialBody body;
    
                if(pOrS == 'P')
                    body = new Planet(name, new Vector3(x,y,z));
                else if (pOrS == 'S')
                    body = new Star(name, new Vector3(x,y,z));
                else
                    throw new IllegalArgumentException("Unknown command: "+pOrS);
    
                system.addBody(body);
                System.out.println(system);
            }
        }

        /* now calculate the velocities and the positions after `timeUnits` units of time */
        for (int i = 0; i < timeUnits; i++)
            system.updateState();

        System.out.println(system);
    }

}