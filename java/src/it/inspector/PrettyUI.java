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
        if (ask_str != "") System.out.println("\n" + ask_str);
        printAsKebab(" [Y]es/[N]o ");

        PrettyUI.print("");
        String answer = sc.next();

        if (answer.equals("y") || answer.equals("yes") || answer.equals("Y") || answer.equals("YES")) {
            return true;
        }
        System.out.println();
        return false;
    }

    public static int askInt(Scanner sc){
        int number;
        PrettyUI.print("");
        try{
            number = sc.nextInt();
        }catch (InputMismatchException e){
            PrettyUI.println("You typed a wrong character.");
            sc.nextLine();
            number = -1;
        }
        return number;
    }
}
/* dash => \u2500 */