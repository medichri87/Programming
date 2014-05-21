package ADT;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/27/2014 <p>
 * Class Purpose: Provides a skeletal implementation of the EAbstractCollection interface, which provides for the
 * methods that will apply to ALL Collection types (List, Stack, and Queue). i.e. ALL Collections provide at least these
 * behaviors
 */
public abstract class EAbstractCollection<E> implements ECollection<E> {
    /**
     * Add this element to the Collection
     *
     * @param e value to add
     */
    public void add (E e) {
        throw new UnsupportedOperationException();
    }

    /**
     * Empty the entire Collection, removing all items
     */
    public abstract void clear ();

    /**
     * Determine if this value is found within this Collection
     *
     * @param o the value to search for
     * @return true, if found
     */
    public boolean contains (E o) {
        EIterator<E> it = iterator();
        while (it.hasNext()) {
            if (it.next().equals(o))
                return true;
        }
        return false;
    }

    /**
     * Determine if the Collection is empty(no items)
     *
     * @return true, if no items are present
     */
    public boolean isEmpty () {
        return (size() == 0);
    }

    /**
     * Remove this element from the Collection
     *
     * @param o the value to remove
     * @return true, if found and removed
     */
    public abstract boolean remove (E o);

    /**
     * Determine if the every element in the argument Collection is found within this Collection
     *
     * @param c the collection to find within
     * @return true, if this entire collection is found (as a subset)
     */
    public boolean containsAll (ECollection<E> c) {
        EIterator<E> it = c.iterator();
        while (it.hasNext()) {
            if (!contains(it.next()))
                return false;
        }
        return true;
    }

    /**
     * Add this entire argument Collection to the current Collection
     *
     * @param c the collection to add in its entirety
     */
    public void addAll (ECollection<E> c) {
        EIterator<E> it = c.iterator();
        if (c == null)
            throw new NullPointerException();
        else {
            while (it.hasNext()) {
                this.add(it.next());
            }
        }
    }

    /**
     * Output the elements of the current Collection
     *
     * @return a String containing all the values of this Collection
     */
    public String toString () {
        String out = "";
        out += "[";
        EIterator<E> it = iterator();
        while (it.hasNext())
            out += it.next() + (it.hasNext() ? ", " : "");
        out += "]";
        return out;
    }

    /**
     * The size of the current Collection
     *
     * @return the number of elements found
     */
    public abstract int size ();

    /**
     * Pass the current Collection off to an Object array
     *
     * @return an Object array consisting of the value found within the Collection
     */
    public Object[] toArray () {
        EIterator<E> it = iterator();
        Object[] out = new Object[size()];
        int index = 0;
        while (it.hasNext())
            out[index++] = it.next();
        return out;
    }

    /**
     * An iterator that provides for sequential traversal of the Collection
     *
     * @return a new iterator from the starting point of the current Collection
     */
    public abstract EIterator<E> iterator ();


}
