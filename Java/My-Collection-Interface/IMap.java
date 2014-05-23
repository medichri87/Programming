package ADT;

/**
 * Created by Chris Medina on 5/22/2014.
 * <p>Purpose: IMap is a map-based interface, whereby implementing classes will allow for entry key-value pairs,
 * similar to a dictionary.</p>
 */
public interface IMap<K, V> {

    /**
     * Return the size of the current ADT.IMap
     *
     * @return the size of the ADT.IMap
     */
    int size ();

    /**
     * Determine if the current Map is empty
     *
     * @return true, if there are no elements in the ADT.IMap
     */
    boolean isEmpty ();

    /**
     * Determine if the ADT.IMap contains a key
     *
     * @param key the key to search for
     * @return true, if the key is found
     */
    boolean containsKey (K key);

    /**
     * Determine if the ADT.IMap contains a value
     *
     * @param value the value to search for
     * @return true, if the value is found
     */
    boolean containsValue (V value);

    /**
     * Retrieve a value from a specific key
     *
     * @param key the key to search for
     * @return the value associated with this key
     */
    V get (K key);

    /**
     * Add this key-value pair to the ADT.IMap
     *
     * @param key   the key to insert
     * @param value the value to insert
     */
    void put (K key, V value);

    /**
     * Remove a key-value pair from the ADT.IMap
     *
     * @param key the key to remove
     * @return the value associated with this key
     */
    V remove (K key);

    /**
     * Reset, empty the ADT.IMap
     */
    void clear ();

    /**
     * Provides an EList view of the entries of the current ADT.IMap
     *
     * @return EList view of the entries of the current ADT.IMap
     */
    EList<Entry<K, V>> entrySet ();

    /**
     * Provides an EList view of the values of the current ADT.IMap
     *
     * @return EList view of the values of the current ADT.IMap
     */
    EList<V> values ();

    /**
     * Provides an EList view of the keys of the current ADT.IMap
     *
     * @return EList view of the keys of the current ADT.IMap
     */
    EList<K> keySet ();


    /**
     * Pass the current mappings off to an array
     *
     * @return a new array based on the current key-value mappings
     */
    Object[] toArray ();

    /**
     * Interface Entry represents the individual mappings(key-value pairs) in the ADT.IMap
     *
     * @param <K>
     * @param <V>
     */
    interface Entry<K, V> {
        /**
         * Get the key for the current ADT.IMap Entry
         *
         * @return the key for the current ADT.IMap Entry
         */
        K getKey ();

        /**
         * Get the value for the current ADT.IMap Entry
         *
         * @return the value for the current ADT.IMap Entry
         */
        V getValue ();

        void setValue (V value);

        boolean equals (Object o);

        int hashCode ();
    }

}
