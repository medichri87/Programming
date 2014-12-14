// using System;
// using System.Linq;
using System;

namespace Collection
{
    /// <summary>
    /// Stack is a LIFO (Last-In, Last-Out) special purpose data structure where items are put 
    /// onto a Stack and the item most recently added is removed first
    /// </summary>
    public interface Stack<E> : Collection<E>
	{
        /// <summary>
        /// Push the specified item onto the Stack
        /// </summary>
        /// <param name="item">Item. the item to put onto the Stack</param>
        void Push(E item);

        /// <summary>
        /// Shows, but doesn't remove, the item that will be removed next upon calling pop()
        /// </summary>
        E Peek();

        /// <summary>
        /// Remove the item from the top of the Stack (last inserted)
        /// </summary>
        E Pop();
	}
}

