// using System;
// using System.Linq;
using System;
using System.Linq;

namespace Collection
{
    //Dynamically-expanding, array-based implementation of the List interface.
    public class ArrayList<E> : AbstractList<E>, List<E>
	{
        private E[] array;

        public int size { get; private set; }

        private static readonly int DEFAULT_MAX_SIZE = 10;
        private int currMaxSize;

        public ArrayList()
            : this(10)
        {

        }

        public ArrayList(int ms){
            this.size = 0;
            this.currMaxSize = ms;
            this.array = new E[ms];
        }

        public ArrayList(E[] arr)
            : this(arr.Length){
            foreach (E item in arr)
                insertLast(item);
        }

        public ArrayList(Collection<E> collection)
            : this(collection.ToArray()){
        }

        public override void Add(E item) {
            insertLast(item);
        }

        private void insertLast(E item){
            CheckForExpansion();
            array[size++] = item;
        }

        private void CheckForExpansion(){
            if (size == currMaxSize){
                Array.Resize<E>(ref array, (currMaxSize *= 3));
            }
        }

        public override int Size() {
            return size;
        }

        public override E Remove(E item) {
            int index = IndexOf(item);
            if (index != -1){
                return RemoveIndex(index);
            }
            throw new InvalidOperationException("Item not found");
        }

        public override Iterator<E> Iterator() {
            return new ListItr(this);
        }

        public override List<E> SubList(int from, int to) {
            if (from < 0 || to < 0 || from > to){
                throw new IndexOutOfRangeException("Bad index choice");
            }

            List<E> list = new ArrayList<E>();
            for (int i = from; i < to; i++){
                list.Add(array[i]);
            }

            return list;
        }

        public override E RemoveIndex(int index) {
            CheckIndex(index);
            E temp = array[index];
            for (int i = index; i < size - 1; i++){
                array[i] = array[i + 1];
            }
            size--;
            return temp;
        }

        private void CheckIndex(int index){
            if (index < 0 || index >= size)
                throw new IndexOutOfRangeException("Bad index choice");
        }

       
        public override void Clear() {
            Array.Clear(array, 0, size);
            Array.Resize<E>(ref array, (currMaxSize = DEFAULT_MAX_SIZE));
            size = 0;
        }

        private class ListItr : Iterator<E>{
            private ArrayList<E> list;
            private int index;

            public ListItr(ArrayList<E> temp){
                list = temp;
                index = 0;
            }

            public bool HasNext() {
                return (index < list.Size());
            }

            public E Next() {
                return list.array[index++];
            }

            public void Reset()
            {
                index = 0;
            }

        }

	}

}

