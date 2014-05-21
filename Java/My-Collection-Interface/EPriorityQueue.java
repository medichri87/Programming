package ADT;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/30/2014 <p>
 * Class Purpose: A priority Queue based on a Max-Heap, where the largest items filter to the top
 */
public class EPriorityQueue<E> extends EAbstractQueue<E> {

    private Tree<E> heap;

    /**
     * Construct an empty Priority Queue
     */
    public EPriorityQueue () {
        heap = new Heap<>();
    }

    /**
     * Construct a Priority Queue based on array input
     *
     * @param temp the array of values to input into Heap
     */
    public EPriorityQueue (Object[] temp) {
        heap = new Heap<>(temp);
    }

    /**
     * Construct a Priority Queue based on a ECollection of input
     *
     * @param c the ECollection to insert into this Queue
     */
    public EPriorityQueue (ECollection<? extends E> c) {
        heap = new Heap<>(c);
    }

    /**
     * Add this element to the front of the Queue
     *
     * @param e value to add
     */
    public void add (E e) {
        heap.add(e);
    }

    /**
     * Remove the element at the front of the Queue
     *
     * @return the front/first element in the Queue
     */
    public E remove () {
        if (heap.isEmpty())
            return null;
        return heap.remove();
    }

    /**
     * Empty the entire Queue, removing all items
     */
    public void clear () {
        heap.clear();
    }

    /**
     * The size of the current Queue
     *
     * @return the number of elements found
     */
    public int size () {
        return heap.size();
    }

    /**
     * An iterator that provides for sequential traversal of the Queue
     *
     * @return a new iterator from the starting point of the current Queue
     */
    public EIterator<E> iterator () {
        return heap.iterator();
    }

}





