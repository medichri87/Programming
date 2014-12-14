package ICollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @date Jul 25, 2014
 * @author Chris Medina
 * @purpose Provides a skeletal(partial) implemntation of the IDictionary
 * interface
 */
public abstract class AbstractIDictionary<K, V> implements IDictionary<K, V> {

    @Override
    public int size() {
        return entries().size(); //toArray().length
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public abstract boolean containsKey(K key);

    @Override
    public boolean containsValue(V value) {
        List<KeyValuePair<K, V>> list = entries();
        for (KeyValuePair<K, V> pair : list) {
            if (pair.getValue().equals(value))
                return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");

        List<KeyValuePair<K, V>> list = entries();

        for (KeyValuePair<K, V> pair : list) {
            if (pair.getKey().equals(key))
                return pair.getValue();
        }

        throw new NoSuchElementException("Key not found");
    }

    @Override
    public abstract boolean put(K key, V value);

    @Override
    public abstract V remove(K key);

    @Override
    public abstract void clear();

    @Override
    public List<K> keys() {
        List<KeyValuePair<K, V>> list = entries();
        List<K> keys = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            keys.add(list.get(i).getKey());
        }

        return keys;
    }

    @Override
    public List<V> values() {
        List<KeyValuePair<K, V>> list = entries();
        List<V> vals = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            vals.add(list.get(i).getValue());
        }

        return vals;
    }

    @Override
    public abstract List<KeyValuePair<K, V>> entries();

    @Override
    public Object[] toArray() {
        return entries().toArray();
    }

    @Override
    public String toString() {
        List<KeyValuePair<K, V>> list = entries();
        StringBuilder sb = new StringBuilder("{");

        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1)
                sb.append(list.get(i)).append(", ");
            else
                sb.append(list.get(i));
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj.getClass() != this.getClass() || obj == null)
            return false;
        IDictionary<K, V> map = (IDictionary<K, V>) obj;
        return Arrays.equals(this.toArray(), map.toArray());
    }

}
