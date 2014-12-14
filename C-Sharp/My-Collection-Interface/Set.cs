// using System;
// using System.Linq;
using System;

namespace Collection
{
    /// <summary>
    /// Set implementations represent a data structure which doesn't allow duplicate values
    /// </summary>
    public interface Set<E> : Collection<E>
	{
        /// <summary>
        /// Determines if Set contains the given item
        /// </summary>
        /// <param name="item">The item to search for</param>
        bool Contains(E item);

        /// <summary>
        /// Determine if the current Set contains all the items in the given Set
        /// </summary>
        /// <returns><c>true</c>, if all items in the given Set are found in the current Set, <c>false</c> otherwise.</returns>
        /// <param name="collection">Collection.</param>
        bool ContainsAll(Collection<E> collection);

        /// <summary>
        /// Adds all items from the given Set into the current Set
        /// </summary>
        /// <param name="collection">The Set of items to insert into current</param>
        void AddAll(Collection<E> collection);

        bool Equals(Object obj);

        int GetHashCode();

        /// <summary>
        /// Add the specified item into the Set
        /// </summary>
        /// <param name="item">The item to insert</param>
        void Add(E item);

        /// <summary>
        /// Number of items in the current Set
        /// </summary>
        int Size();

        /// <summary>
        /// Determines if the Set is empty
        /// </summary>
        /// <returns><c>true</c>, if empty<c>false</c> otherwise.</returns>
        bool IsEmpty();

        /// <summary>
        /// Remove the specified item
        /// </summary>
        /// <param name="item">The item to remove</param>
        E Remove(E item);

        /// <summary>
        /// Provides an iterator to traverse the items in current Set in a linear fashion
        /// </summary>
        Iterator<E> Iterator();

        /// <summary>
        /// Pass the contents of the current Set into an array of the same type
        /// </summary>
        /// <returns>The array containing all the items of the Set</returns>
        E[] ToArray();

        /// <summary>
        /// Removes all items from the current Set
        /// </summary>
        void Clear();
	}
}

