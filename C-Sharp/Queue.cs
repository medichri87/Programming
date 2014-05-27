using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OOP
{
    /// <summary>
    /// Date: 5-25-2014
    /// Represents a generic Queue data structure. Items are added to the end and removed from the front
    /// </summary>
    /// <typeparam name="E">Generic, type-safe implementation</typeparam>
    public class Queue<E>
    {
        private E[] array;
        private int max_size;
        
        public int size { get; private set; }

        public Queue (int max) {
            this.max_size = max;
            this.array = new E[max];
            this.size = 0;
        }

        public Queue ()
            : this(10) {

        }

        /// <summary>
        /// Determine if this Queue is empty
        /// </summary>
        /// <returns>true, if it has no items</returns>
        public bool isEmpty () {
            return (size == 0);
        }

        /// <summary>
        /// Determine if this Queue is full
        /// </summary>
        /// <returns>true, if is full. Once reached, the array dynamically grows</returns>
        public bool isFull () {
            return (size == max_size);
        }

        private void ensureCapacity () {
            if (isFull()) {
                max_size *= 3;
                Array.Resize(ref array, max_size);
            }
        }

        /// <summary>
        /// Append this item to the end of the queue
        /// </summary>
        /// <param name="item">the item to append</param>
        public void enqueue (E item) {
            ensureCapacity();
            array[size++] = item;
        }

        /// <summary>
        /// Remove the item at the front of the queue, or throw exception if empty
        /// </summary>
        /// <returns>the item removed from the front of queue</returns>
        public E dequeue () {
            if (isEmpty())
                throw new InvalidOperationException("Queue is empty");
            E temp = array[0];

            for (int i = 0; i < size - 1; i++) {
                array[i] = array[i + 1];
            }

            Array.Resize(ref array, array.Length - 1);

            size--;
            return temp;
        }

        /// <summary>
        /// Show the item that will be deleted next 
        /// </summary>
        /// <returns>the item to be removed next upon calling dequeue</returns>
        public E peek () {
            if (isEmpty())
                throw new InvalidOperationException("Queue is empty");
            return array[0];
        }

        public override string ToString () {
            StringBuilder sb = new StringBuilder("[");

            for (int i = 0; i < size; i++) {
                if (i < size - 1) {
                    sb.Append(array[i] + ", ");
                }
                else {
                    sb.Append(array[i]);
                }
            }

            sb.Append("]");
            return sb.ToString();
        }

    }

}
