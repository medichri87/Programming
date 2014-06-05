using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OOP {
    /// <summary>
    /// SLinkedList represents a Singly-linked, double-ended linked list
    /// Date: 6-4-2013
    /// </summary>
    /// <typeparam name="E">Generic, type-safe implementation</typeparam>
    public class SLinkedList<E> {
        private Node<E> First;
        private Node<E> Last;

        public int Size { get; private set; }

        /// <summary>
        /// Create an empty Linked List
        /// </summary>
        public SLinkedList() {
            this.Size = 0;
            this.First = null;
            this.Last = null;
        }
        /// <summary>
        /// Determine if current List is empty
        /// </summary>
        /// <returns>true, if list is empty</returns>
        public bool IsEmpty() {
            return (First == null);
        }

        /// <summary>
        /// Insert a value to the front of the list
        /// </summary>
        /// <param name="item">the value to insert</param>
        public void InsertFirst(E item) {
            Size++;
            Node<E> node = new Node<E>(item, First);
            if (IsEmpty()) {
                Last = node;
            }
            First = node;
        }

        /// <summary>
        /// Insert a value to the end of the list
        /// </summary>
        /// <param name="item">the value to insert</param>
        public void InsertLast(E item) {
            Size++;
            Node<E> node = new Node<E>(item, null);
            if (IsEmpty())
                First = node;
            else
                Last.Next = node;
            Last = node;
        }

        /// <summary>
        /// Insert a value immediately before another specified value
        /// </summary>
        /// <param name="find">the value to find, which we are inserting in front of</param>
        /// <param name="item">the value to insert</param>
        public void InsertBefore(E find, E item) {
            Node<E> curr = First;
            Node<E> previous = First;

            while (!Equals(curr.Val, find)) {
                previous = curr;
                curr = curr.Next;
                if (curr == null)
                    throw new ArgumentException("Value not found");
            }

            Node<E> node = new Node<E>(item, curr);
            if (curr == First) {
                InsertFirst(item);
                return;
            } else {
                InsertAfter(previous.Val, item);
                return;
            }
        }


        /// <summary>
        /// Insert a value immediately after another specified value
        /// </summary>
        /// <param name="find">the value to find, which we are inserting after</param>
        /// <param name="val">the value to insert</param>
        public void InsertAfter(E find, E val) {
            Node<E> curr = First;
            while (!curr.Val.Equals(find)) {
                curr = curr.Next;
                if (curr == null)
                    throw new ArgumentException("Value not found");
            }

            Node<E> node = new Node<E>(val, curr.Next);
            if (curr == First) {
                if (curr.Next == null) {
                    Last = node;
                }
                First.Next = node;
            } else if (curr == Last) {
                Last.Next = node;
                Last = node;
            } else {
                curr.Next = node;
            }
            Size++;
        }

        /// <summary>
        /// Remove a specific value from the list
        /// </summary>
        /// <param name="find"></param>
        /// <returns>The removed value, if found</returns>
        public E Remove(E find) {
            Node<E> curr = First, previous = First;

            while (!curr.Val.Equals(find)) {
                previous = curr;
                curr = curr.Next;
                if (curr == null)
                    throw new ArgumentException("Value not found");
            }

            if (curr == First) {
                if (curr.Next == null) {
                    First = null;
                    Last = null;
                    return curr.Val;
                }
                First = First.Next;
            } else if (curr == Last) {
                previous.Next = null;
                Last = previous;
            } else {
                previous.Next = curr.Next;
            }

            return curr.Val;
        }

        /// <summary>
        /// Remove the first value in the list
        /// </summary>
        /// <returns>the first value</returns>
        public E RemoveFirst() {
            return Remove(First.Val);
        }

        /// <summary>
        /// Remove the last value in the list
        /// </summary>
        /// <returns>the last value in the list</returns>
        public E RemoveLast() {
            return Remove(First.Val);
        }

        /// <summary>
        /// Get the first item in the current list
        /// </summary>
        /// <returns></returns>
        public object GetFirst() {
            if (IsEmpty())
                return null;
            return First.Val;
        }

        /// <summary>
        /// Get the last element in the list
        /// </summary>
        /// <returns></returns>
        public object GetLast() {
            if (IsEmpty())
                return null;
            return Last.Val;
        }

        public override string ToString() {
            StringBuilder sb = new StringBuilder("[");
            Node<E> f = First;

            while (f != null) {
                if (f.Next == null)
                    sb.Append(f);
                else
                    sb.Append(f + ", ");
                f = f.Next;
            }

            sb.Append("]");
            return sb.ToString();
        }

        /// <summary>
        /// Represent the individual Nodes which hold the generic values within the list.
        /// (Node is an encapsulating class of generic-type values)
        /// </summary>
        /// <typeparam name="E"></typeparam>
        private sealed class Node<E> {
            public E Val { get; set; }
            public Node<E> Next { get; set; }

            public Node(E val, Node<E> next) {
                this.Val = val;
                this.Next = next;
            }

            public override string ToString() {
                return String.Format("{0}", this.Val);
            }

            private bool Equals(Node<E> other) {
                return EqualityComparer<E>.Default.Equals(this.Val, other.Val);
            }

            public override bool Equals(object obj) {
                if (ReferenceEquals(null, obj)) return false;
                if (ReferenceEquals(this, obj)) return true;
                return obj is Node<E> && Equals((Node<E>)obj);
            }

            public override int GetHashCode() {
                return EqualityComparer<E>.Default.GetHashCode(Val);
            }
        }

    }

}
