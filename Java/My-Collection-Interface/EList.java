package ADT;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/23/2014 <p>
 * The EList interface provides the template for classes to implement common List behaviors like random access,
 * insertion, and deletion, and producing sub-lists. EList extends the implementation of the ECollection interface.
 */
public interface EList<E> extends ECollection<E> {
    /**
     * Add this element to the end of the current Collection
     *
     * @param e Value to add
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
     * @return True, if found
     */
    boolean contains (E o);

    /**
     * Determine if the Collection is empty(no items)
     *
     * @return True, if no items are present
     */
    boolean isEmpty ();

    /**
     * Remove this element from the Collection
     *
     * @param o the value to remove
     * @return True, if found and removed
     */
    boolean remove (E o);

    /**
     * Remove the element at this index
     *
     * @param index the index of the item to remove
     * @return the item removed, if found
     */
    E removeIndex (int index);

    /**
     * The size of the current Collection
     *
     * @return The number of elements found
     */
    int size ();

    /**
     * Pass the current Collection off to an Object array
     *
     * @return An Object array consisting of the values found within the Collection
     */
    Object[] toArray ();

    /**
     * Append this element at this index of the current Collection
     *
     * @param index the position to insert the item into
     * @param item  the value to insert
     */
    void add (int index, E item);

    /**
     * Get the value associated with this index
     *
     * @param index the index of the item to find
     * @return The value associated with this index within the Collection
     */
    E get (int index);

    /**
     * Find the index of this value
     *
     * @param o the value to search for
     * @return The index associated with this value
     */
    int indexOf (E o);

    /**
     * Find the last index this value is found in the current Collection
     *
     * @param o the value to search for
     * @return The last index associated with this value
     */
    int lastIndexOf (E o);

    /**
     * An iterator that provides for sequential traversal of the Collection
     *
     * @return A new iterator from the starting point of the current Collection
     */
    EIterator<E> iterator ();

    /**
     * Produces a Sublist(in the form of an EArrayList) consisting of the elements between two indices, non-inclusive;
     *
     * @param from the starting index
     * @param to   end index, non-inclusive
     * @return a sublist consisting of the elements between two indices
     */
    EList<E> subList (int from, int to);
}
