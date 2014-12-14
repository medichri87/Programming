/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ICollection;

/**
 * @date Jul 26, 2014
 * @author Chris Medina
 *
 * IEnumerable represents a data structure which is "enumerable", where it can be traversed in a linear fashion.
 */
public interface IEnumerable<E> {

    IEnumerator<E> iterator();
}
