package ICollection;

import java.util.NoSuchElementException;

/**
 *
 *
 * @date Jul 28, 2014
 * @author Chris Medina
 *
 * DLinkedIList is a doubly-linked list reprenstation of the IList interface. A
 * doubly-linked list is a linked list which maintains a list of nodes where
 * each maintain a pointer (reference) to the 'previous' & the 'next' value.
 */
public class DLinkedIList<E> extends AbstractIList<E> implements IDualEnded<E> {

    private Node<E> first, last;
    private int size;

    public DLinkedIList() {
        first = null;
        last = null;
        size = 0;
    }

    public DLinkedIList(E[] array) {
        this();
        for (E item : array) {
            add(item);
        }
    }

    public DLinkedIList(ICollection<E> collection) {
        this(collection.toArray());
    }

    @Override
    public E removeIndex(int index) {
        checkIndex(index);
        Node<E> temp = atIndex(index);
        return remove(temp.val);
    }

    private Node<E> atIndex(int index) {
        Node<E> curr = first;
        int i = 0;
        while (i < index) {
            i++;
            curr = curr.next;
        }
        return curr;
    }

    @Override
    public void set(int index, E item) {
        checkIndex(index);
        Node<E> temp = atIndex(index);
        temp.val = item;
    }

    @Override
    public void addIndex(int index, E item) {
        if (index == size)
            insertLast(item);
        else {
            Node<E> temp = atIndex(index);
            insertBefore(temp.val, item);
        }
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public IEnumerator<E> iterator() {
        return new ListItr<>();
    }

    @Override
    public E remove(E item) {
        if (isEmpty())
            throw new NoSuchElementException("Item not found");
        Node<E> find = search(item);
        if (find == first) {
            if (first.next == null) {
                first = null;
                last = null;
            } else {
                first.next.previous = null;
                first = first.next;
            }
        } else if (find == last) {
            last.previous.next = null;
            last = last.previous;
        } else {
            find.previous.next = find.next;
            find.next.previous = find.previous;
        }
        size--;
        return find.val;
    }

    @Override
    public void add(E item) {
        insertLast(item);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void insertFirst(E item) {
        Node<E> node = new Node<>(null, item, first);
        if (isEmpty())
            last = node;
        else {
            first.previous = node;
        }
        first = node;
        size++;
    }

    @Override
    public void insertLast(E item) {
        Node<E> node = new Node<>(last, item, null);
        if (isEmpty())
            first = node;
        else {
            last.next = node;
        }
        last = node;
        size++;
    }

    @Override
    public void insertAfter(E item, E val) {
        if (!contains(item))
            throw new NoSuchElementException("Value not found");
        Node<E> temp = search(item);
        Node<E> node = new Node<>(temp, val, temp.next);
        if (temp == first) {
            if (temp.next == null) {
                last = node;
            } else
                first.next.previous = node;
            first.next = node;
        } else if (temp == last) {
            last.next = node;
            last = node;
        } else {
            temp.next.previous = node;
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
    public void insertBefore(E item, E val) {
        Node<E> temp = search(item);
        Node<E> node = new Node<>(temp.previous, val, temp);
        if (temp == first)
            insertFirst(val);
        else {
            temp.previous.next = node;
            temp.previous = node;
        }
        size++;
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
            throw new NoSuchElementException("IList is empty");
        return first.val;
    }

    @Override
    public E last() {
        if (isEmpty())
            throw new NoSuchElementException("IList is empty");
        return last.val;
    }

    private class ListItr<E> implements IListEnumerator<E> {

        private Node<E> prev, curr, next;

        public ListItr() {
            this.prev = null;
            this.curr = null;
            this.next = (Node<E>) first;
        }

        @Override
        public boolean hasPrevious() {
            return (prev != null);
        }

        @Override
        public E previous() {
            next = curr;
            curr = prev;
            prev = prev.previous;
            return curr.val;
        }

        @Override
        public boolean hasNext() {
            return (next != null);
        }

        @Override
        public E next() {
            prev = curr;
            curr = next;
            next = next.next;
            return curr.val;
        }

        @Override
        public void reset() {
            this.prev = null;
            this.curr = null;
            this.next = (Node<E>) first;
        }

    }

}
