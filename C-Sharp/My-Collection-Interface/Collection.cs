// using System;
// using System.Linq;
using System;

namespace Collection
{
    /// <summary>
    /// Collection is the base interface for all operations allowable by implementing classes. 
    /// All Collection-implementing classes have the ability to insert, remove, and search for items.
    /// </summary>
    public interface Collection<E>
	{
        /// <summary>
        /// Determines if Collection contains the given item
        /// </summary>
        /// <param name="item">The item to search for</param>
        bool Contains(E item);

        /// <summary>
        /// Determine if the current Collection contains all the items in the given Collection
        /// </summary>
        /// <returns><c>true</c>, if all items in the given Collection are found in the current Collection, <c>false</c> otherwise.</returns>
        /// <param name="collection">Collection.</param>
        bool ContainsAll(Collection<E> collection);

        /// <summary>
        /// Adds all items from the given Collection into the current Collection
        /// </summary>
        /// <param name="collection">The Collection of items to insert into current</param>
        void AddAll(Collection<E> collection);

        bool Equals(Object obj);

        int GetHashCode();

        /// <summary>
        /// Add the specified item into the Collection
        /// </summary>
        /// <param name="item">The item to insert</param>
        void Add(E item);

        /// <summary>
        /// Number of items in the current Collection
        /// </summary>
        int Size();

        /// <summary>
        /// Determines if the Collection is empty
        /// </summary>
        /// <returns><c>true</c>, if empty<c>false</c> otherwise.</returns>
        bool IsEmpty();

        /// <summary>
        /// Remove the specified item
        /// </summary>
        /// <param name="item">The item to remove</param>
        E Remove(E item);

        /// <summary>
        /// Provides an iterator to traverse the items in current Collection in a linear fashion
        /// </summary>
        Iterator<E> Iterator();

        /// <summary>
        /// Pass the contents of the current Collection into an array of the same type
        /// </summary>
        /// <returns>The array containing all the items of the Collection</returns>
        E[] ToArray();

        /// <summary>
        /// Removes all items from the current Collection
        /// </summary>
        void Clear();
	}
}

