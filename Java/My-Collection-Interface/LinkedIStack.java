package ICollection;

import java.util.NoSuchElementException;

/**
 * LinkedIStack represents a Linked List implementation of a Stack
 *
 * @param <E>
 * @date Jul 28, 2014
 * @author Chris Medina
 *
 */
public class LinkedIStack<E> extends AbstractIStack<E> implements IStack<E> {

    private IList<E> list;

    public LinkedIStack() {
        this.list = new DLinkedIList<>();
    }

    public LinkedIStack(E[] arr) {
        this.list = new DLinkedIList<>(arr);
    }

    public LinkedIStack(ICollection<E> collection) {
        this.list = new DLinkedIList<>(collection);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public IEnumerator<E> iterator() {
        return list.iterator();
    }

    @Override
    public void add(E item) {
        list.add(item);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void push(E item) {
        ((DLinkedIList<E>) list).insertLast(item);
    }

    @Override
    public E pop() {
        if (isEmpty())
            throw new NoSuchElementException("Stack is empty");
        return ((DLinkedIList<E>) list).removeLast();
    }

    @Override
    public E peek() {
        return ((DLinkedIList<E>) list).first();
    }

}
