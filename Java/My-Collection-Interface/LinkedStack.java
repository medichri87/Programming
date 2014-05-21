package ADT;

import java.util.EmptyStackException;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/23/2014 <p>
 * Class Purpose: Represents a Linked-List Stack implementation using a Doubly-linked , Double-ended List
 */
public class LinkedStack<E> extends AbstractStack<E> implements EStack<E> {

    private EList<E> stack;

    public LinkedStack () {
        this.stack = new DLinkedList<>();
    }

    public LinkedStack (Object[] in) {
        this.stack = new DLinkedList<>(in);
    }

    public LinkedStack (ECollection<? extends E> in) {
        this.stack = new DLinkedList<>(in);
    }

    /**
     * Add this element to the end of the current Stack
     *
     * @param e value to add
     */
    public void add (E e) {
        stack.add(e);
    }

    /**
     * Empty the entire Stack, removing all items
     */
    public void clear () {
        stack.clear();
    }

    /**
     * The size of the current Stack
     *
     * @return the number of elements found
     */
    public int size () {
        return stack.size();
    }

    /**
     * An iterator that provides for sequential traversal of the Stack
     *
     * @return a new iterator from the starting point of the current Stack
     */
    public EIterator<E> iterator () {
        return stack.iterator();
    }

    /**
     * Remove the element at the top of the Stack
     *
     * @return the element at the top of Stack
     * @throws java.util.EmptyStackException if the Stack is empty
     */
    public E pop () {
        if (isEmpty())
            throw new EmptyStackException();
        return ((DLinkedList<E>) stack).removeLast();
    }

    /**
     * Insert this element onto the top of the Stack
     *
     * @param item the item to insert
     */
    public void push (E item) {
        ((DLinkedList<E>) stack).insertFirst(item);
    }


}
