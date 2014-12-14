// using System;
// using System.Linq;
using System;

namespace Collection
{
    /// <summary>
    /// Represents the individual entries that make up a Map, which contains a Key and Value
    /// </summary>
    public interface Entry<K, V>{
        /// <summary>
        /// The key for this Entry
        /// </summary>
        K Key();

        /// <summary>
        /// The value for this Entry
        /// </summary>
        V Value();

        /// <summary>
        /// Determines whether the specified Entry is equal to the current Entry
        /// </summary>
        /// <returns>true if the specified object is equal to the current object; otherwise, false.</returns>
        bool Equals(Object obj);

        /// <summary>
        /// Serves as a hash function for a particular type.
        /// </summary>
        /// <returns>A hash code for the current object.</returns>
        /// <filterpriority>2</filterpriority>
        int GetHashCode();
    }
}

