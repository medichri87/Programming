package ADT;

import java.util.EmptyStackException;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/23/2014 <p>
 * Class Purpose: Provides skeletal implementation of the Stack interface
 */
public abstract class AbstractStack<E> extends EAbstractCollection<E> implements EStack<E> {

    protected AbstractStack () {
    }

    /**
     * Add this element to the end of the current Stack
     *
     * @param e value to add
     */
    public abstract void add (E e);

    /**
     * Empty the entire Stack, removing all items
     */
    public abstract void clear ();

    /**
     * Determine if the Stack is empty(no items)
     *
     * @return true, if no items are present
     */
    public boolean isEmpty () {
        return (size() == 0);
    }

    /**
     * Remove this element from the Stack
     *
     * @param o the value to remove
     * @return true, if found and removed
     */
    public boolean remove (E o) {
        throw new UnsupportedOperationException("Stacks don't permit specific " +
                "index or element removal");
    }

    /**
     * The size of the current Stack
     *
     * @return the number of elements found
     */
    public abstract int size ();

    /**
     * Append this element at this index of the current Stack
     *
     * @param index the position to insert the item into
     * @param item  the value to insert
     */
    public void add (int index, E item) {
        throw new UnsupportedOperationException("Not a supported operation for Stack types");
    }

    /**
     * Get the value associated with this index
     *
     * @param index the index of the item to find
     * @return the value associated with this index within the Stack
     */
    public E get (int index) {
        throw new UnsupportedOperationException();
    }

    /**
     * Find the index of this value
     *
     * @param o the value to search for
     * @return the index associated with this value
     */
    public int indexOf (E o) {
        throw new UnsupportedOperationException();
    }

    /**
     * Find the last index this value is found in the current Stack
     *
     * @param o the value to search for
     * @return the last index associated with this value
     */
    public int lastIndexOf (E o) {
        throw new UnsupportedOperationException();
    }

    /**
     * An iterator that provides for sequential traversal of the Stack
     *
     * @return a new iterator from the starting point of the current Stack
     */
    public abstract EIterator<E> iterator ();

    /**
     * Remove the element at the top of the Stack
     *
     * @return the element at the top of Stack
     */
    public abstract E pop ();

    /**
     * Push this element onto the top/front of the Stack
     *
     * @param item the item to insert
     */
    public abstract void push (E item);

    /**
     * Peek at the item that will be removed next
     *
     * @return the element to be next removed
     */
    public E peek () {
        if (isEmpty())
            throw new EmptyStackException();
        EIterator<E> it = iterator();
        E peek = null;
        while (it.hasNext())
            peek = it.next();
        return peek;
    }

    /**
     * Pass the current Stack off to an Object array
     *
     * @return an Object array consisting of the value found within the Collection
     */
    public Object[] toArray () {
        Object[] out = new Object[size()];

        for (int i = size() - 1, index = 0; i >= 0; i--) {
            out[index++] = this.pop();
        }

        return out;
    }
}
