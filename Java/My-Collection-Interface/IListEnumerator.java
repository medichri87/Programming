/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ICollection;

/**
 * @date Jul 28, 2014
 * @author Chris Medina
 *
 * A dual-way iterator specific to IList-implementing types
 */
public interface IListEnumerator<E> extends IEnumerator<E> {

    /**
     * Determine if the currently pointed-to value in the IListEnumerator has a
     * previous value(a value before it)
     *
     * @return true, if there is an item prior to current
     */
    boolean hasPrevious();

    /**
     * Moves the IListEnumerator to the previous value
     *
     * @return thr previous value
     */
    E previous();
}
