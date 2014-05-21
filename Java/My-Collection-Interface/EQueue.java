package ADT;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/27/2014 <p>
 * Class Purpose:
 */
public interface EQueue<E> extends ECollection<E> {
    /**
     * Add this element to the front of the Queue
     *
     * @param e value to add
     */
    void add (E e);

    /**
     * Remove the element at the front of the Queue
     *
     * @return the front/first element in the Queue
     */
    E remove ();

    /**
     * Retrieve the element that will be deleted next(upon calling remove())
     *
     * @return the next element to be removed
     */
    E peek ();
}
