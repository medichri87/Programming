// using System;
using System.Linq;
using System;
using Collection;

namespace Collection
{
    /// <summary>
    /// Represents a Doubly-Linked, Double-ended linked list implementation of the List interface
    /// </summary>
    public class DLinkedList<E> : AbstractList<E>, List<E>, DoubleEnded<E>
	{

        private Node<E> first, last;

        public int Count { get; private set; }

        public DLinkedList()
		{
            Count = 0;
            first = null;
            last = null;
		}

        public DLinkedList(E[] arr)
            : this(){
            foreach (E item in arr)
                InsertLast(item);
        }

        public DLinkedList(Collection<E> collection)
            : this(collection.ToArray()){

        }

        public override bool IsEmpty()
        {
            return (first == null);
        }

        public E RemoveFirst() {
            return Remove(first.Value);
        }

        public E RemoveLast() {
            return Remove(last.Value);
        }

        public E GetFirst(){
            if (IsEmpty())
                throw new InvalidOperationException("List is empty");
            return first.Value;
        }

        public E GetLast() {
            if (IsEmpty())
                throw new InvalidOperationException("List is empty");
            return last.Value;        
        }

        public void InsertFirst(E item) {
            Node<E> node = new Node<E>(null, item, first);
            if (IsEmpty())
                last = node;
            else
                first.Previous = node;
            first = node;
            Count++;
        }

        public void InsertLast(E item) {
            Node<E> node = new Node<E>(last, item, null);
            if (IsEmpty())
                first = node;
            else
                last.Next = node;
            last = node;
            Count++;
        }

        public void InsertBefore(E find, E val) {
            if (IsEmpty())
                throw new InvalidOperationException("List is empty");
            Node<E> curr = first;
            while (!curr.Value.Equals(find)){
                curr = curr.Next;
                if (curr == null)
                    throw new InvalidOperationException("Item not found");
            }

            if (curr == first){
                InsertFirst(val);
            }
            else {
                InsertAfter(curr.Previous.Value, val);
            }
        }

        public void InsertAfter(E find, E val) {
            if (IsEmpty())
                throw new InvalidOperationException("List is empty");
            Node<E> curr = first;
            while (!curr.Value.Equals(find)){
                curr = curr.Next;
                if (curr == null)
                    throw new InvalidOperationException("Item not found");
            }
            Node<E> node = new Node<E>(curr, val, curr.Next);
            if (curr == first){
                if (curr.Next == null){
                    InsertLast(val);
                    return;
                }
                else {
                    first.Next.Previous = node;
                    first.Next = node;
                }
            }
            else if (curr == last){
                last.Next = node;
                last = node;
            }
            else {
                curr.Next.Previous = node;
                curr.Next = node;
            }
            Count++;
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
            Node<E> curr = first;
            while (!curr.Value.Equals(item)){
                curr = curr.Next;
                if (curr == null)
                    throw new InvalidOperationException("Item not found");
            }

            if (curr == first){
                if (curr.Next == null){
                    first = null;
                    last = null;
                }
                else {
                    first.Next.Previous = null;
                    first = first.Next;
                }
            }
            else if (curr == last){
                last.Previous.Next = null;
                last = last.Previous;
            }
            else {
                curr.Next.Previous = curr.Previous;
                curr.Previous.Next = curr.Next;
            }

            Count--;
            return curr.Value;
        }

        public override Iterator<E> Iterator() {
            return new ListItr(this);
        }

        public override void Clear() {
            first = null;
            last = null;
            Count = 0;
        }

        public override List<E> SubList(int from, int to) {
            if (from < 0 || to < 0 || from > to || to > Count){
                throw new IndexOutOfRangeException("Index is out of range");
            }
            E[] arr = this.ToArray().SubArray(from, to);
            List<E> list = new ArrayList<E>(arr);
            return list;
        }

        public override E RemoveIndex(int index) {
            Node<E> temp = nodeAtIndex(index);
            return Remove(temp.Value);
        }

        private Node<E> nodeAtIndex(int index){
            if (index < 0 || index > Count)
                throw new IndexOutOfRangeException("Index is out of range");
            Node<E> curr = first;
            int idx = 0;

            while (idx < index){
                curr = curr.Next;
                idx++;
            }

            return curr;
        }

        private class Node<E>{
            public Node<E> Previous { get; set; }

            public Node<E> Next { get; set; }

            public E Value { get; set; }

            public Node(Node<E> prev, E val, Node<E> next){
                this.Previous = prev;
                this.Value = val;
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
                return Previous.Equals(other.Previous) && Next.Equals(other.Next) && Value.Equals(other.Value);
            }


            public override int GetHashCode() {
                unchecked {
                    return (Previous != null ? Previous.GetHashCode() : 0) ^ (Next != null ? Next.GetHashCode() : 0) ^ (Value != null ? Value.GetHashCode() : 0);
                }
            }

            public override string ToString() {
                return string.Format("%s", this.Value);
            }
            
            
        }

        private class ListItr : ListIterator<E>{
            private DLinkedList<E> instance;
            private Node<E> prev, curr;

            public ListItr(DLinkedList<E> instance){
                this.instance = instance;
                prev = null;
                curr = instance.first;
            }

            public bool HasPrevious() {
                return (prev != null);
            }

            public E Previous() {
                Node<E> temp = prev;
                curr = prev;
                prev = prev.Previous;
                return temp.Value;
            }

            public bool HasNext() {
                return (curr != null);
            }

            public E Next() {
                Node<E> temp = curr;
                prev = curr;
                curr = curr.Next;
                return temp.Value;
            }

            public void Reset() {
                prev = null;
                curr = instance.first;
            }
            
        }
    }


}

