package it.fragri;

import java.lang.reflect.Method;
import java.util.Scanner;

class TestAlgorithms{
    
    final int N_EXECUTIONS = 100;
    final String[] MODULES = {"Multiplications","Searches","Sorting","Matrices"};

    /** * clears terminal and puts cursor to top left. */
    public void clearTerminal(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void print_introduction(){
        System.out.printf("# Welcome to the testing grounds!\nHere you can test the algorithms currently present in fragri's library!\nOnce you choose a function, this script will execute it %d times, generatig a corresponding random input each time.\nIn each execution, a timer starts counting after the input is generated, stopping only after the end of the function's execution end.\nThis script will then calculate the arithmetic mean of the executions time and display the result.",N_EXECUTIONS);
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

            System.out.println("Which module would you like to test?");
            // print modules names with side index.
            for (int i=0; i<MODULES.length; i++) System.out.println(i+" - "+MODULES[i]);

            do { 
                chose_module = sc.nextInt();
                if (chose_module < 0) System.out.println("Number read smaller than 0. Type a valid module number.");
                if (chose_module > MODULES.length) System.out.println("Number read greater than number of modules. Type a valid module number.");
            }while (chose_module < 0 || chose_module > MODULES.length);

            System.out.println("Which function would you like to test?");
            print_function_per_module(chose_module);
            do { 
                chose_function = sc.nextInt();
                chose_module = sc.nextInt();
                if (chose_module < 0) System.out.println("Number read smaller than 0. Type a valid function number.");
                if (chose_module > MODULES.length) System.out.println("Number read greater than number of functions. Type a valid function number.");
            }while (chose_function < 0 || chose_function > MODULES.length);
            
            chose_function += chose_module*100;
        }
        return chose_function;
    }

    // print functions relative to selected module.                         work in progress
    private void print_function_per_module(int module){
        int i=0;
        try {
            Class<?> chose_class = Class.forName("it.fragri."+MODULES[module]);
            Method[] metodi = chose_class.getDeclaredMethods();
            for (Method metodo : metodi) {
                if (java.lang.reflect.Modifier.isPublic(metodo.getModifiers())) {
                    System.out.println(i+" - "+metodo.getName());
                    i++;
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("error\n"+e);
        }
    }
    public void main(String[] args) {
        clearTerminal();

        print_introduction(); 

        int f_index = choose_function();
        System.out.println("Hai scelto il modulo "+f_index/100+" e la funzione "+f_index%100+".");
    }
}