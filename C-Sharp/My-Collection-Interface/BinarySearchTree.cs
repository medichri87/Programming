// using System;
// using System.Linq;
using System;
using System.Linq;
using System.Collections;
using System.Collections.Generic;

namespace Collection
{
    /// <summary>
    /// Represents a Binary Search Tree which provides O(lg n) search, deletion, and insertion performance (average case)
    /// </summary>
    public class BinarySearchTree<E> : AbstractTree<E>, Tree<E> where E: IComparable
	{
        public TreeNode<E> Root { get; set; }

        public int Count { get; protected set; }

        //for the purpose of toString and toArray
        private List<E> output;

        public BinarySearchTree()
		{
            Root = null;
            Count = 0;
        }

        public BinarySearchTree(E[] array)
            : this(){
            foreach (E item in array)
                Add(item);
        }

        public BinarySearchTree(Collection<E> collection)
            : this(collection.ToArray()){

        }

        #region implemented abstract members of AbstractTree

        public override void Clear() {
            Root = null;
            Count = 0;
        }

        public override void Add(E item) {
            if (IsEmpty()){
                this.Root = new TreeNode<E>(null, item, null, null);
            }
            else {
                TreeNode<E> curr = Root;
                TreeNode<E> parent = Root;
                bool isLeft = false;

                while (curr != null){
                    parent = curr;
                    int cmp = item.CompareTo(curr.Value);

                    //value already exists
                    if (cmp == 0)
                        return;
                    //smaller than current
                    else if (cmp == -1){
                        curr = curr.Left;
                        isLeft = true;
                    }
                    //larger than current
                    else{
                        curr = curr.Right;
                        isLeft = false;
                    }
                }

                TreeNode<E> node = new TreeNode<E>(null, item, null, parent);

                if (isLeft)
                    parent.Left = node;
                else
                    parent.Right = node;
            }
            Count++;
        }


        public override int Size() {
            return Count;
        }

        public override E Remove() {
            return Remove(Root.Value);
        }

        //only called for removing node with two children
        private TreeNode<E> Replacement(TreeNode<E> curr){
            TreeNode<E> parent = curr;
            TreeNode<E> replace = curr.Right;

            while (replace.Left != null){
                parent = replace;
                replace = replace.Left;
            }

            if (replace != curr.Right){
                parent.Left = replace.Right;
                replace.Right = curr.Right;
            }

            return replace;
        }

        public override E[] ToArray() {
            output = new ArrayList<E>();
            InOrderTraversal(Root);
            return output.ToArray();
        }

        private void InOrderTraversal(TreeNode<E> root){
            if (root != null){
                InOrderTraversal(root.Left);
                output.Add(root.Value);
                InOrderTraversal(root.Right);
            }
        }

        public override Iterator<E> Iterator() {
            output = new ArrayList<E>();
            InOrderTraversal(Root);
            return output.Iterator();
        }

        public override bool Contains(E item)
        {
            if (IsEmpty())
                return false;
            TreeNode<E> curr = Root;
            while (!curr.Value.Equals(item)){
                //assumes comparable reference type
                int cmp = item.CompareTo(curr.Value);
                if (cmp == -1)
                    curr = curr.Left;
                else
                    curr = curr.Right;
                if (curr == null)
                    return false;
            }
            return true;
        }

        public override E Remove(E item)
        {
            TreeNode<E> parent = Root;
            TreeNode<E> curr = Root;
            bool isLeft = false;

            if (IsEmpty())
                throw new TreeEmptyException("Tree is empty");
            while (!curr.Value.Equals(item)){
                parent = curr;
                //assumes comparable reference type
                int cmp = item.CompareTo(curr.Value);
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

            //no children
            if (ReferenceEquals(null, curr.Left) && ReferenceEquals(null, curr.Right)){
                if (Root == curr){
                    Root = null;
                }
                else if (isLeft){
                    parent.Left = null;
                }
                else {
                    parent.Right = null;
                }
            }

            //no left child
            else if (ReferenceEquals(null, curr.Left)){
                if (Root == curr){
                    Root = parent.Right;
                }
                else if (isLeft){
                    parent.Left = curr.Right;
                }
                else {
                    parent.Right = curr.Right;
                }
            }

            //no right child
            else if (ReferenceEquals(null, curr.Right)){
                if (Root == curr){
                    Root = parent.Left;
                }
                else if (isLeft){
                    parent.Left = curr.Left;
                }
                else {
                    parent.Right = curr.Left;
                }
            }
            else {

                TreeNode<E> replacement = Replacement(curr);

                if (Root == curr){
                    Root = replacement;
                }
                else if (isLeft){
                    parent.Left = replacement;
                }
                else {
                    parent.Right = replacement;
                }

                replacement.Left = curr.Left;
            }

            Count--;
            return curr.Value;
        }

        #endregion

        public class TreeNode<E> {
            public E Value { get; set; }

            public TreeNode<E> Left { get; set; }

            public TreeNode<E> Right { get; set; }

            public TreeNode<E> Parent { get; set; }

            public TreeNode(TreeNode<E> left, E value, TreeNode<E> right, TreeNode<E> parent){
                this.Left = left;
                this.Value = value;
                this.Right = right;
                this.Parent = parent;
            }

            public override bool Equals(object obj) {
                if (obj == null)
                    return false;
                if (ReferenceEquals(this, obj))
                    return true;
                if (obj.GetType() != typeof(TreeNode<E>))
                    return false;
                TreeNode<E> other = (TreeNode<E>)obj;
                return Value.Equals(other.Value) && Left.Equals(other.Left) && Right.Equals(other.Right);
            }


            public override int GetHashCode() {
                unchecked {
                    return (!ReferenceEquals(null, Value) ? Value.GetHashCode() : 0) ^ (Left != null ? Left.GetHashCode() : 0) ^ (Right != null ? Right.GetHashCode() : 0);
                }
            }

        }

	}
}

