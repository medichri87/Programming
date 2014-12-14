package ICollection;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @date Jul 26, 2014
 * @author Chris Medina
 * @purpose
 *
 * Represents a data structure which is an array-based implementation of the
 * IList interface. Index-based (specific access) insertion, searching, and
 * deletion are permitted. When the present max size is reached of this
 * structure, it will automatically expand to fufill the needs of the user.
 */
public class ArrayIList<E> extends AbstractIList<E> implements IList<E> {

    private int size;
    private E[] array;
    private static final int DEFAULT_MAX_SIZE = 10;
    private int currMaxSize;

    public ArrayIList() {
        this(10);
    }

    public ArrayIList(int ms) {
        currMaxSize = ms;
        size = 0;
        array = (E[]) new Object[ms];
    }

    private void checkForExpansion() {
        if (size == currMaxSize) {
            array = Arrays.copyOf(array, (currMaxSize *= 3));
        }
    }

    public E removeIndex(int index) {
        checkIndex(index);
        E temp = array[index];
        for (int i = index; i < size() - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return temp;
    }

    @Override
    public void set(int index, E item) {
        checkIndex(index);
        array[index] = item;
    }

    @Override
    public void addIndex(int index, E item) {
        checkIndex(index);
        checkForExpansion();
        for (int i = size(); i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = item;
        size++;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size(); i++) {
            array[i] = null;
        }
        size = 0;
        currMaxSize = DEFAULT_MAX_SIZE;
    }

    @Override
    public IEnumerator<E> iterator() {
        return new Itr();
    }

    @Override
    public E remove(E item) {
        int index = indexOf(item);
        if (index == -1)
            throw new NoSuchElementException("Item not found");
        return removeIndex(index);
    }

    @Override
    public void add(E item) {
        checkForExpansion();
        array[size++] = item;
    }

    @Override
    public int size() {
        return size;
    }

    private class Itr implements IEnumerator<E> {

        private int index;

        public Itr() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return (index < size());
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
