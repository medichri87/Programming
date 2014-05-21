package ADT;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/23/2014 <p>
 * The ECollection interface provides a template of esstential methods for all of the common data structures. All
 * classes which descend from this interface, or extend from implementing classes, must implement all of these methods.
 */
public interface ECollection<E> {

    /**
     * Add this element to the Collection
     *
     * @param e value to add
     */
    void add (E e);

    /**
     * Empty the entire Collection, removing all items
     */
    void clear ();

    /**
     * Determine if this value is found within this Collection
     *
     * @param o the value to search for
     * @return true, if found
     */
    boolean contains (E o);

    /**
     * Determine if the Collection is empty(no items)
     *
     * @return true, if no items are present
     */
    boolean isEmpty ();

    /**
     * Remove this element from the Collection
     *
     * @param o the value to remove
     * @return true, if found and removed
     */
    boolean remove (E o);

    /**
     * Determine if the every element in the argument Collection is found within this Collection
     *
     * @param c the collection to find within
     * @return true, if this entire collection is found (as a subset)
     */
    boolean containsAll (ECollection<E> c);

    /**
     * Add this entire argument Collection to the current Collection
     *
     * @param c the collection to add in its entirety
     */
    void addAll (ECollection<E> c);

    /**
     * The size of the current Collection
     *
     * @return the number of elements found
     */
    int size ();

    /**
     * Pass the current Collection off to an Object array
     *
     * @return an Object array consisting of all the values found within the Collection
     */
    Object[] toArray ();

    /**
     * An iterator that provides for sequential traversal of the Collection
     *
     * @return a new iterator from the starting point of the current Collection
     */
    EIterator<E> iterator ();

}
