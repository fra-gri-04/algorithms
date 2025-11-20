package it.benchmarking.inspector;

import java.util.Random;

/**
 * Provides methods to generate random content for arrays and numbers variables. 
*/
public class GenerateRandom {

    /* Top limit of a generated int */
    private static int MAX_GEN_INT = 10000;

    /**
     * Generate an array of length n filled with random ints in range [0,MAX_GEN_INT)
     * @param n size of the array
     * @return an array of n random ints.
     */
    public static int[] array(int n){
        Random r = new Random();
        int[] a = new int[n];

        for (int i=0; i<a.length; i++){
            a[i] = r.nextInt(MAX_GEN_INT);
        }
        
        return a;
    }

    /**
     * Generate a single random int
     * @return random int in range [0, MAX_GEN_INT]
     */
    public static int integer(){
        Random r = new Random();
        return r.nextInt(MAX_GEN_INT);
    }


    public static void setMax(int max){
        if (max <= 0) throw new IllegalArgumentException();

        MAX_GEN_INT = max;
    }

    public static int getMax(){
        return MAX_GEN_INT;
    }
}
