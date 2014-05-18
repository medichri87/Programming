__author__ = 'Chris Medina'
"""
Author: CJ Medina
Date: 2-24-2014
Purpose: Represent a Binary Search Tree in Python
"""


class Node:
    """Class represents the individual Nodes that make up a Binary Search Tree"""

    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None

    def __str__(self):
        return str(self.val)


class Tree:
    """Represents a Binary search tree that provides insertion,
    removal, utility, and search methods in O(n lg n)"""

    #CONSTANTS FOR TRAVERSAL CHOICE
    PRE, IN, POST = (1, 2, 3)

    def __init__(self):
        self.root = None
        self.size = 0

    def is_empty(self) -> bool:
        return self.root is None

    def contains(self, val):
        """Determine if this value is found within the current Tree"""
        start = self.root
        while start is not None:
            if start.val == val:
                return True
            if val < start.val:
                start = start.left
            else:
                start = start.right
        return False

    def insert(self, val):
        """Insert a value into the current Tree"""
        if self.is_empty():
            self.root = Node(val)
        else:
            parent = self.root
            is_left = None
            current = self.root
            while current is not None:
                parent = current
                if val < current.val:
                    is_left = True
                    current = current.left
                else:
                    current = current.right
                    is_left = False
            if is_left:
                parent.left = Node(val)
            else:
                parent.right = Node(val)
        self.size += 1

    def remove(self, val):
        """Remove a value from the current Tree.
        Returns true, if found and removed"""
        if self.contains(val) is False:
            return False
        else:
            self.size -= 1
            parent = self.get_parent(val)
            current = self.find(val)
            is_left = (parent.left == current)

            if current.left is None and current.right is None:
                if current == self.root:
                    self.root = None
                elif is_left:
                    parent.left = None
                else:
                    parent.right = None
            elif current.left is None:
                if current == self.root:
                    self.root = self.root.right
                elif is_left:
                    parent.left = current.right
                else:
                    parent.right = current.right
            elif current.right is None:
                if current == self.root:
                    self.root = self.root.left
                elif is_left:
                    parent.left = current.left
                else:
                    parent.right = current.left
            else:
                replace = self.find_replacement(val)
                if current == self.root:
                    self.root = replace
                elif is_left:
                    parent.left = replace
                else:
                    parent.right = replace
                replace.left = current.left
            return True

    def max_depth(self, node):
        """Determine the number of levels/depths that are found in the
        current tree. The count is from root --> bottom of tree"""
        if node is not None:
            return 1 + max(self.max_depth(node.left), self.max_depth(node.right))
        else:
            return 0

    def left_depth(self):
        """Determine the depth of the left half of the Tree"""
        return self.max_depth(self.get_root().left)

    def right_depth(self):
        """Determine the depth of the right half of the Tree"""
        return self.max_depth(self.get_root().right)

    def find_replacement(self, val):
        """Find the node that will replace this value upon removal from the current Tree"""
        curr = self.find(val)
        parent = self.get_parent(val)
        replace = curr

        if self.has_right_child(curr.val):
            replace = replace.right

        while replace.left is not None:
            parent = replace
            replace = replace.left

        if replace is not curr.right:
            parent.left = replace.right
            replace.right = curr.left

        return replace

    def get_size(self):
        return self.size

    def get_root(self):
        return self.root

    def display(self, choice=1):
        """Display the current Tree based on choice.
        1 --- Pre-Order traversal
        2 --- In-Order traversal,
        3 --- Post-Order traversal"""
        if choice == Tree.PRE:
            self.pre_order(self.root)
        elif choice == Tree.IN:
            self.in_order(self.root)
        elif choice == Tree.POST:
            self.post_order(self.root)
        return

    def pre_order(self, root=None):
        if isinstance(root, Node):
            print(root)
            self.pre_order(root.left)
            self.pre_order(root.right)

    def in_order(self, root=None):
        if isinstance(root, Node):
            self.in_order(root.left)
            print(root)
            self.in_order(root.right)

    def post_order(self, root=None):
        if isinstance(root, Node):
            self.post_order(root.left)
            self.post_order(root.right)
            print(root)

    def find(self, val):
        """Returns the node associated with this value.
        If value is not found within the Tree, None will be returned."""
        start = self.root
        while start.val is not val:
            if start is None:
                return None
            if val.__lt__(start.val):
                start = start.left
            else:
                start = start.right
        return start

    def left_child(self, val):
        """Return the Node to the left of this value"""
        if self.contains(val):
            return self.find(val)
        return None

    def right_child(self, val):
        """Return the Node to the right of this value"""
        if self.contains(val):
            return self.find(val)
        return None

    def has_left_child(self, val):
        """Determine if this value has a left child (Node to the left)"""
        if self.contains(val) is False:
            return False
        return self.find(val).left is not None

    def has_right_child(self, val):
        """Determine if this value has a right child (Node to the right)"""
        if self.contains(val) is False:
            return False
        return self.find(val).right is not None

    def max(self):
        """Return the maximum value in this Tree"""
        start = self.get_root()
        while start.right is not None:
            start = start.right
        return start

    def min(self):
        """Return the minimum value in this Tree"""
        start = self.root
        while start.left is not None:
            start = start.left
        return start

    def get_parent(self, val):
        """Return the parent(Node) of this value, or None if not found"""
        if self.contains(val) is False:
            return None

        #root doesnt have a parent
        if val.__eq__(self.root):
            return None

        start = self.root
        parent = self.root
        while start.val is not val:
            parent = start
            if val < start.val:
                start = start.left
            else:
                start = start.right
        return parent

    def is_balanced(self) -> bool:
        """Determine if this tree is balanced. Depth/level count for
        each half (left / right ) must be within one of each
        other to be considered balanced"""
        return abs(self.left_depth() - self.right_depth()) <= 1


