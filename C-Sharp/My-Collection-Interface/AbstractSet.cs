// using System;
// using System.Linq;
using System;

namespace Collection
{
    public abstract class AbstractSet<E> : AbstractCollection<E>, Set<E>
	{
        protected AbstractSet(){
        }

        public override abstract void Add(E item);

        public override abstract int Size();

        public override abstract E Remove(E item);

        public override abstract Iterator<E> Iterator();

        public override abstract void Clear();

	}
}

