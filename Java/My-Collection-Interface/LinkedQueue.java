package ADT;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/27/2014 <p>
 * Class Purpose: Represents a Queue which is run as a a doubly-linked, double-ended Linked List
 */
public class LinkedQueue<E> extends EAbstractQueue<E> implements EQueue<E> {
    private EList<E> list;

    /**
     * Construct an empty Queue
     */
    public LinkedQueue () {
        list = new DLinkedList<>();
    }

    /**
     * Construct a Queue based on an array input
     *
     * @param in the array input to input into the Queue
     */
    public LinkedQueue (Object[] in) {
        list = new DLinkedList<>(in);
    }

    /**
     * Insert the ECollection into the current Queue. By default, these values are inserted in the order they appear.
     *
     * @param c
     */
    public LinkedQueue (ECollection<? extends E> c) {
        list = new DLinkedList<>(c);
    }

    /**
     * Add this element to the front of the Queue
     *
     * @param e value to add
     */
    public void add (E e) {
        list.add(e);
    }

    /**
     * Remove the element at the front of the Queue
     *
     * @return the front/first element in the Queue
     */
    public E remove () {
        return list.removeIndex(0);
    }

    /**
     * Empty the entire Queue, removing all items
     */
    public void clear () {
        list.clear();
    }

    /**
     * The size of the current Queue
     *
     * @return the number of elements found
     */
    public int size () {
        return list.size();
    }

    /**
     * An iterator that provides for dual-way traversal of the Queue
     *
     * @return a new iterator from the starting point of the current Queue
     */
    public EIterator<E> iterator () {
        return ((DLinkedList<E>) list).listIterator();
    }
}
