// using System;
// using System.Linq;
using System;
using System.Runtime.InteropServices;

namespace Collection
{
    /// <summary>
    /// Represents a Singly-Linked, Double-ended linked list implementation of the List interface
    /// </summary>
    public class SLinkedList<E> : AbstractList<E>, List<E>, DoubleEnded<E>
	{

        private Node<E> First;

        private Node<E> Last;

        public int Count { get; private set; }

        public SLinkedList()
		{
            this.First = null;
            this.Last = null;
            this.Count = 0;
		}

        public SLinkedList(E[] arr)
            : this(){
            foreach (E item in arr)
                InsertLast(item);
        }

        public SLinkedList(Collection<E> collection)
            : this(collection.ToArray()){
        }

        public override void Add(E item) {
            InsertLast(item);
        }

        public override int Size() {
            return Count;
        }

        public override E Remove(E item) {
            if (IsEmpty())
                throw new InvalidOperationException("List is empty");
            Node<E> curr = First, prev = First;

            while (!curr.Val.Equals(item)){
                prev = curr;
                curr = curr.Next;
                if (curr == null)
                    throw new InvalidOperationException("Item not found");
            }

            if (curr == First){
                if (curr.Next == null){
                    First = null;
                    Last = null;
                }
                else {
                    First = First.Next;
                }
            }
            else if (curr == Last){
                prev.Next = null;
                Last = prev;
            }
            else {
                prev.Next = curr.Next;
            }
                
            Count--;
            return curr.Val;
        }

        public override Iterator<E> Iterator() {
            return new ListItr(this);
        }

        public override void Clear() {
            First = null;
            Last = null;
            Count = 0;
        }

        public override List<E> SubList(int from, int to) {
            if (from < 0 || to < 0 || from > to || to > this.Count){
                throw new IndexOutOfRangeException("Index is out of range");
            }
            E[] array = this.ToArray();
            List<E> list = new ArrayList<E>();

            for (int i = from; i < to; i++){
                list.Add(array[i]);
            }

            return list;
        }

        public override E RemoveIndex(int index) {
            Node<E> temp = nodeAtIndex(index);
            return Remove(temp.Val);
        }

        private Node<E> nodeAtIndex(int index){
            if (IsEmpty())
                throw new InvalidOperationException("List is empty");
            if (index < 0 || index >= Size())
                throw new IndexOutOfRangeException("Index is out of range");
            Node<E> curr = First;
            int i = 0;

            while (i < index){
                curr = curr.Next;
                i++;
            }

            return curr;
        }

        public E RemoveFirst() {
            return Remove(First.Val);
        }

        public E RemoveLast() {
            return Remove(Last.Val);
        }

        public E GetFirst() {
            if (IsEmpty())
                throw new InvalidOperationException("List is empty");
            return First.Val;
        }

        public E GetLast() {
            if (IsEmpty())
                throw new InvalidOperationException("List is empty");
            return Last.Val;        
        }

        public void InsertFirst(E item) {
            Node<E> node = new Node<E>(item, First);
            if (IsEmpty())
                Last = node;
            First = node;
            Count++;        
           }

        public void InsertLast(E item) {
            Node<E> node = new Node<E>(item, null);
            if (IsEmpty())
                First = node;
            else{
                Last.Next = node;
            }
            Last = node;
            Count++;
        }

        public void InsertBefore(E find, E val) {
            if (!Contains(find))
                throw new InvalidOperationException("Item not found");
            Node<E> curr = First;
            Node<E> prev = First;
            while (curr.Val.Equals(find)){
                prev = curr;
                curr = curr.Next;
            }

            if (curr == First)
                InsertFirst(val);
            else{
                InsertAfter(prev.Val, val);
            }
        }

        public void InsertAfter(E find, E val) {
            if (!Contains(find))
                throw new InvalidOperationException("Item not found");
            Node<E> curr = First;
            Node<E> prev = First;

            while (!curr.Val.Equals(find)){
                prev = curr;
                curr = curr.Next;
            }

            Node<E> node = new Node<E>(val, curr.Next);

            if (curr == First){
                if (curr.Next == null){
                    InsertLast(val);
                    return;
                }
                else
                    First.Next = node;   
            }
            else if (curr == Last){
                Last.Next = node;
                Last = node;
            }
            else {
                prev.Next = node;
            }

            Count++;
        }

        private class Node<E>{
            public E Val { get; set; }

            public Node<E> Next { get; set; }

            public Node(E val, Node<E> next){
                this.Val = val;
                this.Next = next;
            }

            public override bool Equals(object obj) {
                if (obj == null)
                    return false;
                if (ReferenceEquals(this, obj))
                    return true;
                if (obj.GetType() != typeof(Node<E>))
                    return false;
                Node<E> other = (Node<E>)obj;
                return Val.Equals(other.Val) && Next == other.Next;
            }


            public override int GetHashCode() {
                unchecked {
                    return (Val != null ? Val.GetHashCode() : 0) ^ (Next != null ? Next.GetHashCode() : 0);
                }
            }

            public override string ToString() {
                return string.Format("%s", this.Val);
            }

        }

        private class ListItr : Iterator<E>{
            #region Iterator implementation

            private Node<E> curr;
            private SLinkedList<E> instance;

            public ListItr(SLinkedList<E> instance){
                this.instance = instance;
                curr = instance.First;
            }

            public bool HasNext() {
                return (curr != null);
            }

            public E Next() {
                Node<E> temp = curr;
                curr = curr.Next;
                return temp.Val;
            }

            public void Reset() {
                curr = instance.First;
            }

            #endregion

        }
    }


}

