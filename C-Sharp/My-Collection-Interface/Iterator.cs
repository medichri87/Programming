// using System;
// using System.Linq;
using System;

namespace Collection
{
    public interface Iterator<E>
	{
        /// <summary>
        /// Determines whether this iterator has a next value (can move forward by one)
        /// </summary>
        /// <returns><c>true</c> if this iterator has next; otherwise, <c>false</c>.</returns>
        bool HasNext();

        /// <summary>
        /// Moves the iterator up one item and returns the prior-occupied item
        /// </summary>
        E Next();

        /// <summary>
        /// Reset this iterator to the beginning
        /// </summary>
        void Reset();
	}
}

