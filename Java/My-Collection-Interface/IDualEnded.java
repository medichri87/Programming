/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ICollection;

/**
 * @param <E>
 * @date Jul 27, 2014
 * @author Chris Medina
 */

/*
 *
 * IDualEnded allows implementing classes the ability to remove and insert into 
 * the front as well as the rear of the implementing type, as well as retreiving 
 * the first and last elements.
 */
public interface IDualEnded<E> {

    /**
     * Insert an item to the front of the IList
     *
     * @param item the item to insert
     */
    void insertFirst(E item);

    /**
     * Insert an item to the end of the IList
     *
     * @param item the item to insert
     */
    void insertLast(E item);

    /**
     * Insert one value after another given value
     *
     * @param item the item to insert after
     * @param val the item to insert
     */
    void insertAfter(E item, E val);

    /**
     * Insert one value before another given value
     *
     * @param item the item to insert before
     * @param val the item to insert
     */
    void insertBefore(E item, E val);

    /**
     * Remove the first item in the IList
     *
     * @return the front item (first value in IList)
     */
    E removeFirst();

    /**
     * Remove the last item in the IList
     *
     * @return the last item (end item)
     */
    E removeLast();

    /**
     * Returns the first item in the IList
     *
     * @return the item at the front of the IList
     */
    E first();

    /**
     * Returns the last item in the IList
     *
     * @return the item at the end of the IList
     */
    E last();
}
