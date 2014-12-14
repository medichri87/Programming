package ICollection;

import java.util.Arrays;

/**
 *
 * @date Jul 29, 2014
 * @author Chris Medina
 *
 * Represents a data structure which is an array-based implementation of the
 * IQueue interface. Queues are a FIFO (First-In, First-Out) structure which
 * removes items in the order they are inserted. This implementation
 * automatically expands when the present max size is reached.
 */
public class ArrayIQueue<E> extends AbstractIQueue<E> implements IQueue<E> {

    private int size;
    private E[] array;
    private static final int DEFAULT_MAX_SIZE = 10;
    private int currMaxSize;

    public ArrayIQueue() {
        this(10);
    }

    public ArrayIQueue(int ms) {
        size = 0;
        currMaxSize = ms;
        array = (E[]) new Object[ms];
    }

    public ArrayIQueue(E[] arr) {
        this(arr.length);
        for (E item : arr) {
            enqueue(item);
        }
    }

    public ArrayIQueue(ICollection<E> collection) {
        this(collection.toArray());
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        array = Arrays.copyOf(array, DEFAULT_MAX_SIZE);
        size = 0;
        currMaxSize = DEFAULT_MAX_SIZE;
    }

    @Override
    public IEnumerator<E> iterator() {
        return new QueueItr();
    }

    @Override
    public void add(E item) {
        enqueue(item);
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
    public void enqueue(E item) {
        checkForExpansion();
        array[size++] = item;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new QueueEmptyException("Queue is empty");
        E temp = array[0];
        array[0] = null;
        array = Arrays.copyOfRange(array, 1, size--);
        return temp;
    }

    private class QueueItr implements IEnumerator<E> {

        private int index;

        public QueueItr() {
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
