using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Stack
{
    /// <summary>
    /// Name: Chris Medina
    /// Date: 5-24-2014
    /// Purpose: Class to represent a Stack data structure
    /// </summary>
    /// <typeparam name="E">generic, type-safe</typeparam>
    class Stack<E>
    {
        private E[] array;
        private int max_size;
        private int size;

        /// <summary>
        /// Create a default-size Stack 
        /// </summary>
        public Stack() : this(10) {
            
        }

        /// <summary>
        /// Create a Stack with a user-defined max size. Once max size is reached, the array will expand dynamically.
        /// </summary>
        /// <param name="user_max"></param>
        public Stack(int user_max) {
            this.max_size = user_max;
            this.array = new E[max_size];
            this.size = 0;
        }

        /// <summary>
        /// Determine if this Stack is empty
        /// </summary>
        /// <returns>true, if Stack has no items in it</returns>
        public bool isEmpty() {
            return (size == 0);
        }


        /// <summary>
        /// Determine if this Stack is full
        /// </summary>
        /// <returns>true, if Stack is full. If full, the array will expand dynamically.</returns>
        public bool isFull() {
            return (size == max_size);
        }

        /// <summary>
        /// Get the size of the current Stack
        /// </summary>
        /// <returns>Size of current stack</returns>
        public int getSize() {
            return this.size;
        }

        /// <summary>
        /// Grow if current max size has been reached
        /// </summary>
        private void ensureCapacity() {
            if (isFull()) {
                max_size *= 3;
                Array.Resize(ref array, max_size);
            }
        }

        /// <summary>
        /// Push an item onto the Stack
        /// </summary>
        /// <param name="item">The item to push on the Stack</param>
        public void push(E item) {
            ensureCapacity();
            if (isFull())
                throw new StackOverflowException("Stack is full");
            else {
                array[size++] = item;
            }
        }

        /// <summary>
        /// Remove the most recently pushed item from the Stack.
        /// </summary>
        /// <returns>The last item in the array, or throws exception if empty</returns>
        public E pop() {
            if (isEmpty()) {
                throw new InvalidOperationException("Stack is empty");
            }
            else {
                array[--size] = default(E);
                return array[size];
            }
        }

        /// <summary>
        /// Empty the current Stack
        /// </summary>
        public void clear() {
            max_size = 10;
            size = 0;
            Array.Resize(ref array, max_size);
        }

        public override string ToString() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < getSize(); i++) {
                if (i < getSize() - 1) {
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

    class RunStack
    {
        public static void Main(String[] args) {

            Stack<Int32> s = new Stack<Int32>(2);

            s.push(25);
            s.push(45);
            s.push(55);

            s.pop();

            Console.WriteLine(s);

            //hold output until key press
            Console.ReadKey();
        }
    }
}
