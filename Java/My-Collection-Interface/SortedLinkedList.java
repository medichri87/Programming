package ADT;

import java.util.NoSuchElementException;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/26/2014 <p>
 * Class Purpose: Represents a double-linked, doulby-ended List which provides for ordered insertion
 */
public class SortedLinkedList<E> extends AbstractLinkedList<E> implements EList<E> {
    private Node<E> first, last;
    private int size;

    /**
     * Create an empty Linked List
     */
    public SortedLinkedList () {
        size = 0;
        first = null;
        last = null;
    }

    /**
     * Insert each value from this input array in sorted order
     *
     * @param in the array to input the values from into the Linked List
     */
    public SortedLinkedList (Object[] in) {
        this();
        for (Object t : in)
            add((E) t);
    }

    /**
     * Insert each value from this Collection in sorted order
     *
     * @param in Collection of values to input into the Linked List
     */
    public SortedLinkedList (ECollection<? extends E> c) {
        this();
        EIterator<? extends E> it = c.iterator();
        while (it.hasNext())
            add(it.next());
    }


    /**
     * Places this element in the right place within the current List (Sorted order)
     *
     * @param e Value to add
     */
    public void add (E e) {
        if (isEmpty()) {
            insertFirst(e);
        } else {
            Node<E> curr = last;
            while (((Comparable) curr.val).compareTo(e) > 0) {
                if (curr == first) {
                    insertFirst(e);
                    return;
                }
                curr = curr.previous;
            }
            //larger than every element
            if (curr == last) {
                insertLast(e);
                return;
            }
            insertAfter(curr.val, e);
        }
    }

    /**
     * Insert this value to the end of the List
     *
     * @param val the value to insert
     */
    private void insertLast (E val) {
        Node<E> node = new Node<E>(last, val, null);
        if (first == null)
            first = node;
        else {
            last.next = node;
        }
        last = node;
        size++;
    }

    public Node<E> getFirst () {
        return first;
    }

    public Node<E> getLast () {
        return last;
    }

    /**
     * PRIVATE!
     * Search for this value within the List
     *
     * @param val the value to search for
     * @return the value, if found
     */
    private Node<E> search (E val) {
        Node<E> f = first;
        while (f.val != val) {
            if (f.next == null)
                return null;
            f = f.next;
        }
        return f;
    }

    /**
     * PRIVATE!
     * Inserts a new Node after a certain value
     *
     * @param find the Node to insert after
     * @param item the Node to insert
     */
    private void insertAfter (E find, E item) {
        Node<E> f = search(find);
        if (f == null)
            return;
        Node<E> node = new Node<>(f, item, f.next);
        size++;
        if (f == first) {
            if (first.next == null) {
                last = node;
            } else {
                first.next.previous = node;
            }
            first.next = node;
        } else if (f == last) {
            last.next = node;
            last = node;
        } else {
            f.next.previous = node;
            f.next = node;
        }
    }

    private void insertFirst (E o) {
        Node<E> node = new Node<>(null, o, first);
        if (isEmpty()) {
            last = node;
        } else {
            first.previous = node;
        }
        first = node;
        size++;
    }

    /**
     * Empty the entire Collection, removing all items
     */
    public void clear () {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Remove this element from the Collection
     *
     * @param o the value to remove
     * @return True, if found and removed
     */
    public boolean remove (E o) {
        if (isEmpty())
            throw new NoSuchElementException("List is empty. Nothing to remove!");
        Node<E> f = first;
        while (f.val != o) {
            if (f.next == null)
                throw new NoSuchElementException("Element not found");
            f = f.next;
        }
        size--;
        if (f == first) {
            if (first.next == null) {
                first = null;
                last = null;
                return true;
            }
            first.next.previous = null;
            first = first.next;
        } else if (f == last) {
            last.previous.next = null;
            last = last.previous;
        } else {
            f.next.previous = f.previous;
            f.previous.next = f.next;
        }
        return true;
    }

    /**
     * Remove the element at this index
     *
     * @param index the index of the item to remove
     * @return the item removed, if found
     */
    public E removeIndex (int index) {
        if (isEmpty())
            throw new NoSuchElementException("List is empty. Nothing to remove!");
        if (!rangeCheck(index))
            throw new IndexOutOfBoundsException("Index out of range");
        int idx = 0;
        Node<E> f = first;
        while (idx < index) {
            f = f.next;
            idx++;
        }
        E temp = f.val;
        remove(f.val);
        return temp;
    }

    private boolean rangeCheck (int index) {
        return (index >= 0 || index < size);
    }

    /**
     * The size of the current Collection
     *
     * @return The number of elements found
     */
    public int size () {
        return size;
    }

    /**
     * An iterator that provides for sequential traversal of the Collection
     *
     * @return A new iterator from the starting point of the current Collection
     */
    public EIterator<E> iterator () {
        return listIterator();
    }

    public EListIterator<E> listIterator () {
        return new ListItr();
    }

    private class ListItr implements EListIterator<E> {

        private Node<E> current, previous, next;

        private ListItr () {
            previous = null;
            current = null;
            next = first;
        }

        /**
         * Proceed and return the next element in the current Linked-List
         *
         * @return the next element
         */
        public E next () {
            previous = current;
            current = next;
            next = next.next;
            return current.val;
        }

        /**
         * Determines if there is a value to move forward to
         *
         * @return true, if the next value exists(non-null)
         */
        public boolean hasNext () {
            return (next != null);
        }

        /**
         * Reset the iterator to the beginning
         */
        public void reset () {
            previous = null;
            current = null;
            next = first;
        }

        /**
         * Determines if this LinkedList has a previous element
         *
         * @return true, if there is a previous element
         */
        public boolean hasPrevious () {
            return (previous != null);
        }

        /**
         * Return the previous element
         *
         * @return previous element
         */
        public E previous () {
            next = current;
            current = previous;
            previous = previous.previous;
            return current.val;
        }
    }

    private static class Node<E> {
        E val;
        Node<E> next, previous;

        private Node (Node<E> previous, E val, Node<E> next) {
            this.previous = previous;
            this.val = val;
            this.next = next;
        }

        public String toString () {
            return String.format("%s", val);
        }
    }
}
