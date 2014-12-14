// using System;
// using System.Linq;
using System;

namespace Collection
{
    public abstract class AbstractStack<E> : AbstractCollection<E>, Stack<E>
	{
        protected AbstractStack()
		{

		}

        public abstract void Push(E item);

        public virtual E Peek() {
            Iterator<E> it = this.Iterator();
            E peek = default(E);
            if (IsEmpty()){
                throw new StackEmptyException("Stack is empty");
            }

            while (it.HasNext()){
                peek = it.Next();
            }

            return peek;
        }

        public abstract E Pop();

        public override E Remove(E item)
        {
            throw new NotSupportedException("Stacks don't permit specific removal");
        }

        public class StackEmptyException : Exception{
            public StackEmptyException() {
            }

            public StackEmptyException(String msg)
                : base(msg){

            }
        }
    }

}

