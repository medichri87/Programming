// using System;
// using System.Linq;
using System;

namespace Collection
{
    /// <summary>
    /// The List interface is the base interface for all classes which permit random access, insertion and deletion. 
    /// See: ArrayList and LinkedList 
    /// </summary>
    public interface List<E> : Collection<E>
	{
        /// <summary>
        /// Produce a sublist of the current List based on the items between two indexes, exclusive (up to, but not including 'to' index). 
        /// </summary>
        /// <returns>A sublist of items</returns>
        /// <param name="from">From. The starting index</param>
        /// <param name="to">To. The ending index, not included</param>
        List<E> SubList(int from, int to);

        /// <summary>
        /// Get the item at a specified index
        /// </summary>
        /// <param name="index">Index. The index to return the value for</param>
        E Get(int index);

        /// <summary>
        /// Removes the item at a specific index.
        /// </summary>
        /// <returns>The item at the specific index</returns>
        /// <param name="index">Index. The index of the item to remove</param>
        E RemoveIndex(int index);

        /// <summary>
        /// Finds the first index of a specific item
        /// </summary>
        /// <returns>The first index this item appears at, or -1 if not found</returns>
        /// <param name="item">Item. the item to search for</param>
        int IndexOf(E item);

        /// <summary>
        /// Finds the last index of a specific item
        /// </summary>
        /// <returns>The last index this item appears at, or -1 if not found</returns>
        /// <param name="item">Item. the item to search for</param>
        int LastIndexOf(E item);
	}
}

