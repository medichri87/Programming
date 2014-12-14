using System.Linq;
using System;

namespace Collection
{
    /// <summary>
    /// Represents an implementation of the Stack interface using a Doubly-Linked List 
    /// </summary>
    public class LinkedStack<E> : AbstractStack<E>, Stack<E>
	{
        private List<E> list;

        public LinkedStack()
		{
            list = new DLinkedList<E>();
		}

        public LinkedStack(E[] arr)
        {
            list = new DLinkedList<E>(arr);
        }

        public LinkedStack(Collection<E> collection)
            : this(collection.ToArray())
        {

        }

        public override void Add(E item) {
            list.Add(item);
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

        public override void Push(E item) {
            (list as DLinkedList<E>).InsertLast(item);
        }

        public override E Pop() {
            return (list as DLinkedList<E>).RemoveLast();
        }
    }
}

