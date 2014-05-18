import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by Chris Medina on 5/14/2014.
 * Purpose: An array-based implementation of a Stack
 */
public class Stack<E> {
    private int size;
    private int max_size;
    private E[] array;

    public Stack (int max) {
        this.max_size = max;
        this.size = 0;
        this.array = (E[]) new Object[max_size];
    }

    public Stack () {
        this(10);
    }

    /**
     * Determine if this Stack is empty
     *
     * @return true, if empty
     */
    public boolean isEmpty () {
        return (size == 0);
    }

    /**
     * Determine if this Stack is full
     *
     * @return true, if empty
     */
    public boolean isFull () {
        return (size == max_size);
    }

    public void ensureCapacity () {
        if (isFull()) {
            max_size *= 3;
            array = Arrays.copyOf(array, max_size);
        }
    }

    /**
     * Size of the current Stack
     *
     * @return the number of elements on the Stack
     */
    public int size () {
        return size;
    }

    /**
     * Push this value onto the top of the Stack
     *
     * @param val
     */
    public void push (E val) {
        ensureCapacity();
        array[size++] = val;
    }

    /**
     * Remove the last-inserted value from the Stack
     *
     * @return the element last-inserted
     */
    public E pop () {
        if (isEmpty())
            throw new NoSuchElementException("Stack is empty");
        E temp = array[--size];
        array = Arrays.copyOfRange(array, 0, size);
        return temp;
    }

    /**
     * Show the next element to be removed from the Stack
     *
     * @return the value to be removed next
     */
    public E peek () {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return array[size - 1];
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

    /**
     * Sort a stack
     *
     * @return a sorted Stack
     */
    public Stack<E> sort () {
        Stack<E> buf = new Stack<>();
        while (!this.isEmpty()) {
            E temp = this.pop();
            while (!buf.isEmpty() && ((Comparable) temp).compareTo(buf.peek()) < 0) {
                this.push(buf.pop());
            }
            buf.push(temp);
        }
        return buf;
    }

}
