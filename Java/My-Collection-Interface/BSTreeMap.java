package ADT;

import java.util.NoSuchElementException;

/**
 * Created by Chris Medina on 5/23/2014.
 * <p>Purpose: A binary search tree implementation of my IMap interface</p>
 */
public class BSTreeMap<K, V> implements IMap<K, V> {

    private int size;
    private Node<K, V> root;
    private EList<Entry<K, V>> nodes;

    public BSTreeMap () {
        root = null;
        size = 0;
        nodes = new EArrayList<>();
    }

    public BSTreeMap (IMap<K, V> c) {
        EList<Entry<K, V>> entries = c.entrySet();

        for (int i = 0; i < c.size(); i++) {
            K key = entries.get(i).getKey();
            V val = entries.get(i).getValue();
            put(key, val);
        }
    }

    /**
     * Return the size of the current TreeMap
     *
     * @return the size of the TreeMap
     */
    @Override
    public int size () {
        return size;
    }

    /**
     * Determine if the current Map is empty
     *
     * @return true, if there are no elements in the TreeMap
     */
    @Override
    public boolean isEmpty () {
        return (root == null);
    }

    /**
     * Determine if the TreeMap contains a key
     *
     * @param key the key to search for
     * @return true, if the key is found
     */
    @Override
    public boolean containsKey (K key) {
        Node<K, V> curr = root;
        if (isEmpty())
            return false;
        while (curr.key != key) {
            if (((Comparable<K>) key).compareTo(curr.getKey()) < 0)
                curr = curr.left;
            else
                curr = curr.right;
            if (curr == null)
                return false;
        }
        return true;
    }

    @Override
    public String toString () {
        //call inorder traversal to build list of keys from current map for our EList in order
        //to get the values for printing
        nodes.clear();
        inOrderDisplay(root);

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < nodes.size(); i++) {
            if (i < nodes.size() - 1)
                sb.append(nodes.get(i) + ", ");
            else
                sb.append(nodes.get(i));
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Determine if the TreeMap contains a value
     *
     * @param value the value to search for
     * @return true, if the value is found
     */
    @Override
    public boolean containsValue (V value) {
        if (isEmpty())
            return false;
        Node<K, V> curr = root;
        while (curr.getValue() != value) {
            if (((Comparable<V>) value).compareTo(curr.getValue()) < 0)
                curr = curr.left;
            else
                curr = curr.right;
            if (curr == null)
                return false;
        }
        return true;
    }

    /**
     * Retrieve the value associated with a specific key
     *
     * @param key the key to search for
     * @return the value associated with this key
     */
    @Override
    public V get (K key) {
        if (!containsKey(key))
            throw new NoSuchElementException("Key not found");
        Node<K, V> curr = root;
        while (curr.getKey() != key) {
            if (((Comparable<K>) key).compareTo(curr.getKey()) < 0)
                curr = curr.left;
            else
                curr = curr.right;
        }
        return curr.getValue();
    }

    /**
     * Add this key-value pair to the TreeMap
     *
     * @param key   the key to insert
     * @param value the value to insert
     */
    @Override
    public void put (K key, V value) {
        if (containsKey(key) || key == null)
            return;

        boolean isLeft = false;
        Node<K, V> curr = root, parent = root;
        size++;
        if (isEmpty()) {
            root = new Node<>(null, key, value, null, null);
        } else {
            while (curr != null) {
                parent = curr;
                if (((Comparable) key).compareTo(curr.getKey()) < 0) {
                    curr = curr.left;
                    isLeft = true;
                } else {
                    curr = curr.right;
                    isLeft = false;
                }
            }

            Node<K, V> insert = new Node<>(null, key, value, null, parent);
            if (isLeft)
                parent.left = insert;
            else
                parent.right = insert;
        }
    }

    /**
     * Remove a key-value pair from the TreeMap
     *
     * @param key the key to remove
     * @return the value associated with this key
     */
    @Override
    public V remove (K key) {
        if (!containsKey(key) || key == null)
            throw new NoSuchElementException("Key not found");

        boolean isLeft = isLeftChild(key);
        Node<K, V> curr = find(key);
        Node<K, V> parent = getParent(curr.key);

        //has no children
        if (hasNoChildren(key)) {
            if (curr == root) {
                root = null;
            } else if (isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        //has no left child
        else if (!hasLeftChild(key)) {
            if (curr == root) {
                root = curr.right;
            } else if (isLeft) {
                parent.left = curr.right;
            } else {
                parent.right = curr.right;
            }
        }
        //has no right child
        else if (!hasRightChild(key)) {
            if (curr == root) {
                root = curr.left;
            } else if (isLeft) {
                parent.left = curr.left;
            } else {
                parent.right = curr.left;
            }
        }
        //has two children
        else {
            Node<K, V> replace = findReplacement(curr);
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
        return curr.getValue();
    }

    /**
     * Find the Node which will replace the one we are removing to restructure the Tree
     *
     * @param node the node to find a replacement for
     * @return the replacement Node
     */
    public Node<K, V> findReplacement (Node<K, V> node) {
        Node<K, V> replace = node.right;
        Node<K, V> parent = node;

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

    public void inOrderDisplay (Node<K, V> root) {
        if (root != null) {
            inOrderDisplay(root.left);
            nodes.add(root);
            inOrderDisplay(root.right);
        }
    }

    public void preOrderDisplay (Node<K, V> root) {
        if (root != null) {
            nodes.add(root);
            preOrderDisplay(root.left);
            preOrderDisplay(root.right);
        }
    }

    /**
     * Reset, empty the TreeMap
     */
    @Override
    public void clear () {
        size = 0;
        root = null;
    }

    /**
     * Provides an EList view of the entries of the current TreeMap
     *
     * @return EList view of the entries of the current TreeMap
     */
    @Override
    public EList<Entry<K, V>> entrySet () {
        inOrderDisplay(root);
        return nodes;
    }

    /**
     * Provides an EList view of the values of the current TreeMap
     *
     * @return EList view of the values of the current TreeMap
     */
    @Override
    public EList<V> values () {
        inOrderDisplay(root);
        EList<V> values = new EArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            values.add(nodes.get(i).getValue());
        }
        return values;
    }

    /**
     * Find the maximum value in the TreeMap
     *
     * @return the max value
     */
    public Node<K, V> max () {
        Node<K, V> curr = root;
        if (isEmpty())
            return curr;
        else {
            for (; curr.right != null; curr = curr.right) ;
            return curr;
        }
    }

    /**
     * Find the minimum value in the TreeMap
     *
     * @return the min value
     */
    public Node<K, V> min () {
        Node<K, V> curr = root;
        if (isEmpty())
            return curr;
        else {
            for (; curr.left != null; curr = curr.left) ;
            return curr;
        }
    }

    /**
     * Provides an EList view of the keys of the current TreeMap
     *
     * @return EList view of the keys of the current TreeMap
     */
    @Override
    public EList<K> keySet () {
        inOrderDisplay(root);
        EList<K> keys = new EArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            keys.add(nodes.get(i).getKey());
        }
        return keys;
    }

    /**
     * Perform an iterative breadth-First traversal of this TreeMap
     */
    public void breadthFirst () {
        EQueue<Node<K, V>> queue = new ArrayQueue<>();
        Node<K, V> curr = root;

        if (curr == null)
            return;
        else
            queue.add(curr);

        while (!queue.isEmpty()) {
            Node<K, V> node = queue.remove();

            System.out.println(node);
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
    }

    /**
     * Perform an iterative depth-First traversal of this TreeMap
     */
    public void depthFirst () {
        EStack<Node<K, V>> stack = new ArrayStack<>();
        Node<K, V> curr = root;

        if (curr == null)
            return;
        else
            stack.add(curr);

        while (!stack.isEmpty()) {
            Node<K, V> node = stack.pop();

            System.out.println(node);
            if (node.right != null)
                stack.add(node.right);
            if (node.left != null)
                stack.add(node.left);
        }
    }

    /**
     * Print only the Nodes which have no children
     */
    public void printLeafNodes () {
        EQueue<Node<K, V>> queue = new ArrayQueue<>();
        Node<K, V> curr = root;
        int width = 0;

        if (curr == null)
            return;
        else {
            queue.add(curr);
        }

        while (!queue.isEmpty()) {
            Node<K, V> node = queue.remove();

            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);

            //no children, print
            if (node.left == null && node.right == null)
                System.out.println(node);
        }
    }

    /**
     * Get the max depth of this TreeMap
     *
     * @return the maximum number of depths/levels to this Tree
     */
    public int depth () {
        return depth(root);
    }

    private int depth (Node<K, V> root) {
        if (root == null)
            return 0;
        else
            return 1 + Math.max(depth(root.left), depth(root.right));
    }

    /**
     * Pass the current mappings off to an array
     *
     * @return a new array based on the current key-value mappings
     */
    @Override
    public Object[] toArray () {
        inOrderDisplay(root);
        return nodes.toArray();
    }

    private static class Node<K, V> implements Entry<K, V> {
        K key;
        V value;
        Node<K, V> left, right, parent;

        private Node (Node<K, V> left, K key, V value, Node<K, V> right, Node<K, V> parent) {
            this.left = left;
            this.key = key;
            this.value = value;
            this.right = right;
            this.parent = parent;
        }

        /**
         * Get the key for the current ADT.IMap Entry
         *
         * @return the key for the current ADT.IMap Entry
         */
        @Override
        public K getKey () {
            return key;
        }

        /**
         * Get the value for the current ADT.IMap Entry
         *
         * @return the value for the current ADT.IMap Entry
         */
        @Override
        public V getValue () {
            return value;
        }

        @Override
        public void setValue (V value) {
            this.value = value;
        }

        @Override
        public boolean equals (Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<K, V> node = (Node<K, V>) o;

            if (parent != null ? !parent.equals(node.parent) : node.parent != null) return false;
            if (value != null ? !value.equals(node.value) : node.value != null) return false;

            return true;
        }

        @Override
        public int hashCode () {
            int result = value != null ? value.hashCode() : 0;
            result = 31 * result + (parent != null ? parent.hashCode() : 0);
            return result;
        }

        @Override
        public String toString () {
            return String.format("%s=%s", this.key, this.value);
        }
    }

    /**
     * Produce an array from this Tree in a Breadth-first order
     *
     * @return a breadth-first ordered array
     */
    private Node<K, V>[] toArrayBFS () {
        Node<K, V>[] out = new Node[size()];
        int index = 0;

        EQueue<Node<K, V>> queue = new ArrayQueue<>();
        Node<K, V> curr = root;

        if (curr == null)
            return null;
        else
            queue.add(curr);

        while (!queue.isEmpty()) {
            Node<K, V> node = queue.remove();

            out[index++] = node;

            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }

        return out;
    }

    /**
     * Find this key within the current Tree
     *
     * @param key the key to search for
     * @return the Node which encapsulates this key
     */
    public Node<K, V> find (K key) {
        Node<K, V> curr = root;
        if (isEmpty() || !containsKey(key))
            throw new NoSuchElementException("Key not found");
        while (curr.key != key) {
            if (((Comparable) key).compareTo(curr.getKey()) < 0)
                curr = curr.left;
            else
                curr = curr.right;
        }
        return curr;
    }

    /**
     * Determine if the Node which holds this key has a left child
     *
     * @param key the key to search for
     * @return true, if this Node is a left child
     */
    public boolean isLeftChild (K key) {
        Node<K, V> check = find(key);
        if (root == check)
            return false;
        else
            return check.parent.left == check;
    }

    /**
     * Determine if the Node which holds this key has a right child
     *
     * @param key the key to search for
     * @return true, if this Node is a right child
     */
    public boolean isRightChild (K key) {
        Node<K, V> check = find(key);
        if (root == check)
            return false;
        else
            return check.parent.right == check;
    }

    /**
     * Determine if the Node which holds this key has a left child
     *
     * @param key the key to search for
     * @return true, if it has a left child
     */
    public boolean hasLeftChild (K key) {
        Node<K, V> check = find(key);
        return check.left != null;
    }

    /**
     * Determine if the Node which holds this key has a right child
     *
     * @param key the key to search for
     * @return true, if it has a right child
     */
    public boolean hasRightChild (K key) {
        Node<K, V> check = find(key);
        return check.right != null;
    }

    /**
     * Determine if Node holding this key has two children nodes( left & right )
     *
     * @param key the key to search for
     * @return true, if it has two children
     */
    public boolean hasTwoChildren (K key) {
        Node<K, V> check = find(key);
        return check.left != null && check.right != null;
    }

    /**
     * Determine if Node holding this key has no children nodes( left & right )
     *
     * @param key the key to search for
     * @return true, if it has no children
     */
    public boolean hasNoChildren (K key) {
        Node<K, V> check = find(key);
        return check.left == null && check.right == null;
    }

    /**
     * Get the parent Node associated with this key
     *
     * @param key the key to search for
     * @return the parent of this Node
     */
    public Node<K, V> getParent (K key) {
        Node<K, V> check = find(key);
        if (check == root)
            return root;
        return check.parent;
    }
}
