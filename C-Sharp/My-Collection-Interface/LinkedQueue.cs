// using System;
// using System.Linq;
using System;

namespace Collection
{
    /// <summary>
    /// A Linked-list implementation of the Queue interface
    /// </summary>
    public class LinkedQueue<E> : AbstractQueue<E> , Queue<E>
	{
        private List<E> list;

        public LinkedQueue()
		{
            this.list = new DLinkedList<E>();
		}

        public LinkedQueue(E[] array)
            : this()
        {
            this.list = new DLinkedList<E>(array);
        }

        public LinkedQueue(Collection<E> collection)
            : this(collection.ToArray())
        {

        }

        public override void Add(E item) {
            Enqueue(item);
        }

        public override int Size() {
            return list.Size();
        }

        public override Iterator<E> Iterator() {
            return list.Iterator();
        }

        public override void Clear() {
            list.Clear();
        }

        public override E Dequeue() {
            return (list as DLinkedList<E>).RemoveFirst();
        }

        public override void Enqueue(E item) {
            (list as DLinkedList<E>).InsertLast(item);
        }

	}
}

