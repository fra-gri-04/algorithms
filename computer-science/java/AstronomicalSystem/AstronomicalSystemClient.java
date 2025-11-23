package AstronomicalSystem;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Allows verification of the behavior of an astronomical system.
 *
 * <p>For more details, see the <em>overview</em> of this package.
 */
public abstract class AstronomicalSystemClient {
    private final static String verbose_option = "-v";

    /** . */
    private AstronomicalSystemClient(){}
    
    /**
    * Simulates an astronomical system.
    *
    * <p>Reads the information of celestial bodies from standard input, and simulates the system for
    * the number of steps specified as the first argument on the command line; hence it emits on the
    * standard error the state of the system and the total energy.
    *
    * Put '-v' as first argument on the command line to visualize the system.
    * @param args the number of simulation steps.
    */
    public static void main(String[] args) {
        AstronomicalSystem system = new AstronomicalSystem();
        int timeUnits = 0;
        String option = "";
        if (args.length != 0){
            option = args[0];
            if (!(option.equals(verbose_option))) timeUnits = Integer.parseInt(option);
        }else throw new NoSuchElementException("No number specified in command line");

        // input:
        try (Scanner s = new Scanner(System.in)){
            
            if (timeUnits == 0){
                
            }
            
            if (option.equals(verbose_option)){
                System.out.println("Astronomical System Input:");
                System.out.print("How many time steps should this system simulate? ");
                timeUnits = s.nextInt();
                if (timeUnits <= 0) throw new IllegalArgumentException("Time steps must be positive.");
                System.out.println("The accepted format for a command is:\n<body_type> <name> <x> <y> <z>\nAccepted body types: Planet [P], Star [S]\nAn example to place the Sun at position 0 0 0 would be:\nS Sun 0 0 0\nAdd celestial bodies, then ctrl+z to start:");
            }
            while (s.hasNext()) {
                // user input
                char pOrS = s.next().charAt(0); // can be P or S
                String name = s.next();
                int x = s.nextInt();
                int y = s.nextInt();
                int z = s.nextInt();
                   
                switch (pOrS) {
                    case 'P', 'p' -> {
                        system.addBody(new Planet(name, new Vector3(x,y,z)));
                    }
                    case 'S', 's' -> {
                        system.addBody(new Star(name, new Vector3(x,y,z)));
                    }
                    default -> throw new IllegalArgumentException("Unknown command: "+pOrS);
                }    
            }
        }

        if (option.equals(verbose_option)){
            CelestialVisualizer.visualize(system, timeUnits);
        }else{
            /* now calculate the velocities and the positions after `timeUnits` units of time */
            for (int i = 0; i < timeUnits; i++)
                system.updateState();
            System.out.println(system);
            
        }
    } 
}