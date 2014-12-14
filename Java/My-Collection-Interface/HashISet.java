package ICollection;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @param <E>
 * @date Jul 26, 2014
 * @author Chris Medina
 * @purpose
 *
 * HashISet represents a data structure which doesn't permit duplicate entries.
 * This implementation is backed by a HashIDictionary .
 */
public class HashISet<E> extends AbstractISet<E> implements ISet<E> {

    private IDictionary<E, Object> dictionary;

    //dummy, sentinel value useful for inserting a throw-away value
    private static final Object DUMMY = new Object();

    public HashISet() {
        this.dictionary = new HashIDictionary<>();
    }

    public HashISet(ICollection<E> collection) {
        this();
        addAll(collection);
    }

    public HashISet(E[] array) {
        this();
        for (E item : array) {
            add(item);
        }
    }

    @Override
    public int size() {
        return dictionary.size();
    }

    @Override
    public void add(E item) {
        dictionary.put(item, DUMMY);
    }

    @Override
    public E remove(E item) {
        if (dictionary.containsKey(item))
            dictionary.remove(item);
        throw new NoSuchElementException("Item not found");
    }

    @Override
    public void clear() {
        dictionary.clear();
    }

    @Override
    public IEnumerator<E> iterator() {
        return new Itr();
    }

    private class Itr implements IEnumerator<E> {

        private List<E> keys;
        private int index;

        public Itr() {
            index = 0;
            keys = dictionary.keys();
        }

        @Override
        public boolean hasNext() {
            return (index < keys.size());
        }

        @Override
        public E next() {
            return keys.get(index++);
        }

        @Override
        public void reset() {
            index = 0;
        }
    }

}
