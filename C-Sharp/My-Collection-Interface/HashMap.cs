// using System;
// using System.Linq;
using System;
using System.Runtime.Remoting.Lifetime;

namespace Collection
{
    public class HashMap<K, V> : AbstractMap<K, V> , Map<K, V>
	{

        public int Count { get; private set; }

        private List<Node<K, V>>[] list;
        private int currMaxSize;
        private readonly int DEFAULT_SIZE = 10;

        public HashMap(int ms)
		{
            Count = 0;
            list = new DLinkedList<Node<K, V>>[ms];
            this.currMaxSize = DEFAULT_SIZE;

            for (int i = 0; i < list.Length; i++)
                list[i] = new DLinkedList<Node<K, V>>();
		}

        public HashMap()
            : this(10){

        }

        #region implemented abstract members of AbstractMap

        public override bool ContainsKey(K key) {
            int code = hash(key.GetHashCode());
            List<Node<K, V>> temp = list[code];
            for (int i = 0; i < temp.Size(); i++){
                K tempKey = temp.Get(i).Key();
                if (tempKey.Equals(key))
                    return true;
            }
            return false;
        }

        public override V Remove(K key) {
            if (IsEmpty())
                throw new MapEmptyException("Map is empty");
            else if (!ContainsKey(key))
                throw new InvalidOperationException("Key not found");
            V tempVal = default(V);
            int code = hash(key.GetHashCode());
            List<Node<K, V>> temp = list[code];
            for (int i = 0; i < temp.Size(); i++){
                Node<K, V> entry = temp.Get(i);
                K tempKey = entry.Key();
                tempVal = entry.Value();
                if (tempKey.Equals(key)){
                    temp.Remove(entry);
                    break;
                }
            }
            Count--;
            return tempVal;
        }

        private int hash(int key){
            return (key % list.Length);
        }

        public override void Clear() {
            for (int i = 0; i < list.Length; i++){
                list[i].Clear();
            }
            Count = 0;
            currMaxSize = DEFAULT_SIZE;
        }

        public override int Size() {
            return Count;
        }

        public override V Get(K key) {
            int code = hash(key.GetHashCode());
            List<Node<K, V>> temp = list[code];
            for (int i = 0; i < temp.Size(); i++){
                K tempKey = temp.Get(i).Key();
                V tempVal = temp.Get(i).Value();
                if (tempKey.Equals(key))
                    return tempVal;
            }
            throw new InvalidOperationException("Key not found");
        }

        public override V Put(K key, V value) {
            CheckForExpansion();
            if (ContainsKey(key)){
                V oldValue = Get(key);
                int code = hash(key.GetHashCode());
                List<Node<K, V>> temp = list[code];
                for (int i = 0; i < temp.Size(); i++){
                    Node<K, V> entry = temp.Get(i);
                    K tempKey = entry.Key();
                    V tempVal = entry.Value();
                    if (tempKey.Equals(key)){
                        //update value to match new entry
                        entry.value = value;
                    }
                }
                return oldValue;
            }
            else {
                int code = hash(key.GetHashCode());
                List<Node<K, V>> temp = list[code];
                Node<K, V> node = new Node<K, V>(key, value);
                temp.Add(node);
                Count++;
                return value;
            }
        }

        private void CheckForExpansion(){
            int total = 0;
            for (int i = 0; i < list.Length; i++){
                total += list[i].Size();

                //if any bucket has more than 2 items and total items is twice starting max, triple total size
                if (list[i].Size() > 2 && total > (currMaxSize * 2)){
                    //BucketCheck();
                    //expand by copying contents into new array, but save original array first
                    List<Node<K, V>>[] tempArray = list;

                    //Create blank array of triple the length of the original
                    list = new List<Node<K, V>>[(currMaxSize *= 3)];
                    for (int a = 0; a < list.Length; a++)
                        list[a] = new DLinkedList<Node<K, V>>();

                    //rehash the original items into expanded array
                    for (int j = 0; j < tempArray.Length; j++){
                        for (int k = 0; k < tempArray[j].Size(); k++){
                            Node<K, V> entry = tempArray[j].Get(k);
                            int code = hash(entry.Key().GetHashCode());
                            list[code].Add(entry);                           
                        }
                    }
                    //BucketCheck();
                    return;
                }
            }
        }

        /*
        public void BucketCheck(){
            for (int i = 0; i < list.Length; i++){
                Console.WriteLine("Bucket (" + i + ")" + " contains: " + list[i].Size() + " elements");
            }
        } 
        */

        public override List<Entry<K, V>> Entries() {
            List<Entry<K, V>> entries = new ArrayList<Entry<K, V>>();

            for (int i = 0; i < list.Length; i++){
                List<Node<K, V>> temp = list[i];
                if (!temp.IsEmpty()){
                    for (int j = 0; j < temp.Size(); j++){
                        entries.Add(temp.Get(j));
                    }
                }
            }

            return entries;
        }

        /// <summary>
        /// Overriden here to ensure that objects that are equal also maintain the same Hash Code. 
        /// Equal Maps will contains the exact same elements in the same order each with the same hashcode.
        /// </summary>
        /// <filterpriority></filterpriority>
        public override int GetHashCode(){
            int result = 11;
            for (int i = 0; i < list.Length; i++){
                for (int j = 0; j < list[i].Size(); j++){
                    Node<K, V> temp = list[i].Get(j);
                    result += (7 * temp.GetHashCode());
                }
            }
            return result;
        }


        #endregion

        /*
         * Represents the individual entries in the Map, which encapsulates the Key and Value 
        */
        private class Node<K, V> : Entry<K, V>{
            public K key { get; set; }

            public V value { get; set; }

            public K Key() {
                return this.key;
            }

            public V Value() {
                return this.value;
            }

            public Node(K key, V value){
                this.key = key;
                this.value = value;
            }

            public override string ToString()
            {
                return string.Format("{0}={1}", this.Key(), this.Value());
            }

            public override bool Equals(object obj) {
                if (obj == null)
                    return false;
                if (ReferenceEquals(this, obj))
                    return true;
                if (obj.GetType() != typeof(Node<K, V>))
                    return false;
                Node<K, V> other = (Node<K, V>)obj;
                return key.Equals(other.key) && value.Equals(other.value);
            }


            public override int GetHashCode() {
                unchecked {
                    return (key != null ? key.GetHashCode() : 0) ^ (value != null ? value.GetHashCode() : 0);
                }
            }
        }
            
    }

}

