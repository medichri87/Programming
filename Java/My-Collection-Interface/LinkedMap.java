package ADT;

import java.util.NoSuchElementException;

/**
 * Created by Chris Medina on 5/22/2014.
 * <p>Purpose: ADT.LinkedMap provdes a doubly-linked, double-ended implementation of the ADT.IMap interface, which maps
 * key-value pairs, similar to a dictionary. Duplicates and/ or null values are not permitted.</p>
 */
public class LinkedMap<K, V> implements IMap<K, V> {

    private int size;
    private Node<K, V> first, last;

    public LinkedMap () {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    /**
     * Return the size of the current ADT.LinkedMap
     *
     * @return the size of the ADT.LinkedMap
     */
    @Override
    public int size () {
        return size;
    }

    /**
     * Determine if the current Map is empty
     *
     * @return true, if there are no elements in the ADT.LinkedMap
     */
    @Override
    public boolean isEmpty () {
        return (first == null);
    }

    /**
     * Determine if the ADT.LinkedMap contains a key
     *
     * @param key the key to search for
     * @return true, if the key is found
     */
    @Override
    public boolean containsKey (K key) {
        Node<K, V> f = first;
        while (f != null) {
            if (f.key.equals(key))
                return true;
            f = f.next;
        }
        return false;
    }

    /**
     * Determine if the ADT.LinkedMap contains a value
     *
     * @param value the value to search for
     * @return true, if the value is found
     */
    @Override
    public boolean containsValue (V value) {
        Node<K, V> f = first;
        while (f != null) {
            if (f.value.equals(value))
                return true;
            f = f.next;
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
        Node<K, V> f = first;
        while (f != null) {
            if (f.key.equals(key))
                return f.getValue();
            f = f.next;
        }
        throw new NoSuchElementException("Key not found");
    }

    /**
     * Add this key-value pair to the ADT.LinkedMap
     *
     * @param key   the key to insert
     * @param value the value to insert
     */
    @Override
    public void put (K key, V value) {
        if (containsKey(key) || key == null)
            return;
        insertLast(key, value);
    }

    private void insertLast (K key, V value) {
        Node<K, V> node = new Node<>(last, key, value, null);
        if (isEmpty())
            first = node;
        else {
            last.next = node;
        }
        last = node;
        size++;
    }

    /**
     * Remove a key-value pair from the ADT.LinkedMap
     *
     * @param key the key to remove
     * @return the value associated with this key
     */
    @Override
    public V remove (K key) {
        if (!containsKey(key))
            throw new NoSuchElementException("Key not found");
        Node<K, V> curr = first;
        while (curr.key != key) {
            curr = curr.next;
        }

        if (curr == first) {
            if (first.next == null) {
                first = null;
                last = null;
            } else
                first.next.previous = null;
            first = first.next;
        } else if (curr == last) {
            last.previous.next = null;
            last = last.previous;
        } else {
            curr.previous.next = curr.next;
            curr.next.previous = curr.previous;
        }

        size--;
        return curr.getValue();
    }

    /**
     * Reset, empty the ADT.LinkedMap
     */
    @Override
    public void clear () {
        size = 0;
        first = null;
        last = null;
    }

    /**
     * Provides an EList view of the entries of the current ADT.LinkedMap
     *
     * @return EList view of the entries of the current ADT.LinkedMap
     */
    @Override
    public EList<Entry<K, V>> entrySet () {
        EList<Entry<K, V>> list = new EArrayList<>();
        Node<K, V> f = first;
        while (f != null) {
            list.add(f);
            f = f.next;
        }
        return list;
    }

    /**
     * Provides an EList view of the values of the current ADT.LinkedMap
     *
     * @return EList view of the values of the current ADT.LinkedMap
     */
    @Override
    public EList<V> values () {
        EList<V> list = new EArrayList<>();
        Node<K, V> f = first;
        while (f != null) {
            list.add(f.getValue());
            f = f.next;
        }
        return list;
    }

    /**
     * Provides an EList view of the keys of the current ADT.LinkedMap
     *
     * @return EList view of the keys of the current ADT.LinkedMap
     */
    @Override
    public EList<K> keySet () {
        EList<K> list = new EArrayList<>();
        Node<K, V> f = first;
        while (f != null) {
            list.add(f.getKey());
            f = f.next;
        }
        return list;
    }

    /**
     * Pass the current mappings off to an array
     *
     * @return a new array based on the current key-value mappings
     */
    @Override
    public Object[] toArray () {
        Object[] out = new Object[size()];
        Node<K, V> f = first;
        int index = 0;
        while (f != null) {
            out[index++] = f;
            f = f.next;
        }
        return out;
    }

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder("[");
        Node<K, V> f = first;
        while (f != null) {
            if (f.next == null)
                sb.append(f.getKey() + "=" + f.getValue());
            else
                sb.append(f.getKey() + "=" + f.getValue() + ", ");
            f = f.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Node represents the individual mappings (single key-value pair)
     *
     * @param <K> generic, key type parameter
     * @param <V> generic, value type parameter
     */
    private static class Node<K, V> implements Entry<K, V> {
        K key;
        V value;
        Node<K, V> previous, next;

        private Node (Node<K, V> previous, K key, V val, Node<K, V> next) {
            this.key = key;
            this.value = val;
            this.previous = previous;
            this.next = next;
        }

        /**
         * Get the key for the current entry
         *
         * @return the key for the current entry
         */
        @Override
        public K getKey () {
            return key;
        }

        /**
         * Get the value for the current entry
         *
         * @return the value for the current entry
         */
        @Override
        public V getValue () {
            return value;
        }

        /**
         * Set the value for the current entry
         */
        @Override
        public void setValue (V value) {
            this.value = value;
        }

        @Override
        public boolean equals (Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<K, V> node = (Node<K, V>) o;

            if (key != null ? !key.equals(node.key) : node.key != null) return false;
            if (value != null ? !value.equals(node.value) : node.value != null) return false;

            return true;
        }

        @Override
        public int hashCode () {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }

        @Override
        public String toString () {
            return String.format("%s=%s", this.key, this.value);
        }
    }

}
