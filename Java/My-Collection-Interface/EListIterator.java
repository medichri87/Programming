package ADT;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/23/2014 <p>
 * EListIterator allows for dual-way (forwards and backwards) traversal of the doubly-linked linked list
 */
public interface EListIterator<E> extends EIterator<E> {
    /**
     * Proceed and return the next element in the current Collection
     *
     * @return the next element
     */
    E next ();

    /**
     * Determines if there is a value to move forward to
     *
     * @return true, if the next value exists(non-null)
     */
    boolean hasNext ();

    /**
     * Reset the iterator to the beginning
     */
    void reset ();

    /**
     * Determines if this LinkedList has a previous element
     *
     * @return true, if there is a previous element
     */
    boolean hasPrevious ();

    /**
     * Return the previous element
     *
     * @return previous element
     */
    E previous ();
}
