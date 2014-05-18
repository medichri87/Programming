import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by Chris Medina on 5/14/2014.
 * Purpose: Array-based implementation of a Queue
 */
public class Queue<E> {
    private int size;
    private int max_size;
    private E[] array;

    public Queue (int max) {
        this.max_size = max;
        this.size = 0;
        this.array = (E[]) new Object[max_size];
    }

    public Queue () {
        this(10);
    }

    /**
     * Determine if this Queue is empty
     *
     * @return true, if empty
     */
    public boolean isEmpty () {
        return (size == 0);
    }

    /**
     * Determine if this Queue is full
     *
     * @return true, if full
     */
    public boolean isFull () {
        return (size == max_size);
    }

    private void ensureCapacity () {
        if (isFull()) {
            max_size = max_size * 3;
            array = Arrays.copyOf(array, max_size);
        }
    }

    /**
     * Insert into this queue on a max-value priority basis, such that larger numbers appear at the front of the array
     *
     * @param val the value to insert
     */
    public void priority_insert (E val) {
        ensureCapacity();
        if (isEmpty()) {
            array[0] = val;
        } else {
            int j = size - 1;
            while (j >= 0 && ((Comparable) val).compareTo(array[j]) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = val;
        }
        size++;
    }

    /**
     * Insert this value into the rear of the array
     *
     * @param val the value to insert
     */
    public void insert (E val) {
        ensureCapacity();
        array[size++] = val;
    }

    /**
     * Remove this value from the front of the Queue
     *
     * @return the value removed
     */
    public E remove () {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");
        else {
            E temp = array[0];
            size--;
            System.arraycopy(array, 1, array, 0, size);
            return temp;
        }
    }

    /**
     * Displays the next value to be removed
     *
     * @return the first value in the array (next to be removed)
     */
    public E peek () {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");
        return array[0];
    }

    public int size () {
        return size;
    }

    public String toString () {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i < size - 1)
                sb.append(array[i] + ", ");
            else
                sb.append(array[i]);
        }
        sb.append("]");
        return sb.toString();
    }

}
