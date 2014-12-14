// using System;
// using System.Linq;
using System;

namespace Collection
{
    //Map is a type which associates values with keys (Key-Value pairs), similar in concept to a dictionary.
    public interface Map<K, V>
	{
        /// <summary>
        /// Determines if this Map contains this Key
        /// </summary>
        /// <returns><c>true</c>, if key is contained in this Map, <c>false</c> otherwise.</returns>
        bool ContainsKey(K key);

        /// <summary>
        /// Determines if this Map contains this value
        /// </summary>
        /// <returns><c>true</c>, if value is contained in this Map, <c>false</c> otherwise.</returns>
        bool ContainsValue(V value);

        /// <summary>
        /// Size of this Map
        /// </summary>
        int Size();

        /// <summary>
        /// Get the value associated with this Key
        /// </summary>
        /// <param name="key">Key. the key to search for</param>
        V Get(K key);

        /// <summary>
        /// Puts a new key-value pair into the Map. If the pair's key is already in the map, this returns the old value,
        /// else it simply inserts and returns the placed value
        /// </summary>
        /// <param name="key">Key.</param>
        /// <param name="value">Value.</param>
        V Put(K key, V value);

        /// <summary>
        /// Determine if this Map is empty
        /// </summary>
        /// <returns><c>true</c>, if empty, <c>false</c> otherwise.</returns>
        bool IsEmpty();

        /// <summary>
        /// Provides a List view of the Keys in this Map
        /// </summary>
        List<K> Keys();

        /// <summary>
        /// Provides a List view of the Values in this Map
        /// </summary>
        List<V> Values();

        /// <summary>
        /// Provides a List view of the Entries (key-value pair) in this Map
        /// </summary>
        List<Entry<K, V>> Entries();

        /// <summary>
        /// Clear the Map of all of its contents
        /// </summary>
        void Clear();

        /// <summary>
        /// Remove this key from the Map (Removes the key-value pairing which holds this key)
        /// </summary>
        /// <param name="key">the key to search for</param>
        /// <returns>The value associated with the removed key</returns>
        V Remove(K key);
    }

}

