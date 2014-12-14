// using System;
// using System.Linq;
using System;

namespace Collection
{
    //Allows front and rear insertion, removal, and retreival (for List types)
    public interface DoubleEnded<E>
	{
	      //Remove the first item
        E RemoveFirst();

        //Remove the last item
        E RemoveLast();

        //Returns the first item
        E GetFirst();

        //Returns the last item
        E GetLast();

        //Insert to the front
        void InsertFirst(E item);

        //Insert to the end
        void InsertLast(E item);

        //Insert an item before another 
        void InsertBefore(E find, E val);

        //Insert an item after another
        void InsertAfter(E find, E val);
	}
}

