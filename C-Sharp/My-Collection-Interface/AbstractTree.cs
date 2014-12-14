// using System;
// using System.Linq;
using System;
using System.Text;

namespace Collection
{
    public abstract class AbstractTree<E> : Tree<E>
	{
        protected AbstractTree()
		{
		}

        #region Tree implementation

        public virtual bool Contains(E item){
            E[] arr = ToArray();
            for (int i = 0; i < arr.Length; i++){
                if (arr[i].Equals(item))
                    return true;
            }
            return false;
        }

        public abstract void Clear();

        public abstract void Add(E item);

        public virtual E Remove(E item){
            throw new NotSupportedException("Not a supported operation");
        }

        public abstract int Size();

        public abstract E Remove();

        public bool IsEmpty(){
            return (Size() == 0);
        }

        public override string ToString()
        {
            StringBuilder sb = new StringBuilder("[");
            E[] arr = ToArray();

            for (int i = 0; i < arr.Length; i++){
                if (i < arr.Length - 1)
                    sb.Append(arr[i] + ", ");
                else
                    sb.Append(arr[i]);
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
            Tree<E> temp = (obj as Tree<E>);
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

        public abstract E[] ToArray();

        public abstract Iterator<E> Iterator();

        #endregion

        public class TreeEmptyException : Exception{
            public TreeEmptyException()
                : base(){

            }

            public TreeEmptyException(String msg)
                : base(msg){

            }
        }
	}
}

