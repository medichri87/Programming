package ICollection;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * MaxHeap is a max-heap implementation of the ITree interface. In this heap,
 * the root value will always be the maximum value because every item inserted
 * into the heap will move up correctly into its place (above all items smaller
 * than itself) in the array, which is important for implementing priority
 * queues.
 *
 * @param <E> generic, type-safe form
 * @date Jul 30, 2014
 * @author Chris Medina
 */
public class MaxHeap<E> extends AbstractITree<E> implements ITree<E> {

    private TreeNode<E>[] array;
    private int size;
    private static final int DEFAULT_MAX_SIZE = 10;
    private int currMaxSize;

    public MaxHeap() {
        this(10);
    }

    public MaxHeap(int ms) {
        array = new TreeNode[ms];
        size = 0;
        currMaxSize = ms;
    }

    public MaxHeap(E[] array) {
        this(array.length);
        for (E item : array) {
            add(item);
        }
    }

    public MaxHeap(ICollection<E> collection) {
        this(collection.toArray());
    }

    @Override
    public E root() {
        if (isEmpty())
            throw new NoSuchElementException("Not supported yet.");
        return array[0].value;
    }

    @Override
    public E min() {
        if (isEmpty())
            throw new NoSuchElementException("Heap is empty");
        E temp = array[0].value;
        for (int i = 1; i < size; i++) {
            int cmp = ((Comparable<E>) array[i].value).compareTo(temp);
            if (cmp == -1)
                temp = array[i].value;
        }
        return temp;
    }

    @Override
    public E max() {
        return root();
    }

    @Override
    public void add(E item) {
        checkForExpansion();
        TreeNode<E> node = new TreeNode<>(item);
        array[size] = node;
        trickleUp(size++);
    }

    private void checkForExpansion() {
        if (size == currMaxSize) {
            array = Arrays.copyOf(array, (currMaxSize *= 3));
        }
    }

    private void trickleUp(int index) {
        TreeNode<E> bottom = array[index];
        int parent = (index - 1) / 2;

        while (index > 0) {
            int cmp = ((Comparable<E>) bottom.value).compareTo(array[parent].value);
            if (cmp > 0) {
                //swap parent with bottom
                array[index] = array[parent];
                array[parent] = bottom;
            } else
                break;
            index = parent;
            parent = (parent - 1) / 2;
        }

        array[index] = bottom;
    }

    private void trickleDown(int index) {
        TreeNode<E> top = array[0];
        int largerChild = -1, leftChild = 0, rightChild = 0;

        while (index < size / 2) {
            leftChild = (2 * index + 1);
            rightChild = leftChild + 1;

            if (rightChild < size && ((Comparable<E>) array[rightChild].value).compareTo(array[leftChild].value) > 0) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }

            if (((Comparable<E>) top.value).compareTo(array[largerChild].value) >= 0) {
                break;
            }

            array[index] = array[largerChild];
            index = largerChild;
        }

        array[index] = top;
    }

    @Override
    public E removeRoot() {
        if (isEmpty())
            throw new TreeEmptyException("Tree is empty");
        TreeNode<E> temp = array[0];
        array[0] = array[--size];
        trickleDown(0);
        return temp.value;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }

        size = 0;
        currMaxSize = DEFAULT_MAX_SIZE;
        array = Arrays.copyOf(array, DEFAULT_MAX_SIZE);
    }

    @Override
    public int size() {
        return size;
    }

    public IEnumerator<E> iterator() {
        return new TreeItr();
    }

    @Override
    public E[] toArray() {
        E[] out = (E[]) new Object[size()];

        for (int i = 0; i < size(); i++) {
            out[i] = array[i].value;
        }

        return out;
    }

    private static final class TreeNode<E> {

        E value;

        public TreeNode(E value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 67 * hash + Objects.hashCode(this.value);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            final TreeNode<E> other = (TreeNode<E>) obj;
            return Objects.equals(this.value, other.value);
        }

        @Override
        public String toString() {
            return String.valueOf(this.value);
        }

        public void setValue(E value) {
            this.value = value;
        }

    }

    private class TreeItr implements IEnumerator<E> {

        private int index;

        public TreeItr() {
            index = 0;
        }

        @Override
        public void reset() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return (index < size);
        }

        @Override
        public E next() {
            return array[index++].value;
        }

    }

}
