package ADT;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/23/2014 <p>
 * Class Purpose: Constructs an expandable array(similar to java.util.Arraylist)
 */
public class EArrayList<E> extends EAbstractList<E> implements EList<E> {
    private E[] array;
    private int max_size;
    private final int DEFAULT_MAX_SIZE = 10;
    private int size;

    /**
     * Construct a default elastic array with a default size of 10
     */
    public EArrayList () {
        this.size = 0;
        this.max_size = DEFAULT_MAX_SIZE;
        this.array = (E[]) new Object[max_size];
    }

    /**
     * Construct an elastic array from an array input
     *
     * @param array array input to construct from
     */
    public EArrayList (Object[] array) {
        this();
        this.max_size = array.length * 2;
        for (Object item : array)
            add((E) item);
    }

    /**
     * Insert each value from this Collection in sorted order
     *
     * @param in Collection of values to input into the Linked List
     */
    public EArrayList (ECollection<? extends E> c) {
        this();
        EIterator<? extends E> it = c.iterator();
        while (it.hasNext())
            add(it.next());
    }

    /**
     * Ensure the entry index is within the proper bounds
     *
     * @param index the index to determine if within bounds (0 ---> size - 1)
     * @return true, if index is within the proper bounds of the array
     */
    private boolean rangeCheck (int index) {
        if (index < 0 || index >= size)
            return false;
        return true;
    }

    /**
     * Remove the element at this index
     *
     * @param index the index of the item to remove
     * @return the item removed, if found
     */
    public E removeIndex (int index) {
        if (isEmpty())
            throw new NoSuchElementException("List is empty. Nothing to remove");
        if (!rangeCheck(index)) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }
        E temp = array[index];
        remove(array[index]);
        return temp;
    }

    /**
     * Helper method to ensure that the array grows when the current max size is reached(Doubles in size each time)
     */
    private void ensureCapacity () {
        if (size == max_size) {
            max_size = max_size * 2;
            array = Arrays.copyOf(array, max_size);
        }
    }

    /**
     * Append this value to the end of the current array
     *
     * @param e the item to append
     */
    public void add (E e) {
        ensureCapacity();
        array[size++] = e;
    }

    /**
     * Empty the entire current array
     * (size is now 0)
     */
    public void clear () {
        for (int i = 0; i < size; i++)
            array[i] = null;
        System.gc(); // not needed?
        this.size = 0;
        this.max_size = DEFAULT_MAX_SIZE;
    }

    /**
     * Determine if the array is empty
     *
     * @return true, if empty
     */
    public boolean isEmpty () {
        return (size == 0);
    }

    /**
     * Remove this value from the array, if found
     *
     * @param o the value to remove the array
     * @return true, if found and removed
     */
    public boolean remove (E o) {
        if (isEmpty())
            return false;
        else {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(o)) {
                    for (int j = i; j < size - 1; j++) {
                        array[j] = array[j + 1];
                    }
                    size--;
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * The size of the current List
     *
     * @return The number of elements found in this List
     */
    public int size () {
        return size;
    }


    /**
     * Add a value to the array at this index point(shifts array elements above index up)
     *
     * @param index the index to insert a value at
     * @param item  the item to insert
     */
    public void add (int index, E item) {
        if (index < 0 || index > size)
            throw new ArrayIndexOutOfBoundsException("Bad index range");
        else {
            ensureCapacity();
            if (index == size)
                add(item);
            else {
                for (int i = size; i > index; i--) {
                    array[i] = array[i - 1];
                }
                size++;
                array[index] = item;
            }
        }
    }

    /**
     * Get the value at this index within the current array
     *
     * @param index the index to return the value for
     * @return the value associated with this index in the array
     */
    public E get (int index) {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException("Bad index range");
        else {
            return array[index];
        }
    }

    /**
     * Find the first index of this value within the current array
     *
     * @param o the value to search for in the current array
     * @return the first index if found, -1 if not found
     */
    public int indexOf (E o) {
        int index = 0;
        EIterator<E> it = iterator();
        while (it.hasNext()) {
            if (it.next().equals(o))
                return index;
            index++;
        }
        return -1;
    }

    /**
     * An iterator of the array, which allows for sequential traversal of the current array
     *
     * @return a new iterator from its starting point(beginning of array)
     */
    public EIterator<E> iterator () {
        return new Itr();
    }

    /**
     * Supporting class that provides for the iteration of the current array elements(from beginning to end,
     * sequentially)
     */
    private class Itr implements EIterator<E> {
        private E next;
        private int index;

        private Itr () {
            index = 0;
            next = array[index];
        }

        public E next () {
            next = array[index++];
            return next;
        }

        public boolean hasNext () {
            return (index < size);
        }

        public void reset () {
            index = 0;
            next = array[index];
        }
    }

}











