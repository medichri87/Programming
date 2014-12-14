package ICollection;

import java.util.NoSuchElementException;

/**
 *
 * @param <E>
 * @date Jul 29, 2014
 * @author Chris Medina
 *
 * @purpose Provides a skeletal (partial) implementation of the IQueue interface
 */
public abstract class AbstractIQueue<E> extends AbstractICollection<E> implements IQueue<E> {

    @Override
    public abstract void enqueue(E item);

    @Override
    public abstract E dequeue();

    @Override
    public E peek() {
        IEnumerator<E> it = iterator();
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");
        return it.next();
    }

    @Override
    public E remove(E item) {
        throw new UnsupportedOperationException("Queues cannot remove specific items");
    }

    protected static class QueueEmptyException extends RuntimeException {

        public QueueEmptyException() {
        }

        public QueueEmptyException(String message) {
            super(message);
        }

    }

}
