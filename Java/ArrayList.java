import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by Chris Medina on 5/18/2014.
 *
 * <p>Purpose: ArrayList represents an expandable array that triples in potential size
 * when the current bound has been reached.</p>
 */
public class ArrayList<E> {
    private E[] array;
    private int size, max_size;

    /**
     * Create an ArrayList of a specific max length.
     *
     * @param max the starting maximum number of elements this ArrayList will accomadate.
     *            Once max size has been reached, the ArrayList will expand necessarily.
     */
    public ArrayList (int max) {
        this.max_size = max;
        this.size = 0;
        this.array = (E[]) new Object[max];
    }

    /**
     * Create an ArrayList with a default max size of 10
     */
    public ArrayList () {
        this(10);
    }

    /**
     * Determines if this ArrayList is empty (no items in the array)
     *
     * @return true, if the number of values in array is currently 0.
     */
    public boolean isEmpty () {
        return (size == 0);
    }

    /**
     * Determine if current ArrayList is full
     *
     * @return true, if the number of values has reached the current max.
     * Once current max size has been reached, the ArrayList will triple in size.
     */
    public boolean isFull () {
        return (size == max_size);
    }

    /**
     * Private use only:
     * If current max size is reached, the list triples in size.
     */
    private void ensureCapacity () {
        if (isFull()) {
            max_size *= 3;
            array = Arrays.copyOf(array, max_size);
        }
    }

    /**
     * Appends this item to the end of the ArrayList
     *
     * @param item the item to insert
     */
    public void add (E item) {
        ensureCapacity();
        array[size++] = item;
    }

    /**
     * Insert an element at a specific index within the ArrayList
     *
     * @param item  the item to insert
     * @param index the index to insert this value into within the ArrayList
     * @throws java.lang.ArrayIndexOutOfBoundsException if index is out of bounds
     */
    public void insertAt (E item, int index) {
        if (index > size)
            throw new ArrayIndexOutOfBoundsException();
        else if (index == size)
            add(item);
        else {
            ensureCapacity();
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = item;
            size++;
        }
    }

    /**
     * Remove this value from the ArrayList
     *
     * @param item the item to remove
     * @return the item removed
     * @throws java.util.NoSuchElementException if value not found
     */
    public E remove (E item) {
        int index = indexOf(item);
        return removeIndex(index);
    }

    /**
     * Remove a value from a certain index
     *
     * @param index the index in the ArrayList to remove an item from
     * @return the item removed
     * @throws java.util.NoSuchElementException if item is not found within the ArrayList
     */
    public E removeIndex (int index) {
        E temp = null;
        if (index < 0 || index >= size) {
            throw new NoSuchElementException("Item not found");
        } else {
            temp = array[index];
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            array = Arrays.copyOf(array, array.length - 1);
        }
        size--;
        return temp;
    }

    /**
     * Find the first index for which this item is found within the ArrayList
     *
     * @param item the item to search for
     * @return the first index where the value is found within the array, or -1 if not found
     */
    public int indexOf (E item) {
        for (int i = 0; i < size; i++) {
            if (array[i] == item)
                return i;
        }
        return -1;
    }

    /**
     * Find the last index for which this item is found within the ArrayList
     *
     * @param item the item to search for
     * @return the last index where the value is found within the array, or -1 if not found
     */
    public int lastIndexOf (E item) {
        int last = -1;
        for (int i = 0; i < size; i++) {
            if (array[i] == item)
                last = i;
        }
        return last;
    }

    /**
     * Determine if this ArrayList contains a certain value
     *
     * @param item the value to search for within this List
     * @return true, if found
     */
    public boolean contains (E item) {
        for (int i = 0; i < size; i++) {
            if (array[i] == item)
                return true;
        }
        return false;
    }

    public String toString () {
        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                sb.append(array[i]);
            } else
                sb.append(array[i] + ", ");
        }
        sb.append("]");
        return sb.toString();
    }

}
