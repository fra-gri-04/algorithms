package it.dataStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Queue implementation using an array of ints.
 * It has two pointers: one to the start and one to the end of the queue, in order to have
 * O(1) time when in need to reach the end of the queue.
 * It resizes itself when the array size is reached by doubling its length.
 * When the queue is shorter than the array's length half, it shrinks.
 * 
 * It can be initialized empty or by passing an array of integers. Using the second method will insert the elements as they are found in the array.
 */
public class Queue implements Iterable<Integer>{
    private int one;
    private int last;
    private int[] elements;

    public Queue(int[] array){
        elements = new int[array.length];
        one = 0;
        last = array.length;
        System.arraycopy(array, 0, elements, 0, array.length);
    }

    public Queue(){
        this.one = 0;
        this.last = 0;
        this.elements = new int[1];
    }

    public void enqueue(int e){
        elements[last] = e;
        last = (last + 1) % elements.length;
        
        if (last <= one){                               // [.,.,., L, O,.,.,.]
            int[] x;
            int n_elements = elements.length - one + last;
            if (n_elements == elements.length){
                // increase array dimension
                x = new int[elements.length*2];
                // takes the right side of one until the end of elements
                System.arraycopy(elements, one, x, 0, elements.length-one);
                // takes the left side of last until last is reached, adds up in order
                System.arraycopy(elements, 0, x, elements.length-one, last);
    
                last = elements.length - one + last;
                one = 0;

                elements = x;
            }
        }
    }

    public boolean isEmpty(){
        return one == last;
    }

    public int first(){
        return elements[one];
    }

    public int dequeue(){
        int served = elements[one];
        one = (one+1) % elements.length;

        if (Math.abs(last - one) < elements.length/2){
            // if it is empty
            if (isEmpty()){
                one = 0;
                last = 0;
                // resets elements array dimension
                elements = new int[1];
            }else{
                // creates an array of half the dimension and fills it with the values
                int[] x = new int[elements.length/2];

                int n = size();

                for (int i = 0; i < n; i++){
                    x[i] = elements[(one+i) % elements.length];
                }
                
                elements = x; 
                last = n;
                one = 0;
            }
            
        }

        return served;
    }

    private int size(){
        if (last < one) 
            return elements.length + last - one;

        return Math.abs(last - one);        
    }

    /**
     * Returns a print-ready string to show the real contents of the elements array
     * @return the contents of the underlying elements array
     */
    public String testString(){
        String res = "[";
        int i;
        for (i=0; i<elements.length-1;i++)
            res += elements[i] + ", ";
        res += elements[i];
        return res+"]\none: "+one+", last: "+last+"\n";
    }

    @Override
    public String toString(){
        String res = "";

        if (isEmpty())
            return "Queue is empty";

        int n = size();

        for (int i = 0; i < n; i++){
            res += elements[(one+i) % elements.length]+" > ";
        }
        return res;
    }

    @Override
    public Iterator<Integer> iterator(){ 
        final int[] els = new int[elements.length];
        System.arraycopy(elements, 0, els, 0, elements.length);      
        return new Iterator<Integer>(){
            // immutable copies of attributes to prevent mid-iteration changes
            private int index = 0;
            private final int size = size();
            
            private final int one = Queue.this.one;
            private final int last = Queue.this.last;

            @Override
            public boolean hasNext(){
                if(isEmpty()) return false;
                return index < size;
            }

            @Override
            public Integer next(){
                if(!hasNext()) throw new NoSuchElementException();
                int result = els[(one + index) % els.length];
                index++;
                return result;
            }
        };
    }
}
