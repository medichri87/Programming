package ADT;

import java.util.NoSuchElementException;

/**
 * Package: ADT <p>
 * Author: Chris Medina <p>
 * Date: 3/27/2014 <p>
 * Class Purpose: Represents a Binary Search Tree
 */
public class BinarySearchTree<E> extends AbstractTree<E> implements Tree<E> {
    private Node<E> root;
    private int size;
    private EList<E> list;

    /**
     * Construct a blank Binary Search tree
     */
    public BinarySearchTree () {
        size = 0;
        this.root = null;
        list = new EArrayList<>();
    }

    /**
     * Construct a Binary Search tree from an array input
     *
     * @param array the array input of values
     */
    public BinarySearchTree (Object[] array) {
        this();
        for (Object temp : array)
            add((E) temp);
    }

    /**
     * Construct a binary search tree from this Collection
     *
     * @param c the collection to input values from into the Tree
     */
    public BinarySearchTree (ECollection<? extends E> c) {
        this();
        EIterator<? extends E> it = c.iterator();
        while (it.hasNext())
            add(it.next());
    }

    /**
     * Remove the root from the Tree
     *
     * @return true, if found and removed
     */
    public E remove () {
        if (isEmpty())
            return null;
        E temp = root.val;
        remove(root.val);
        return temp;
    }

    /**
     * Empty the entire Tree, removing all items
     */
    public void clear () {
        root = null;
        size = 0;
    }

    /**
     * Add this element to the Tree
     *
     * @param e value to add
     */
    public void add (E e) {
        size++;
        if (isEmpty()) {
            root = new Node<>(null, e, null, null);
        } else {
            Node<E> f = root, parent = root;
            boolean isLeft = false;

            while (f != null) {
                if (e == f.val) {
                    return;
                }
                parent = f;
                if (((Comparable<E>) e).compareTo(f.val) < 0) {
                    f = f.left;
                    isLeft = true;
                } else {
                    f = f.right;
                    isLeft = false;
                }
            }
            Node<E> node = new Node<>(null, e, null, parent);
            if (isLeft)
                parent.left = node;
            else
                parent.right = node;
        }
    }

    /**
     * Return the number of levels/depths contained in this Tree
     *
     * @return the # of levels for the Tree
     */
    public int depth () {
        return depth(root);
    }

    private int depth (Node<E> root) {
        if (root == null)
            return 0;
        else
            return 1 + Math.max(depth(root.left
            ), depth(root.right));
    }

    public Node<E> find (E val) {
        return find(root, val);
    }

    private Node<E> find (Node<E> root, E val) {
        if (root == null)
            return null;
        else if (root.val == val)
            return root;
        if (((Comparable) val).compareTo(root.val) < 0) {
            return find(root.left, val);
        } else {
            return find(root.right, val);
        }
    }

    /**
     * Determine if the current Tree is balanced(i.e. - left half and right half no more than 1 level depth difference)
     *
     * @return true, if the tree is balanced
     */
    public boolean isBalanced () {
        return Math.abs(depth(root.left) - depth(root.right)) <= 1;
    }

    /**
     * Necessary recursive function to override toString for proper display
     *
     * @param root the root node
     */
    private void display (Node<E> root) {
        if (root != null) {
            display(root.left);
            list.add(root.val);
            display(root.right);
        }
    }

    public E root () {
        if (isEmpty())
            return null;
        return root.val;
    }

    public Node<E> getRoot () {
        return root;
    }

    public void displayTree (Node<E> root) {
        if (root != null) {
            displayTree(root.left);
            System.out.println(root);
            displayTree(root.right);
        }
    }

    /**
     * Determine if this value has two children(a node value to the left and the right of it)
     *
     * @param val the value to determine if there are two children for
     * @return true, if this value has two children
     */
    public boolean hasTwoChildren (E val) {
        if (isEmpty())
            return false;
        return (findNode(val).left != null && findNode(val).right != null);
    }

    /**
     * Pass the current Tree off to an Object array
     *
     * @return an Object array consisting of the value found within the Tree
     */
    public Object[] toArray () {
        return list.toArray();
    }

    /**
     * Determine if the every element in the argument Collection is found within this Collection
     *
     * @param c the collection to find within
     * @return true, if this entire collection is found (as a subset)
     */
    public boolean containsAll (ECollection<E> c) {
        Object[] in = c.toArray();
        for (Object temp : in) {
            if (!contains((E) temp))
                return false;
        }
        return true;
    }

    /**
     * Determine if this value is found within this Tree
     *
     * @param o the value to search for
     * @return true, if found
     */
    public boolean contains (E o) {
        Node<E> f = root;
        while (f != null) {
            if (f.val.equals(o))
                return true;
            if (((Comparable<E>) o).compareTo(f.val) < 0) {
                f = f.left;
            } else {
                f = f.right;
            }
        }
        return false;
    }

    /**
     * Determine if the Tree is empty(no items)
     *
     * @return true, if no items are present
     */
    public boolean isEmpty () {
        return (root == null);
    }

    /**
     * Add this entire argument Collection to the current Tree
     *
     * @param c the Tree to add in its entirety
     */
    public void addAll (ECollection<E> c) {
        Object[] in = c.toArray();
        for (Object temp : in)
            add((E) temp);
    }

    /**
     * Output the elements of the current Tree
     *
     * @return a String containing all the values of this Tree
     */
    public String toString () {
        display(root);
        String out = "[";
        for (int i = 0; i < list.size(); i++) {
            out += list.get(i) + (i < size - 1 ? ", " : "");
        }
        return out + "]";
    }

    public enum Choice {
        Pre, In, Post;
    }

    public void display (Choice c) {
        switch (c) {
            case Pre:
                preOrder(this.root);
                break;
            case In:
                inOrder(this.root);
                break;
            case Post:
                postOrder(this.root);
                break;
        }
        System.out.println();
    }

    private void preOrder (Node<E> root) {
        if (root != null) {
            System.out.print(root + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    /*
    private void inOrder (Node<E> root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println(root + " ");
            inOrder(root.right);
        }
    }
    */

    private void postOrder (Node<E> root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root + " ");
        }
    }

    /**
     * Remove this element from the Tree
     *
     * @param o the value to remove
     * @return true, if found and removed
     */
    public boolean remove (E val) {
        if (isEmpty() || !contains(val)) {
            return false;
        } else {
            Node<E> curr = root;
            boolean isLeft = false;
            Node<E> parent = root;

            while (curr.val != val) {
                parent = curr;
                if (((Comparable<E>) val).compareTo(curr.val) < 0) {
                    curr = curr.left;
                    isLeft = true;
                } else {
                    curr = curr.right;
                    isLeft = false;
                }
            }
            if (curr.left == null && curr.right == null) {
                if (root == curr) {
                    root = null;
                } else if (isLeft) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else if (curr.left == null) {
                if (root == curr) {
                    root = root.right;
                } else if (isLeft) {
                    parent.left = curr.right;
                } else {
                    parent.right = curr.right;
                }
            } else if (curr.right == null) {
                if (root == curr) {
                    root = root.left;
                } else if (isLeft) {
                    parent.left = curr.left;
                } else {
                    parent.right = curr.left;
                }
            } else {
                Node<E> replace = findReplacement(curr);
                if (root == curr) {
                    root = replace;
                } else if (isLeft) {
                    parent.left = replace;
                } else {
                    parent.right = replace;
                }
                replace.left = curr.left;
            }
            list.remove(val);
            this.size--;
        }
        return true;
    }

    /**
     * Finds replacement node for the Node we wish to delete
     *
     * @param node the node we are going to remove
     * @return the node that will fill in as a replacement for the removed Node
     */
    private Node<E> findReplacement (Node<E> node) {
        Node<E> replace = node.right;
        Node<E> parent = node;

        while (replace.left != null) {
            parent = replace;
            replace = replace.left;
        }

        if (replace != node.right) {
            parent.left = replace.right;
            replace.right = node.right;
        }

        return replace;
    }

    /**
     * The size of the current Tree
     *
     * @return the number of elements found
     */
    public int size () {
        return size;
    }

    /**
     * An iterator that provides for sequential traversal of the Tree
     *
     * @return a new iterator from the starting point of the current Tree
     */
    public EIterator<E> iterator () {
        return treeIterator();
    }

    /**
     * Determine if this value has a left child
     *
     * @param val the value to find a left child for
     * @return true, if this value in the tree has a value (non-null) to the left
     */
    public boolean hasLeftChild (E val) {
        if (isEmpty())
            return false;
        return (findNode(val).left != null);
    }

    /**
     * Determine if this value has a right child
     *
     * @param val the value to find a right child for
     * @return true, if this value in the tree has a value (non-null) to the right
     */
    public boolean hasRightChild (E val) {
        if (isEmpty())
            return false;
        return (findNode(val).right != null);
    }

    /**
     * Find the parent for this value in the Tree
     *
     * @param val the value to return a parent for
     * @return the parent value, or null if not found
     */
    public E parent (E val) {
        if (isEmpty() || !contains(val))
            return null;
        Node<E> temp = findNode(val);
        return (findNode(val).parent.val);
    }

    /**
     * Find the left child for this value (value one to the left)
     *
     * @param val the value to return a left child for
     * @return the the left child for this value, or null if not found
     */
    public E leftChild (E val) {
        if (isEmpty() || !contains(val))
            return null;
        return (findNode(val).left.val);
    }

    private Node<E> findNode (E val) {
        Node<E> f = root;
        while (f != null) {
            if (f.val.equals(val))
                return f;
            if (((Comparable<E>) val).compareTo(f.val) < 0) {
                f = f.left;
            } else {
                f = f.right;
            }
        }
        return null;
    }

    /**
     * Find the right child for this value (value one to the right)
     *
     * @param val the value to return a right child for
     * @return the the right child for this value, or null if not found
     */
    public E rightChild (E val) {
        if (isEmpty() || !contains(val))
            return null;
        return (findNode(val).right.val);
    }

    /**
     * Search for this value within the Tree
     *
     * @param val the value to search for
     * @return the value if found, null if not found
     */
    public E search (E val) {
        Node<E> f = root;
        while (f != null) {
            if (f.val.equals(val))
                return f.val;
            if (((Comparable<E>) val).compareTo(f.val) < 0) {
                f = f.left;
            } else {
                f = f.right;
            }
        }
        return null;
    }

    /**
     * A special iterator necessary for traversing any Tree type
     *
     * @return an iterator that allows for specialized traversal for Tree types
     */
    public TreeIterator<E> treeIterator () {
        return new TreeItr();
    }

    private static class Node<E> {
        E val;
        Node<E> left, right, parent;

        private Node (Node<E> left, E val, Node<E> right, Node<E> parent) {
            this.left = left;
            this.val = val;
            this.right = right;
            this.parent = parent;
        }

        public String toString () {
            return String.format("%s", val);
        }
    }

    private class TreeItr implements TreeIterator<E> {

        private Node<E> left, current, right, parent;

        private TreeItr () {
            left = root;
            right = root;
            parent = null;
            current = root;
        }

        /**
         * Reset the iterator to the beginning
         */
        public void reset () {
            left = null;
            right = null;
            parent = null;
            current = root;
        }

        /**
         * Return the current element in the current Tree
         *
         * @return the next element
         */
        public E next () {
            if (isEmpty())
                return null;
            return root.val;
        }

        /**
         * Determines if there is a value to move forward to
         *
         * @return true, if the next value exists(non-null)
         */
        public boolean hasNext () {
            return (current.left != null || current.right != null);
        }

        /**
         * Move left to the next element
         *
         * @return the left element, or null if not found
         */
        public E left () {
            parent = current;
            left = current;
            current = current.left;
            return left.val;
        }

        /**
         * Move right to the next element
         *
         * @return the left element, or null if not found
         */
        public E right () {
            parent = current;
            right = current;
            current = current.right;
            return right.val;
        }

        /**
         * Determine if there is any non-null value to the left of this element
         *
         * @return true, if there is a non-null value to the left of this
         */
        public boolean hasNextLeft () {
            return (current != null);
        }

        /**
         * Determine if there is any non-null value to the right of this element
         *
         * @return true, if there is a non-null value to the right of this
         */
        public boolean hasNextRight () {
            return (current != null);
        }

        /**
         * Get the parent value for this value
         *
         * @return the parent value (i.e. value directly above)
         */
        public E parent () {
            if (isEmpty())
                return null;
            return parent.val;
        }
    }

    private void breadthFirst (Node<E> root) {
        if (root == null)
            return;
        else {
            EQueue<Node<E>> q = new LinkedQueue<>();
            q.add(root);
            while (!q.isEmpty()) {
                Node<E> remove = q.remove();
                System.out.print(remove.val + " ");
                if (remove.left != null)
                    q.add(remove.left);
                if (remove.right != null)
                    q.add(remove.right);
            }
        }
    }

    /**
     * Breadth-first display of the current Tree (visits Nodes by levels, in order from left to right
     */
    public void breadthFirst () {
        breadthFirst(root);
        System.out.println();
    }

    private void depthFirst (Node<E> root) {
        EStack<Node<E>> stack = new LinkedStack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node<E> remove = stack.pop();
            System.out.print(remove.val + " ");
            if (remove.right != null)
                stack.add(remove.right);
            if (remove.left != null)
                stack.add(remove.left);
        }
    }

    /**
     * Same traversal as Pre-Order, but non recursive, Stack based implementation
     *
     * @param root the starting Node
     */
    public void depthFirst () {
        depthFirst(root);
        System.out.println();
    }

    private void inOrder (Node<E> root) {
        EStack<Node<E>> stack = new LinkedStack<>();
        Node<E> current = root;

        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.add(current);
                current = current.left;
            } else {
                current = stack.pop();
                System.out.print(current + " ");
                current = current.right;
            }
        }
    }

    /**
     * Find the common ancestor(parent to both) to these two Nodes
     *
     * @param one the first value
     * @param two the second value
     * @return the common ancestor which is an ancestor to both of these values in the current Tree
     */
    public Node<E> commonAncestor (E one, E two) {
        if (!contains(one) || !contains(two))
            throw new NoSuchElementException("Node values not found");

        Node<E> root = this.root;
        while (true) {
            if (root.left == one || root.left == two || root.right == one || root.right == two)
                return root;
            if ((((Comparable<E>) one).compareTo(root.val) < 0) && ((Comparable<E>) two).compareTo(root.val) < 0) {
                root = root.left;
            } else if ((((Comparable<E>) one).compareTo(root.val) > 0) && ((Comparable<E>) two).compareTo(root.val) >
                    0) {
                root = root.right;
            } else {
                return root;
            }
        }
    }

    /**
     * Display the tree in an In-Order fashion(non-recursive, Stack based implementation)
     */
    public void inOrder () {
        inOrder(root);
        System.out.println();
    }

    private void reverseOrder (Node<E> root) {
        EStack<Node<E>> stack = new LinkedStack<>();
        Node<E> current = root;
        if (root == null)
            return;
        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.add(current);
                current = current.right;
            } else {
                current = stack.pop();
                System.out.print(current + " ");
                current = current.left;
            }
        }
    }

    /**
     * Displays the tree in descending order (opposite of In-Order traversal)
     */
    public void reverseOrder () {
        reverseOrder(root);
        System.out.println();
    }

    public static void main (String[] args) {
        Tree<Integer> tree = new BinarySearchTree<>(new Integer[]{20, 8, 22, 4, 12, 10, 14});

        System.out.println(((BinarySearchTree<Integer>) tree).commonAncestor(4, 14));

    }
}
