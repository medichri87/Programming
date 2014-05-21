package ADT;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/27/2014 <p>
 * Class Purpose: Represents an array-based Stack which is run as an EArrayList(expandable array)
 */
public class ArrayQueue<E> extends EAbstractQueue<E> implements EQueue<E> {

    private EList<E> list;

    /**
     * Construct an empty Queue
     */
    public ArrayQueue () {
        list = new EArrayList<>();
    }

    /**
     * Construct an queue array from an array input. By default, these values are inserted in the order they appear.
     *
     * @param in array input to construct from
     */
    public ArrayQueue (Object[] in) {
        list = new EArrayList<>(in);
    }

    /**
     * Construct a queue from an input Queue
     *
     * @param c Queue input to construct a queue from. By default, these values are inserted in the order they appear.
     */
    public ArrayQueue (ECollection<? extends E> c) {
        list = new EArrayList<>(c);
    }

    /**
     * Add this element to the front of the Queue
     *
     * @param e value to add
     */
    public void add (E e) {
        list.add(e);
    }

    /**
     * Remove the element at the front of the Queue
     *
     * @return the front/first element in the Queue
     */
    public E remove () {
        return list.removeIndex(0);
    }

    /**
     * Empty the entire Queue, removing all items
     */
    public void clear () {
        list.clear();
    }

    /**
     * The size of the current Queue
     *
     * @return the number of elements found
     */
    public int size () {
        return list.size();
    }

    /**
     * An iterator that provides for sequential traversal of the Queue
     *
     * @return a new iterator from the starting point of the current Queue
     */
    public EIterator<E> iterator () {
        return list.iterator();
    }

}
