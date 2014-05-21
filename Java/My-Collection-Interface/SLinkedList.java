package ADT;

import java.util.NoSuchElementException;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/23/2014 <p>
 * Class Purpose: Represents a Singly-linked, double-ended Linked List
 */
public class SLinkedList<E> extends AbstractLinkedList<E> implements EList<E>, DoubleEnded<E> {

    private Node<E> first, last;
    private int size;

    /**
     * Construct an empty Linked List
     */
    public SLinkedList () {
        size = 0;
        first = null;
        last = null;
    }

    /**
     * Construct a Linked List from the values in this array
     *
     * @param in array to input into this Linked List from
     */
    public SLinkedList (Object[] in) {
        this();
        for (Object val : in)
            add((E) val);
    }

    /**
     * Insert each value from this Collection in sorted order
     *
     * @param in Collection of values to input into the Linked List
     */
    public SLinkedList (ECollection<? extends E> c) {
        this();
        EIterator<? extends E> it = c.iterator();
        while (it.hasNext())
            add(it.next());
    }

    /**
     * Add this element to the end of the current Linked-List
     *
     * @param e value to add
     */
    public void add (E e) {
        insertLast(e);
    }

    /**
     * Empty the entire Linked-List, removing all items
     */
    public void clear () {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Get the last value in the current Linked List
     *
     * @return the last value in the List
     */
    public E getLast () {
        if (isEmpty())
            return null;
        return last.val;
    }

    /**
     * Insert this value before a certain value, if that value is found
     *
     * @param find the value to insert before
     * @param val  the value to insert
     */
    public void insertBefore (E find, E val) {
        if (isEmpty())
            throw new IllegalArgumentException("The list is empty");
        Node<E> f = first;
        while (f.val != find) {
            if (f.next == null)
                return; //not found
            f = f.next;
        }
        if (f == first)
            insertFirst(val);
        else {
            Node<E> runner = first;
            while (runner.next != f) {
                runner = runner.next;
            }
            insertAfter(runner.val, val);
        }
    }

    /**
     * Remove the front/first value from the List
     *
     * @return the head or front value from the List
     */
    public E removeFirst () {
        return null;
    }

    /**
     * Remove the last/end value from the List
     *
     * @return the last value from the List
     */
    public E removeLast () {
        return null;
    }

    /**
     * Get the first value in the current Linked List
     *
     * @return the first value in the List
     */
    public E getFirst () {
        if (isEmpty())
            return null;
        return first.val;
    }

    /**
     * Find this value within the current Linked List
     *
     * @param val the value to search for
     * @return the value, if found
     * @throws java.util.NoSuchElementException if element is not present in the List
     */
    public E find (E val) {
        EIterator<E> it = iterator();
        while (it.hasNext()) {
            E temp;
            if ((temp = it.next()).equals(val))
                return temp;
        }
        throw new NoSuchElementException("Element not found");
    }

    /**
     * Remove this element from the Linked-List
     *
     * @param o the value to remove
     * @return true, if found and removed
     */
    public boolean remove (E o) {
        Node<E> f = first;
        while (f.val != o) {
            if (f.next == null)
                return false;
            f = f.next;
        }
        size--;
        if (f == first) {
            if (first.next == null) {
                first = null;
                last = null;
                return true;
            }
            first = first.next;
        } else if (f == last) {
            Node<E> runner = first;
            while (runner.next != last)
                runner = runner.next;
            runner.next = last.next;
            last = runner;
        } else {
            Node<E> runner = first;
            while (runner.next != f)
                runner = runner.next;
            runner.next = f.next;
        }
        return true;
    }

    /**
     * The size of the current Linked-List
     *
     * @return the number of elements found
     */
    public int size () {
        return size;
    }

    /**
     * Append this element at this index of the current Linked-List
     *
     * @param index the position to insert the item into
     * @param item  the value to insert
     * @throws java.lang.IllegalArgumentException if the input index is out of range
     */
    public void add (int index, E item) {
        if (index > size || index < 0)
            throw new IllegalArgumentException("Index out of range");
        if (index == 0) {
            insertFirst(item);
        } else if (index == size) {
            insertLast(item);
        } else {
            int idx = 0;
            Node<E> f = first;
            while (idx < (index - 1)) {
                idx++;
                f = f.next;
            }
            insertAfter(f.val, item);
        }
    }


    /**
     * Remove the element at this index
     *
     * @param index the index of the item to remove
     * @return the item removed, if found
     */
    public E removeIndex (int index) {
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

    /**
     * Ensure proper index range(0 --> size - 1)
     *
     * @param index the index to perform the bounds check for
     * @return true, if within the proper range
     */
    private boolean rangeCheck (int index) {
        if (index < 0 || index >= size)
            return false;
        return true;
    }

    /**
     * An iterator that provides for sequential traversal of the Collection
     *
     * @return a new iterator from the starting point of the current Collection
     */
    public EIterator<E> iterator () {
        return new ListItr();
    }

    /**
     * Get the value associated with this index
     *
     * @param index the index of the item to find
     * @return the value associated with this index within the Linked-List
     * @throws java.lang.IllegalArgumentException if the input index is out of range
     */
    public E get (int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Index out of range");
        Node<E> f = first;
        while (index > 0) {
            index--;
            f = f.next;
        }
        return f.val;
    }

    /**
     * Insert this value at the beginning of this list
     *
     * @param val the value to insert
     */
    public void insertFirst (E val) {
        Node<E> node = new Node<E>(val, first);
        if (isEmpty()) {
            last = node;
        }
        first = node;
        size++;
    }

    /**
     * Insert this value at the end of the current list
     *
     * @param val
     */
    public void insertLast (E val) {
        Node<E> node = new Node<E>(val, null);
        if (isEmpty())
            first = node;
        else
            last.next = node;
        last = node;
        size++;
    }

    /**
     * Insert a value after another chosen value
     *
     * @param find the value to insert after
     * @param val  the value to insert
     */
    public void insertAfter (E find, E val) {
        Node<E> f = first;
        while (f.val != find) {
            if (f.next == null)
                return;
            f = f.next;
        }
        Node<E> node = new Node<>(val, f.next);
        size++;
        if (f == first) {
            if (first.next == null) {
                last = node;
            }
            first.next = node;
        } else if (f == last) {
            last.next = node;
            last = node;
        } else {
            f.next = node;
        }
    }

    private static class Node<E> {
        E val;
        Node<E> next;

        private Node (E val, Node<E> next) {
            this.val = val;
            this.next = next;
        }

        public String toString () {
            return String.format("%s", val);
        }
    }

    private class ListItr implements EIterator<E> {
        private Node<E> current, next;

        private ListItr () {
            current = null;
            next = first;
        }

        /**
         * Proceed and return the next element in the current Linked-List
         *
         * @return the next element
         */
        public E next () {
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
            current = null;
            next = first;
        }
    }
}
