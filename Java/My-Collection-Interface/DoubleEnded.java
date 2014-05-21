package ADT;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/25/2014 <p>
 * DoubleEnded interface allows implentating classes to insert and remove elements in the list at both ends.
 */
public interface DoubleEnded<E> extends ECollection<E> {
    /**
     * Inserts this value to the beginning of the List
     *
     * @param val the value to insert
     */
    void insertFirst (E val);

    /**
     * Insert this value to the end of the List
     *
     * @param val the value to insert
     */
    void insertLast (E val);

    /**
     * Insert this value before a certain value, if that value is found
     *
     * @param find the value to insert before
     * @param val  the value to insert
     */
    void insertBefore (E find, E val);

    /**
     * Insert this value after a certain value, if that value is found
     *
     * @param find the value to insert after
     * @param val  the value to insert
     */
    void insertAfter (E find, E val);

    /**
     * Remove the front/first value from the List
     *
     * @return the head or front value from the List
     */
    E removeFirst ();

    /**
     * Remove the last/end value from the List
     *
     * @return the last value from the List
     */
    E removeLast ();

    /**
     * Retrieve the last value in the List
     *
     * @return the last value in the list
     */
    E getLast ();

    /**
     * Retrive the first value in the List
     *
     * @return the first value in the list
     */
    E getFirst ();
}
