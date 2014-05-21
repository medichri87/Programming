package ADT;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/27/2014 <p>
 * Class Purpose:
 */
public interface Tree<E> extends ECollection<E> {

    /**
     * Find the max value in the current Tree
     *
     * @return the max value in the Tree
     */
    E max ();

    /**
     * Find the min value in the current Tree
     *
     * @return the min value in the Tree
     */
    E min ();

    /**
     * Retrive the root value from the Tree
     *
     * @return the root/first value
     */
    E root ();

    /**
     * Determine if this value has a left child
     *
     * @param val the value to find a left child for
     * @return true, if this value in the tree has a value (non-null) to the left
     */
    boolean hasLeftChild (E val);

    /**
     * Determine if this value has a right child
     *
     * @param val the value to find a right child for
     * @return true, if this value in the tree has a value (non-null) to the right
     */
    boolean hasRightChild (E val);

    /**
     * Determine if this value has two children(a node value to the left and the right of it)
     *
     * @param val the value to determine if there are two children for
     * @return true, if this value has two children
     */
    boolean hasTwoChildren (E val);

    /**
     * Return the number of levels/depths contained in this Tree
     *
     * @return the # of levels for the Tree
     */
    int depth ();

    /**
     * Determine if the current Tree is balanced(i.e. - left half and right half no more than 1 level depth difference)
     *
     * @return true, if the tree is balanced
     */
    boolean isBalanced ();

    /**
     * Find the parent for this value in the Tree
     *
     * @param val the value to return a parent for
     * @return the parent value, or null if not found
     */
    E parent (E val);

    /**
     * Find the left child for this value (value one to the left)
     *
     * @param val the value to return a left child for
     * @return the the left child for this value, or null if not found
     */
    E leftChild (E val);

    /**
     * Find the right child for this value (value one to the right)
     *
     * @param val the value to return a right child for
     * @return the the right child for this value, or null if not found
     */
    E rightChild (E val);

    /**
     * Search for this value within the Tree
     *
     * @param val the value to search for
     * @return the value if found, null if not found
     */
    E search (E val);

    /**
     * Pass the current Collection off to an Object array
     *
     * @return an Object array consisting of the value found within the Collection
     */
    Object[] toArray ();

    /**
     * Add this entire argument Collection to the current Collection
     *
     * @param c the collection to add in its entirety
     */
    void addAll (ECollection<E> c);

    /**
     * Determine if the every element in the argument Collection is found within this Collection
     *
     * @param c the collection to find within
     * @return true, if this entire collection is found (as a subset)
     */
    boolean containsAll (ECollection<E> c);

    /**
     * A special iterator necessary for traversing any Tree type
     *
     * @return an iterator that allows for specialized traversal for Tree types
     */
    TreeIterator<E> treeIterator ();

    /**
     * Remove the root from the Tree
     *
     * @param o the value to remove
     * @return true, if found and removed
     */
    E remove ();
}
