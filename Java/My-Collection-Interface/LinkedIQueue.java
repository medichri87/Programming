package ICollection;

/**
 * Linked List implementation of the IQueue interface
 *
 * @date Jul 29, 2014
 * @author Chris Medina
 */
public class LinkedIQueue<E> extends AbstractIQueue<E> implements IQueue<E> {

    private IList<E> list;

    /**
     * Constructs an empty LinkedIQueue
     */
    public LinkedIQueue() {
        list = new DLinkedIList<>();
    }

    /**
     * Constructs a LinkedIQueue based on an array of input
     *
     * @param array the array of input to insert into the Queue
     */
    public LinkedIQueue(E[] array) {
        list = new DLinkedIList<>(array);
    }

    /**
     * Constructs a LinkedIQueue based on an ICollection of inputs
     *
     * @param collection the ICollection to input into the Queue
     */
    public LinkedIQueue(ICollection<E> collection) {
        list = new DLinkedIList<>(collection);
    }

    @Override
    public E peek() {
        return ((DLinkedIList<E>) list).first();
    }

    @Override
    public void enqueue(E item) {
        ((DLinkedIList<E>) list).insertLast(item);
    }

    @Override
    public E dequeue() {
        return ((DLinkedIList<E>) list).removeFirst();
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
        enqueue(item);
    }

    @Override
    public int size() {
        return list.size();
    }

}
