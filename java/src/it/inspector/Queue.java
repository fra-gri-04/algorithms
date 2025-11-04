package it.inspector;

class Node{
    int data;
    Node next;
}
/**
 * Queue implementation using two pointers: one to the start and one to the end of the queue, in order to have
 * O(1) time when in need to reach the end of the queue.
 * 
 */
public class Queue {
    Node one;
    Node last;

    public Queue(){
        one = null;
        last = null;
    }

    public void enqueue(int element){
        Node x = new Node();
        x.data = element;
        x.next = null;

        if (one == null){
            one = x;
            last = x;
        }else{
            last.next = x;
            last = x;
        }
    }

    public boolean isEmpty(){
        return one == null;
    }

    public int first(){
        return one.data;
    }

    public Node dequeue(){
        Node x = one;
        one = one.next;
        // if the queue has only one element, last must be deleted too.
        if (one == null)
            last = null;
        return x;
    }

}
