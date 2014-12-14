/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ICollection;

import java.util.List;

/**
 * @param <K> Generic type for KEY
 * @param <V> Generic type for VALUE
 * @date Jul 25, 2014
 * @author Chris Medina
 *
 * IDictionary is an interface which provides the base contract from which
 * classes provide implementations of searching, deletion, and insertion of
 * Key-Value pairs (An Object which contains a key and value as 1 unit) into a
 * Dictionary-like structure
 */
public interface IDictionary<K, V> {

    /**
     * Size of the Dictionary
     *
     * @return the size of the Dictionary
     */
    int size();

    /**
     * Determine if the Dictionary is empty(Dictionary has no items in it)
     *
     * @return true, if Dictionary is empty
     */
    boolean isEmpty();

    /**
     * Determine if this key is in the Dictionary
     *
     * @param key the key to search for
     * @return true, if this key is found
     */
    boolean containsKey(K key);

    /**
     * Determine if this value is in the Dictionary
     *
     * @param value the value to search for
     * @return true, if this value is found
     */
    boolean containsValue(V value);

    /**
     * Retrieves the value associated with this key, assuming it is found
     *
     * @param key the key to find a value for
     * @return the value associated with this key if found
     */
    V get(K key);

    /**
     * Put an item into the Dictionary
     *
     * @param key the key of the item
     * @param value the value of the item
     * @return true, if key was not already found in Dictionary and KeyValuePair
     * was correctly placed into Dictionary
     */
    boolean put(K key, V value);

    /**
     * Remove a specific key from the Dictionary (Removes KeyValuePair)
     *
     * @param key the key to delete
     * @return the value associated with the deleted key if found, else null
     */
    V remove(K key);

    /**
     * Empty the Dictionary of all of its items (size becomes 0)
     */
    void clear();

    /**
     * Provides a List-view of the keys in the Dictionary
     *
     * @return a List of all of the keys in the current Dictionary
     */
    List<K> keys();

    /**
     * Provides a List-view of the values in the Dictionary
     *
     * @return a List of all of the values in the current Dictionary
     */
    List<V> values();

    /**
     * Provides a List-view of all of the KeyValuePairs(each entry) in this
     * Dictionary
     *
     * @return a List of all KeyValuePairs in Dictionary
     */
    List<KeyValuePair<K, V>> entries();

    /**
     * Pass the contents of the current Dictionary into an array
     *
     * @return array of all KeyValuePairs in Dictionary
     */
    Object[] toArray();

    boolean equals(Object obj);

    /**
     * Represents the individual entries in a Dictionary structure where each
     * item contains a Key and a Value, defined as a "KeyValuePair"
     *
     * @param <K> Generic type for KEY
     * @param <V> Generic type for VALUE
     */
    interface KeyValuePair<K, V> {

        /**
         * Get the key from this KeyValuePair
         *
         * @return the key
         */
        K getKey();

        /**
         * Get the value from this KeyValuePair
         *
         * @return the value
         */
        V getValue();

        /**
         * Set the current value to a new value in a KeyValuePair
         * <p>
         * Note: Only useful when putting a KeyValuePair which contains a key
         * <i>already found in the Dictionary</i>, in which case this is
         * necessary to overwrite the old value and replace it with the new
         * one</p>
         *
         * @param newVal the value to replace the old one with
         * @return the old, replaced value
         */
        V setValue(V newVal);

        @Override
        boolean equals(Object obj);

        @Override
        int hashCode();
    }
}
