package ICollection;

import java.util.Arrays;

/**
 *
 * @date Jul 30, 2014
 * @author Chris Medina
 *
 * Provides a skeletal (partial) implementation of the ITree interface
 */
public abstract class AbstractITree<E> implements ITree<E> {

    @Override
    public abstract int size();

    @Override
    public boolean contains(E item) {
        E[] arr = (E[]) toArray();
        for (E e : arr) {
            if (e.equals(item))
                return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(ICollection<? extends E> collection) {
        IEnumerator<? extends E> it = collection.iterator();
        while (it.hasNext()) {
            E temp = it.next();
            if (!contains(temp))
                return false;
        }
        return true;
    }

    @Override
    public void addAll(ICollection<? extends E> collection) {
        IEnumerator<? extends E> it = collection.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
    }

    @Override
    public abstract void clear();

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public abstract E removeRoot();

    @Override
    public E remove(E item) {
        throw new UnsupportedOperationException("Cannot perform specific removal here");
    }

    @Override
    public abstract void add(E item);

    @Override
    public abstract E max();

    @Override
    public abstract E min();

    @Override
    public abstract E root();

    @Override
    public abstract E[] toArray();

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    /**
     * Exception thrown when user expects a returned value but Tree is empty
     */
    protected static class TreeEmptyException extends RuntimeException {

        public TreeEmptyException() {
            super();
        }

        public TreeEmptyException(String message) {
            super(message);
        }
    }

}
