// using System;
// using System.Linq;
using System;

namespace Collection
{
    /// <summary>
    /// Queue is a data structure which is FIFO (First-In, First-Out) similar to a line. Items are inserted at the end and are removed from the front.
    /// </summary>
    public interface Queue<E> : Collection<E>
	{
        /// <summary>
        /// Shows, but doesn't remove, the next item to be removed
        /// </summary>
        E Peek();

        /// <summary>
        /// Removes the first item in the Queue
        /// </summary>
        E Dequeue();

        /// <summary>
        /// Inserts an item into the Queue
        /// </summary>
        /// <param name="item">Item. the item to insert</param>
        void Enqueue(E item);
	}
}

