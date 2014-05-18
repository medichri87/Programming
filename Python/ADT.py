__author__ = 'cj'

"""
This file contains personal implementations of common data structures such as:
1. Stack
2. Queue
3. Single-Linked List
4. Doubly-Linked List
5. Stack implemented as #4
6. Queue implemented as #4
7. Heap
8. Tree
"""


class Node:
    """Class represents the individual Nodes that make up a Binary Search Tree or Linked Lists"""

    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None
        self.previous = None
        self.next = None

    def __str__(self):
        return str(self.val)

    def set_val(self, val):
        self.val = val

    def get_val(self):
        return self.val


class SLinkedList:
    """Represents a Singly-Linked, Double-Ended Linked List
    Allows for optional input types as a starting point, which are
    pre-inserted into the list.

    Optional argument 'insert_first' leaves it up to the user whether
    they want to insert each value in 'data' will be inserted at the end
    or at the front of the list"""

    def __init__(self, data=[] or () or "", insert_first=True):

        self.first = Node(None)
        self.last = Node(None)
        self.size = 0

        if data is not None:
            for each in data:
                if insert_first is False:
                    self.insert_last(each)
                else:
                    self.insert_first(each)

    def get_first(self):
        return self.first

    def display(self):
        start = self.first
        while start is not None:
            print(start)
            start = start.next

    def is_empty(self):
        return self.size is 0

    def insert_first(self, val):
        insert = Node(val)

        if self.is_empty():
            self.last = insert
        else:
            insert.next = self.first
        self.first = insert
        self.size += 1

    def insert_last(self, val):
        insert = Node(val)
        if self.is_empty():
            self.first = insert
        else:
            insert.next = None
            self.last.next = insert
        self.last = insert
        self.size += 1

    def get_size(self):
        return self.size

    def get_last(self):
        return self.last

    def contains(self, val):
        if self.is_empty():
            return False
        else:
            start = self.first
            while start is not None:
                if start.val is val:
                    return True
                start = start.next
            return False

    def get(self, index):
        """Returns the Node at this index within the current List (recursive)"""
        #error case
        if index > self.size:
            raise IndexError("Index out of bounds")
        if index is 0:
            return self.first
        else:
            return self.get(index - 1).next

    def insert_after(self, find, val):
        insert = Node(val)
        start = self.get_first()
        while start.val is not find:
            start = start.next
            if start is None:
                return False
        if start.val == self.first.val:
            if self.first.next is None:
                self.last = insert
            insert.next = self.first.next
            self.first.next = insert
        elif start.val == self.last.val:
            insert.next = None
            self.last.next = insert
            self.last = insert
        else:
            insert.next = start.next
            start.next = insert
        self.size += 1
        return True

    def find(self, val):
        start = self.first
        while start.val is not val:
            start = start.next
            if start is None:
                return None
        return start

    def remove(self, val):
        start = self.get_first()
        runner = self.get_first()

        if self.contains(val) is False:
            return False
        while start.val is not val:
            runner = start
            start = start.next
        if start.val == self.first.val:
            if self.first.next is None:
                self.last = None
                self.first = None
                return None
            self.first = self.first.next
        elif start.val == self.last.val:
            runner.next = None
            self.last = runner
        else:
            runner.next = start.next
        self.size -= 1

    def remove_first(self):
        return self.remove(self.get_first().val)

    def remove_last(self):
        return self.remove(self.get_last().val)


class DLinkedList:
    """Represents a Double-Linked, Double-Ended Linked List
    Allows for optional input types as a starting point, which are
    pre-inserted into the list.

    Optional argument 'insert_first' leaves it up to the user whether
    they want to insert each value in 'data' will be inserted at the end
    or at the front of the list"""

    def __init__(self, data=[] or () or "", insert_first=True):
        self.first = None
        self.last = None
        self.size = 0

        if data is not None:
            for each in data:
                if insert_first is False:
                    self.insert_last(each)
                else:
                    self.insert_first(each)

    def is_empty(self) -> bool:
        return self.first is None

    def get_size(self):
        return self.size

    def display(self):
        start = self.first
        while start is not None:
            print(start)
            start = start.next

    def insert_first(self, val):
        node = Node(val)
        self.size += 1
        if self.is_empty():
            self.last = node
        else:
            node.previous = None
            node.next = self.first
            self.first.previous = node
        self.first = node

    def insert_last(self, val):
        node = Node(val)
        self.size += 1
        if self.is_empty():
            self.first = node
        else:
            node.next = None
            node.previous = self.last
            self.last.next = node
        self.last = node

    def insert_after(self, find, val):
        current = self.first
        while current.val is not find:
            current = current.next
            if current is None:
                return
        node = Node(val)
        self.size += 1
        if current == self.first:
            if self.first.next is None:
                self.last = node
            else:
                self.first.next.previous = node
            node.next = self.first.next
            node.previous = self.first
            self.first.next = node
        elif current == self.last:
            node.next = None
            node.previous = self.last
            self.last.next = node
            self.last = node
        else:
            node.next = current.next
            node.previous = current
            current.next.previous = node
            current.next = node

    def max(self):
        if self.is_empty():
            return None
        else:
            start = self.first
            max = start.val
            while start is not None:
                if start.val > max:
                    max = start.val
                start = start.next
            return max

    def min(self):
        if self.is_empty():
            return None
        else:
            start = self.first
            min = start.val
            while start is not None:
                if start.val < min:
                    min = start.val
                start = start.next
            return min

    def remove(self, val):
        start = self.first
        while start.val is not val:
            start = start.next
            if start is None:
                return
        self.size -= 1
        #remove 'start'
        if start == self.first:
            if self.first.next is None:
                self.first = None
                self.last = None
                return None
            self.first.previous = None
            self.first = self.first.next
        elif start == self.last:
            start.previous.next = None
            self.last = self.last.previous
        else:
            start.previous.next = start.next
            start.next.previous = start.previous

    def remove_first(self):
        return self.remove(self.first.val)

    def remove_last(self):
        return self.remove(self.last.val)

    def get(self, index):
        if index is 0:
            return self.first
        else:
            return self.get(index - 1).next

    def contains(self, val):
        start = self.first
        while start.val is not val:
            start = start.next
            if start is None:
                return False
        return True

    def mid(self):
        current = self.first
        previous = self.first
        index = 0

        while current is not None:
            if index % 2 != 0:
                previous = previous.next
            current = current.next
            index += 1
        return previous

    def nth_last(self, n):
        if n == 0:
            return self.last
        else:
            return self.nth_last(n - 1).previous

            #ALTERNATE to above
            #def n_last(self, n):
            #prev = self.first
            # curr = self.first

            #while n >= 0:
            #  curr = curr.next
            #  n -= 1

            #while curr is not None:
            #   curr = curr.next
            #  prev = prev.next

            # return prev

    def get_first(self):
        return self.first

    def get_last(self):
        return self.last


class Tree:
    """Represents a Binary search tree that provides insertion,
    removal, utility, and search methods in O(n lg n)

    Allows for pre-insertion based on optional input of a string , list, or tuple"""

    #CONSTANTS FOR TRAVERSAL CHOICE
    PRE, IN, POST = (1, 2, 3)

    def __init__(self, data="" or [] or () or {}):
        self.root = None
        self.size = 0

        if data is not None:
            if type(data) is dict:
                for k, v in data.items():
                    self.insert(str(k) + ":" + str(v))
            else:
                for each in data:
                    self.insert(each)

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
        start = self.root
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


class Queue:
    def __init__(self):
        self.size = 0
        self.array = []

    def is_empty(self):
        return self.size == 0

    def insert(self, val):
        self.array.append(val)
        self.size += 1

    def remove(self):
        if self.is_empty():
            return
        else:
            self.array.pop(0)
            self.size -= 1

    def peek(self):
        if self.is_empty():
            return None
        return self.array[0]

    def display(self):
        print(self.array)

    def size(self):
        return self.size


class Stack:
    def __init__(self, entry=[]):
        self.size = 0
        self.array = []

        if entry is not None:
            self.size = entry.__len__()
            self.array = entry.copy()

    def is_empty(self):
        return self.size == 0

    def peek(self):
        if self.is_empty():
            return None
        return self.array[self.size - 1]

    def push(self, val):
        self.array.append(val)
        self.size += 1

    def pop(self):
        if self.is_empty():
            return None
        self.size -= 1
        return self.array.pop(self.size)

    def display(self):
        print(self.array)


class LinkedStack:
    """Represents a Doubly-Linked List representation of a Stack"""

    def __init__(self):
        self.list = DLinkedList()

    def is_empty(self):
        return self.list.is_empty()

    def size(self):
        return self.list.get_size()

    def push(self, val):
        """Insert this value to the front of this Stack"""
        self.list.insert_first(val)

    def pop(self):
        """Remove the first value in the Stack (top)"""
        return self.list.remove_first()

    def display(self):
        self.list.display()


class LinkedQueue:
    """Represents a Doubly-Linked List representation of a Queue"""

    def __init__(self):
        self.list = DLinkedList()

    def is_empty(self):
        return self.list.is_empty()

    def size(self):
        return self.list.get_size()

    def enqueue(self, val):
        """Insert this value into the queue(at the rear/ end)"""
        self.list.insert_last(val)

    def dequeue(self):
        """Remove the first(front) value in the queue"""
        return self.list.remove_first()

    def display(self):
        self.list.display()


class Heap:
    def __init__(self):
        self.size = 0
        self.array = []

    def is_empty(self):
        return self.size is 0

    def display(self):
        for each in self.array:
            print(each)
        print()

    def change(self, index, val):
        if index < 0 or index >= self.size:
            return False

        self.array[index].set_val(val)

        if self.array[index].val < val:
            self.trickleUp(index)
        else:
            self.trickleDown(index)
        return True

    def insert(self, val):
        node = Node(val)
        self.array.append(node)
        self.trickleUp(self.size)
        self.size += 1

    def trickleUp(self, index):
        parent = int((index - 1) / 2)
        bottom = self.array[index]
        while index > 0 and self.array[parent].val < bottom.val:
            self.array[index] = self.array[parent]
            index = parent
            parent = int((parent - 1) / 2)
        self.array[index] = bottom

    def remove(self):
        if self.is_empty():
            return None
        root = self.array[0]
        self.size -= 1
        self.array[0].set_val(self.array[self.size])
        self.trickleDown(0)
        return root

    def trickleDown(self, index):
        largerChild = 0
        top = self.array[index]

        while index < int(self.size / 2):
            leftChild = int(index * 2 + 1)
            rightChild = int(leftChild + 1)

            if rightChild < self.size and self.array[leftChild].val < self.array[rightChild].val:
                largerChild = rightChild
            else:
                largerChild = leftChild

            if top.val >= self.array[largerChild].val:
                break

            self.array[index] = self.array[largerChild]
            index = largerChild

        self.array[index] = top





