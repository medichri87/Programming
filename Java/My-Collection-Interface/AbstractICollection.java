package ICollection;

/**
 * @date Jul 26, 2014
 * @author Chris Medina
 * @purpose Provides a skeletal (partial) implementation of the ICollection interface
 */
public abstract class AbstractICollection<E> implements ICollection<E> {

    @Override
    public abstract void clear();

    @Override
    public abstract IEnumerator<E> iterator();

    @Override
    public abstract E remove(E item);

    @Override
    public abstract void add(E item);

    @Override
    public abstract int size();

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public boolean contains(E item) {
        IEnumerator<E> it = this.iterator();
        while (it.hasNext()) {
            if (it.next().equals(item))
                return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(ICollection<E> collection) {
        IEnumerator<E> it = collection.iterator();
        while (it.hasNext()) {
            E next = it.next();
            if (!this.contains(next))
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
    public E[] toArray() {
        E[] out = (E[]) new Object[size()];
        IEnumerator<E> it = iterator();

        int i = 0;
        while (it.hasNext()) {
            out[i++] = it.next();
        }
        return out;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        IEnumerator<E> it = iterator();

        while (it.hasNext()) {
            E item = it.next();
            if (it.hasNext())
                sb.append(item).append(", ");
            else
                sb.append(item);
        }

        sb.append("]");
        return sb.toString();
    }

    @Override
    /*Two ICollections are considered equal if they are the same size and all items in the ICollection are in the correct order. 
     For a more specific equality test, subclasses will override this implementation. */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        ICollection<E> temp = (ICollection<E>) obj;
        if (temp.size() != this.size())
            return false;
        IEnumerator<E> one = this.iterator();
        IEnumerator<E> two = temp.iterator();

        while (one.hasNext()) {
            E oneVal = one.next();
            E twoVal = two.next();
            if (oneVal != twoVal)
                return false;
        }
        return true;
    }

}
