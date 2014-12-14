// using System;
// using System.Linq;
using System;
using System.Text;

namespace Collection
{
    public abstract class AbstractMap<K, V> : Map<K, V>
	{
        protected AbstractMap()
		{

		}

        #region Map implementation

        public abstract bool ContainsKey(K key);

        public virtual bool ContainsValue(V value) {
            List<Entry<K,V>> mappings = this.Entries();
            for (int i = 0; i < mappings.Size(); i++){
                V tempVal = mappings.Get(i).Value();
                if (tempVal.Equals(value))
                    return true;
            }
            return false;
        }

        public abstract int Size();

        public abstract V Get(K key);

        public abstract V Put(K key, V value);

        public bool IsEmpty() {
            return (Size() == 0);
        }

        public abstract V Remove(K key);

        public List<K> Keys() {
            List<K> keyList = new ArrayList<K>();
            for (int i = 0; i < Entries().Size(); i++){
                keyList.Add(Entries().Get(i).Key());
            }
            return keyList;
        }

        public List<V> Values() {
            List<V> valueList = new ArrayList<V>();
            for (int i = 0; i < Entries().Size(); i++){
                valueList.Add(Entries().Get(i).Value());
            }
            return valueList;
        }

        public abstract List<Entry<K, V>> Entries();

        public override bool Equals(object obj)
        {
            if (this == obj)
                return true;
            if (ReferenceEquals(null, obj) || this.GetType() != obj.GetType())
                return false;
            Map<K, V> temp = (Map<K, V>)obj;
            if (temp.Size() != this.Size())
                return false;

            List<Entry<K, V>> one = this.Entries();
            List<Entry<K, V>> two = this.Entries();

            for (int i = 0; i < one.Size(); i++){
                if (!(one.Get(i).Equals(two.Get(i))))
                    return false;
            }
            return true;
        }

        public abstract void Clear();

        public override string ToString()
        {
            List<Entry<K, V>> entries = Entries();
            StringBuilder sb = new StringBuilder("{");
            for (int i = 0; i < entries.Size(); i++){
                Entry<K, V> entry = entries.Get(i);
                if (i < entries.Size() - 1){
                    sb.Append(entry + ", ");
                }
                else
                    sb.Append(entry);
            }
            sb.Append("}");
            return sb.ToString();
        }

        #endregion

        public class MapEmptyException : Exception{
            public MapEmptyException()
                : base(){

            }

            public MapEmptyException(String msg)
                : base(msg){

            }
        }
	}
}

