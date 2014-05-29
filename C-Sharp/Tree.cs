using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Collections;
using System.Collections.ObjectModel;

namespace OOP {
    /// <summary>
    /// Date: 5-28-2014
    /// Construct a Binary Search Tree. Generic type-parameter must be of an IComparable type.
    /// </summary>
    public class Tree<E> where E : IComparable<E> {

        public TreeNode<E> Root { get; private set; }

        public int Size { get; private set; }

        private IList<TreeNode<E>> list;

        /// <summary>
        /// Initializes a new instance of the <see cref="Tree"/> class.
        /// </summary>
        public Tree() {
            this.list = new List<TreeNode<E>>();
            this.Size = 0;
            this.Root = null;
        }

        /// <summary>
        /// Pass the contents of an array into the Tree upon initialization
        /// </summary>
        /// <param name="array"></param>
        public Tree(E[] array)
            : this() {

            foreach (E item in array)
                Add(item);
        }

        /// <summary>
        /// Pass the contents of a ICollection into the Tree upon initialization
        /// </summary>
        /// <param name="array"></param>
        public Tree(ICollection<E> temp)
            : this() {

            IEnumerator<E> it = temp.GetEnumerator();
            while (it.MoveNext())
                this.Add(it.Current);
        }

        /// <summary>
        /// Determines whether the Tree is empty.
        /// </summary>
        /// <returns><c>true</c> if this instance is empty; otherwise, <c>false</c>.</returns>
        public bool IsEmpty() {
            return (Root == null);
        }

        /// <summary>
        /// Search for a specific value.
        /// </summary>
        /// <param name="val">Value. the value to search for</param>
        public TreeNode<E> Search(E val) {
            TreeNode<E> f = Root;
            while (!f.Value.Equals(val)) {
                if (f == null)
                    throw new ArgumentNullException("Value not found");
                if (val.CompareTo(f.Value) < 0)
                    f = f.Left;
                else
                    f = f.Right;
            }
            return null;
        }

        /// <summary>
        /// Determines whether this TreeNode has a left child.
        /// </summary>
        /// <returns><c>true</c> if this TreeNode has left child the specified find; otherwise, <c>false</c>.</returns>
        /// <param name="find">Find. The value to determine left-child status for</param>
        public bool HasLeftChild(E find) {
            if (IsEmpty())
                return false;
            return Search(find).Left != null;
        }

        /// <summary>
        /// Determines whether this value has no left child.
        /// </summary>
        /// <returns><c>true</c> if this value has no left child; otherwise, <c>false</c>.</returns>
        /// <param name="find">Find. the value to find to determine if it has no left child</param>
        public bool HasNoLeftChild(E find) {
            return !HasLeftChild(find);
        }

        /// <summary>
        /// Determines whether this value has no right child.
        /// </summary>
        /// <returns><c>true</c> if this value has no right child; otherwise, <c>false</c>.</returns>
        /// <param name="find">Find. the value to find to determine if it has no right child</param>
        public bool HasNoRightChild(E find) {
            return !HasRightChild(find);
        }

        /// <summary>
        /// Determines whether this value has a right child.
        /// </summary>
        /// <returns><c>true</c> if this TreeNode has right child the specified find; otherwise, <c>false</c>.</returns>
        /// <param name="find">Find. The value to determine right-child status for</param>
        public bool HasRightChild(E find) {
            if (IsEmpty())
                return false;
            return Search(find).Right != null;
        }

        /// <summary>
        /// Determines whether this value has two children (Has a node to the left AND the right)
        /// </summary>
        /// <returns><c>true</c> if this value has two children; otherwise, <c>false</c>.</returns>
        /// <param name="find">Find. the value to determine the child status of</param>
        public bool HasTwoChildren(E find) {
            return HasLeftChild(find) && HasRightChild(find);
        }

        /// <summary>
        /// Determines whether this value has no children (No node to the left AND to the right)
        /// </summary>
        /// <returns><c>true</c> if this value has no children; otherwise, <c>false</c>.</returns>
        /// <param name="find">Find. the value to determine the child status of</param>
        public bool HasNoChildren(E find) {
            return !HasLeftChild(find) && !HasRightChild(find);
        }

        /// <summary>
        /// Gets the right child of a value within the Tree.
        /// </summary>
        /// <returns>The right child value (value to the left of the argument value)</returns>
        /// <param name="find">Find. The value to return the right child of</param>
        public TreeNode<E> GetRightChild(E find) {
            TreeNode<E> temp = Search(find);
            return temp.Right;
        }

        /// <summary>
        /// Gets the left child of a value within the Tree.
        /// </summary>
        /// <returns>The left child value (value to the left of the argument value)</returns>
        /// <param name="find">Find. The value to return the left child of</param>
        public TreeNode<E> GetLeftChild(E find) {
            TreeNode<E> temp = Search(find);
            return temp.Left;
        }

        /// <summary>
        /// Gets the parent for this value in the Tree.
        /// </summary>
        /// <returns>The parent TreeNode (TreeNode directly above this one)</returns>
        /// <param name="find">Find. the value to return the parent for</param>
        public TreeNode<E> GetParent(E find) {
            if (IsEmpty())
                return null;
            TreeNode<E> temp = Search(find);

            if (temp.Equals(Root)) {
                return Root;
            }

            return temp.Parent;
        }

        /// <summary>
        /// Add this value to the Tree
        /// </summary>
        /// <param name="e">E. the item to add</param>
        public void Add(E e) {
            Size++;
            if (IsEmpty()) {
                Root = new TreeNode<E>(null, e, null, null);
            }
            else {
                TreeNode<E> f = Root, parent = Root;
                bool isLeft = false;

                while (f != null) {
                    if (f.Value.Equals(e)) {
                        return;
                    }
                    parent = f;
                    if (((IComparable<E>)e).CompareTo(f.Value) < 0) {
                        f = f.Left;
                        isLeft = true;
                    }
                    else {
                        f = f.Right;
                        isLeft = false;
                    }
                }
                TreeNode<E> node = new TreeNode<E>(null, e, null, parent);
                if (isLeft)
                    parent.Left = node;
                else
                    parent.Right = node;
            }
        }

        /// <summary>
        /// Builds the output String by traversing the current Tree in an In-Order fashion (ordered), 
        /// for when the ToString() method is called.
        /// </summary>
        /// <param name="root">Root.</param>
        private void buildString(TreeNode<E> root) {
            if (root != null) {
                //in-order traversal
                buildString(root.Left);
                list.Add(root);
                buildString(root.Right);
            }
        }

        /// <summary>
        /// Returns a string that represents the current object.
        /// </summary>
        /// <returns>A string that represents the current object.</returns>
        /// <filterpriority>2</filterpriority>
        public override string ToString() {
            list.Clear();
            buildString(Root);
            StringBuilder sb = new StringBuilder("[");

            for (int i = 0; i < list.Count; i++) {
                if (i < list.Count - 1)
                    sb.Append(list[i] + ", ");
                else
                    sb.Append(list[i]);
            }

            sb.Append("]");
            return sb.ToString();
        }

        /// <summary>
        /// Gets the replacement node for the one that is being deleted. 
        /// When a node is removed from the tree, the next in-order successor value 
        /// will be put in place of the node that has been removed.
        /// </summary>
        /// <returns>The replacement node.</returns>
        /// <param name="node">Node.</param>
        private TreeNode<E> GetReplacementNode(TreeNode<E> node) {
            if (IsEmpty())
                throw new InvalidOperationException("Tree is empty");

            TreeNode<E> replace = node.Right;
            TreeNode<E> parent = node;

            while (replace.Left != null) {
                parent = replace;
                replace = replace.Left;
            }

            if (replace != node.Right) {
                parent.Left = replace.Right;
                replace.Right = node.Right;
            }

            return replace;
        }

        /// <summary>
        /// Determines whether this value is a left child.
        /// </summary>
        /// <returns><c>true</c> if this value is left child; otherwise, <c>false</c>.</returns>
        /// <param name="find">Find. the value to determine left-child status for</param>
        public bool IsLeftChild(E find) {
            TreeNode<E> temp = Search(find);
            if (temp == null)
                throw new ArgumentNullException("Value doesn't exist");
            else if (temp.Equals(Root)) {
                return false;
            }
            return temp.Equals(temp.Parent.Left);
        }

        /// <summary>
        /// Determines whether this value is a right child.
        /// </summary>
        /// <returns><c>true</c> if this value is right child; otherwise, <c>false</c>.</returns>
        /// <param name="find">Find. the value to determine right-child status for</param>
        public bool IsRightChild(E find) {
            TreeNode<E> temp = Search(find);
            if (temp == null)
                throw new ArgumentNullException("Value doesn't exist");
            else if (temp.Equals(Root)) {
                return false;
            }
            return temp.Equals(temp.Parent.Right);
        }

        /// <summary>
        /// Determine if this value is found within the current Tree.
        /// </summary>
        /// <param name="find">Find. The value to search for</param>
        public bool Contains(E find) {
            return Search(find) != null;
        }

        /// <summary>
        /// Find the maximum value in the current Tree.
        /// </summary>
        public E Max() {
            if (IsEmpty())
                throw new ArgumentNullException("Tree is empty");
            TreeNode<E> curr = Root;
            while (curr.Right != null)
                curr = curr.Right;

            return curr.Value;
        }

        /// <summary>
        /// Find the minimum value in the current Tree.
        /// </summary>
        public E Min() {
            if (IsEmpty())
                throw new ArgumentNullException("Tree is empty");
            TreeNode<E> curr = Root;
            while (curr.Left != null)
                curr = curr.Left;

            return curr.Value;
        }

        /// <summary>
        /// Remove a value from the current Tree.
        /// </summary>
        /// <param name="find">Find. The value to remove.</param>
        /// <returns>The removed value</returns>
        public E Remove(E find) {
            if (!Contains(find))
                throw new InvalidOperationException("Value not found");

            TreeNode<E> remove = Search(find);
            bool IsLeft = IsLeftChild(find);
            TreeNode<E> Parent = GetParent(find);

            if (HasNoChildren(find)) {
                if (remove.Equals(Root)) {
                    Root = null;
                }
                else if (IsLeft) {
                    Parent.Left = null;
                }
                else {
                    Parent.Right = null;
                }
            }
            else if (HasNoLeftChild(find)) {
                if (remove.Equals(Root)) {
                    Root = Root.Right;
                }
                else if (IsLeft) {
                    Parent.Left = remove.Right;
                }
                else {
                    Parent.Right = remove.Right;
                }
            }
            else if (HasNoRightChild(find)) {
                if (remove.Equals(Root)) {
                    Root = Root.Left;
                }
                else if (IsLeft) {
                    Parent.Left = remove.Left;
                }
                else {
                    Parent.Right = remove.Left;
                }
            }
            else {
                TreeNode<E> Replace = GetReplacementNode(remove);
                if (remove.Equals(Root)) {
                    Root = Replace;
                }
                else if (IsLeft) {
                    Parent.Left = Replace;
                }
                else {
                    Parent.Right = Replace;
                }

                Replace.Left = remove.Left;
            }

            Size--;
            return remove.Value;
        }

        private int Depth(TreeNode<E> root) {
            if (root == null)
                return 0;
            else
                return 1 + (Math.Max(Depth(root.Left), Depth(root.Right)));
        }

        /// <summary>
        /// Get the number of levels/depths to the current Tree.
        /// </summary>
        public int Depth() {
            return Depth(Root);
        }

        public void BreadthFirstDisplay() {

        }
    }

    /// <summary>
    /// TreeNode represnets the individual nodes (entries) that make up a Binary Search Tree.
    /// </summary>
    public class TreeNode<E> {
        public E Value { get; set; }

        public TreeNode<E> Left { get; set; }

        public TreeNode<E> Right { get; set; }

        public TreeNode<E> Parent { get; set; }

        public TreeNode(TreeNode<E> left, E val, TreeNode<E> right, TreeNode<E> parent) {
            this.Value = val;
            this.Left = left;
            this.Right = right;
            this.Parent = parent;
        }

        public override string ToString() {
            return String.Format("{0}", this.Value);
        }

        /// <summary>
        /// Determines whether the specified <see cref="System.Object"/> is equal to the current <see cref="TreeNode"/>.
        /// </summary>
        /// <param name="obj">The <see cref="System.Object"/> to compare with the current <see cref="TreeNode"/>.</param>
        /// <returns><c>true</c> if the specified <see cref="System.Object"/> is equal to the current
        public override bool Equals(object obj) {
            if (obj == this)
                return true;
            else if (obj.GetType() != this.GetType() || obj == null)
                return false;
            else {
                TreeNode<E> Compare = (TreeNode<E>)obj;
                return (Value.Equals(Compare.Value));
            }
        }

        /// <summary>
        /// Serves as a hash function for a particular type.
        /// </summary>
        /// <returns>A hash code for this instance that is suitable for use in hashing algorithms and data structures such as a hash table.</returns>
        public override int GetHashCode() {
            int result = 7;
            result = result * 7 + (this.Value == null ? 0 : Value.GetHashCode());
            result = result * 7 + (this.Left == null ? 0 : Left.GetHashCode());
            result = result * 7 + (this.Right == null ? 0 : Right.GetHashCode());

            return result;
        }
    }

    public class RunIt {
        public static void Main(String[] args) {

            Queue<int> q = new Queue<int>();

            q.Enqueue(1);
            q.Enqueue(3);
            q.Enqueue(6);

            Tree<int> tree = new Tree<int>(q.ToList());

            Console.Write(q.ToList().FindAll(o => o % 2 == 1)[0]);

            Console.ReadKey();

        }
    }
}

