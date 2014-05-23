package ADT;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Created by Chris Medina on 5/22/2014.
 * <p>Purpose: ADT.ArrayMap representes an array-based implementation of the ADT.IMap interface,
 * which allows for insertion of key-value pairs (mappings). Duplicate and/or null keys are not permitted.</p>
 */
public class ArrayMap<K, V> implements IMap<K, V> {
    private int size;
    private final int DEFAULT_MAX_SIZE;
    private int curr_max;
    private DataItem<K, V>[] array;

    public ArrayMap (int start_max) {
        DEFAULT_MAX_SIZE = start_max;
        size = 0;
        curr_max = start_max;
        array = new DataItem[DEFAULT_MAX_SIZE];
    }

    /**
     * Determine is the array has reached the current limit
     *
     * @return true, if size has reached the current limit
     */
    public boolean isFull () {
        return (size == curr_max);
    }

    /**
     * Determine if the current Map is empty
     *
     * @return true, if there are no elements in the ADT.IMap
     */
    @Override
    public boolean isEmpty () {
        return (size == 0);
    }

    /**
     * Ensures that array capacity increase once current size limit has been reached.
     */
    public void ensureCapacity () {
        if (isFull()) {
            curr_max *= 3;
            array = Arrays.copyOf(array, curr_max);
        }
    }

    /**
     * Return the size of the current ADT.IMap
     *
     * @return the size of the ADT.IMap
     */
    @Override
    public int size () {
        return size;
    }

    /**
     * Provides an EList view of the entries of the current ADT.IMap
     *
     * @return EList view of the entries of the current ADT.IMap
     */
    @Override
    public EList<Entry<K, V>> entrySet () {
        EList<Entry<K, V>> set = new EArrayList<>();
        for (int i = 0; i < size; i++) {
            set.add(array[i]);
        }
        return set;
    }

    /**
     * Provides an EList view of the values of the current ADT.IMap
     *
     * @return EList view of the values of the current ADT.IMap
     */
    @Override
    public EList<V> values () {
        EList<V> set = new EArrayList<>();
        for (int i = 0; i < size; i++) {
            set.add(array[i].value);
        }
        return set;
    }

    /**
     * Provides an EList view of the keys of the current ADT.IMap
     *
     * @return EList view of the keys of the current ADT.IMap
     */
    @Override
    public EList<K> keySet () {
        EList<K> set = new EArrayList<>();
        for (int i = 0; i < size; i++) {
            set.add(array[i].key);
        }
        return set;
    }

    /**
     * Determine if the ADT.IMap contains a key
     *
     * @param key the key to search for
     * @return true, if the key is found
     */
    @Override
    public boolean containsKey (K key) {
        for (int i = 0; i < size; i++) {
            if (array[i].key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if the ADT.IMap contains a value
     *
     * @param value the value to search for
     * @return true, if the value is found
     */
    @Override
    public boolean containsValue (V value) {
        for (int i = 0; i < size; i++) {
            if (array[i].value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieve a value from a specific key
     *
     * @param key the key to search for
     * @return the value associated with this key
     */
    @Override
    public V get (K key) {
        for (int i = 0; i < size; i++) {
            if (array[i].key.equals(key)) {
                return array[i].value;
            }
        }
        throw new NoSuchElementException("Key not found");
    }

    /**
     * Add this key-value pair to the ADT.IMap
     *
     * @param key   the key to insert
     * @param value the value to insert
     */
    @Override
    public void put (K key, V value) {
        if (key == null)
            return;
        ensureCapacity();
        DataItem<K, V> item = new DataItem<>(key, value);
        if (containsKey(key))
            return;
        array[size++] = item;
    }

    /**
     * Remove a key-value pair from the ADT.IMap
     *
     * @param key the key to remove
     * @return the value associated with this key
     */
    @Override
    public V remove (K key) {
        if (!containsKey(key))
            throw new NoSuchElementException("Key not found");

        V temp = get(key);
        int index = indexOf(key);

        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return temp;
    }

    public int indexOf (K key) {
        for (int i = 0; i < size; i++) {
            if (array[i].key.equals(key))
                return i;
        }
        return -1;
    }

    /**
     * Pass the current mappings off to an array
     *
     * @return a new array based on the current key-value mappings
     */
    @Override
    public Object[] toArray () {
        Object[] out = new Object[size()];
        for (int i = 0, index = 0; i < size; i++) {
            out[index++] = array[i];
        }
        return out;
    }

    /**
     * Reset, empty the ADT.IMap
     */
    @Override
    public void clear () {
        size = 0;
        array = new DataItem[DEFAULT_MAX_SIZE];
    }

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i < size - 1)
                sb.append(array[i].key + "=" + array[i].value + ", ");
            else
                sb.append(array[i].key + "=" + array[i].value);
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * DataItem represents the individual entries in a map (a single key-value pair)
     *
     * @param <K> generic, key type parameter
     * @param <V> generic, value type parameter
     */
    private static class DataItem<K, V> implements IMap.Entry<K, V> {
        K key;
        V value;

        private DataItem (K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString () {
            return String.format("%s=%s", this.key, this.value);
        }

        @Override
        public boolean equals (Object obj) {
            DataItem<K, V> temp = (DataItem<K, V>) obj;
            if (obj == this)
                return true;
            else if (this.key == temp.key && this.value == temp.value)
                return true;
            else if (this.getClass() != temp.getClass() || obj == null)
                return false;
            return false;
        }

        @Override
        public int hashCode () {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        /**
         * Get the key for the current ADT.IMap Entry
         *
         * @return the key for the current ADT.IMap Entry
         */
        @Override
        public K getKey () {
            return null;
        }

        /**
         * Get the value for the current ADT.IMap Entry
         *
         * @return the value for the current ADT.IMap Entry
         */
        @Override
        public V getValue () {
            return null;
        }

        @Override
        public void setValue (V value) {
            this.value = value;
        }
    }

}
