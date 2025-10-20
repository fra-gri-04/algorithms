package java.src.it.fragri;

/**
 * Class to test different sorting algorithms' times and efficiency.
 * @author Francesco Grillo
 */
public class Sorting{

    /**
     * Sorts the given array.
     * Until the array has been fully traversed with index i, starting from position 0 finds the minimum value in the remaining positions and swaps the minimum with the i-th element.
     *  
     * T = \({@literal \theta((array length)^2)}\)
     * @param array list of elements.
     * 
     */
    public void selectionSort(int[] array){
        for (int i = 0; i < array.length-1; i++){
            // find minimum value's index in remaining array.
            int m = i;
            for (int j = i+1; j < array.length; j++)
                if (array[j] < array[m]) m = j;
            // swaps minimum with current value inspected.
            int x = array[i];
            array[i] = array[m];
            array[m] = x;
        }
    }
    
    /**
     * Sorts the given array.
     * Until the array has been fully traversed with index i, takes the i-th element and inserts it in the position in the positions less than i, which represent the ordered side of the array. 
     *  
     * \({@literal T_best = \theta(array length-1) }\)
     * \({@literal T_worst = \theta(array length)^2 }\)
     * @param array list of elements.
     * 
     */
    public void insertionSort(int[] array){
        // insert array[i] in the right position
        for (int i=1; i < array.length; i++){
            int j = i-1;
            while(j >= 0 && array[j] > array[i]){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = array[i];
        }
    }

    /**
     * Sorts the given array.
     * <p>Until the array has been sorted, it swaps neighbours if one is less than the other.
     * <p>Greater elements end up in the end of the array, like bubbles float up to the surface. Thanks to this observation, it does not search always until the end of the array, but until array.length-searched_positions.
     *  
     * \({@literal T_best = \theta(array length-1) }\)
     * \({@literal T_worst = \theta(array length)^2 }\)
     * @param array list of elements.
     * 
     */
    public void bubbleSort(int[] array){
        int end=0;
        boolean sorted;
        do { 
            sorted = false;
            for (int j=1; j < array.length - end; j++){
                if (array[j] < array[j-1]){
                    // swaps array[j] with array[j-1]
                    int x = array[j];
                    array[j] = array[j-1];
                    array[j-1] = x;
                }
            }
            end++;
        } while (sorted && (end < array.length));
    }


    /**
     * Sorts the given array. With Divide Et Impera concept.
     * <p>Splits in half the array, sorts the two halves and then merges the result, picking the minimum value from the two halves until both have been traversed.
     * 
     * \({@literal T_best = \theta(array length-1) }\)
     * \({@literal T_worst = \theta(array length)^2 }\)
     * @param array list of elements.
     * 
     */
    public void mergeSort_using_space(int[] array){
        if (array.length > 1){

        }
    }
}