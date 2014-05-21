package ADT;

import java.util.EmptyStackException;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/23/2014 <p>
 * Class Purpose:
 */
public class ArrayStack<E> extends AbstractStack<E> implements EStack<E> {
    private EList<E> list;

    /**
     * Construct an empty Stack
     */
    public ArrayStack () {
        list = new EArrayList<>();
    }

    /**
     * Construct a Stack from an array input
     *
     * @param in the array to insert into the Stack upon constructor invocation
     */
    public ArrayStack (Object[] in) {
        list = new EArrayList<>(in);
    }

    public ArrayStack (ECollection<? extends E> in) {
        list = new EArrayList<>(in);
    }

    /**
     * Empty the entire Stack, removing all items
     */
    public void clear () {
        list.clear();
    }

    /**
     * Add this element to the end of the current Stack
     *
     * @param e value to add
     */
    public void add (E e) {
        push(e);
    }

    /**
     * The size of the current Stack
     *
     * @return the number of elements found
     */
    public int size () {
        return list.size();
    }

    /**
     * An iterator that provides for sequential traversal of the Stack
     *
     * @return a new iterator from the starting point of the current Stack
     */
    public EIterator<E> iterator () {
        return list.iterator();
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
        return list.removeIndex(size() - 1);
    }

    /**
     * Push this element onto the top of the Stack
     *
     * @param item the item to insert
     */
    public void push (E item) {
        list.add(item);
    }

}
