/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ICollection;

/**
 * @param <E>
 * @date Jul 26, 2014
 * @author Chris Medina
 *
 * <p>
 * IEnumerator provides for linear traversal of any class implement
 * ICollection</p>
 */
public interface IEnumerator<E> {

    /**
     * Determine if there is another item after the current one to traverse to
     *
     * @return true, if there is another item to move forward to
     */
    boolean hasNext();

    /**
     * Moves the iterator forward one item assuming there is another item to
     * move forward to
     *
     * @return the next item in the iterator
     */ 
    E next();

    /**
     * Set the iterator back to the beginning
     */
    void reset();
}
