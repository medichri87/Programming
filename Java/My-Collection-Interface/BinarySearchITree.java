package ICollection;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 *
 * @date Jul 31, 2014
 * @author Chris Medina
 *
 * A binary search tree is a type of binary(left, right) tree where the nodes
 * are arranged in a specific ordering, as follows: for a given node, all
 * elements to its left are less than the node and all the elements to the right
 * are greater than the node. Duplicates are typically not permitted.
 */
public class BinarySearchITree<E> extends AbstractITree<E> implements ITree<E> {
    private Node<E> root;
    private int size;
    private final IList<Node<E>> list;

    public BinarySearchITree() {
        root = null;
        size = 0;
        list = new ArrayIList<>();
    }

    public BinarySearchITree(E[] array) {
        this();
        for (E item : array) {
            add(item);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean contains(E item) {
        if (isEmpty())
            return false;
        Node<E> curr = root;
        while (curr.value() != item) {
            int cmp = ((Comparable<E>) item).compareTo(curr.value());
            if (cmp == -1)
                curr = curr.left;
            else
                curr = curr.right;
            if (curr == null)
                return false;
        }
        return true;
    }

    @Override
    public E removeRoot() {
        return remove(root());
    }

    @Override
    public E remove(E item) {
        Node<E> curr = root, parent = root;
        boolean isLeft = false;

        while (curr.value() != item) {
            parent = curr;
            int cmp = ((Comparable<E>) item).compareTo(curr.value());
            if (cmp == -1)
                curr = curr.left;
            else
                curr = curr.right;
            if (curr == null)
                throw new NoSuchElementException("Value not found");
        }

        //no children, set null
        if (curr.left == null && curr.right == null) {
            if (curr == root) {
                root = null;
            } else if (isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (curr.left == null) {
            //no left child
            if (curr == root) {
                root = curr.right;
            } else if (isLeft) {
                parent.left = curr.right;
            } else {
                parent.right = curr.right;
            }
        } else if (curr.right == null) {
            //no right child
            if (curr == root) {
                root = curr.left;
            } else if (isLeft) {
                parent.left = curr.left;
            } else {
                parent.right = curr.left;
            }
        } else if (curr.right != null && curr.right != null) {
            //two children
            Node<E> replace = replacement(curr);

            if (curr == root) {
                root = replace;
            } else if (isLeft) {
                parent.left = replace;
            } else {
                parent.right = replace;
            }
            replace.left = curr.left;
        }

        size--;
        return curr.value();
    }

    private Node<E> replacement(Node<E> curr) {
        Node<E> replace = curr.right;
        Node<E> parent = curr;

        while (replace.left != null) {
            parent = replace;
            replace = replace.left;
        }

        if (replace != curr.right) {
            parent.left = replace.right;
            replace.right = curr.right;
        }

        return replace;
    }

    @Override
    public void add(E item) {

        if (contains(item))
            throw new IllegalArgumentException("Item is already found in Tree");

        Node<E> rootEntry = new Node<>(item, null, null, null);

        Node<E> parent = root, curr = root;
        boolean isLeft = false;

        if (isEmpty()) {
            root = rootEntry;
        } else {
            while (curr != null) {
                parent = curr;
                int cmp = ((Comparable<E>) item).compareTo(curr.value());
                if (cmp == -1) {
                    curr = curr.left;
                    isLeft = true;
                } else {
                    curr = curr.right;
                    isLeft = false;
                }
            }

            Node<E> node = new Node<>(item, null, null, parent);

            if (isLeft)
                parent.left = node;
            else
                parent.right = node;
        }

        size++;

    }

    @Override
    public E max() {
        if (isEmpty())
            throw new TreeEmptyException("Tree is empty");
        Node<E> curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr.value();
    }

    @Override
    public E min() {
        if (isEmpty())
            throw new TreeEmptyException("Tree is empty");
        Node<E> curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr.value();
    }

    @Override
    public E root() {
        if (isEmpty())
            throw new TreeEmptyException("Tree is empty");
        return root.value();
    }

    @Override
    public E[] toArray() {
        list.clear();
        inOrderTraversal(root);
        E[] out = (E[]) new Object[list.size()];

        for (int i = 0; i < list.size(); i++) {
            E temp = list.get(i).value();
            out[i] = temp;
        }

        return out;
    }

    private void inOrderTraversal(Node<E> root) {
        if (root != null) {
            inOrderTraversal(root.left);
            list.add(root);
            inOrderTraversal(root.right);
        }
    }

    /**
     * Provides a breadth-first view of the current Search Tree as an array
     *
     * @return the array based on a breadth-first search
     */
    public E[] breadthFirstToArray() {
        E[] out = (E[]) new Object[size];
        IQueue<Node<E>> queue = new LinkedIQueue<>();
        Node<E> curr = root;
        int index = 0;

        if (!isEmpty())
            queue.enqueue(curr);

        while (!queue.isEmpty()) {
            Node<E> temp = queue.dequeue();

            if (temp.left != null) {
                queue.enqueue(temp.left);
            }
            if (temp.right != null) {
                queue.enqueue(temp.right);
            }

            out[index++] = temp.value();
        }

        return out;
    }

    private static class Node<E> implements ITree.TreeEntry<E> {

        E value;
        Node<E> left, right, parent;

        public Node(E val, Node<E> left, Node<E> right, Node<E> parent) {
            this.value = val;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        @Override
        public E value() {
            return value;
        }

        @Override
        public E left() {
            return left.value;
        }

        @Override
        public E right() {
            return right.value;
        }

        @Override
        public E parent() {
            return parent.value;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash *= 13 + Objects.hashCode(this.value);
            hash *= 13 + Objects.hashCode(this.left);
            hash *= 13 + Objects.hashCode(this.right);
            hash *= 13 + Objects.hashCode(this.parent);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            final Node<?> other = (Node<?>) obj;
            if (!Objects.equals(this.value, other.value))
                return false;
            if (!Objects.equals(this.left, other.left))
                return false;
            if (!Objects.equals(this.right, other.right))
                return false;
            return Objects.equals(this.parent, other.parent);
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

    }

}
