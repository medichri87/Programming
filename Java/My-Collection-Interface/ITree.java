/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ICollection;

/**
 * @param <E>
 * @date Jul 30, 2014
 * @author Chris Medina
 *
 */
public interface ITree<E> {

    /**
     * The root value of the ITree
     *
     * @return the root value
     */
    E root();

    /**
     * The minimum value of the ITree
     *
     * @return the minimum value
     */
    E min();

    /**
     * The maximum value of the ITree
     *
     * @return the maximum value
     */
    E max();

    /**
     * Insert an item into the ITree
     *
     * @param item the item to insert
     */
    void add(E item);

    /**
     * Remove a given item from the ITree
     *
     * @param item the item to remove
     * @return the removed item
     */
    E remove(E item);

    /**
     * Remove the root value from the ITree
     *
     * @return the root (first) item
     */
    E removeRoot();

    /**
     * Determine if this ITree is empty
     *
     * @return true, if ITree is empty
     */
    boolean isEmpty();

    /**
     * Empty the ITree of all of its contents
     */
    void clear();

    /**
     * Add an ICollection into the ITree
     *
     * @param collection the ICollection of items to insert
     */
    void addAll(ICollection<? extends E> collection);

    /**
     * Determine if ITree contains all items in a given ICollection
     *
     * @param collection the ICollection of items to search for
     * @return true, if all items in the collection are found in the ITree
     */
    boolean containsAll(ICollection<? extends E> collection);

    /**
     * Determine if an ITree contains a specific item
     *
     * @param item the item to search for
     * @return true, if ITree contains given item
     */
    boolean contains(E item);

    /**
     * Size of the current ITree
     *
     * @return the current size (number of elements)
     */
    int size();

    /**
     * Pass the current contents of the ITree into an array of the declared type
     *
     * @return an array of all ITree items
     */
    E[] toArray();

    @Override
    boolean equals(Object obj);

    interface TreeEntry<E> {

        /**
         * Returns the value of the Node
         *
         * @return the value
         */
        E value();

        /**
         * The left child of the Node
         *
         * @return the value of left child
         */
        E left();

        /**
         * The right child of the Node
         *
         * @return the value of right child
         */
        E right();

        /**
         * Parent value of this Node
         *
         * @return the parent of this Node
         */
        E parent();

        @Override
        boolean equals(Object obj);

        @Override
        int hashCode();
    }
}
