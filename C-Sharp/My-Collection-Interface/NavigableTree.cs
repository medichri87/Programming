// using System;
// using System.Linq;
using System;
using System.Text;
using System.Linq;
using System.Collections.Generic;
using System.Collections;

namespace Collection
{
    public sealed class NavigableTree<E> : BinarySearchTree<E>, SortedTree<E> where E:IComparable
    {

        /// <summary>
        /// Create a blank tree using the default comparison (Natural ordering)
        /// </summary>
        public NavigableTree()
        {
        }

        public NavigableTree(E[] arr)
            : base(arr)
        {
        }

        public NavigableTree(Collection<E> collection)
            : this(collection.ToArray())
        {
        }

        public E Max() {
            if (IsEmpty())
                throw new TreeEmptyException("Tree is empty");
            TreeNode<E> curr = Root;
            while (curr.Right != null)
                curr = curr.Right;
            return curr.Value;
        }

        public E Min() {
            if (IsEmpty())
                throw new TreeEmptyException("Tree is empty");
            TreeNode<E> curr = Root;
            while (curr.Left != null)
                curr = curr.Left;
            return curr.Value;
        }

        E SortedTree<E>.Root() {
            if (IsEmpty())
                throw new TreeEmptyException("Tree is empty");
            return Root.Value;
        }

        public int Depth() {
            return depth(Root);
        }

        private int depth(TreeNode<E> root){
            if (root == null)
                return 0;
            else
                return 1 + Math.Max(depth(root.Left), depth(root.Right));
        }

        public bool IsBalanced() {
            if (IsEmpty())
                return false;
            int left = depth(Root.Left);
            int right = depth(Root.Right);
            return (int)Math.Abs(left - right) <= 1;
        }


        public SortedTree<E> HeadTree(E val) {
            if (IsEmpty())
                throw new TreeEmptyException("Tree is empty");
            E[] arr = ToArray();

            int i = 0;
            while (arr[i].CompareTo(val) < 0)
                i++;

            return new NavigableTree<E>(arr.SubArray<E>(0, i));
        }

        public SortedTree<E> TailTree(E val) {
            if (IsEmpty())
                throw new TreeEmptyException("Tree is empty");
            E[] arr = ToArray();

            int i = 0;
            while (arr[i].CompareTo(val) < 0)
                i++;

            return new NavigableTree<E>(arr.SubArray<E>(i, arr.Length));
        }

        public SortedTree<E> SubTree(E fromVal, E toVal) {
            if (IsEmpty())
                throw new TreeEmptyException("Tree is empty");
            E[] arr = ToArray();

            int i = 0;
            while ((arr[i] as IComparable<E>).CompareTo(fromVal) < 0)
                i++;

            int j = i;
            while ((arr[j] as IComparable<E>).CompareTo(toVal) < 0){
                j++;
            }

            return new NavigableTree<E>(arr.SubArray<E>(i, j));
        }

        public E HigherVal(E than) {
            if (IsEmpty())
                throw new TreeEmptyException("Tree is empty");
            if (HasRightChild(than))
                return RightChild(than);
            throw new InvalidOperationException("No value lower than this one");
        }

        public E LowerVal(E than) {
            if (IsEmpty())
                throw new TreeEmptyException("Tree is empty");
            if (HasLeftChild(than))
                return LeftChild(than);
            throw new InvalidOperationException("No value lower than this one");
        }

        public E Parent(E val) {
            TreeNode<E> search = find(val);
            if (search == Root)
                return Root.Value;
            return search.Parent.Value;
        }

        public E RightChild(E val) {
            return find(val).Right.Value;
        }

        public E LeftChild(E val) {
            return find(val).Left.Value;
        }

        public bool HasLeftChild(E val) {
            return !ReferenceEquals(find(val).Left, null);
        }

        public bool HasRightChild(E val) {
            return !ReferenceEquals(find(val).Right, null);
        }

        public bool HasTwoChildren(E val) {
            return (HasLeftChild(val) && HasRightChild(val));
        }

        public bool HasNoChildren(E val) {
            return (!HasLeftChild(val) && !HasRightChild(val));
        }

        private TreeNode<E> find(E val){
            TreeNode<E> curr = Root;
            bool isLeft = false;

            if (IsEmpty())
                throw new TreeEmptyException("Tree is empty");
            while (!curr.Value.Equals(val)){
                int cmp = val.CompareTo(curr.Value);
                if (cmp == -1){
                    curr = curr.Left;
                    isLeft = true;
                }
                else{
                    curr = curr.Right;
                    isLeft = false;
                }
                if (curr == null)
                    throw new InvalidOperationException("Item not found");
            }

            return curr;
        }
            
    }


}








