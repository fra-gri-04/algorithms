package FloorTiles;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class TilesClient {
    private static final char QUAD = 'Q';
    private static final char TRIANGLE = 'T';
    private static final char RHOMBUS = 'R';
    private static final char PAVEMENT = 'P';

    public static void main(String[] args) {
        List<FloorCovering> createdCovering = new ArrayList<FloorCovering>();

        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNext()) {
                String command = sc.next();

                switch (command.charAt(0)) {
                    case QUAD -> {
                        int side = sc.nextInt();
                        int cost = sc.nextInt();

                        SquareTile squareTile = new SquareTile(cost, side);

                        createdCovering.add(squareTile);
                    }
                    case TRIANGLE -> {
                        int base = sc.nextInt();
                        int height = sc.nextInt();
                        int cost = sc.nextInt();

                        TriangleTile triangleTile = new TriangleTile(cost, base, height);

                        createdCovering.add(triangleTile);
                    }
                    case RHOMBUS -> {
                        int diagonal1 = sc.nextInt();
                        int diagonal2 = sc.nextInt();
                        int cost = sc.nextInt();

                        RhombusTile rhombusTile = new RhombusTile(cost, diagonal1, diagonal2);

                        createdCovering.add(rhombusTile);
                    }

                    case PAVEMENT -> {
                        Pavement pavement = new Pavement();
                        while (sc.hasNextInt()) {
                            // quantity of flooring
                            int quantity = sc.nextInt();
                            // object of flooring
                            int choice = sc.nextInt();
                            FloorCovering chosen = createdCovering.get(choice);
                            while (quantity > 0) {
                                pavement.add(chosen);
                                quantity--;
                            }
                        }
                        if (pavement.components().isEmpty())
                            throw new IllegalArgumentException("Failed Pavement creation.");
                        else
                            createdCovering.add(pavement);
                    }
                    default -> throw new IllegalArgumentException("Unknown command " + command);
                }
            }
            // end of input:
            for (FloorCovering fc : createdCovering)
                if (fc instanceof Pavement)
                    System.out.println(fc.surface() + "\t" + fc.cost());
        }
    }
}
