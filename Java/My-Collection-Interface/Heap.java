package ADT;

import java.util.Arrays;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/28/2014 <p>
 * Class Purpose: Represents a Max-Heap data structure, wherby larger values are inserted close to the top relative to
 * the root value. Useful for making a Priorty queue
 */
public class Heap<E> extends AbstractTree<E> implements Tree<E> {

    private int size;
    private Node<E>[] array;
    private int currentMaxSize;
    private static final int DEFAULT_MAX_SIZE = 10;

    /**
     * Construct a default length Heap
     */
    public Heap () {
        size = 0;
        currentMaxSize = DEFAULT_MAX_SIZE;
        array = new Node[DEFAULT_MAX_SIZE];
    }

    /**
     * Construct a Heap with a specific start length
     *
     * @param startLen the starting length of the Heap
     */
    public Heap (int startLen) {
        size = 0;
        currentMaxSize = startLen;
        array = new Node[startLen];
    }

    /**
     * Create a Heap from the Object array of values
     *
     * @param in the array of values to insert into the Queue
     */
    public Heap (Object[] in) {
        this(in.length);
        for (Object temp : in)
            add((E) temp);
    }

    /**
     * Create a Heap from a Collection input
     *
     * @param c the Collection to insert into the Heap
     */
    public Heap (ECollection<? extends E> c) {
        this(c.size());
        EIterator<? extends E> it = c.iterator();
        while (it.hasNext())
            add(it.next());
    }

    /**
     * Empty the entire Collection, removing all items
     */
    public void clear () {
        currentMaxSize = DEFAULT_MAX_SIZE;
        size = 0;
        for (int i = 0; i < array.length; i++)
            array[i] = null;
        System.gc();
    }

    /**
     * Add this element to the Heap
     *
     * @param e value to add
     */
    public void add (E e) {
        ensureCapacity();
        Node<E> node = new Node<E>(e);
        array[size] = node;
        trickleUp(size++);
    }

    /**
     * Double in size if current max size is reached
     */
    private void ensureCapacity () {
        if (size == currentMaxSize) {
            currentMaxSize = currentMaxSize * 2;
            array = Arrays.copyOf(array, currentMaxSize);
        }
    }

    private void trickleDown (int index) {
        int largerChild;
        Node<E> top = array[index];

        while (index < (size / 2)) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            //find larger child
            if (rightChild < size && ((Comparable<E>) array[leftChild].item).compareTo(array[rightChild].item) < 0)
                largerChild = rightChild;
            else
                largerChild = leftChild;

            if (((Comparable<E>) top.item).compareTo(array[largerChild].item) >= 0)
                break;

            array[index] = array[largerChild];
            index = largerChild;
        }
        array[index] = top;
    }

    private void trickleUp (int index) {
        int parent = (index - 1) / 2;
        Node<E> bottom = array[index];
        while (index > 0 && ((Comparable<E>) array[parent].item).compareTo(bottom.item) < 0) {
            array[index] = array[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        array[index] = bottom;
    }

    /**
     * Remove the root from the Tree
     *
     * @return true, if found and removed
     */
    public E remove () {
        if (isEmpty())
            return null;
        Node<E> root = array[0];
        array[0].setItem(array[--size].item);
        trickleDown(0);
        return root.item;
    }

    /**
     * The size of the current Heap
     *
     * @return the number of elements found
     */
    public int size () {
        return size;
    }

    /**
     * An iterator that provides for sequential traversal of the Heap
     *
     * @return a new iterator from the starting point of the current Heap
     */
    public EIterator<E> iterator () {
        return treeIterator();
    }

    /**
     * Find the max value in the current Heap
     *
     * @return the max value in the Heap
     */
    public E max () {
        if (isEmpty())
            return null;
        return (array[0].item);
    }

    /**
     * Find the min value in the current Heap
     *
     * @return the min value in the Heap
     */
    public E min () {
        if (isEmpty())
            return null;
        E min = array[0].item;
        for (int i = 1; i < size; i++) {
            if (((Comparable) array[i].item).compareTo(min) < 0)
                min = array[i].item;
        }
        return min;
    }

    /**
     * Retrive the root value from the Heap
     *
     * @return the root/first value
     */
    public E root () {
        if (isEmpty())
            return null;
        return (array[0].item);
    }

    /**
     * Determine if this value has a left child
     *
     * @param val the value to find a left child for
     * @return true, if this value in the tree has a value (non-null) to the left
     */
    public boolean hasLeftChild (E val) {
        if (isEmpty())
            return false;
        int index = indexOf(val);
        if (index >= 0) {
            int leftChild = 2 * index + 1;
            if (leftChild >= 0 && leftChild < size) {
                return array[leftChild].item != null;
            }
        }
        return false;
    }

    /**
     * Find the index associated with this item in the Heap
     *
     * @param item the item to retrive the index for
     * @return the index that this value is found at within the Heap
     */
    public int indexOf (E item) {
        for (int i = 0; i < size; i++) {
            if (array[i].item.equals(item))
                return i;
        }
        return -1;
    }

    /**
     * Determine if this value has a right child
     *
     * @param val the value to find a right child for
     * @return true, if this value in the tree has a value (non-null) to the right
     */
    public boolean hasRightChild (E val) {
        if (isEmpty())
            return false;
        int index = indexOf(val);
        if (index >= 0) {
            int rightChild = (2 * index + 1) + 1;
            if (index >= 0 && index < size) {
                return array[rightChild].item != null;
            }
        }
        return false;
    }

    /**
     * Determine if this value has two children(a node value to the left and the right of it)
     *
     * @param val the value to determine if there are two children for
     * @return true, if this value has two children
     */
    public boolean hasTwoChildren (E val) {
        if (isEmpty())
            return false;
        int index = indexOf(val);
        if (index >= 0) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            if ((leftChild >= 0 && leftChild < size) && (rightChild >= 0 && rightChild < size))
                return array[leftChild].item != null && array[rightChild].item != null;
        }
        return false;
    }

    /**
     * Find the parent for this value in the Heap
     *
     * @param val the value to return a parent for
     * @return the parent value, or null if not found
     */
    public E parent (E val) {
        if (isEmpty())
            return null;
        for (int i = 0; i < array.length; i++) {
            if (array[i].item.equals(val)) {
                return array[(i - 1) / 2].item;
            }
        }
        return null;
    }

    /**
     * Find the left child for this value (value one to the left)
     *
     * @param val the value to return a left child for
     * @return the the left child for this value, or null if not found
     */
    public E leftChild (E val) {
        if (isEmpty())
            return null;
        int index = indexOf(val);
        if (index >= 0) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            return array[leftChild].item;
        }
        return null;
    }

    /**
     * Find the right child for this value (value one to the right)
     *
     * @param val the value to return a right child for
     * @return the the right child for this value, or null if not found
     */
    public E rightChild (E val) {
        if (isEmpty())
            return null;
        int index = indexOf(val);
        if (index >= 0) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            return array[rightChild].item;
        }
        return null;
    }

    /**
     * Search for this value within the Heap
     *
     * @param val the value to search for
     * @return the value if found, null if not found
     */
    public E search (E val) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].item.equals(val))
                return array[i].item;
        }
        return null;
    }

    /**
     * A special iterator necessary for traversing any Heap type
     *
     * @return an iterator that allows for specialized traversal for Heap types
     */
    public TreeIterator<E> treeIterator () {
        return new TreeItr();
    }

    private static class Node<E> {
        E item;

        private Node (E item) {
            this.item = item;
        }

        public E getItem () {
            return item;
        }

        public void setItem (E val) {
            this.item = val;
        }

        @Override
        public String toString () {
            return String.format("%s", item);
        }
    }

    private class TreeItr implements TreeIterator<E> {
        private Node<E> current;
        private int index;

        private TreeItr () {
            index = 0;
            current = array[index];
        }

        /**
         * Reset the iterator to the beginning
         */
        public void reset () {
            index = 0;
            current = null;
        }

        /**
         * Proceed and return the next element in the current Heap
         *
         * @return the next element
         */
        public E next () {
            if (hasNext())
                return array[index++].item;
            return null;
        }

        /**
         * Determines if there is a value to move forward to
         *
         * @return true, if the next value exists(non-null)
         */
        public boolean hasNext () {
            if (isEmpty())
                return false;
            return (index < size);
        }

        /**
         * Move left to the next element
         *
         * @return the left element, or null if not found
         */
        public E left () {
            if (hasNextLeft()) {
                current = array[--index];
                return current.item;
            }
            return null;
        }

        /**
         * Move right to the next element
         *
         * @return the right element, or null if not found
         */
        public E right () {
            return next();
        }

        /**
         * Determine if there is any non-null value to the left of this element
         *
         * @return true, if there is a non-null value to the left of this
         */
        public boolean hasNextLeft () {
            if (isEmpty())
                return false;
            return (index > 0);
        }

        /**
         * Determine if there is any non-null value to the right of this element
         *
         * @return true, if there is a non-null value to the right of this
         */
        public boolean hasNextRight () {
            return hasNext();
        }

        /**
         * Get the parent value for this value
         *
         * @return the parent value (i.e. value directly above)
         */
        public E parent () {
            if (isEmpty())
                return null;
            return (array[(index - 1)].item);
        }
    }
}
