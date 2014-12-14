package ICollection;

import java.util.NoSuchElementException;

/**
 * @date Jul 27, 2014
 * @author Chris Medina
 * @purpose
 *
 * Provides a skeletal (partial) implementation of the IStack interface
 */
public abstract class AbstractIStack<E> extends AbstractICollection<E> implements IStack<E> {

    @Override
    public abstract void clear();

    @Override
    public abstract IEnumerator<E> iterator();

    @Override
    public E remove(E item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public abstract void add(E item);

    @Override
    public abstract int size();

    @Override
    public abstract void push(E item);

    @Override
    public E peek() {
        if (isEmpty())
            throw new NoSuchElementException("Stack is empty");
        IEnumerator<E> it = iterator();
        E item = null;
        while (it.hasNext()) {
            item = it.next();
        }
        return item;
    }

    @Override
    public abstract E pop();

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (this.getClass() != obj.getClass() || obj == null) {
            return false;
        }
        IStack<E> temp = (IStack<E>) obj;
        if (this.size() != temp.size())
            return false;

        IEnumerator<E> orig = this.iterator();
        IEnumerator<E> other = temp.iterator();

        while (orig.hasNext()) {
            E item1 = orig.next();
            E item2 = other.next();
            if (!item1.equals(item2))
                return false;
        }
        return true;
    }

}
