// using System;
// using System.Linq;
using System;

namespace Collection
{
    /// <summary>
    /// Array-based implementation of the Queue interface
    /// </summary>
    public class ArrayQueue<E>: AbstractQueue<E>, Queue<E>
	{
        public int Count { get; private set; }

        private E[] array;
        private int currMaxSize;
        private static readonly int DEFAULT_SIZE = 10;


        public ArrayQueue()
		{
            array = new E[DEFAULT_SIZE];
            currMaxSize = DEFAULT_SIZE;
		}

        public ArrayQueue(E[] arr)
            : this()
        {
            foreach (E item in arr){
                Enqueue(item);
            }
        }

        public override void Add(E item) {
            Enqueue(item);
        }

        public override int Size() {
            return Count;
        }

        public override Iterator<E> Iterator() {
            return new QueueItr(this);
        }

        public override void Clear() {
            Array.Clear(array, 0, Count);
            Array.Resize<E>(ref array, (currMaxSize = DEFAULT_SIZE));
            Count = 0;    
        }

        public override E Dequeue() {
            if (IsEmpty())
                throw new QueueEmptyException("Queue is empty");
            E temp = array[0];

            for (int i = 0; i < Count - 1; i++){
                array[i] = array[i + 1];
            }

            Count--;

            Array.Resize<E>(ref array, Count);
            return temp;
        }

        public sealed override void Enqueue(E item) {
            CheckForExpansion();
            array[Count++] = item;
        }

        private void CheckForExpansion(){
            if (Count == currMaxSize){
                Array.Resize<E>(ref array, (currMaxSize *= 3));
            }
        }

        private class QueueItr : Iterator<E>{
            private ArrayQueue<E> instance;
            private int index;

            public QueueItr(ArrayQueue<E> instance) {
                this.instance = instance;
                this.index = 0;
            }

            public bool HasNext() {
                return (index < instance.Count);
            }

            public E Next() {
                return instance.array[index++];
            }

            public void Reset() {
                index = 0;
            }

        }

	}
}

