using System.Linq;
using System;
using Collection;

namespace Collection
{
    /// <summary>
    /// An implementation of the Queue interface using two Stacks
    /// </summary>
    public class TwoStackQueue<E> : AbstractQueue<E>, Queue<E>
	{
        private Stack<E> inbox, outbox;

        public TwoStackQueue()
		{
            inbox = new LinkedStack<E>();
            outbox = new LinkedStack<E>();
		}

        public TwoStackQueue(E[] arr)
            : this()
        {
            foreach (E item in arr)
                Enqueue(item);
        }

        public TwoStackQueue(Collection<E> collection)
            : this(collection.ToArray())
        {

        }

        public override void Add(E item) {
            Enqueue(item);
        }

        public override int Size() {
            return inbox.Size();
        }

        public override Iterator<E> Iterator() {
            return inbox.Iterator();
        }

        public override void Clear() {
            inbox.Clear();
            outbox.Clear();
        }

        public override E Dequeue() {
            if (inbox.IsEmpty())
                throw new InvalidOperationException("Stack is empty");

            //Put all inbox items into outbox
            while (!inbox.IsEmpty()){
                outbox.Push(inbox.Pop());
            }

            E temp = outbox.Pop();

            //Push contents of outbox back into inbox
            while (!outbox.IsEmpty()){
                inbox.Push(outbox.Pop());
            }

            return temp;
        }

        public sealed override void Enqueue(E item) {
            inbox.Push(item);
        }
            	
    }
}

