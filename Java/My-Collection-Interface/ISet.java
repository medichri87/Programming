/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ICollection;

/**
 * @param <E>
 * @date Jul 25, 2014
 * @author Chris Medina
 * <p>
 * The ISet interface is part of the ICollection interface, which provides
 * methods for searching, deletion, and insertng items. The unique aspect of
 * ISet interface is that implementing classes do not permit duplicate entries.
 * No duplicates will be found within an ISet implemntation of any kind.</p>
 */
public interface ISet<E> extends ICollection<E> {

    int size();

    void add(E item);

    E remove(E item);

    IEnumerator<E> iterator();

    boolean equals(Object obj);
}
