package it.fragri;

import java.io.File;
import java.lang.reflect.Method;
import java.util.InputMismatchException;
import java.util.Scanner;


class PrettyUI{
    /** * clears terminal and puts cursor to top left. */
    public static void clearTerminal(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void print(String str){
        System.err.print(str);
    }
    public static void println(String str){
        System.err.println(str);
    }
}

class TestAlgorithms{
    /** Private constructor */
    // private TestAlgorithms(){}

    final int N_EXECUTIONS = 100;

    String[] modules;
    String[] module_functions;


    
    private void print_introduction(){
        PrettyUI.print("# Welcome to the testing grounds!\nHere you can test the algorithms currently present in fragri's library!\nOnce you choose a function, this script will execute it "+N_EXECUTIONS+" times, generatig a corresponding random input each time.\nIn each execution, a timer starts counting after the input is generated,\nstopping only after the end of the function's execution end.\nThis script will then calculate the arithmetic mean of the executions time and display the result.");
    }

    /**
     * Provides user interaction to choose the function that the user wants to execute.
     * It points to the function by using a module approach:
     * Module index is on the hundreds digit, function index on the tens and ones digits.
     * example: chose_function = 105 means module 1 and function 05 has been chose.
     */
    private int choose_function(){
        int chose_function;
        try(Scanner sc = new Scanner(System.in)){
            int chose_module;

            // ------- SELECT MODULE -------- // 
            PrettyUI.print("Which module would you like to test?");
            cycle_modules();

            do { 
                chose_module = sc.nextInt();
                if (chose_module < 0) PrettyUI.print("Number read smaller than 0. Type a valid module number.");
                if (chose_module > modules.length) PrettyUI.print("Number read greater than number of modules. Type a valid module number.");
            }while (chose_module < 0 || chose_module >= modules.length);

            // ------- SELECT MODULE FUNCTION -------- // 
            PrettyUI.print("Which function would you like to test?");
            cycle_function_per_module(chose_module);
            do { 
                chose_function = sc.nextInt();
                // checks number input
                if (chose_function < 0) PrettyUI.print("Number read smaller than 0. Type a valid function number.");
                if (chose_function > module_functions.length) PrettyUI.print("Number read greater than number of functions. Type a valid function number.");
            }while (chose_function < 0 || chose_function >= module_functions.length);
            
            chose_function += chose_module * 100;
            return chose_function;
        }catch (InputMismatchException e){
            PrettyUI.println("You have typed a wrong character.");
            return -1;
        }
    }

    /** * print functions relative to selected module. */
    private void cycle_function_per_module(int module){
        int i=0;
        try {
            Class<?> chose_class = Class.forName("it.fragri."+modules[module]);
            Method[] methods = chose_class.getDeclaredMethods();

            // updates global variable
            module_functions = new String[methods.length];

            for (Method m : methods) {
                if (java.lang.reflect.Modifier.isPublic(m.getModifiers())) {

                    // save in the global array the functions in selected module
                    String name = m.getName();
                    module_functions[i] = name;

                    // display module function
                    PrettyUI.print(i+" - "+name);
                    
                    i++;
                }
            }
        } catch (ClassNotFoundException e) {
            PrettyUI.print("error\n"+e);
        }
    }
    
    /** print modules present in the executing directory */
    private void cycle_modules(){
        String path = TestAlgorithms.class.getResource("TestAlgorithms.java").getPath();
        File currentDir = new File(path).getParentFile();
        File[] filesList = currentDir.listFiles();

        int i = 0;

        // intantiates modules array
        modules = new String[filesList.length];

        String current_class_name = TestAlgorithms.class.getName().replace("it.fragri.", "");

        for (File file : filesList) {
            // save in the global array the functions in selected module
            String name = file.getName().replace(".java","");

            if (name.equals(current_class_name)) continue;

            modules[i] = name;

            // display module function
            PrettyUI.print(i+" - " + name);
            i++;
        }
    }

    public void main(String[] args) {
        PrettyUI.clearTerminal();

        print_introduction(); 

        /* Get function index to execute */
        int f_index = -1;
        while (f_index == -1)
            f_index = choose_function();

        PrettyUI.print("Hai scelto il modulo "+f_index/100+" e la funzione "+f_index%100+"\nEseguo funzione "+ module_functions[f_index%100] + ".");
    }
}