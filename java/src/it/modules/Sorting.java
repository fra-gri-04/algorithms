package it.modules;
import it.inspector.Queue;

/**
 * Class to test different sorting algorithms' times and efficiency.
 * @author Francesco Grillo
 */
public class Sorting{
    /** private constructor to prevent initialization */
    // private Sorting(){}

    /**
     * Sorts the given array.
     * Until the array has been fully traversed with index i, starting from position 0 finds the minimum value in the remaining positions and swaps the minimum with the i-th element.
     *  
     * T = \({@literal \theta((array length)^2)}\)
     * @param array list of elements.
     * 
     */
    public static void selectionSort(int[] array){
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
    public static void insertionSort(int[] array){
        // insert array[i] in the right position
        for (int i=1; i < array.length; i++){
            int k = array[i];
            int j = i-1;
            while(j >= 0 && array[j] > k){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = k;
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
    public static void bubbleSort(int[] array){
        int end=0;
        boolean sorted;
        do { 
            sorted = true;
            for (int j=1; j < array.length - end; j++){
                if (array[j] < array[j-1]){
                    // swaps array[j] with array[j-1]
                    int x = array[j];
                    array[j] = array[j-1];
                    array[j-1] = x;
                    sorted = false;
                }
            }
            end++;
        } while (!sorted && (end < array.length));
    }


    /**
     * Sorts the given array. With Divide Et Impera concept.
     * <p>Splits in half the array, creates two sections, sorts them and then merges the result, picking the minimum value from the two halves until both have been traversed.
     * 
     * \({@literal T = \theta(array length * log_2(array length)) }\)

     * @param array list of elements.
     * 
     */
    public static void mergeSortUsingSpace(int[] array){
        if (array.length > 1){
            int m = array.length/2;
            int[] B = new int[m];
            int[] C = new int[array.length - m];

            System.arraycopy(array, 0, B, 0, B.length);
            System.arraycopy(array, m, C, 0, C.length);
            
            mergeSortUsingSpace(B);
            mergeSortUsingSpace(C);
            
            int[] x = merge_using_space(B,C);
            System.arraycopy(x, 0, array, 0, x.length);
        }
    }
    private static int[] merge_using_space(int[] B, int[] C){
        int iB=0; int iC=0; int k=0;

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
        /* // if sections have different lengths, finish by adding the remaining elements of the longer section.
        if (iB < B.length)
            System.arraycopy(B, iB, result, k, B.length - iB);
        if (iC < C.length)
            System.arraycopy(C, iC, result, k, C.length - iC); */

        while (iB < B.length){
            result[k] = B[iB];
            iB++;
            k++;
        }
        while (iC < C.length){
            result[k] = C[iC];
            iC++;
            k++;
        }

        return result;
    }

    /**
     * Sorts the given array. With Divide Et Impera concept.
     * <p>Splits in half the array, sorts the two halves and then merges the result, picking the minimum value from the two halves until both have been traversed.
     * Instead of creating multiple arrays, it uses indices to indicate the start `s` and the end `e` of the current section inspected, so it does not allocate any new arrays. 
     * 
     * \({@literal T = \theta(array length * log_2(array length)) }\)
     * 
     * @param array list of elements.
     * 
     */
    public static void mergeSort(int[] array){
        merge_sort(array, 0, array.length, new int[array.length]);
    }
    private static void merge_sort(int[] array, int s, int e, int[] x){
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
    private static void merge(int[] array, int s, int m, int e, int[] x){
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
            System.arraycopy(array, i1, x, k, m-i1);
        }
        // if in the second half there are still elements.
        if (i2 < e){
            System.arraycopy(array, i2, x, k, e-i2);
        }

        // System.arraycopy(x, 0, array, s, e-s);
        for (int i=s; i<e; i++)
            array[i] = x[i-s]; 
    }     

    private static int quickSort_subdivide(int[] array, int s, int e){
        int pivot = array[s];
        int left = s;
        int right = e;
        while (left < right){
            // move to the left the right index
            do { right--; } while (array[right] > pivot);

            // move to the right the left index until it's greater than the pivot or until it has touched the right index
            do { left++; }  while(left < right && array[left] < pivot);

            if (left < right){
                int tmp = array[right];
                array[right] = array[left];
                array[left] = tmp;
            }
        }
        array[s] = array[right];
        array[right] = pivot;
        return right;
    }

    private static void quickSort_using_space(int[] array, int s, int e){
        if ((e-s) > 1){
            int m = quickSort_subdivide(array, s, e);
            
            quickSort_using_space(array, s, m);
            quickSort_using_space(array, m+1, e);           // terminal recursion
        }
    }


    /**
     * Sorts the array of length n using the Divide Et Impera concept.
     * <p>Splits the array in two parts, in order to have bigger elements to the right and smaller on the left.
     * Then sorts the two halves and puts them back together using recursion on both parts without checking their lengths.
     * 
     * Best case:
     * 
     * T = \({@literal \theta(n * log{n})}\)
     * 
     * Worst case:
     * 
     * T = \({@literal \theta((n)^2)}\)
     * 
     * @param array array of ints to sort
      */
    public static void quickSortUsingSpace(int[] array){
        quickSort_using_space(array, 0, array.length);
    }


    private static void quick_sort(int[] array, int s, int e){
        // while there are elements to check
        while((e-s) > 1){
            int m = quickSort_subdivide(array, s, e);
            // check which part is the shortest and apply recursion on that
            if ((m-s) < (e-m)){
                quick_sort(array, s, m);
                s = m+1;
            }else{
                quick_sort(array, m+1, e);
                e = m;
            }
        }
    }


    /**
     * Sorts the array of length n using the Divide Et Impera concept.
     * <p>Splits the array in two parts, in order to have bigger elements to the right and smaller on the left.
     * Then sorts the two halves and puts them back together using recursion on just the shortest part of the two.
     * 
     * Best case:
     * 
     * T = \({@literal \theta(n * log{n})}\)
     * 
     * Worst case:
     * 
     * T = \({@literal \theta((n)^2)}\)
     * 
     * @param array array of ints to sort
      */
    public static void quickSort(int[] array){
        quick_sort(array, 0, array.length);
    }
    /**
     * Given a max heap tree with a wrong root, fixes the structure in order to have the root as the greatest element and 
     * each child is smaller than the father.
     * 
     * With i as an index of a node, 2*i+1 indicates the first son and 2*i+2 the second one.
     * 
     * @param array heap tree implementation
     * @param r root value
     * @param l last element
     */
    private static void fixHeap(int[] array, int r, int l){
        int v = r;                  // examinated position
        int x = array[v];           // position to fix
        boolean toFix = true;
        do { 
            if ((2*v + 1) >= l) toFix = false;
            else{
                // it takes the greatest child of v
                int u = 2 * v +1;
                if (u+1 < l && array[u+1] > array[u]) u=u+1;
                
                if (array[u] > x){
                    array[v] = array[u];
                    v = u;
                }else 
                    toFix = false;
            }
        } while (toFix);

        array[v] = x;
    }

    private static void createHeap(int[] array){
        for (int i = array.length/2; i >= 0; i--){
            fixHeap(array, i, array.length);
        }
    }

    public static void heapSort(int[] array){
        int tmp;
        createHeap(array);

        for (int l=array.length-1; l>0; l--){
            tmp = array[0];
            array[0] = array[l];
            array[l] = tmp;
            fixHeap(array, 0, l);
        }
    }
    /**
     * Sorts an array without using comparisons
     * Utilizes a helping array Y of length equals to the max value inside the array to sort, in order to save recurrencies of elements.
     * then iterates trough Y and inseerts into the result the index i repeated Y[i] times. 
     * 
     * @param array array to sort
     * @param k max value found in the array
     */
    public static void integerSort(int[] array, int k){
        int[] y = new int[k];
        for (int a : array){
            y[a]++;
        }
        int i=0, j=0;
        while (j < y.length){
            for (int v=0; v < y[j]; v++)
                array[i++] = j;
            j++;
        }
    }

    /**
     * Sorts an array without using comparisons.
     * Utilizes a helping array of buckets (queues), to save each element with the key corresponding to the index position.
     * It then iterates through the array of buckets and trought the queues to fill the resulting array.
     * 
     * if b is in the order of O(n) => T = O(n)
     * if b is in the order of O(n) => T = O(n^2)
     * 
     * in general: T = O(n + b)
     * 
     * @param array array to sort
     * @param b max value found in the array => number of buckets
     */
    public static void bucketSort(int[] array, int b){
        Queue[] buckets = new Queue[b];
        // initialization of helping array of buckets
        for (int i = 0; i < b; i++)
            buckets[i] = new Queue();
        
        // bucket filling
        for (int i=0; i < array.length; i++)
            // value of element is the bucket index
            buckets[array[i]].enqueue(array[i]);

        // Assignment of values to array
        int j = 0;
        for (int i=0; i < b; i++)
            while (!buckets[i].isEmpty()){
                array[j] = buckets[i].dequeue();
                j++;
            }
    } 

    private static void bucketSort_radix(int[] array, int base, int t){
        Queue[] buckets = new Queue[base];
        // initialization of helping array of buckets
        for (int i = 0; i < base; i++)
            buckets[i] = new Queue();
        
        // bucket filling
        for (int i=0; i < array.length; i++){
            // value of element is the t-th digit of array[i]
            int x = array[i];

            for (int k=0; k < t && x > 0; k++, x /= 10){}
            
            if (x >= 0)
                buckets[x%10].enqueue(array[i]);
        }

        // Assignment of values to array
        int j = 0;
        for (int i=0; i < base; i++)
            while (!buckets[i].isEmpty()){
                array[j] = buckets[i].dequeue();
                j++;
            }
    }

    /**
     * Sorts the array without using comparisons.
     * Sorts each element using only one digit of the number itself, repeating bucket sort until all digits are covered
     * @param array
     * @param max
     */
    public static void radixSort(int[] array, int max){
        // digit index
        int t = 0;
        // while there are still digits in A so that L_k / b^t != 0 
        int BASE = 10;

        do { 
            bucketSort_radix(array, BASE, t);
            t++;
            max /= 10;
        } while (max / BASE != 0);
    }
}