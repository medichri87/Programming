package ADT;

import java.util.Stack;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/24/2014 <p>
 * Class Purpose: Provide the methods that accomplish the contract for all Stack implementations. All stacks are LIFO
 * (Last-In, First-Out) in nature, so these methods provide those features, to be accomplished by classes that carry out
 * the Stack interface.
 */
public interface EStack<E> extends ECollection<E> {
    E peek ();

    void push (E val);

    E pop ();
}
