// using System;
// using System.Linq;
using System;

namespace Collection
{
    public class HashSet<E> : AbstractSet<E>, Set<E>
	{
        private Map<E, Object> map;

        //blank object, of no value
        private static readonly Object SENTINEL = new Object();

        public HashSet()
		{
            map = new HashMap<E, object>();
		}

        public HashSet(E[] arr)
        {
            map = new HashMap<E, object>(arr.Length);
            foreach (E item in arr)
                map.Put(item, SENTINEL);
        }

        public HashSet(Collection<E> collection)
            : this(collection.ToArray())
        {
        }

        public override bool Contains(E item)
        {
            return map.ContainsKey(item);
        }

        public override void Add(E item)
        {
            if (!map.ContainsKey(item))
                map.Put(item, SENTINEL);
        }

        public override int Size()
        {
            return map.Size();
        }

        public override bool IsEmpty()
        {
            return map.IsEmpty();
        }

        public override E Remove(E item)
        {
            throw new NotImplementedException();
        }

        public override Iterator<E> Iterator()
        {
            return map.Keys().Iterator();
        }

        public override void Clear()
        {
            map.Clear();
        }
	}
}

