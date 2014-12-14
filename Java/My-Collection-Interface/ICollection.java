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
 * <p>
 * The ICollection interface provides methods for searching, deletion,
 * iteration, and insertion along with several bulk operations (containsAll and
 * addAll) for all implementing classes.
 * </p>
 */
public interface ICollection<E> extends IEnumerable<E> {

    /**
     * Determine if this ICollection is empty
     *
     * @return true, if ICollection is empty
     */
    boolean isEmpty();

    /**
     * Determine if an item exists within the current ICollection
     *
     * @param item the item to search for
     * @return true, if found
     */
    boolean contains(E item);

    /**
     * Search in ICollection for all items within the argument ICollection
     *
     * @param collection the ICollection of items to search for
     * @return true, if ALL elements in the argument are found within the
     * invoking ICollection
     */
    boolean containsAll(ICollection<E> collection);

    /**
     * Add all items from this ICollection into the invoking ICollection
     *
     * @param collection the ICollection to insert the items from
     */
    void addAll(ICollection<? extends E> collection);

    /**
     * Get the size of the current ICollection
     *
     * @return the number of items found in ICollection
     */
    int size();

    /**
     * Insert an item into the ICollection
     *
     * @param item the item to insert
     */
    void add(E item);

    /**
     * Remove a specific item from the ICollection
     *
     * @param item the item to remove
     * @return the removed item, if found
     */
    E remove(E item);

    /**
     * Provides an iterator to traverse over the items within the current
     * ICollection
     *
     * @return the iterator to use for traversal
     */
    @Override
    IEnumerator<E> iterator();

    /**
     * Pass the current ICollection off into an array
     *
     * @return an array of all the items within the ICollection
     */
    E[] toArray();

    /**
     * Remove all items from the current ICollection
     */
    void clear();
}
