// using System;
// using System.Linq;
using System;
using System.IO;

namespace Collection
{
    //A Stack which allows for O(1) Max and Min retreival
    public class MaxMinStack<E> : LinkedStack<E> where E: IComparable
	{
        private Stack<E> minStack, maxStack;

        public MaxMinStack()
            : base()
		{
            minStack = new LinkedStack<E>();
            maxStack = new LinkedStack<E>();
		}

        public MaxMinStack(E[] arr)
        {
            minStack = new LinkedStack<E>();
            maxStack = new LinkedStack<E>();
            foreach (E item in arr)
                this.Push(item);
        }

        public MaxMinStack(Collection<E> collection)
            : this(collection.ToArray()) 
        {

        }

        public E Max(){
            if (maxStack.IsEmpty()){
                throw new StackEmptyException("Stack is empty");
            }
            return maxStack.Peek();
        }

        public E Min(){
            if (minStack.IsEmpty()){
                throw new StackEmptyException("Stack is empty");
            }
            return minStack.Peek();
        }

        public sealed override void Push(E item)
        {
            base.Push(item);

            if (minStack.IsEmpty())
                minStack.Push(item);
            else if (item.CompareTo(Min()) < 0)
                minStack.Push(item);

            if (maxStack.IsEmpty())
                maxStack.Push(item);
            else if (item.CompareTo(Max()) > 0)
                maxStack.Push(item);


        }

        public override E Pop()
        {

            if (IsEmpty())
                throw new StackEmptyException("Stack is empty");
            E pop = base.Pop();

            if (pop.Equals(Min())){
                minStack.Pop();
                Console.WriteLine("Removed: " + pop + " from minStack");
            }

            if (pop.Equals(Max())){
                maxStack.Pop();
                Console.WriteLine("Removed: " + pop + " from maxStack");
            }

            return pop;
        }

	}
}

