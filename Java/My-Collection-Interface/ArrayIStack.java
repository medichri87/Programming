package ICollection;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @date Jul 27, 2014
 * @author Chris Medina
 * @purpose
 *
 * Represents a data structure which is an array-based implementation of the
 * IStack interface. Stacks are a LIFO (Last-In, First-Out) structure which
 * removes the most recent item first. This implementation automatically expands
 * when the present max size is reached.
 */
public class ArrayIStack<E> extends AbstractIStack<E> implements IStack<E> {

    private int size;
    private E[] array;
    private static final int DEFAULT_MAX_SIZE = 10;
    private int currMaxSize;

    public ArrayIStack() {
        this(10);
    }

    public ArrayIStack(int ms) {
        this.size = 0;
        this.currMaxSize = ms;
        this.array = (E[]) new Object[ms];
    }

    public ArrayIStack(E[] array) {
        this(array.length);
        for (E entry : array) {
            push(entry);
        }
    }

    public ArrayIStack(ICollection<E> collection) {
        this(collection.size());
        addAll(collection);
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
    public IEnumerator<E> iterator() {
        return new StackItr();
    }

    @Override
    public void add(E item) {
        push(item);
    }

    private void checkForExpansion() {
        if (size == currMaxSize) {
            array = Arrays.copyOf(array, (currMaxSize *= 3));
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(E item) {
        checkForExpansion();
        array[size++] = item;
    }

    @Override
    public E pop() {
        if (isEmpty())
            throw new NoSuchElementException("Stack is empty");
        E temp = array[size - 1];
        array[--size] = null;
        return temp;
    }

    private class StackItr implements IEnumerator<E> {

        private int index;

        public StackItr() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return (index < size);
        }

        @Override
        public E next() {
            return array[index++];
        }

        @Override
        public void reset() {
            index = 0;
        }

    }

}
