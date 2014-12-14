package ICollection;

import java.util.NoSuchElementException;

/**
 * Provides a Stack which performs the usual functions of a Stack such as pop(),
 * peek(), and push, but also maintains track of the max and min values in
 * constant time O(1).
 *
 * @date Jul 29, 2014
 * @author Chris Medina
 */
public class MinMaxStack<E> extends LinkedIStack<E> {

    private IStack<E> minStack, maxStack;

    public MinMaxStack() {
        this.minStack = new LinkedIStack<>();
        this.maxStack = new LinkedIStack<>();
    }

    public MinMaxStack(E[] arr) {
        this();
        for (E item : arr) {
            this.push(item);
        }
    }

    public MinMaxStack(ICollection<E> collection) {
        this(collection.toArray());
    }

    public E max() {
        if (maxStack.isEmpty())
            throw new NoSuchElementException("Stack is empty");
        return maxStack.peek();
    }

    public E min() {
        if (minStack.isEmpty())
            throw new NoSuchElementException("Stack is empty");
        return minStack.peek();
    }

    @Override
    public void push(E item) {
        super.push(item);

        if (minStack.isEmpty())
            minStack.push(item);
        else if (((Comparable<E>) item).compareTo(min()) < 0) {
            minStack.push(item);
        }

        if (maxStack.isEmpty())
            maxStack.push(item);
        else if (((Comparable<E>) item).compareTo(max()) > 0) {
            maxStack.push(item);
        }

        //System.out.println("Current maxStack: " + maxStack + " Current minStack: " + minStack);
    }

    @Override
    public E pop() {
        E removed = super.pop();
        if (isEmpty())
            throw new NoSuchElementException("Stack is empty");
        //removing max
        if (removed.equals(max())) {
            maxStack.pop();
        }
        if (removed.equals(min())) {
            minStack.pop();
        }

        //System.out.println("Current maxStack: " + maxStack + " Current minStack: " + minStack);
        return removed;
    }

}
