package it.inspector;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/* Pretty print Idea:
* 
* Full terminal screen cleared,
* ------- title ---------
* the dashes are one single line and they occupy the whole terminal space
* Input:
* yes or no like: Select key: [Y]es or [N]o 
*/

class PrettyUI {
    /** * clears terminal and puts cursor to top left. */
    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\n\n\n");
    }

    public static void printTitle() {
        printAsKebab(" TESTING ALGORITHMS ");
    }

    private static int countDashes(String s) {
        int terminal_length = 120;
        return (terminal_length - s.length()) / 2;
    }

    public static void printAsKebab(String s) {
        System.out.print("\u2500".repeat(countDashes(s)));
        System.out.print(s);
        System.out.print("\u2500".repeat(countDashes(s)));
        System.out.println();
    }

    public static void print(String str) {
        System.out.print(" ".repeat(countDashes(str)) + str);
    }

    public static void println(String str) {
        System.out.println(" ".repeat(countDashes(str)) + str);
    }

    public static boolean yes_or_no(String ask_str, Scanner sc) {
        System.out.print("\n" + ask_str + "\n");
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

class TestAlgorithms {
    /** Private constructor */
    // private TestAlgorithms(){}

    final static int N_EXECUTIONS = 100;

    static String[] modules = new String[] {};
    static String[] module_functions = new String[] {};

    private static void print_introduction() {
        PrettyUI.printTitle();
        System.out.println(
                "# Welcome to the testing grounds!\nHere you can test the algorithms currently present in fragri's library!\nOnce you choose a function, this script will execute it "
                        + N_EXECUTIONS
                        + " times, generatig a corresponding random input each time.\nIn each execution, a timer starts counting after the input is generated,\nstopping only after the end of the function's execution end.\nThis script will then calculate the arithmetic mean of the executions time and display the result.");
    }

    /**
     * Provides user interaction to choose the function that the user wants to
     * execute.
     * It points to the function by using a module approach:
     * Module index is on the hundreds digit, function index on the tens and ones
     * digits.
     * example: chose_function = 105 means module 1 and function 05 has been chose.
     */
    private static int choose_function(Scanner sc) {
        int chose_function;
        int chose_module;
        
        do {
            PrettyUI.clearTerminal();
            // --------- SELECT MODULE ---------- //
            PrettyUI.printAsKebab(" Which module would you like to test? ");
            cycle_modules();

            chose_module = PrettyUI.askInt(sc);
            sc.nextLine();

            // checks number input
            if (chose_module < 0 || chose_module >= modules.length){
                PrettyUI.println("Type a valid module number.");
                sc.nextLine();
            }

        } while (chose_module < 0 || chose_module >= modules.length);

        do {
            PrettyUI.clearTerminal();
            // ------- SELECT MODULE FUNCTION -------- //
            PrettyUI.printAsKebab(" Which function would you like to test? ");
            cycle_function_per_module(chose_module);

            chose_function = PrettyUI.askInt(sc);
            sc.nextLine();

            // checks number input
            if (chose_function < 0 || chose_function >= module_functions.length){
                PrettyUI.println("Type a valid function number.");
                sc.nextLine();
            }
        } while (chose_function < 0 || chose_function >= module_functions.length);

        chose_function += chose_module * 100;
        return chose_function;
    }

    /** * print functions relative to selected module. */
    private static void cycle_function_per_module(int module) {
        int i = 0;
        try {
            Class<?> chose_class = Class.forName("it.modules." + modules[module]);
            Method[] methods = chose_class.getDeclaredMethods();

            // updates global variable of module_functions
            module_functions = new String[]{};
            for (Method m : methods) {
                if (java.lang.reflect.Modifier.isPublic(m.getModifiers())) {

                    // save in the global array the functions in selected module
                    String name = m.getName();
                    module_functions = Arrays.copyOf(module_functions, module_functions.length + 1);
                    module_functions[i] = name;

                    // display module function
                    PrettyUI.println(i + " - " + name);

                    i++;
                }
            }
            PrettyUI.printAsKebab("");
        } catch (ClassNotFoundException e) {
            PrettyUI.print("error\n" + e);
        } catch (NoSuchElementException e) {
            PrettyUI.print("TypeError\n" + e);
        }
    }

    /** print modules present in the executing directory */
    private static void cycle_modules() {
        try {
            String path = TestAlgorithms.class.getResource("").getPath().replace("inspector/", "modules/");
            File currentDir = new File(path);
            File[] filesList = currentDir.listFiles();

            int i = 0;

            // initiates modules array
            modules = new String[]{};

            String current_class_name = TestAlgorithms.class.getName().replace("it.modules.", "");

            for (File file : filesList) {
                // save in the global array the functions in selected module
                String name = file.getName().replace(".java", "");

                if (name.equals(current_class_name))
                    continue;

                modules = Arrays.copyOf(modules, modules.length + 1);
                modules[i] = name;

                // display module function
                PrettyUI.println(i + " - " + name);
                i++;
            }
            PrettyUI.printAsKebab("");
        } catch (NoSuchElementException e) {
            PrettyUI.print("error\n" + e);
        }
    }

    private static void exit() {
        PrettyUI.println("Currently closing...\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int f_index;

        PrettyUI.clearTerminal();

        print_introduction();

        if (!PrettyUI.yes_or_no("Should we proceed?", sc)) {
            exit();
            return;
        }

        f_index = choose_function(sc);

        System.out.println("Hai scelto il modulo " + f_index / 100 + " e la funzione " + f_index % 100 +
                "\nEseguo funzione " + module_functions[f_index % 100] + ".");

        switch (module_functions[f_index % 100]) {
            // sorting algorithms
            case "mergeSort", "selectionSort", "insertionSort", "bubbleSort", "mergeSortUsingSpace" -> {

                break;
            }
            // functions that require just one array
            case "linearMin", "recursiveMin" -> {

                break;
            }
            // functions that require two ints
            case "russianPeasant", "repeatedAdditions" -> {

                break;
            }
            // functions that require one array and one int
            case "linearSearch", "binarySearch", "binarySearchRecursive" -> {

                break;
            }
            default -> {
                PrettyUI.println("Error.");
                exit();
            }
        }

    }
}

/* dash => \u2500 */