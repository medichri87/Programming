// using System;
// using System.Linq;
using System;

namespace Collection
{
    //An iterator specifically designed for the the List-types
    public interface ListIterator<E> : Iterator<E>
	{
	      //Determine if there is a previous item behind the currently pointed to item
        bool HasPrevious();

        //Returns the previous item 
        E Previous();
	}
}

