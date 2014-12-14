package ICollection;

import java.util.NoSuchElementException;

/**
 * @date Jul 27, 2014
 * @author Chris Medina
 * @purpose
 *
 * SLinkedIList is a singly-linked list reprenstation of the IList interface. A
 * singly-linked list is a linked list which maintains a list of nodes where
 * each maintain a pointer (reference) to the 'next' value.
 */
public class SLinkedIList<E> extends AbstractIList<E> implements IDualEnded<E> {

    private Node<E> first, last;
    private int size;

    public SLinkedIList() {
        first = null;
        last = null;
        size = 0;
    }

    public SLinkedIList(E[] array) {
        this();
        for (E item : array) {
            add(item);
        }
    }

    public SLinkedIList(ICollection<E> collection) {
        this(collection.toArray());
    }

    @Override
    public E removeIndex(int index) {
        Node<E> rem = atIndex(index);
        return remove(rem.val);
    }

    @Override
    public void set(int index, E item) {
        checkIndex(index);
        Node<E> temp = atIndex(index);
        temp.val = item;
    }

    private Node<E> atIndex(int index) {
        checkIndex(index);
        int i = 0;
        Node<E> curr = first;
        while (i < index) {
            curr = curr.next;
            i++;
        }
        return curr;
    }

    @Override
    public void addIndex(int index, E item) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        else if (index == size)
            insertLast(item);
        else {
            Node<E> find = atIndex(index);
            insertBefore(find.val, item);
        }
    }

    @Override
    public void insertAfter(E find, E val) {
        if (!contains(find))
            throw new NoSuchElementException("Value not found");
        Node<E> temp = search(find);
        Node<E> node = new Node<>(val, temp.next);

        if (temp == first) {
            if (temp.next == null)
                last = node;
            temp.next = node;
        } else if (temp == last) {
            temp.next = node;
            last = node;
        } else {
            temp.next = node;
        }
        size++;
    }

    private Node<E> search(E item) {
        Node<E> curr = first;
        while (curr.val != item) {
            curr = curr.next;
            if (curr == null)
                throw new NoSuchElementException("Value not found");
        }
        return curr;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public IEnumerator<E> iterator() {
        return new ListItr();
    }

    @Override
    public E remove(E item) {
        Node<E> curr = first, prev = first;
        if (!contains(item))
            throw new NoSuchElementException("Value not found");

        while (curr.val != item) {
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
        return curr.val;
    }

    @Override
    public void add(E item) {
        insertLast(item);
    }

    @Override
    public void insertBefore(E item, E val) {
        if (!contains(item))
            throw new NoSuchElementException("Value not found");
        Node<E> curr = first, prev = first;

        while (curr.val != item) {
            prev = curr;
            curr = curr.next;
        }

        if (curr == first) {
            insertFirst(val);
        } else {
            insertAfter(prev.val, val);
        }
    }

    @Override
    public void insertLast(E item) {
        Node<E> node = new Node<>(item, null);
        if (isEmpty())
            first = node;
        else
            last.next = node;
        last = node;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void insertFirst(E item) {
        Node<E> node = new Node<>(item, first);
        if (isEmpty())
            last = node;
        size++;
        first = node;
    }

    @Override
    public E removeFirst() {
        return remove(first.val);
    }

    @Override
    public E removeLast() {
        return remove(last.val);
    }

    @Override
    public E first() {
        if (isEmpty())
            throw new NullPointerException("Last is null reference");
        return first.val;
    }

    @Override
    public E last() {
        if (isEmpty())
            throw new NullPointerException("Last is null reference");
        return last.val;
    }

    private static final class Node<E> {

        E val;
        Node<E> next;

        public Node(E val, Node<E> next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    private class ListItr implements IEnumerator<E> {

        private Node<E> prev, curr;

        public ListItr() {
            prev = null;
            curr = first;
        }

        @Override
        public boolean hasNext() {
            return (curr != null);
        }

        @Override
        public E next() {
            prev = curr;
            curr = curr.next;
            return prev.val;
        }

        @Override
        public void reset() {
            prev = null;
            curr = first;
        }

    }
}
