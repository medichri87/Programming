using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OOP
{
    /// <summary>
    /// DLinkedList represents a double-ended, doubly-linked List data structure
    /// Date: 5-26-2014
    /// </summary>
    /// <typeparam name="E">Generic, type-safe</typeparam>
    public class DLinkedList<E>
    {
        public Node<E> First { get; private set; }
        public Node<E> Last { get; private set; }
        public int Size { get; private set; }

        /// <summary>
        /// Construct an empty Linked List
        /// </summary>
        public DLinkedList () {
            First = Last = null;
            Size = 0;
        }

        public override string ToString () {
            StringBuilder sb = new StringBuilder("[");

            for (Node<E> f = First; f != null; f = f.Next) {
                if (f.Next == null)
                    sb.Append(f);
                else
                    sb.Append(f + ", ");
            }
            sb.Append("]");
            return sb.ToString();
        }
        /// <summary>
        /// Determine if this List is empty
        /// </summary>
        /// <returns>true, if there are no items in the list</returns>
        public bool IsEmpty () {
            return (First == null);
        }

        /// <summary>
        /// Insert an item to the front of the List
        /// </summary>
        /// <param name="item"></param>
        public void InsertFirst (E item) {
            Node<E> node = new Node<E>(null, item, First);
            if (IsEmpty())
                Last = node;
            else
                First.Previous = node;
            First = node;
            Size++;
        }

        /// <summary>
        /// Insert an item to the end
        /// </summary>
        /// <param name="item"></param>
        public void InsertLast (E item) {
            Node<E> node = new Node<E>(Last, item, null);
            if (IsEmpty())
                First = node;
            else
                Last.Next = node;
            Last = node;
            Size++;
        }

        /// <summary>
        /// Insert a value immediately after another specific value
        /// </summary>
        /// <param name="find">The item to find to insert after</param>
        /// <param name="item">The actual item to insert</param>
        public void InsertAfter (E find, E item) {
            Node<E> Temp = Search(find);
            Node<E> Node = new Node<E>(Temp, item, Temp.Next);

            if (Temp == First) {
                if (Temp.Next == null)
                    Last = Node;
                else {
                    First.Next.Previous = Node;
                }
                First.Next = Node;
            }
            else if (Temp == Last) {
                Last.Next = Node;
                Last = Node;
            }
            else {
                Temp.Next.Previous = Node;
                Temp.Next = Node;
            }

            Size++;
        }

        /// <summary>
        /// Search for a specific value and return its encapsulating type(Node which stores this E-type value)
        /// </summary>
        /// <param name="find">The value to find</param>
        /// <returns>The Node that holds this value</returns>
        public Node<E> Search (E find) {
            Node<E> f = First;
            while (!f.Val.Equals(find)) {
                f = f.Next;
                if (f == null)
                    throw new ArgumentException("Value not found");
            }
            return f;
        }

        /// <summary>
        /// Remove a value from the List
        /// </summary>
        /// <param name="find">The value to remove</param>
        /// <returns>The removed value if found, or throws ArgumentException if not found</returns>
        public E Remove (E find) {
            if (!Contains(find))
                throw new ArgumentException("Value not found");
            Node<E> f = First;
            while (!f.Val.Equals(find)) {
                f = f.Next;
            }

            if (f == First) {
                if (f.Next == null) {
                    First = null;
                    Last = null;
                    return f.Val;
                }
                else {
                    First.Next.Previous = null;
                }
                First = First.Next;
            }
            else if (f == Last) {
                Last.Previous.Next = null;
                Last = Last.Previous;
            }
            else {
                f.Previous.Next = f.Next;
                f.Next.Previous = f.Previous;
            }

            Size--;
            return f.Val;
        }

        /// <summary>
        /// Remove the first value in the List
        /// </summary>
        /// <returns>The value at the front</returns>
        public E RemoveFirst () {
            return Remove(First.Val);
        }


        /// <summary>
        /// Remove the last value in the List
        /// </summary>
        /// <returns>The value at the end</returns>
        public E RemoveLast () {
            return Remove(Last.Val);
        }

        /// <summary>
        /// Determine if this value is present in the List
        /// </summary>
        /// <param name="find">The value to search for</param>
        /// <returns>true, if found</returns>
        public bool Contains (E find) {
            Node<E> f = First;
            while (!f.Val.Equals(find)) {
                f = f.Next;
                if (f == null)
                    return false;
            }
            return true;
        }
    }

    /// <summary>
    /// Encapsulating data-store class which holds the information about each entry in the Linked List
    /// </summary>
    /// <typeparam name="E">generic, type-safe</typeparam>
    public sealed class Node<E>
    {
        public E Val { get; set; }
        public Node<E> Next { get; set; }
        public Node<E> Previous { get; internal set; }

        public Node (Node<E> prev, E val, Node<E> next) {
            this.Val = val;
            this.Previous = prev;
            this.Next = next;
        }

        public override string ToString () {
            return String.Format("{0}", this.Val);
        }
    }

}
