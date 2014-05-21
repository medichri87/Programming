package ADT;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/27/2014 <p>
 * Class Purpose:
 */
public interface TreeIterator<E> extends EIterator<E> {
    /**
     * Reset the iterator to the beginning
     */
    void reset ();

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
     * Move left to the next element
     *
     * @return the left element, or null if not found
     */
    E left ();

    /**
     * Move right to the next element
     *
     * @return the right element, or null if not found
     */
    E right ();

    /**
     * Determine if there is any non-null value to the left of this element
     *
     * @return true, if there is a non-null value to the left of this
     */
    boolean hasNextLeft ();

    /**
     * Determine if there is any non-null value to the right of this element
     *
     * @return true, if there is a non-null value to the right of this
     */
    boolean hasNextRight ();

    /**Get the parent value for this value
     * @return the parent value (i.e. value directly above)
     */
    E parent();
}
