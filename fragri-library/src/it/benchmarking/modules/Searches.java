package it.modules;

/**
 * Class to test different searching algorithms' times and efficiency of a particular element in an array of elements of the same type.
 * @author Francesco Grillo
 */
public class Searches{
    /** private constructor to prevent initialization */
    private Searches(){}

    /**
     * Inspect the whole array linearly and save the minimum value.
     * T = $\theta$(array length)
     * @param array list of elements
     * @return index of the lowest element
     */
    public static int linearMin(int[] array){
        int min = array[0];
        for (int x : array){
            if (x < min) min = x;
        }
        return min;
    }

    /**
     * Inspect the array by dividing it in two halves, finding the minimums and compairing the two.
     * It checks the halves recursively calling itself.
     * T = $\theta$(array length)
     * @param array list of elements
     * @return index of the lowest element
     */
    public static int recursiveMin(int[] array){
        return recursive_min(array, 0, array.length);
    }
    private static int recursive_min(int[] array, int s, int e){
        // base cases
        if ((e-s) == 1) return array[s];
        if ((e-s) == 2) return array[0] < array[1] ? array[0] : array[1];

        int m = (s+e)/2;
        int min1 = recursive_min(array, s, m);
        int min2 = recursive_min(array, m, e);

        return min1 < min2 ? min1 : min2;
    }

    /**
     * Checks element per element the whole array and stops if it founds the wanted element.
     * Returns -1 if the element is not present in the array.
     * T = $\theta$(array length)
     * @param array list of elements
     * @param element element to find
     * @return index of the wanted element, -1 if not found.
     */
    public static int linearSearch(int[] array, int element){
        int i;
        for (i=0; i < array.length && array[i] != element; i++){}
        if (i == array.length) return -1;
        return i;
    }

    /**
     * Find the index of an element in an array of elements of the same type recursively calling itself. Returns -1 if the element is not present.
     * Requires the array to be sorted. 
     * T = $\theta$(log_2(array length))
     * <p>Divides the array in half, checks if the element is the one wanted. If found, returns. If not, recursively checks in the created halves.
     * @param array list of elements
     * @param element element to find
     * @return index of the wanted element, -1 if not found.
     */
    public static int binarySearchRecursive(int[] array, int element){
        return rec_bin_search(array, element, 0, array.length);
    }
    private static int rec_bin_search(int[] array, int element, int sx, int dx){
        // if the two indeces touch, the whole array has been inspected.
        if (dx <= sx) return -1;

        // gets the half of the current length to be inspected.
        int m = (dx+sx)/2;
        // checks if element is in the first or second half.
        if (array[m] == element) return m;                                  // tail recursion   (can be deleted)
        if (array[m] > element) rec_bin_search(array, element, sx, m);      // tail recursion
        
        return rec_bin_search(array, element, m+1, dx);
    }

    /**
     * Find the index of an element in an array of elements of the same type using iterating on the array using two indices and no recursion. Returns an insertion point to add the element to the array without breaking the order.
     * Requires the array to be sorted. 
     * T = $\theta$(log_2(array length))
     * <p>Divides the array in half, checks if the element is the one wanted. If found, returns. If not, recursively checks in the created halves.
     * @param array list of elements
     * @param element element to find
     * @return index of the wanted element, an insertion point if not found.
     */
    public static int binarySearch(int[] array, int element){
        // initialize start and end indices.
        int sx = 0, dx = array.length;
        int m = 0;
        while (sx<dx){
            // gets the half of the current length to be inspected.
            m = (dx+sx)/2;
            // checks if element is in the first or second half.
            if (array[m] < element) sx = m+1;
            if (array[m] > element) dx = m;
            else return m;
        }
        return -m -1;
    }

}