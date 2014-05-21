package ADT;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/25/2014 <p>
 * Class Purpose: Convenience class that provides a skeletal implementation of the EList interface
 */
public abstract class EAbstractList<E> extends EAbstractCollection<E> implements EList<E> {

    protected EAbstractList () {
    }

    /**
     * Add this element to the end of the current List
     *
     * @param e Value to add
     */
    public void add (E e) {
        add(size(), e);
    }

    /**
     * Empty the entire List, removing all items
     */
    public abstract void clear ();

    /**
     * Determine if the List is empty(no items)
     *
     * @return True, if no items are present
     */
    public boolean isEmpty () {
        return (size() == 0);
    }

    /**
     * Remove this element from the List
     *
     * @param o the value to remove
     * @return True, if found and removed
     */
    public abstract boolean remove (E o);

    /**
     * Remove the element at this index
     *
     * @param index the index of the item to remove
     * @return the item removed, if found
     */
    public abstract E removeIndex (int index);

    /**
     * The size of the current List
     *
     * @return The number of elements found
     */
    public abstract int size ();

    /**
     * Append this element at this index of the current List
     *
     * @param index the position to insert the item into
     * @param item  the value to insert
     */
    public abstract void add (int index, E item);

    /**
     * Get the value associated with this index
     *
     * @param index the index of the item to find
     * @return The value associated with this index within the List
     */
    public abstract E get (int index);

    /**
     * Find the index of this value
     *
     * @param o the value to search for
     * @return The index associated with this value
     */
    public int indexOf (E o) {
        int index = 0;
        EIterator<E> it = iterator();
        if (isEmpty())
            return -1;
        while (it.hasNext()) {
            if (it.next().equals(o))
                return index;
            index++;
        }
        return -1;
    }

    /**
     * Produces a Sublist(in the form of an EArrayList) consisting of the elements between two indices, non-inclusive;
     *
     * @param from the starting index
     * @param to   ending index, non-inclusive
     * @return a sublist consisting of the elements between two indices
     */
    public EList<E> subList (int from, int to) {
        if ((from > to || from < 0 || from > size()) || (to < 0 || to > size()))
            throw new IndexOutOfBoundsException("Index out of range");
        Object[] in = toArray();
        EList<E> list = new EArrayList<>();

        for (int idx = 0; from < to; from++) {
            list.add((E) in[idx++]);
        }
        return list;
    }

    /**
     * Find the last index this value is found in the current List
     *
     * @param o the value to search for
     * @return The last index associated with this value
     */
    public int lastIndexOf (E o) {
        int index = 0;
        int idx = -1;
        EIterator<E> it = iterator();
        if (isEmpty())
            return -1;
        while (it.hasNext()) {
            if (it.next().equals(o))
                idx = index;
            index++;
        }
        return idx;
    }

    /**
     * An iterator that provides for sequential traversal of the List
     *
     * @return A new iterator from the starting point of the current List
     */
    public abstract EIterator<E> iterator ();


}
