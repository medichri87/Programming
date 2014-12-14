// using System;
// using System.Linq;
using System;

namespace Collection
{
    public interface SortedTree<E> : Tree<E>
	{

        /// <summary>
        /// The maximum value of this Tree
        /// </summary>
        E Max();

        /// <summary>
        /// The minimum value of this Tree
        /// </summary>
        E Min();

        /// <summary>
        /// Number of levels or depths of this Tree.
        /// </summary>
        int Depth();

        /// <summary>
        /// Determines whether this Tree is balanced (left and right half of tree is no more than 1 level difference)
        /// </summary>
        /// <returns><c>true</c> if this Tree is balanced; otherwise, <c>false</c>.</returns>
        bool IsBalanced();

        /// <summary>
        /// The root value of this Tree
        /// </summary>
        E Root();

        /// <summary>
        /// Returns a view of the portion of this Tree whose values are strictly less than val.
        /// </summary>
        SortedTree<E> HeadTree(E val);

        /// <summary>
        /// Returns a view of the portion of this Tree whose values are greater than or equal to val.
        /// </summary>
        SortedTree<E> TailTree(E val);

        /// <summary>
        /// Produces a Sub-Tree view of the values between the given values (non-inclusive - up to, but not including toVal)
        /// </summary>
        /// <param name="fromVal">Starting value</param>
        /// <param name="toVal">Value to go up to, but not including</param>
        SortedTree<E> SubTree(E fromVal, E toVal);

        /// <summary>
        /// Returns a value just greater than this value
        /// </summary>
        /// <returns>Value just above the given one</returns>
        E HigherVal(E than);

        /// <summary>
        /// Returns a value just lower than this value
        /// </summary>
        /// <returns>Value just below the given one</returns>
        E LowerVal(E than);

        /// <summary>
        /// Parent of the specified val.
        /// </summary>
        /// <param name="val">the value to search for</param>
        E Parent(E val);

        /// <summary>
        /// Right child value of the given value
        /// </summary>
        /// <returns>Value to the right of the given value</returns>
        /// <param name="val">value to search for</param>
        /// 
        E RightChild(E val);

        /// <summary>
        /// Left child value of the given value
        /// </summary>
        /// <returns>Value to the Left of the given value</returns>
        /// <param name="val">value to search for</param>
        E LeftChild(E val);

        /// <summary>
        /// Determines whether this value has left child
        /// </summary>
        /// <returns><c>true</c> if this instance has left child; otherwise, <c>false</c>.</returns>
        /// <param name="val">value to determine for</param>
        bool HasLeftChild(E val);

        /// <summary>
        /// Determines whether this value has right child
        /// </summary>
        /// <returns><c>true</c> if this instance has right child; otherwise, <c>false</c>.</returns>
        /// <param name="val">value to determine for</param>
        bool HasRightChild(E val);

        /// <summary>
        /// Determines whether this value has two children
        /// </summary>
        /// <returns><c>true</c> if this value has two children; otherwise, <c>false</c>.</returns>
        /// <param name="val">value to determine for</param>
        bool HasTwoChildren(E val);

        bool HasNoChildren(E val);

    }
}

