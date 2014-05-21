package ADT;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/23/2014 <p>
 * Class Purpose: Represents a Doubly-Linked, Double-Ended linked list
 */
public class DLinkedList<E> extends AbstractLinkedList<E> implements EList<E>, DoubleEnded<E> {
    private Node<E> first, last;
    private int size;

    /**
     * Construct an empty Linked List
     */
    public DLinkedList () {
        size = 0;
        first = null;
        last = null;
    }

    /**
     * Construct a Linked List from the values in this array
     *
     * @param in array to input into this Linked List from
     */
    public DLinkedList (Object[] in) {
        this();
        for (Object val : in)
            add((E) val);
    }

    /**
     * Insert each value from this Collection in sorted order
     *
     * @param in Collection of values to input into the Linked List
     */
    public DLinkedList (ECollection<? extends E> c) {
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
     * Find a value within the List
     *
     * @param val the value to search for
     * @return the value, if found
     * @throws java.util.NoSuchElementException if the value is not found
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
     * @throws java.lang.IllegalArgumentException if the index is out of range
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
     * Get the value associated with this index
     *
     * @param index the index of the item to find
     * @return the value associated with this index within the Linked-List
     * @throws java.lang.IllegalArgumentException if the index is out of range
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
     * An iterator that provides for sequential traversal of the Linked-List
     *
     * @return a new iterator from the starting point of the current Linked-List
     */
    public EIterator<E> iterator () {
        return listIterator();
    }

    public EListIterator<E> listIterator () {
        return new ListItr();
    }

    /**
     * Insert this value to the front of the current List
     *
     * @param item the value to insert
     */
    public void insertFirst (E item) {
        Node<E> node = new Node<E>(null, item, first);
        if (isEmpty())
            last = node;
        else {
            first.previous = node;
        }
        first = node;
        size++;
    }

    /**
     * Retrieve the last value in the List
     *
     * @return the last value in the list
     */
    public E getLast () {
        if (isEmpty())
            return null;
        return last.val;
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
        if (index < 0 || index >= size)
            return false;
        return true;
    }

    /**
     * Retrive the first value in the List
     *
     * @return the first value in the list
     */
    public E getFirst () {
        if (isEmpty())
            return null;
        return first.val;
    }

    /**
     * Remove this element from the Linked-List
     *
     * @param o the value to remove
     * @return true, if found and removed
     * @throws java.util.NoSuchElementException if the value is not found within the List or the List is currently
     * empty
     */
    public boolean remove (E val) {
        if (isEmpty())
            throw new NoSuchElementException("List is empty. Nothing to remove!");
        Node<E> f = first;
        while (f.val != val) {
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
     * Insert this value to the end of the List
     *
     * @param val the value to insert
     */
    public void insertLast (E val) {
        Node<E> node = new Node<E>(last, val, null);
        if (first == null)
            first = node;
        else {
            last.next = node;
        }
        last = node;
        size++;
    }

    /**
     * Inserts a new Node after a certain value
     *
     * @param find the Node to insert after
     * @param item the Node to insert
     */
    public void insertAfter (E find, E item) {
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
            node.next = last.next;
            node.previous = last;
            last.next = node;
            last = node;
        } else {
            node.next = f.next;
            node.previous = f;
            f.next.previous = node;
            f.next = node;
        }
    }

    /**
     * Search for this value within the List
     *
     * @param val the value to search for
     * @return the value, if found
     */
    public Node<E> search (E val) {
        Node<E> f = first;
        while (f.val != val) {
            if (f.next == null)
                return null;
            f = f.next;
        }
        return f;
    }

    /**
     * Remove the front/first value from the List
     *
     * @return the head or front value from the List
     */
    public E removeFirst () {
        if (isEmpty())
            throw new NoSuchElementException();
        else {
            E temp = first.val;
            remove(first.val);
            return temp;
        }
    }

    /**
     * Remove the last/end value from the List
     *
     * @return the last value from the List
     */
    public E removeLast () {
        if (isEmpty())
            throw new NoSuchElementException();
        else {
            E temp = last.val;
            remove(last.val);
            return temp;
        }
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

    public static void main (String[] args) {
        EList<Integer> list = new DLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println(Arrays.toString(list.toArray()));

    }
}
