package ICollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @param <K>
 * @param <V>
 * @date Jul 25, 2014
 * @author Chris Medina
 * @purpose
 *
 * HashIDictionary represents a data structure whose entries are Key-Value
 * pairs. For every entry in an Dictionary, there is a value and its key. The
 * Dictionary operates using the "seperate chaining" concept whereby each
 * prospective entry is 'hashed' using its key, and is stored into a specific
 * "bucket" (in this case a seperate singly-Linked List). Each bucket is
 * represented by a specific singly-Linked List.
 *
 *
 */
public class HashIDictionary<K, V> extends AbstractIDictionary<K, V> implements IDictionary<K, V> {

    private int size;
    private SLinkedList<K, V>[] list;
    private int currMaxSize;
    private static final int DEFAULT_SIZE = 10;

    public HashIDictionary() {
        this(10);
    }

    public HashIDictionary(int ms) {
        this.size = 0;
        this.currMaxSize = ms;
        list = new SLinkedList[ms];

        for (int i = 0; i < list.length; i++) {
            list[i] = new SLinkedList<>();
        }
    }

    @Override
    public boolean containsKey(K key) {
        int hash = hash(key.hashCode());
        Node<K, V> curr = list[hash].getFirst();
        while (curr != null) {
            if (curr.getKey().equals(key))
                return true;
            curr = curr.next;
        }
        return false;
    }

    @Override
    public boolean put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Dictionaries do not allow for null keys");

        int code = hash(key.hashCode());

        //key found, replace value
        if (containsKey(key)) {
            Node<K, V> curr = list[code].first;

            while (!curr.getKey().equals(key)) {
                curr = curr.next;
            }

            curr.setValue(value);
            return false;
        } else {
            ensureCapacity();
            list[code].insertFirst(key, value);
            size++;
            return true;
        }

    }

    private int hash(int code) {
        return (code % currMaxSize);
    }

    private void ensureCapacity() {
        if (size == currMaxSize) {
            currMaxSize *= 3;
            list = Arrays.copyOf(list, currMaxSize);

            for (int i = size; i < currMaxSize; i++) {
                list[i] = new SLinkedList<>();
            }
        }
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key))
            throw new NoSuchElementException("Key not found");
        int code = hash(key.hashCode());
        return list[code].removeKey(key).getValue();
    }

    @Override
    public void clear() {
        size = 0;
        list = Arrays.copyOf(list, (currMaxSize = DEFAULT_SIZE));
        for (int i = 0; i < list.length; i++) {
            list[i].first = null;
            list[i].last = null;
        }
    }

    @Override
    public List<KeyValuePair<K, V>> entries() {
        List<KeyValuePair<K, V>> temp = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            Node<K, V> curr = list[i].getFirst();
            while (curr != null) {
                temp.add(curr);
                curr = curr.next;
            }
        }
        return temp;
    }

    private static class Node<K, V> implements IDictionary.KeyValuePair<K, V> {

        Node<K, V> next;
        K key;
        V val;

        public Node(K key, V val, Node<K, V> next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.val;
        }

        @Override
        public V setValue(V newVal) {
            V old = val;
            this.val = newVal;
            return old;
        }

        @Override
        public int hashCode() {
            int hash = 31;
            hash = 11 * hash + Objects.hashCode(this.key);
            hash = 11 * hash + Objects.hashCode(this.val);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            final Node<K, V> other = (Node<K, V>) obj;
            return Objects.equals(this.key, other.key);
        }

        @Override
        public String toString() {
            return String.format("%s=%s", this.key, this.val);
        }

    }

    private static class SLinkedList<K, V> {

        private int size;
        private Node<K, V> first, last;

        public SLinkedList() {
            this.first = null;
            this.last = null;
            this.size = 0;
        }

        public boolean containsKey(K key) {
            Node<K, V> curr = first;
            while (curr != null) {
                if (curr.key.equals(key))
                    return true;
                curr = curr.next;
            }
            return false;
        }

        public boolean containsValue(V value) {
            Node<K, V> curr = first;
            while (curr != null) {
                if (curr.val.equals(value))
                    return true;
                curr = curr.next;
            }
            return false;
        }

        public Node<K, V> getFirst() {
            return first;
        }

        public Node<K, V> getLast() {
            return last;
        }

        public void insertFirst(K key, V val) {
            Node<K, V> node = new Node<>(key, val, first);
            if (isEmpty())
                last = node;
            first = node;
            size++;
        }

        public void insertLast(K key, V val) {
            Node<K, V> node = new Node<>(key, val, null);
            if (isEmpty())
                first = node;
            else
                last.next = node;
            last = node;
            size++;
        }

        public Node<K, V> removeKey(K key) {
            Node<K, V> curr = first, prev = first;
            while (!curr.getKey().equals(key)) {
                if (curr == null)
                    throw new NoSuchElementException("Key not found");
                prev = curr;
                curr = curr.next;
            }

            if (curr == first) {
                if (first.next == null) {
                    first = null;
                    last = null;
                } else {
                    first = first.next;
                }
            } else if (curr == last) {
                prev.next = null;
                last = prev;
            } else {
                prev.next = curr.next;
            }
            size--;
            return curr;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return (first == null);
        }
    }

}
