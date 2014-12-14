package ICollection;

/**
 *
 * @date Jul 30, 2014
 * @author Chris Medina
 *
 * A priority queue is a data structure which will insert the largest(highest
 * priority) value to the front of the queue. This implementation is backed by a
 * Max-Heap structure.
 */
public class PriorityIQueue<E> extends AbstractIQueue<E> implements IQueue<E> {

    private ITree<E> heap;

    public PriorityIQueue() {
        this.heap = new MaxHeap<>();
    }

    public PriorityIQueue(E[] array) {
        this.heap = new MaxHeap<>(array);
    }

    public PriorityIQueue(ICollection<E> collection) {
        this.heap = new MaxHeap<>(collection);
    }

    @Override
    public void enqueue(E item) {
        heap.add(item);
    }

    @Override
    public E dequeue() {
        return heap.removeRoot();
    }

    @Override
    public void clear() {
        heap.clear();
    }

    @Override
    public void add(E item) {
        enqueue(item);
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public IEnumerator<E> iterator() {
        return ((MaxHeap<E>) heap).iterator();
    }

}
