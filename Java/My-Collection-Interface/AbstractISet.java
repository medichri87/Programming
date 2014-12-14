package ICollection;

/**
 * @date Jul 26, 2014
 * @author Chris Medina
 * @purpose
 */
public abstract class AbstractISet<E> extends AbstractICollection<E> implements ISet<E> {

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (this.getClass() != obj.getClass() || obj == null)
            return false;
        ISet<E> set = (ISet<E>) obj;
        if (set.size() != this.size())
            return false;

        E[] arr = set.toArray();
        E[] arr2 = this.toArray();
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].equals(arr2[i]))
                return false;
        }
        return true;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IEnumerator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E remove(E item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void add(E item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
