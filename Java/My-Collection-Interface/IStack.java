/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ICollection;

/**
 * IStack represents a LIFO (Last-In, First-Out) data structure where items are
 * inserted to the top (or end) and are the first to be removed.
 *
 * @date Jul 27, 2014
 * @author Chris Medina
 */
public interface IStack<E> extends ICollection<E> {

    /**
     * Push an item onto the IStack
     *
     * @param item the item to add to the IStack
     */
    void push(E item);

    /**
     * Returns the item that will be removed next upon calling pop()
     *
     * @return the next item to be removed
     */
    E peek();

    /**
     * Remove the item from the top of the IStack (most recent item inserted)
     *
     * @return the removed item
     */
    E pop();

    public boolean equals(Object obj);
}
