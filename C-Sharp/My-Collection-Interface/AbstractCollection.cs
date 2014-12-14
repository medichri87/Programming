// using System;
// using System.Linq;
using System;
using System.Text;
using System.Security.AccessControl;

namespace Collection
{
    /// <summary>
    /// Convenience class that partially implements the Collection interface
    /// </summary>
    public abstract class AbstractCollection<E> : Collection<E>
	{
        protected AbstractCollection()
		{

		}

        public virtual bool Contains(E item) {
            Iterator<E> it = this.Iterator();
            while (it.HasNext()){
                E temp = it.Next();
                if (temp.Equals(item))
                    return true;
            }
            return false;     
        }

        public bool ContainsAll(Collection<E> collection) {
            if (this.Size() > 0 && collection.IsEmpty())
                return false;
            Iterator<E> it = collection.Iterator();
            while (it.HasNext()){
                E temp = it.Next();
                if (!Contains(temp))
                    return false;
            }
            return true;
        }

        public void AddAll(Collection<E> collection) {
            Iterator<E> it = collection.Iterator();
            while (it.HasNext())
                Add(it.Next());
        }

        public abstract void Add(E item);

        public abstract int Size();

        public virtual bool IsEmpty() {
            return (Size() == 0);
        }

        public abstract E Remove(E item);

        public abstract Iterator<E> Iterator();

        public override string ToString()
        {
            StringBuilder sb = new StringBuilder("[");
            Iterator<E> it = Iterator();
            while (it.HasNext()){
                E temp = it.Next();
                if (it.HasNext())
                    sb.Append(temp).Append(", ");
                else
                    sb.Append(temp);
            }
            sb.Append("]");
            return sb.ToString();
        }

        public override bool Equals(object obj)
        {
            if (this == obj)
                return true;
            if (GetType() != obj.GetType() || ReferenceEquals(null, obj))
                return false;
            Collection<E> temp = (obj as Collection<E>);
            if (temp.Size() != this.Size())
                return false;
            Iterator<E> one = temp.Iterator();
            Iterator<E> two = this.Iterator();

            while (one.HasNext()){
                E o1 = one.Next();
                E o2 = two.Next();
                if (!o1.Equals(o2)){
                    return false;
                }
            }

            return true;
        }

        public override int GetHashCode() {
            return base.GetHashCode();
        }

        public virtual E[] ToArray()
        {
            E[] temp = new E[Size()];
            Iterator<E> it = Iterator();
            int index = 0;

            while (it.HasNext()){
                temp[index++] = it.Next();
            }

            return temp;
        }

        public abstract void Clear();
                    
	}
}

