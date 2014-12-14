// using System;
// using System.Linq;
using System;

namespace Collection
{
    public abstract class AbstractList<E> : AbstractCollection<E>, Collection<E>, List<E>
	{
        protected AbstractList()
		{

		}

        public abstract List<E> SubList(int from, int to);

        public E Get(int index) {
            if (IsEmpty())
                throw new InvalidOperationException("List is empty");
            else if (index < 0 || index >= Size())
                throw new IndexOutOfRangeException("Index is out of range");

            Iterator<E> it = this.Iterator();
            int idx = 0;
            E temp = default(E);

            while (true){
                temp = it.Next();
                if (idx == index)
                    return temp;
                idx++;
            }

        }

        public abstract E RemoveIndex(int index);

        public int IndexOf(E item) {
            if (IsEmpty())
                throw new InvalidOperationException("List is empty");
            Iterator<E> it = this.Iterator();
            E temp = default(E);
            int index = 0;

            while (it.HasNext()){
                temp = it.Next();
                if (temp.Equals(item))
                    return index;
                index++;
            }

            return -1;
        }

        public int LastIndexOf(E item) {
            if (IsEmpty())
                throw new InvalidOperationException("List is empty");
            Iterator<E> it = this.Iterator();
            E temp = default(E);
            int index = 0;
            int idx = -1;

            while (it.HasNext()){
                temp = it.Next();
                if (temp.Equals(item))
                    idx = index;
                index++;
            }

            return idx;        
        }

	}
}

