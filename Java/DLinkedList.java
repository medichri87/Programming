import java.util.NoSuchElementException;

/**
 * Created by Chris Medina on 5/16/2014.
 * <p>Purpose:  To create a Doubly-linked, double-ended Linked List that allows for insertion and deletion at both
 * ends,
 * with forwards and backwards traversal.</p>
 */
public class DLinkedList<E> {

    private Node<E> first, last;
    private int size;

    /**
     * Create an empty Linked List
     */
    public DLinkedList () {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Determine if List is empty
     *
     * @return true, if no elements are present
     */
    public boolean isEmpty () {
        return (first == null);
    }

    public String toString () {
        StringBuilder sb = new StringBuilder("[");
        for (Node<E> f = first; f != null; f = f.next) {
            if (f.equals(last))
                sb.append(f);
            else
                sb.append(f + ", ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Empty the list
     */
    public void clearAll () {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Insert a Node at the front of the list
     *
     * @param val the value to insert
     */
    public void insertFirst (E item) {
        Node<E> node = new Node<>(null, item, first);
        if (isEmpty())
            last = node;
        else {
            first.previous = node;
        }
        size++;
        first = node;
    }

    /**
     * Insert a Node to the end of the list
     *
     * @param val the value to insert
     */
    public void insertLast (E item) {
        Node<E> node = new Node<>(last, item, null);
        if (isEmpty())
            first = node;
        else {
            last.next = node;
        }
        size++;
        last = node;
    }


    /**
     * Insert a Node in front of another (right of)
     *
     * @param find the value to insert after
     * @param val  the value to insert
     */
    public void insertAfter (E find, E item) {
        Node<E> curr = first;
        while (curr.val != find) {
            curr = curr.next;
            if (curr == null)
                return;
        }

        Node<E> node = new Node<>(curr, item, curr.next);
        if (curr == first) {
            if (first.next == null) {
                last = node;
            } else
                first.next.previous = node;
            first.next = node;
        } else if (curr == last) {
            last.next = node;
            last = node;
        } else {
            curr.next.previous = node;
            curr.next = node;
        }
        size++;
    }

    /**
     * Remove this value from the Linked List
     *
     * @param val the value to remove
     * @return the Node removed
     * @throws java.util.NoSuchElementException if value not found
     */
    public Node<E> remove (E find) {
        Node<E> curr = first;
        while (curr.val != find) {
            curr = curr.next;
            if (curr == null)
                throw new NoSuchElementException("Value not found in List");
        }

        if (curr == first) {
            if (first.next == null) {
                Node<E> tempFirst = first;
                first = null;
                last = null;
                return tempFirst;
            } else
                first.next.previous = null;
            first = first.next;
        } else if (curr == last) {
            last.previous.next = null;
            last = last.previous;
        } else {
            curr.next.previous = curr.previous;
            curr.previous.next = curr.next;
        }

        size--;
        return curr;
    }

    /**
     * Remove the first Node from the List
     *
     * @return the head node
     */
    public Node<E> removeFirst () {
        return remove(first.val);
    }

    /**
     * Remove the last Node from the List
     *
     * @return the last node
     */
    public Node<E> removeLast () {
        return remove(last.val);
    }


    /**
     * Remove every nth Node from this List
     *
     * @param n The number of steps we take until we remove the current Node
     */
    public void removeEveryNthNode (int n) {
        int ptr = 1;
        for (Node<E> f = first; f != null; f = f.next) {
            if (ptr == n) {
                remove(f.val);
                ptr = 1;
            } else
                ptr++;
        }
    }

    /**
     * Return a Node based on its index within the list (recursive)
     *
     * @param index the index of the element to retireve
     * @return the Node at this index
     */
    public Node<E> get (int index) {
        if (index == 0)
            return first;
        else
            return get(index - 1).next;
    }

    /**
     * Find this value within the linked list (recursive)
     *
     * @param find the value to find
     * @return true, if found
     */
    public boolean contains (Node<E> start, E find) {
        if (start != null) {
            if (start.val == find)
                return true;
            else
                return contains(start.next, find);
        } else
            return false;
    }

    /**
     * Find this value within the linked list
     *
     * @param find the value to find
     * @return true, if found
     */
    public boolean contains (E find) {
        if (isEmpty())
            return false;
        Node<E> curr = first;
        while (curr.val != find) {
            if (curr.equals(last))
                return false;
            curr = curr.next;
        }
        return true;
    }

    /**
     * Determine if List contains Duplicates
     * O(n^2)
     */
    public boolean containsDuplicates () {
        for (Node<E> prev = first; prev != null; prev = prev.next) {
            for (Node<E> curr = prev.next; curr != null; curr = curr.next) {
                if (prev.equals(curr)) ;
                return true;
            }
        }
        return false;
    }

    /**
     * Remove duplicates from the current list
     * O(n^2)
     */
    public void removeDuplicates () {
        for (Node<E> prev = first; prev != null; prev = prev.next) {
            for (Node<E> curr = prev.next; curr != null; curr = curr.next) {
                if (prev.equals(curr))
                    remove(prev.val);
            }
        }
    }

    /**
     * Find the nth-To-last element in the Linked List
     *
     * @param n the n-th last element to find
     * @return the nth-To-Last element in list
     */
    public Node<E> nthLast (int n) {
        Node<E> curr = first, prev = first;
        while (n-- > 0)
            curr = curr.next;
        while (curr != null) {
            prev = prev.next;
            curr = curr.next;
        }
        return prev;
    }

    /**
     * Find the middle Node in the current List
     *
     * @return the middle Node
     */
    public Node<E> mid () {
        Node<E> curr = first, prev = first;
        int ptr = 1;
        while (curr != null) {
            if (ptr % 2 == 0)
                prev = prev.next;
            curr = curr.next;
            ptr++;
        }
        return prev;
    }

    /**
     * The dual-way (forwards/ backwards) traversal iterator for this List
     *
     * @return an iterator for the List
     */
    public ListIterating<E> listIterator () {
        return new ListIterator();
    }

    /**
     * Class to represent the individual Nodes in the Linked List
     *
     * @param <E> generic form
     */
    private static class Node<E> {
        E val;
        Node<E> previous, next;


        private Node (Node<E> previous, E val, Node<E> next) {
            this.previous = previous;
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString () {
            return String.format("%s", val);
        }

        @Override
        public boolean equals (Object obj) {
            if (obj == this)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;

            Node<E> temp = (Node<E>) obj;
            return (this.val == temp.val);
        }

        @Override
        public int hashCode () {
            int result = 17;
            result = result + (previous == null ? 0 : previous.hashCode());
            result = result + (val == null ? 0 : val.hashCode());
            result = result + (next == null ? 0 : next.hashCode());

            return super.hashCode();
        }
    }

    /**
     * Class that provides for dual-way traversal of List
     */
    private class ListIterator implements ListIterating<E> {

        Node<E> curr, prev, next;

        private ListIterator () {
            prev = null;
            curr = null;
            next = first;
        }

        @Override
        public boolean hasPrevious () {
            return (prev != null);
        }

        @Override
        public E previous () {
            next = curr;
            curr = prev;
            prev = prev.previous;
            return curr.val;
        }

        @Override
        public boolean hasNext () {
            return (next != null);
        }

        @Override
        public E next () {
            prev = curr;
            curr = next;
            next = next.next;
            return curr.val;
        }

        @Override
        public void reset () {
            prev = null;
            curr = null;
            next = first;
        }

    }
}

