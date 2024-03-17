import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Stack;

/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm. You can use this class' insert method to build
 * a regular binary search tree, and its toString method to display a level-order
 * traversal of the tree.
 */
public class RedBlackTree<T extends Comparable<T>> {

    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always maintained.
     */
    protected static class Node<T> {
        public T data;
        public int blackHeight = 0; // 0 = red, 1 = black, 2 = double-black
        public Node<T> parent; // null for root node
        public Node<T> leftChild;
        public Node<T> rightChild;
        public Node(T data) { this.data = data; }
        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }

    }

    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After  
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when the newNode and subtree contain
     *      equal data references
     */
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if(root == null) {
            root = newNode; size++;
            root.blackHeight = 1;
            return true; } // add first node to an empty tree
        else{
            boolean returnValue = insertHelper(newNode,root); // recursively insert into subtree
            if (returnValue) size++;
            else throw new IllegalArgumentException(
                    "This RedBlackTree already contains that value.");
            root.blackHeight = 1;
            return returnValue;
        }
    }

    /**
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the 
     *      newNode should be inserted as a descenedent beneath
     * @return true is the value was inserted in subtree, false if not
     */
    private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
        int compare = newNode.data.compareTo(subtree.data);
        // do not allow duplicate values to be stored within this tree
        if(compare == 0) return false;

            // store newNode within left subtree of subtree
        else if(compare < 0) {
            if(subtree.leftChild == null) { // left subtree empty, add here
                subtree.leftChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(subtree.leftChild); // added this
                return true;
                // otherwise continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.leftChild);
        }

        // store newNode within the right subtree of subtree
        else {
            if(subtree.rightChild == null) { // right subtree empty, add here
                subtree.rightChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(subtree.rightChild); // added this
                return true;
                // otherwise continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.rightChild);
        }
    }

    protected void enforceRBTreePropertiesAfterInsert (Node<T> newRedNode) {
        Node<T> parent = newRedNode.parent;
        // TODO
        // if parent is null, reached root
        if (parent == null) {
            newRedNode.blackHeight = 1;
            return;
        }

        // if parent is black it's good
        if (parent.blackHeight == 1  ) {
            return;
        }

        Node<T> grandparent = parent.parent;
        Node<T> uncle;

        // case 1: "opposite sides" parent is red, parent's sibling is black/null
        // right rotation of case 1
        if (parent.blackHeight == 0) {
            if (parent.isLeftChild() && newRedNode.isLeftChild() &&
                    (grandparent.rightChild.blackHeight == 1 || grandparent.rightChild == null)) {
                parent.blackHeight = 1;
                grandparent.blackHeight = 0;
                rotate(parent, grandparent);
                return;
            }
        }

        // left rotation of case 1
        if (parent.blackHeight == 0) {
            if (!parent.isLeftChild() && !newRedNode.isLeftChild() &&
                    (grandparent.leftChild.blackHeight == 1 || grandparent.leftChild == null)) {
                parent.blackHeight = 1;
                grandparent.blackHeight = 0;
                rotate(parent, grandparent);
                return;
            }
        }

        // case 2: "same sides" rotate 2 red nodes then continue like case 1
        // left rotation of 2 red nodes, then right rotation of case 1
        if (parent.blackHeight == 0) {
            if (parent.isLeftChild() && !newRedNode.isLeftChild() &&
                    (grandparent.rightChild.blackHeight == 1 || grandparent.rightChild == null)) {
                newRedNode.blackHeight = 1;
                grandparent.blackHeight = 0;
                rotate(newRedNode,parent);
                rotate(grandparent.leftChild, grandparent);
                newRedNode.blackHeight = 5;
                return;
            }
        }

        // right rotation of 2 red nodes, then left rotation of case 1
        if (parent.blackHeight == 0) {
            if (!parent.isLeftChild() && newRedNode.isLeftChild() &&
                    (grandparent.leftChild.blackHeight == 1 || grandparent.leftChild == null)) {
                newRedNode.blackHeight = 1;
                grandparent.blackHeight = 0;
                rotate(newRedNode, parent);
                rotate(grandparent.rightChild, grandparent);
                return;
            }
        }

        // case 3: parent's sibling is red, recursive if grandparent (parent's parent) is red
        if (parent.blackHeight == 0) {
            // new node inserted on the left side of grandparent
            if (parent.isLeftChild()) {
                grandparent.blackHeight = 0;
                parent.blackHeight = 1;
                grandparent.rightChild.blackHeight = 1;
                enforceRBTreePropertiesAfterInsert(grandparent);
            }

            // new node inserted on the right side of grandparent
            if (!parent.isLeftChild()) {
                grandparent.blackHeight = 0;
                parent.blackHeight = 1;
                grandparent.leftChild.blackHeight = 1;
                enforceRBTreePropertiesAfterInsert(grandparent);
            }
        }
        return;
    }

    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * rightChild of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
        if (!((child.parent == parent && parent.leftChild == child) ||
                (child.parent == parent && parent.rightChild == child))) {
            throw new IllegalArgumentException();
        }

        // right rotation
        if (child.parent == parent && parent.leftChild == child) {
            parent.leftChild = child.rightChild;

            // check if child's right child is null
            if (child.rightChild != null) {
                child.rightChild.parent = parent;
            }
            child.parent = parent.parent;

            // parent is the root of tree
            if (parent.parent == null) {
                root = child;
            }
            // parent is a left child of its parent
            else if (parent == parent.parent.rightChild) {
                parent.parent.rightChild = child;
            }
            // parent is a right child of its parent
            else {
                parent.parent.leftChild = child;
            }
            child.rightChild = parent;
            parent.parent = child;
        }

        // left rotation
        else {
            parent.rightChild = child.leftChild;

            // check if child's left child is null
            if (child.leftChild != null) {
                child.leftChild.parent = parent;
            }
            child.parent = parent.parent;

            // parent is the root of tree
            if (parent.parent == null) {
                root = child;
            }
            // parent is a left child of its parent
            else if (parent == parent.parent.leftChild) {
                parent.parent.leftChild = child;
            }
            // parent is a right child of its parent
            else {
                parent.parent.rightChild = child;
            }
            child.leftChild = parent;
            parent.parent = child;
        }
    }

    /**
     * Get the size of the tree (its number of nodes).
     * @return the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     * @return true of this.size() return 0, false if this.size() > 0
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Checks whether the tree contains the value *data*.
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    public boolean contains(T data) {
        // null references will not be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");
        return this.containsHelper(data, root);
    }

    /**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     * @param data the data value to look for
     * @param subtree the subtree to search through
     * @return true of the value is in the subtree, false if not
     */
    private boolean containsHelper(T data, Node<T> subtree) {
        if (subtree == null) {
            // we are at a null child, value is not in tree
            return false;
        } else {
            int compare = data.compareTo(subtree.data);
            if (compare < 0) {
                // go left in the tree
                return containsHelper(data, subtree.leftChild);
            } else if (compare > 0) {
                // go right in the tree
                return containsHelper(data, subtree.rightChild);
            } else {
                // we found it :)
                return true;
            }
        }
    }


    /**
     * This method performs an inorder traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * Note that this RedBlackTree class implementation of toString generates an
     * inorder traversal. The toString of the Node class class above
     * produces a level order traversal of the nodes / values of the tree.
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
        // generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        sb.append(toInOrderStringHelper("", this.root));
        if (this.root != null) {
            sb.setLength(sb.length() - 2);
        }
        sb.append(" ]");
        return sb.toString();
    }

    private String toInOrderStringHelper(String str, Node<T> node){
        if (node == null) {
            return str;
        }
        str = toInOrderStringHelper(str, node.leftChild);
        str += (node.data.toString() + ", ");
        str = toInOrderStringHelper(str, node.rightChild);
        return str;
    }

    /**
     * This method performs a level order traversal of the tree rooted
     * at the current node. The string representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * Note that the Node's implementation of toString generates a level
     * order traversal. The toString of the RedBlackTree class below
     * produces an inorder traversal of the nodes / values of the tree.
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        String output = "[ ";
        if (this.root != null) {
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this.root);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.leftChild != null) q.add(next.leftChild);
                if(next.rightChild != null) q.add(next.rightChild);
                output += next.data.toString();
                if(!q.isEmpty()) output += ", ";
            }
        }
        return output + " ]";
    }

    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "\nin order: " + this.toInOrderString();
    }

    @Test
    // tests case 1 situation, where grandparent is black, grandparent's left child (the
    // parent of inserted node) is red, grandparent's right child is black, parent's left child
    // is red (inserted node)
    public void test1() {
        // create tree
        RedBlackTree<Integer> tree1 = new RedBlackTree<Integer>();

        // create nodes
        Node<Integer> two = new Node<Integer>(2);
        Node<Integer> three = new Node<Integer>(3);
        Node<Integer> four = new Node<Integer>(4);

        // make the tree using nodes
        tree1.root = three;
        three.leftChild = two;
        two.parent = three;
        three.rightChild = four;
        four.parent = three;

        // set the default colors for nodes
        tree1.root.blackHeight = 1;
        tree1.root.leftChild.blackHeight = 0;
        tree1.root.rightChild.blackHeight = 1;

        // inserts the new node
        tree1.insert(1);

        // System.out.println(tree1.toLevelOrderString());
        String expected = "[ 2, 1, 3, 4 ]";

        // checks if toLevelOrderString is correct and if each node has correct color
        assertTrue(expected.equals(tree1.toLevelOrderString()) &&
                        tree1.root.blackHeight == 1 && tree1.root.leftChild.blackHeight == 0 &&
                        tree1.root.rightChild.blackHeight == 0 &&
                        tree1.root.rightChild.rightChild.blackHeight == 1,
                "something is wrong for test1");
    }


    @Test
    // tests case 2 situation, where grandparent is black, grandparent's left child (the
    // parent of inserted node) is red, grandparent's right child is black, parent's right child
    // is red (inserted node)
    public void test2() {
        // create tree
        RedBlackTree<Integer> tree2 = new RedBlackTree<Integer>();

        // create nodes
        Node<Integer> one = new Node<Integer>(1);
        Node<Integer> three = new Node<Integer>(3);
        Node<Integer> four = new Node<Integer>(4);

        // make the tree using nodes
        tree2.root = three;
        three.leftChild = one;
        one.parent = three;
        three.rightChild = four;
        four.parent = three;

        // set the default colors for nodes
        tree2.root.blackHeight = 1;
        tree2.root.leftChild.blackHeight = 0;
        tree2.root.rightChild.blackHeight = 1;

        // inserts the new node
        tree2.insert(2);

        // System.out.println(tree2.toLevelOrderString());
        String expected = "[ 2, 1, 3, 4 ]";

        // System.out.println(tree2.root.blackHeight); // should be 1
        // System.out.println(tree2.root.rightChild.blackHeight); // should be 0
        // System.out.println(tree2.root.leftChild.blackHeight); // should be 0
        // System.out.println(tree2.root.rightChild.rightChild.blackHeight); // should be 1

        // checks if toLevelOrderString is correct and if each node has correct color
        assertTrue(expected.equals(tree2.toLevelOrderString()) &&
                        tree2.root.blackHeight == 1 && tree2.root.leftChild.blackHeight == 0 &&
                        tree2.root.rightChild.blackHeight == 0 &&
                        tree2.root.rightChild.rightChild.blackHeight == 1,
                "something is wrong for test2");
    }

    @Test
    // tests case 3 situation, where grandparent is black, grandparent's left child (the
    // parent of inserted node) is red, grandparent's right child is red, parent's left child
    // is red (inserted node)
    public void test3() {
        // create tree
        RedBlackTree<Integer> tree3 = new RedBlackTree<Integer>();

        // create nodes
        Node<Integer> two = new Node<Integer>(2);
        Node<Integer> three = new Node<Integer>(3);
        Node<Integer> four = new Node<Integer>(4);

        // make the tree using nodes
        tree3.root = three;
        three.leftChild = two;
        two.parent = three;
        three.rightChild = four;
        four.parent = three;

        // set the default colors for nodes
        tree3.root.blackHeight = 1;
        tree3.root.leftChild.blackHeight = 0;
        tree3.root.rightChild.blackHeight = 0;

        // inserts the new node
        tree3.insert(1);

        // System.out.println(tree3.toLevelOrderString());
        String expected = "[ 3, 2, 4, 1 ]";

        // checks if toLevelOrderString is correct and if each node has correct color, root will
        // be black due to after calling recursion the insert() method forces root to be black
        assertTrue(expected.equals(tree3.toLevelOrderString()) &&
                        tree3.root.blackHeight == 1 && tree3.root.leftChild.blackHeight == 1 &&
                        tree3.root.rightChild.blackHeight == 1 &&
                        tree3.root.leftChild.leftChild.blackHeight == 0,
                "something is wrong for test3");
    }


    /**
     * Main method to run tests. Comment out the lines for each test to run them.
     * Used for non JUnit5 testing
     * @param args
     */
    public static void main(String[] args) {
        // System.out.println("Test 1 passed: " + test1());
        // System.out.println("Test 2 passed: " + test2());
        // System.out.println("Test 3 passed: " + test3());
    }

}
