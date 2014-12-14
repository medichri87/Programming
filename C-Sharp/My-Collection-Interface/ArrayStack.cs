// using System;
// using System.Linq;
using System;

namespace Collection
{
    public class ArrayStack<E> : AbstractStack<E>, Stack<E>
	{
        public int Count { get; private set; }

        private E[] array;
        private static readonly int DEFAULT_MAX_SIZE = 10;
        private int currMaxSize;

        public ArrayStack()
		{
            this.array = new E[DEFAULT_MAX_SIZE];
            this.currMaxSize = DEFAULT_MAX_SIZE;
            this.Count = 0;
		}

        public ArrayStack(E[] arr)
            : this()
        {
            this.array = new E[arr.Length];

            foreach (E item in arr){
                Push(item);
            }
        }

        public ArrayStack(Collection<E> collection)
            : this(collection.ToArray())
        {

        }

        public override int Size() {
            return Count;
        }

        public override void Add(E item) {
            Push(item);
        }

        public override Iterator<E> Iterator() {
            return new StackItr(this);
        }

        public override void Clear() {
            Count = 0;
            Array.Resize(ref array, DEFAULT_MAX_SIZE);
            currMaxSize = DEFAULT_MAX_SIZE;
        }

        public sealed override void Push(E item) {
            CheckForExpansion();
            array[Count++] = item;
        }

        private void CheckForExpansion(){
            if (Count == currMaxSize){
                Array.Resize<E>(ref array, (currMaxSize *= 3));
            }
        }

        public sealed override E Pop() {
            if (IsEmpty())
                throw new StackEmptyException("Stack is empty");
            E temp = array[--Count];
            Array.Resize<E>(ref array, Count);
            return temp;
        }

        private class StackItr : Iterator<E>{

            private ArrayStack<E> instance;
            private int index;

            public StackItr(ArrayStack<E> instance) {
                this.instance = instance;
            }

            public bool HasNext() {
                return (index < instance.Size());
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

