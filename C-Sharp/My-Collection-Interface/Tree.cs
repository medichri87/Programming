// using System;
// using System.Linq;
using System;

namespace Collection
{
    public interface Tree<E> 
	{
        /// <summary>
        /// Determine if the Tree contains a a specific item
        /// </summary>
        /// <param name="item">the item to insert</param>
        bool Contains(E item);

        /// <summary>
        /// Clear this tree of all its items
        /// </summary>
        void Clear();

        /// <summary>
        /// Add the specified item.
        /// </summary>
        /// <param name="item">the item to insert</param>
        void Add(E item);

        /// <summary>
        /// Remove the specified item from the Tree
        /// </summary>
        /// <param name="item">the removed item</param>
        E Remove(E item);

        /// <summary>
        /// Size of the current Tree
        /// </summary>
        int Size();

        /// <summary>
        /// Remove the root value from Tree
        /// </summary>
        E Remove();

        /// <summary>
        /// Determines whether this Tree is empty.
        /// </summary>
        /// <returns><c>true</c> if this instance is empty; otherwise, <c>false</c>.</returns>
        bool IsEmpty();

        /// <summary>
        /// Pass the contents of the current Tree into an array of the declared type
        /// </summary>
        /// <returns>An array of the contents in the Tree</returns>
        E[] ToArray();

        /// <summary>
        /// Produces an iterator to traverse the elements of the Tree
        /// </summary>
        Iterator<E> Iterator();
	}

}

