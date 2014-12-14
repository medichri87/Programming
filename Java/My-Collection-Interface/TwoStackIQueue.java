package ICollection;

/**
 * Represents a two-stack implementation of the IQueue implementation
 *
 * @date Jul 29, 2014
 * @author Chris Medina
 */
public class TwoStackIQueue<E> extends AbstractIQueue<E> implements IQueue<E> {
    
    private IStack<E> inbox, outbox;
    
    public TwoStackIQueue() {
        this.inbox = new LinkedIStack<>();
        this.outbox = new LinkedIStack<>();
    }
    
    public TwoStackIQueue(E[] array) {
        this.inbox = new LinkedIStack<>(array);
        this.outbox = new LinkedIStack<>(array);
    }
    
    public TwoStackIQueue(ICollection<E> collection) {
        this(collection.toArray());
    }
    
    @Override
    public void enqueue(E item) {
        inbox.push(item);
    }
    
    @Override
    public E dequeue() {
        if (isEmpty())
            throw new QueueEmptyException("Queue is empty");
        //pass current inbox off into outbox
        E temp = null;
        while (!inbox.isEmpty()) {
            outbox.push(temp);
        }

        //remove item from top of outbox stack (front of original)
        temp = outbox.pop();

        //put contents of outbox back into inbox
        while (!outbox.isEmpty()) {
            inbox.push(outbox.pop());
        }
        
        return temp;
    }
    
    @Override
    public void clear() {
        inbox.clear();
    }
    
    @Override
    public IEnumerator<E> iterator() {
        return inbox.iterator();
    }
    
    @Override
    public void add(E item) {
        enqueue(item);
    }
    
    @Override
    public int size() {
        return inbox.size();
    }
    
}
