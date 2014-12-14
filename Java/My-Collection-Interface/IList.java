/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ICollection;

import java.util.Objects;

/**
 * IList represents an interface which provides implementing classes methods for
 * searching, traversal, and random access (specific) deletion, and insertion.
 *
 * @date Jul 26, 2014
 * @author Chris Medina
 *
 * IList interface represents a type which allows for specific(index-based)
 * insertion, retreival, and removal
 */
public interface IList<E> extends ICollection<E> {

    /**
     * Retreive items within a certain index range of this IList
     * <p>
     * Note: Index to is exclusive, meaning up to, but NOT including 'to' index
     *
     * @param from starting index
     * @param to ending index (non-inclusive)
     * @return a sublist of items between the two indexes
     */
    IList<E> subList(int from, int to);

    /**
     * Add an item at a specific index within the IList
     *
     * @param index the index to insert the item at
     * @param item the item to insert
     */
    void addIndex(int index, E item);

    /**
     * Retreive the item at a certain index
     *
     * @param index the item to return an item for
     * @return the item at this index
     */
    E get(int index);

    /**
     * Set the value of of an item at this index
     *
     * @param index the index to replace the value of
     * @param item the item to replace it with
     */
    void set(int index, E item);

    /**
     * Remove an item at a specific index
     *
     * @param index the index of the item to remove
     * @return the removed item, if found
     */
    E removeIndex(int index);

    /**
     * The first index of the specified item
     *
     * @param item the item to search for
     * @return the first index where this item is located, if found
     */
    int indexOf(E item);

    /**
     * The last index of the specified item
     *
     * @param item the item to search for
     * @return the last index of the item, if found
     */
    int lastIndexOf(E item);

    @Override
    boolean equals(Object obj);

    static class Node<E> {

        E val;
        Node<E> next, previous;

        public Node(Node<E> prev, E val, Node<E> next) {
            this.previous = prev;
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 79 * hash + Objects.hashCode(this.val);
            hash = 79 * hash + Objects.hashCode(this.next);
            hash = 79 * hash + Objects.hashCode(this.previous);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            final Node<E> other = (Node<E>) obj;
            if (!Objects.equals(this.val, other.val))
                return false;
            if (!Objects.equals(this.next, other.next))
                return false;
            return Objects.equals(this.previous, other.previous);
        }

    }
}
