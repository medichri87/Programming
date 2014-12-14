// using System;
// using System.Linq;
using System;

namespace Collection
{
    public class PriorityQueue<E> : AbstractQueue<E>, Queue<E> where E: IComparable
	{
        private Tree<E> heap;

        public PriorityQueue()
		{
            heap = new Heap<E>();
		}

        public PriorityQueue(E[] arr)
        {
            heap = new Heap<E>(arr);
        }

        public PriorityQueue(Collection<E> collection)
            : this(collection.ToArray())
        {

        }

        #region implemented abstract members of AbstractCollection

        public override void Add(E item) {
            Enqueue(item);
        }

        public override int Size() {
            return heap.Size();
        }

        public override Iterator<E> Iterator() {
            return heap.Iterator();
        }

        public override void Clear() {
            heap.Clear();
        }

        #endregion

        #region implemented abstract members of AbstractQueue

        public override E Dequeue() {
            return heap.Remove();
        }

        public override void Enqueue(E item) {
            heap.Add(item);
        }

        #endregion
	}
}

