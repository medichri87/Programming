/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ICollection;

/**
 * IQueue represents an interface for implementing a data structure that is FIFO
 * (First-In, First-Out), similar to a customer line whereby cusomters enter a
 * line at the back but leave from the front. In the same manner, items are
 * inserted to the end and removed from the front of a Queue.
 *
 * @date Jul 29, 2014
 * @author Chris Medina
 */
public interface IQueue<E> extends ICollection<E> {

    /**
     * Insert an item to the end of the IQueue
     *
     * @param item the itme to insert
     */
    void enqueue(E item);

    /**
     * Remove the item from the front of the IQueue (first item)
     *
     * @return the removed item
     */
    E dequeue();

    /**
     * Retrieves, but does not remove the item that will be removed upon the
     * next call to dequeue
     *
     * @return the next item to be removed
     */
    E peek();
}
