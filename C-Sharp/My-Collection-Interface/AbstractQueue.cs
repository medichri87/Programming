// using System;
// using System.Linq;
using System;

namespace Collection
{
    public abstract class AbstractQueue<E> : AbstractCollection<E>, Queue<E>
	{

        protected AbstractQueue(){

        }

        public override E Remove(E item){
            throw new NotSupportedException("Queue doesn't permit specific removal");
        }

        public virtual E Peek() {
            if (IsEmpty())
                throw new QueueEmptyException("Queue is empty");
            return Iterator().Next();
        }

        public abstract E Dequeue();

        public abstract void Enqueue(E item);

        public class QueueEmptyException : Exception{
            public QueueEmptyException()
                : base(){

            }

            public QueueEmptyException(String msg)
                : base(msg){

            }
        }
    }


}

