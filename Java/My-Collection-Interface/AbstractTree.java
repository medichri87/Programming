package ADT;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/27/2014 <p>
 * Class Purpose:
 */
public abstract class AbstractTree<E> extends EAbstractCollection<E> implements Tree<E> {
    /**
     * Empty the entire Tree, removing all items
     */
    public abstract void clear ();

    /**
     * Remove this element from the Tree
     *
     * @param o the value to remove
     * @return true, if found and removed
     */
    public boolean remove (E o) {
        throw new UnsupportedOperationException();
    }

    /**
     * Remove the root from the Tree
     *
     * @return true, if found and removed
     */
    public abstract E remove ();

    /**
     * The size of the current Tree
     *
     * @return the number of elements found
     */
    public abstract int size ();

    /**
     * An iterator that provides for sequential traversal of the Tree
     *
     * @return a new iterator from the starting point of the current Tree
     */
    public abstract EIterator<E> iterator ();

    /**
     * Find the max value in the current Tree
     *
     * @return the max value in the Tree
     */
    public E max () {
        TreeIterator<E> it = treeIterator();
        E temp = null;
        if (isEmpty())
            return null;
        while (it.hasNextRight())
            temp = it.right();
        return temp;
    }


    /**
     * Find the min value in the current Tree
     *
     * @return the min value in the Tree
     */
    public E min () {
        TreeIterator<E> it = treeIterator();
        E temp = null;
        if (isEmpty())
            return null;
        while (it.hasNextLeft())
            temp = it.left();
        return temp;
    }

    /**
     * Retrive the root value from the Tree
     *
     * @return the root/first value
     */
    public abstract E root ();

    /**
     * Determine if this value has a left child
     *
     * @param val the value to find a left child for
     * @return true, if this value in the tree has a value (non-null) to the left
     */
    public abstract boolean hasLeftChild (E val);

    /**
     * Determine if this value has two children(a node value to the left and the right of it)
     *
     * @param val the value to determine if there are two children for
     * @return true, if this value has two children
     */
    public abstract boolean hasTwoChildren (E val);

    /**
     * Return the number of levels/depths contained in this Tree
     *
     * @return the # of levels for the Tree
     */
    public int depth () {
        throw new UnsupportedOperationException();
    }

    /**
     * Determine if the current Tree is balanced(i.e. - left half and right half no more than 1 level depth difference)
     *
     * @return true, if the tree is balanced
     */
    public boolean isBalanced () {
        throw new UnsupportedOperationException();
    }

    /**
     * Determine if this value has a right child
     *
     * @param val the value to find a right child for
     * @return true, if this value in the tree has a value (non-null) to the right
     */
    public abstract boolean hasRightChild (E val);

    /**
     * Find the parent for this value in the Tree
     *
     * @param val the value to return a parent for
     * @return the parent value, or null if not found
     */
    public abstract E parent (E val);

    /**
     * Find the left child for this value (value one to the left)
     *
     * @param val the value to return a left child for
     * @return the the left child for this value, or null if not found
     */
    public abstract E leftChild (E val);

    /**
     * Find the right child for this value (value one to the right)
     *
     * @param val the value to return a right child for
     * @return the the right child for this value, or null if not found
     */
    public abstract E rightChild (E val);

    /**
     * Search for this value within the Tree
     *
     * @param val the value to search for
     * @return the value if found, null if not found
     */
    public abstract E search (E val);

    /**
     * A special iterator necessary for traversing any Tree type
     *
     * @return an iterator that allows for specialized traversal for Tree types
     */
    public abstract TreeIterator<E> treeIterator ();

}
