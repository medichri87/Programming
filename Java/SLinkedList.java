import java.util.NoSuchElementException;

/**
 * Created by Chris Medina on 5/14/2014.
 * Purpose: To create a Singly-linked, double-ended Linked List
 */
public class SLinkedList<E> {

    private int size;
    private Node<E> first, last;

    /**
     * Create an empty Linked List
     */
    public SLinkedList () {
        this.size = 0;
        first = last = null;
    }

    /**
     * Linear traversal of the current Linked List
     *
     * @return the iterator
     */
    public Iterating<E> iterator () {
        return new Iterate();
    }

    public String toString () {
        StringBuilder sb = new StringBuilder("[");
        for (Node<E> f = first; f != null; f = f.next) {
            if (f.next == null) {
                sb.append(f);
            } else {
                sb.append(f + ", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean isEmpty () {
        return (first == null);
    }

    public Node<E> first () {
        return first;
    }

    public Node<E> last () {
        return last;
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
     * Return a Node based on its index within the list (recursive)
     *
     * @param index the index of the element to retireve
     * @return the Node at this index
     */
    public Node<E> get (int index) {
        if (index == 0)
            return first;
        else
            return get(index + 1).next;
    }

    /**
     * Insert a Node at the front
     *
     * @param val the value to insert
     */
    public void insertFirst (E val) {
        Node<E> node = new Node<>(val, first);
        if (isEmpty())
            last = node;
        first = node;
        size++;
    }

    /**
     * Insert a Node to the end of the list
     *
     * @param val the value to insert
     */
    public void insertLast (E val) {
        Node<E> node = new Node<>(val, null);
        if (isEmpty())
            first = node;
        else
            last.next = node;
        last = node;
        size++;
    }

    /**
     * Insert a Node in front of another (right of)
     *
     * @param find the value to insert after
     * @param val  the value to insert
     */
    public void insertAfter (E find, E val) {
        Node<E> curr = first;
        while (curr.val != find) {
            curr = curr.next;
            if (curr == null)
                return;
        }

        size++;
        Node<E> node = new Node<>(val, curr.next);

        if (curr == first) {
            if (first.next == null)
                last = node;
            first.next = node;
        } else if (curr == last) {
            last.next = node;
            last = node;
        } else {
            curr.next = node;
        }
    }

    /**
     * Insert this Node behind another node (left of)
     *
     * @param find the value to insert behind
     * @param val  the value to insert
     */
    public void insertBefore (E find, E val) {
        if (!contains(find))
            return;
        else if (first.val == find) {
            insertFirst(val);
            return;
        } else {
            Node<E> previous = first, curr = first;
            while (curr.val != find) {
                previous = curr;
                curr = curr.next;
            }
            Node<E> node = new Node<>(val, curr);
            node.next = curr;
            previous.next = node;
            size++;
        }
    }

    /**
     * Inserts a Node into the correct place in the current list, based on its value relative to the others (ordered)
     *
     * @param val the value to insert
     */
    public void orderedInsert (E val) {
        Node<E> curr = first;
        Node<E> previous = first;
        if (isEmpty()) {
            insertFirst(val);
        } else {
            while (((Comparable<E>) val).compareTo(curr.val) > 0) {
                previous = curr;
                curr = curr.next;

                //larger than all others
                if (curr == null) {
                    insertLast(val);
                    return;
                }
            }

            //value smaller than first
            if (curr == first) {
                insertFirst(val);
            } else {
                //just larger than 'previous'
                insertAfter(previous.val, val);
            }

        }
    }

    /**
     * Remove this value from the Linked List
     *
     * @param val the value to remove
     * @return the Node removed
     * @throws java.util.NoSuchElementException if value not found
     */
    public Node<E> remove (E val) {
        if (!contains(val))
            throw new NoSuchElementException("Value not found in this list");

        Node<E> curr = first;
        Node<E> previous = first;

        while (curr.val != val) {
            previous = curr;
            curr = curr.next;
        }

        if (curr == first) {
            if (first.next == null) {
                first = null;
                last = null;
                return null;
            }
            first = first.next;
        } else if (curr == last) {
            previous.next = null;
            last = previous;
        } else {
            previous.next = curr.next;
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
     * Retrieve the middle element in the current list
     *
     * @return middle element in the current list
     */
    public Node<E> mid () {
        Node<E> previous = first, curr = first;
        int ptr = 1;

        while (curr != null) {
            if (ptr % 2 == 0)
                previous = previous.next;
            curr = curr.next;
            ptr++;
        }

        return previous;
    }

    /**
     * Find the nth-To-last element in the Linked List
     *
     * @param n the n-th last element to find
     * @return the nth-To-Last element in list
     */
    public Node<E> nthLast (int n) {
        Node<E> previous = first, curr = first;

        while (n-- > 0)
            curr = curr.next;
        while (curr != null) {
            curr = curr.next;
            previous = previous.next;
        }

        return previous;
    }

    /**
     * Find this value within the linked list
     *
     * @param find the value to find
     * @return true, if found
     */
    public boolean contains (E find) {
        Node<E> curr = first;
        while (curr.val != find) {
            curr = curr.next;
            if (curr == null)
                return false;
        }
        return true;
    }

    /**
     * Class to represent the individual Nodes in the Linked List
     *
     * @param <E> generic form
     */
    private static class Node<E> {
        E val;
        Node<E> next;

        private Node (E val, Node<E> next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString () {
            return String.format("%s", this.val);
        }
    }

    /**
     * Class that provides for linear traversal of the Linked List
     */
    private class Iterate implements Iterating<E> {
        Node<E> curr, next;

        private Iterate () {
            curr = null;
            next = first;
        }

        @Override
        public boolean hasNext () {
            return (next != null);
        }

        @Override
        public E next () {
            curr = next;
            next = next.next;
            return curr.val;
        }

        @Override
        public void reset () {
            curr = null;
            next = first;
        }
    }


}
