package it.inspector;

import it.modules.*;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

class TestAlgorithms {
    /** Private constructor */
    // private TestAlgorithms(){}

    final static int N_EXECUTIONS = 100;
    final static int N_ELEMENTS = 100;

    static String[] modules = new String[] {};
    static String[] module_functions = new String[] {};

    private static void print_introduction() {
        PrettyUI.printTitle();
        System.out.println(
                "# Welcome to the testing grounds!\nHere you can test the algorithms currently present in fragri's library!\nOnce you choose a function, this script will execute it "
                        + N_EXECUTIONS
                        + " times, generatig a corresponding random input each time.\nIn each execution, a timer starts counting after the input is generated,\nstopping only after the end of the function's execution."
                        +"\nThis script will then calculate the arithmetic mean of the executions time and display the result.");
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

        // choose execution mode

        f_index = choose_function(sc);

        PrettyUI.clearTerminal();
        PrettyUI.printAsKebab(" You selected "+module_functions[f_index % 100]+" ");

        // execute selected function the way you chose
        PrettyUI.println("Would you like to manually fill the data?");
        PrettyUI.println("(this will make the function execute only one time)");
        boolean manualInput = PrettyUI.yes_or_no("", sc);
        long start;
        double duration;
        double durations_mean = 0;

        switch (module_functions[f_index % 100]) {
            // sorting algorithms
            case "mergeSort", "selectionSort", "insertionSort", "bubbleSort", "mergeSortUsingSpace" -> {
                if (manualInput){
                    PrettyUI.println("You selected manual input.");
                    PrettyUI.println("What lenght should the array have? ");
                    int len = PrettyUI.askInt(sc);
                    // input one array
                    int[] array = GenerateRandom.array(len);

                    PrettyUI.println("Generated array: ");
                    // print array:
                    for (int x : array) System.out.print(x+", ");
                    System.out.println();

                    switch(module_functions[f_index % 100]){
                        case "mergeSort" -> {
                            // sort it:
                            start = System.nanoTime();
                            Sorting.mergeSort(array);
                            duration = (double)((System.nanoTime() - start)/1_000_000_000.0); 


                            /*
                                TO DO:
                                currently prints out whole gen array -> truncate print
                                print truncated sorted

                                fix completion print
                            */ 
                            

                            PrettyUI.printAsKebab(" Test Completed! ");
                            PrettyUI.printf("It took: %.7f s. \n", duration);
                            PrettyUI.printAsKebab("\u2500");
                            break;
                        }
                        case "selectionSort" -> {
                            
                            break;
                        }
                        case "insertionSort" -> {
                            
                            break;
                        }
                        case "bubbleSort" -> {
                            
                            break;
                        }
                        case "mergeSortUsingSpace" -> {
                            
                            break;
                        }
                    }                 
                }else{
                    // execute N_EXECUTIONS times with random values, and then do a mean of the durations
                    
                    for (int i=0; i < N_EXECUTIONS; i++){
                        int[] array = GenerateRandom.array(N_ELEMENTS);

                        switch(module_functions[f_index % 100]){
                            case "mergeSort" -> {
                                start = System.nanoTime();
                                Sorting.mergeSort(array);
                                duration = (double)((System.nanoTime() - start)/1_000_000_000.0);

                                durations_mean += duration;
                                break;
                            }
                            case "selectionSort" -> {
                                start = System.nanoTime();
                                Sorting.selectionSort(array);
                                duration = (double)((System.nanoTime() - start)/1_000_000_000.0);

                                durations_mean += duration;
                                break;
                            }
                            case "insertionSort" -> {
                                start = System.nanoTime();
                                Sorting.insertionSort(array);
                                duration = (double)((System.nanoTime() - start)/1_000_000_000.0);

                                durations_mean += duration;
                                break;
                            }
                            case "bubbleSort" -> {
                                start = System.nanoTime();
                                Sorting.bubbleSort(array);
                                duration = (double)((System.nanoTime() - start)/1_000_000_000.0);

                                durations_mean += duration;
                                break;
                            }
                            case "mergeSortUsingSpace" -> {
                                start = System.nanoTime();
                                Sorting.mergeSortUsingSpace(array);
                                duration = (double)((System.nanoTime() - start)/1_000_000_000.0);

                                durations_mean += duration;
                                break;
                            }
                        }
                    }
                    durations_mean = durations_mean / N_EXECUTIONS; 
                    // print result:
                    PrettyUI.clearTerminal();
                    PrettyUI.printAsKebab(" Test Completed! ");
                    PrettyUI.println(module_functions[f_index % 100]+" was executed "+N_EXECUTIONS+" times with arrays of length "+N_ELEMENTS);
                    PrettyUI.printf("On average, it took: %.7f s. \n", durations_mean);
                    PrettyUI.printAsKebab("\u2500");
                }

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
            // functions that require two arrays
            case "matMultiply", "strassenMatMultiply" ->{
                
                break;
            }
            default -> {
                PrettyUI.println("Error.");
                exit();
            }
        }

    }
}

/* 
 * measure time
 * long start = System.nanoTime();
 * // function
 * duration = (double)((System.nanoTime() - start)/1_000_000_000.0);
 */