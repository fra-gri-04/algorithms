package it.fragri;

/**
 * Class to test different sorting algorithms' times and efficiency.
 * @author Francesco Grillo
 */
public class Sorting{
    /** private constructor to prevent initialization */
    private Sorting(){}

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
     * <p>Splits in half the array, creates two sections, sorts them and then merges the result, picking the minimum value from the two halves until both have been traversed.
     * 
     * \({@literal T = \theta(array length * log_2(array length)) }\)

     * @param array list of elements.
     * 
     */
    public void mergeSortUsingSpace(int[] array){
        if (array.length > 1){
            int m = array.length/2;
            int[] B = new int[m];
            int[] C = new int[array.length - m];
            array = merge_using_space(B,C);
        }
    }
    private int[] merge_using_space(int[] B, int[] C){
        int iB=0, iC=0, k=0;
        // creates new array of original length
        int[] result = new int[B.length + C.length];
        // while one or the other sections is not finished, check which is smaller and add it to resulting array.
        while (iB < B.length && iC < C.length){
            if (B[iB] < C[iC]){
                result[k] = B[iB];
                iB++;
            }else{
                result[k] = C[iC];
                iC++;
            }
            k++;
        }
        // if sections have different lengths, finish by adding the remaining elements of the longer section.
        if (iB < B.length)
            System.arraycopy(B, iB, result, k, B.length);
        if (iC < C.length)
            System.arraycopy(C, iC, result, k, C.length);

        return result;
    }

    /**
     * Sorts the given array. With Divide Et Impera concept.
     * <p>Splits in half the array, sorts the two halves and then merges the result, picking the minimum value from the two halves until both have been traversed.
     * Instead of creating multiple arrays, it uses indices to indicate the start `s` and the end `e of the current section inspected, so it does not allocate any new arrays. 
     * 
     * \({@literal T = \theta(array length * log_2(array length)) }\)
     * 
     * @param array list of elements.
     * 
     */
    public void mergeSort(int[] array){
        // temp
        merge_sort(array, 0, array.length, new int[array.length]);
    }
    private void merge_sort(int[] array, int s, int e, int[] x){
        // checks length of current section
        if ((e-s)>1){
            int m = (e+s)/2;

            // recursively sort the halves
            merge_sort(array, s, m, x);
            merge_sort(array, m, e, x);

            // merge sorted halves
            merge(array, s,m,e, x);
        }
    }
    private void merge(int[] array, int s, int m, int e, int[] x){
        int i1 = s, i2 = m, k=0;

        while(i1 < m && i2 < e){
            if (array[i1] < array[i2]){
                x[k] = array[i1];
                i1++;
            }else{
                x[k] = array[i2];
                i2++;
            }
            k++;
        }

        // if in the first half there are still elements.
        if (i1 < m){
            System.arraycopy(array, i1, array, k, m-i1);
        }
        // if in the second half there are still elements.
        if (i1 < e){
            System.arraycopy(array, i2, array, k, e-i2);
        }
        System.arraycopy(x, 0, x, 0, array.length);
    }
}