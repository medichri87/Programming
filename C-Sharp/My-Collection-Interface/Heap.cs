// using System;
// using System.Linq;
using System;

namespace Collection
{
    /// <summary>
    /// Represents a Max Heap where the largest items will always appear in the root position. (A necessary for priority queues)
    /// </summary>
    public class Heap<E> : AbstractTree<E> , Tree<E> where E : IComparable
	{
        private Node<E>[] array;

        public int Count { get; private set; }

        private static readonly int DEFAULT_SIZE = 10;
        private int currMaxSize;

        public Heap(int ms)
		{
            this.array = new Node<E>[ms];
            this.currMaxSize = ms;
            this.Count = 0;
		}

        public Heap()
            : this(DEFAULT_SIZE){

        }

        public Heap(E[] arr)
            : this(arr.Length){
            foreach (E item in arr){
                Add(item);
            }
        }

        public Heap(Collection<E> collection)
            : this(collection.ToArray()){

        }

        #region implemented abstract members of AbstractTree

        public override void Clear() {
            Array.Clear(array, 0, Count);
            Array.Resize<Node<E>>(ref array, (currMaxSize = DEFAULT_SIZE));
            Count = 0;
        }

        public sealed override void Add(E item) {
            CheckForExpansion();
            Node<E> node = new Node<E>(item);
            array[Count] = node;
            TrickleUp(Count++);
        }

        private void CheckForExpansion(){
            if (Count == currMaxSize)
                Array.Resize<Node<E>>(ref array, (currMaxSize *= 3));
        }

        private void TrickleUp(int index){
            int parent = (index - 1) / 2;

            //item to move up into place
            Node<E> bottom = array[index];
            
            while (index > 0 && array[parent].Val.CompareTo(bottom.Val) < 0){

                array[index] = array[parent];

                index = parent;
                parent = (parent - 1) / 2;
            }

            array[index] = bottom;
        }

        public override int Size() {
            return Count;
        }

        public override E Remove() {
            if (IsEmpty())
                throw new TreeEmptyException("Tree is empty");
            E temp = array[0].Val;
            array[0] = array[--Count];
            TrickleDown(0);
            return temp;
        }

        private void TrickleDown(int index){
            Node<E> top = array[0];

            while (index < Count / 2){
                int largerChild = -1;
                int leftChild = (index * 2 + 1), rightChild = leftChild + 1;

                if (rightChild < Count && array[leftChild].Val.CompareTo(array[rightChild].Val) < 0){
                    largerChild = rightChild;
                }
                else{
                    largerChild = leftChild;
                }

                if (top.Val.CompareTo(array[largerChild].Val) >= 0){
                    break;
                }

                array[index] = array[largerChild];
                index = largerChild;
            }

            array[index] = top;
        }

        public override E[] ToArray() {
            E[] arr = new E[Count];
            int index = 0;
            for (int i = 0; i < Count; i++){
                arr[index++] = array[i].Val;
            }
            return arr;
        }

        public override Iterator<E> Iterator() {
            return new TreeItr(this);
        }

        #endregion

        private class TreeItr : Iterator<E>{
            private int index;
            private Heap<E> instance;

            public TreeItr(Heap<E> instance){
                this.index = 0;
                this.instance = instance;
            }

            #region Iterator implementation

            public bool HasNext() {
                return (index < instance.Size());
            }

            public E Next() {
                return instance.array[index++].Val;
            }

            public void Reset() {
                this.index = 0;
            }

            #endregion
        }

        private class Node<E>{
            public E Val { get; set; }

            public Node(E val){
                this.Val = val;
            }

            public override string ToString()
            {
                return string.Format("{0}", Val);
            }

            public override bool Equals(object obj)
            {
                if (this == null || this.GetType() != obj.GetType())
                    return false;
                if (this == obj)
                    return true;
                Node<E> temp = (Node<E>)obj;
                return temp.Val.Equals(this.Val);
            }

            public override int GetHashCode() {
                int result = 11;
                result *= (ReferenceEquals(null, Val) ? 0 : Val.GetHashCode());
                return result;
            }
        }
	}
}

