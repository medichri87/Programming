package ADT;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/27/2014 <p>
 * Class Purpose: A convenience class to provide skeletal implemntation of the Queue interface
 */
public abstract class EAbstractQueue<E> extends EAbstractCollection<E> implements EQueue<E> {
    protected EAbstractQueue () {
    }

    /**
     * Add this element to the front of the Queue
     *
     * @param e value to add
     */
    public abstract void add (E e);

    /**
     * Remove the element at the front of the Queue
     *
     * @return the front/first element in the Queue
     */
    public abstract E remove ();

    /**
     * Retrieve the element that will be deleted next(upon calling remove()). Is always the first element
     *
     * @return the next element to be removed
     */
    public E peek () {
        if (isEmpty())
            return null;
        else
            return iterator().next();
    }

    /**
     * Empty the entire Queue, removing all items
     */
    public abstract void clear ();

    /**
     * Determine if the Queue is empty(no items)
     *
     * @return true, if no items are present
     */
    public boolean isEmpty () {
        return (size() == 0);
    }

    /**
     * Remove this element from the Queue
     *
     * @param o the value to remove
     * @return true, if found and removed
     */
    public boolean remove (E o) {
        throw new UnsupportedOperationException("Queue doesn't allow for specific removal, " +
                "only from front/first value");
    }

    /**
     * The size of the current Queue
     *
     * @return the number of elements found
     */
    public abstract int size ();

    /**
     * An iterator that provides for sequential traversal of the Queue
     *
     * @return a new iterator from the starting point of the current Queue
     */
    public abstract EIterator<E> iterator ();
}
