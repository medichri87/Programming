package ICollection;

/**
 * @date Jul 26, 2014
 * @author Chris Medina
 * @purpose
 *
 * Provides a skeletal (partial) implementation of the IList interface
 */
public abstract class AbstractIList<E> extends AbstractICollection<E> implements IList<E> {

    @Override
    public int lastIndexOf(E item) {
        IEnumerator<E> it = this.iterator();
        int idx = -1;
        int index = 0;
        while (it.hasNext()) {
            E temp = it.next();
            if (temp.equals(item)) {
                idx = index;
            }
            index++;
        }
        return idx;
    }

    @Override
    public int indexOf(E item) {
        IEnumerator<E> it = this.iterator();
        int index = 0;
        while (it.hasNext()) {
            E temp = it.next();
            if (temp.equals(item)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public abstract E removeIndex(int index);

    @Override
    public abstract void set(int index, E item);

    @Override
    public E get(int index) {
        checkIndex(index);
        IEnumerator<E> it = this.iterator();
        int idx = 0;
        E found = null;

        while (it.hasNext()) {
            E item = it.next();
            if (idx == index) {
                found = item;
                break;
            }
            idx++;
        }

        return found;
    }

    @Override
    public abstract void addIndex(int index, E item);

    @Override
    public IList<E> subList(int from, int to) {
        if (to > size() || from < 0 || to < 0 || from > to)
            throw new IndexOutOfBoundsException();

        IList<E> list = new ArrayIList<>();

        E[] arr = this.toArray();
        for (int i = from; i < to; i++) {
            list.add(arr[i]);
        }

        return list;
    }

    protected void checkIndex(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Index is out of range. Must not be less than 0 or greater/equal to the size of the particular IList");
    }

}
