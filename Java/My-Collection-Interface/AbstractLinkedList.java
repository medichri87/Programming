package ADT;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/23/2014 <p>
 * Class Purpose: Convenience class that provides the skeletal framework for all Linked List subtypes
 */
public abstract class AbstractLinkedList<E> extends EAbstractList<E> {

    protected AbstractLinkedList () {
        
    }

    /**
     * Remove the element at this index
     *
     * @param index the index of the item to remove
     * @return the item removed, if found
     */
    public abstract E removeIndex (int index);

    /**
     * Empty the entire Collection, removing all items
     */
    public abstract void clear ();

    /**
     * Remove this element from the Collection
     *
     * @param o the value to remove
     * @return True, if found and removed
     */
    public abstract boolean remove (E o);

    /**
     * The size of the current Collection
     *
     * @return The number of elements found
     */
    public abstract int size ();

    /**
     * Append this element at this index of the current Collection
     *
     * @param index the position to insert the item into
     * @param item  the value to insert
     */
    public void add (int index, E item) {
        throw new UnsupportedOperationException("Can't perform this operation for a Sorted Linked List.");
    }

    /**
     * Get the value associated with this index
     *
     * @param index the index of the item to find
     * @return The value associated with this index within the Collection
     */
    public E get (int index) {
        int idx = 0;
        EIterator<E> it = iterator();
        E temp = null;
        while (idx++ < index) {
            temp = it.next();
        }
        return temp;
    }

}
