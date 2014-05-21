package ADT;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/23/2014 <p>
 * EIterator interface allows for traversal (forward-only) for list-based implementations.
 */
public interface EIterator<E> {
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
}
