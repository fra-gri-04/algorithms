package it.inspector;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Provides useful methods to print in a prettier way
*/
class PrettyUI {
    /** * clears terminal and puts cursor to top left. */
    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println();
    }

    public static void printTitle() {
        printAsKebab(" TESTING ALGORITHMS ");
    }

    private static int countDashes(String s) {
        int terminal_length = 120;
        return (terminal_length - s.length()) / 2;
    }

    public static void printAsKebab(String s) {
        System.out.println();
        System.out.print("\u2500".repeat(countDashes(s)));
        System.out.print(s);
        System.out.println("\u2500".repeat(countDashes(s)));
        System.out.println();
    }

    public static void print(String str) {
        System.out.print(" ".repeat(countDashes(str)) + str);
    }

    public static void println(String str) {
        System.out.println(" ".repeat(countDashes(str)) + str);
    }
    public static void printf(String f, Object... args) {
        String str = String.format(f, args);
        System.out.print(" ".repeat(countDashes(str)));
        System.out.printf(str);
    }

    public static boolean yes_or_no(String ask_str, Scanner sc) {
        if (ask_str.equals("")) println("\n" + ask_str);
        printAsKebab(" [Y]es/[N]o ");

        print("");
        String answer = sc.next();

        if (answer.equals("y") || answer.equals("yes") || answer.equals("Y") || answer.equals("YES")) {
            return true;
        }
        System.out.println();
        return false;
    }

    public static int askInt(Scanner sc){
        int number;
        print("");
        try{
            number = sc.nextInt();
        }catch (InputMismatchException e){
            println("You typed a wrong character.");
            sc.nextLine();
            number = -1;
        }
        return number;
    }

    public static void printArray(int[] a, String s){
        int stop = 10;
        print_array(a,s, stop);
    }
    public static void printArray(int[] a, String s, int stop){
        print_array(a,s, stop);
    }
    private static void print_array(int[] a, String s, int stop){
        if (!s.equals("")) println(s);
        // print array:
        int i;
        System.out.print("[");
        for (i=0; i < a.length && i < stop; i++) 
            if (i < a.length -1) System.out.print(a[i]+", ");
            else System.out.print(a[i]);

        if (i < a.length) System.out.print("...");
        
        System.out.print("]");
        System.out.println();
    }

}
/* dash => \u2500 */