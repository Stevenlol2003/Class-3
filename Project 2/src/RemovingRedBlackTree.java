import java.util.LinkedList;

public class RemovingRedBlackTree<T extends Comparable<T>> implements RemovingRedBlackTreeADT<T> {

	protected Node<T> root; // reference to root node of tree, null when empty
	protected int size = 0; // the number of values in the tree

	public RemovingRedBlackTree() {
		this.root = null;
		this.size = 0;
	}
	
	public RemovingRedBlackTree(boolean notplaceHolder) {
		this.root = null;
		this.size = 0;
	}

	@Override
	/**
	 * Performs a naive insertion into a binary search tree: adding the input data
	 * value to a new node in a leaf position within the tree. After this insertion,
	 * no attempt is made to restructure or balance the tree. This tree will not
	 * hold null references, nor duplicate data values.
	 * 
	 * @param data to be added into this binary search tree
	 * @return true if the value was inserted, false if not
	 * @throws NullPointerException     when the provided data argument is null
	 * @throws IllegalArgumentException when the newNode and subtree contain equal
	 *                                  data references
	 */
	public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
		// null references cannot be stored within this tree
		if (data == null)
			throw new NullPointerException("This RedBlackTree cannot store null references.");

		Node<T> newNode = new Node<>(data);
		if (root == null) {
			root = newNode;
			size++;
			root.blackHeight = 1;
			return true;
		} // add first node to an empty tree
		else {
			boolean returnValue = insertHelper(newNode, root); // recursively insert into subtree
			if (returnValue)
				size++;
			else
				throw new IllegalArgumentException(
						"This RedBlackTree already contains that value.");
			root.blackHeight = 1;
			return returnValue;
		}
	}

	/**
	 * Recursive helper method to find the subtree with a null reference in the
	 * position that the newNode should be inserted, and then extend this tree by
	 * the newNode in that position.
	 * 
	 * @param newNode is the new node that is being added to this tree
	 * @param subtree is the reference to a node within this tree which the newNode
	 *                should be inserted as a descenedent beneath
	 * @return true is the value was inserted in subtree, false if not
	 */
	private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
		int compare = newNode.data.compareTo(subtree.data);
		// do not allow duplicate values to be stored within this tree
		if (compare == 0)
			return false;

		// store newNode within left subtree of subtree
		else if (compare < 0) {
			if (subtree.leftChild == null) { // left subtree empty, add here
				subtree.leftChild = newNode;
				newNode.parent = subtree;
				enforceRBTreePropertiesAfterInsert(newNode);
				return true;
				// otherwise continue recursive search for location to insert
			} else
				return insertHelper(newNode, subtree.leftChild);
		}

		// store newNode within the right subtree of subtree
		else {
			if (subtree.rightChild == null) { // right subtree empty, add here
				subtree.rightChild = newNode;
				newNode.parent = subtree;
				enforceRBTreePropertiesAfterInsert(newNode);
				return true;
				// otherwise continue recursive search for location to insert
			} else
				return insertHelper(newNode, subtree.rightChild);
		}
	}

	/**
	 * Performs the rotation operation on the provided nodes within this tree. When
	 * the provided child is a leftChild of the provided parent, this method will
	 * perform a right rotation. When the provided child is a rightChild of the
	 * provided parent, this method will perform a left rotation. When the provided
	 * nodes are not related in one of these ways, this method will throw an
	 * IllegalArgumentException.
	 * 
	 * @param child  is the node being rotated from child to parent position
	 *               (between these two node arguments)
	 * @param parent is the node being rotated from parent to child position
	 *               (between these two node arguments)
	 * @throws IllegalArgumentException when the provided child and parent node
	 *                                  references are not initially (pre-rotation)
	 *                                  related that way
	 */
	private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {

		// When the provided nodes are not related
		// parent does not have any children
		if (parent.leftChild == null && parent.rightChild == null) {
			throw new IllegalArgumentException();
		}

		// parent has only left child
		if (parent.leftChild != null && parent.rightChild == null
				&& !parent.leftChild.equals(child)) {
			throw new IllegalArgumentException();
		}

		// parent has only right child
		if (parent.rightChild != null && parent.leftChild == null
				&& !parent.rightChild.equals(child)) {
			throw new IllegalArgumentException();
		}

		// parent has both children
		if (parent.leftChild != null && parent.rightChild != null && !parent.leftChild.equals(child)
				&& !parent.rightChild.equals(child)) {
			throw new IllegalArgumentException();
		}

		// When the provided child is a leftChild of the provided parent
		if (parent.leftChild != null && parent.leftChild.equals(child)) {

			// get the parent node of parent
			Node<T> parentParent = parent.parent;

			// set parent's left child and new left child's parent
			parent.leftChild = child.rightChild;
			if (child.rightChild != null) {
				child.rightChild.parent = parent;
			}

			// set new child's right child as parent and parent's parent is new child
			child.rightChild = parent;
			parent.parent = child;

			if (parentParent == null) {
				root = child;
			} else if (parentParent.leftChild == parent) {
				parentParent.leftChild = child;
			} else if (parentParent.rightChild == parent) {
				parentParent.rightChild = child;
			}

			if (child != null) {
				child.parent = parentParent;
			}

		}

		// When the provided child is a rightChild of the provided parent
		else if (parent.rightChild != null && parent.rightChild.equals(child)) {

			// get the parent node of parent
			Node<T> parentParent = parent.parent;

			// set parent's left child and new left child's parent
			parent.rightChild = child.leftChild;
			if (child.leftChild != null) {
				child.leftChild.parent = parent;
			}

			// set new child's right child as parent and parent's parent is new child
			child.leftChild = parent;
			parent.parent = child;

			// set up new root or new relationship between parentParent and new child
			if (parentParent == null) {
				root = child;
			} else if (parentParent.leftChild == parent) {
				parentParent.leftChild = child;
			} else if (parentParent.rightChild == parent) {
				parentParent.rightChild = child;
			}

			if (child != null) {
				child.parent = parentParent;
			}

		}

	}

	/**
	 * This method is to resolve any red-black tree property violations that are
	 * introduced by inserting each new new node into a red-black tree.
	 * 
	 * @param newRed a newly added red node
	 */
	protected void enforceRBTreePropertiesAfterInsert(Node<T> newRed) {

		Node<T> parentNewNode = newRed.parent;

		// is parent is null, do not do anything
		if (parentNewNode == null) {
			root.blackHeight = 1;
		}

		// red parent has a red child, fix
		if ((newRed != null && parentNewNode != null) && newRed.blackHeight == 0
				&& parentNewNode.blackHeight == 0) {

			// case1: no sibling
			// rotate the tree around the parent's parent and parent
			if (parentNewNode.parent.rightChild == null || parentNewNode.parent.leftChild == null) {

				// the same side
				if ((!parentNewNode.isLeftChild() && newRed.isLeftChild())
						|| (parentNewNode.isLeftChild() && !newRed.isLeftChild())) {

					// make it the opposite side
					this.rotate(newRed, parentNewNode);

					// rotate
					this.rotate(newRed, newRed.parent);

					// change the color
					newRed.blackHeight = 1;
					newRed.rightChild.blackHeight = 0;
					newRed.leftChild.blackHeight = 0;

					// the opposite side
				} else {

					// rotate
					this.rotate(parentNewNode, parentNewNode.parent);

					// change the color
					parentNewNode.blackHeight = 1;
					parentNewNode.rightChild.blackHeight = 0;
					parentNewNode.leftChild.blackHeight = 0;

				}
			}

			// case2: parents' sibling is black on the opposite side
			// rotate the tree around child and parent
			else if ((parentNewNode.parent.leftChild.blackHeight == 1
					|| parentNewNode.parent.rightChild.blackHeight == 1)
					&& sideDetector(newRed, parentNewNode)) {

				// rotate
				this.rotate(parentNewNode, parentNewNode.parent);

				// change the color
				parentNewNode.blackHeight = 1;
				parentNewNode.rightChild.blackHeight = 0;
				parentNewNode.leftChild.blackHeight = 0;

			}

			// case3: parents' sibling is black on the same side
			else if ((parentNewNode.parent.leftChild.blackHeight == 1
					|| parentNewNode.parent.rightChild.blackHeight == 1)
					&& !sideDetector(newRed, parentNewNode)) {

				// make it the opposite side
				this.rotate(newRed, parentNewNode);

				// rotate
				this.rotate(newRed, newRed.parent);

				// change the color
				newRed.blackHeight = 1;
				newRed.rightChild.blackHeight = 0;
				newRed.leftChild.blackHeight = 0;

			}

			// case4: parents' sibling is red
			// change the color of parents
			else if ((parentNewNode.blackHeight == 0
					&& parentNewNode.parent.leftChild.blackHeight == 0)
					&& (parentNewNode.blackHeight == 0
							&& parentNewNode.parent.rightChild.blackHeight == 0)) {

				// if parent is on the left side
				if (parentNewNode.isLeftChild()) {
					parentNewNode.blackHeight = 1;
					parentNewNode.parent.blackHeight = 0;
					parentNewNode.parent.rightChild.blackHeight = 1;
				} else {// if the parent is on the right side
					parentNewNode.blackHeight = 1;
					parentNewNode.parent.blackHeight = 0;
					parentNewNode.parent.leftChild.blackHeight = 1;
				}

				// check the upper subtree
				enforceRBTreePropertiesAfterInsert(newRed.parent.parent);

			}

		}

	}

	/**
	 * This method is to determine which side parents's sibling is in.
	 * 
	 * @param newRed a newly added red node
	 * @return true is they are on the same side, false if they are on the different
	 *         side
	 */
	public boolean sideDetector(Node<T> newRed, Node<T> parentNewNode) {

		// Case1: parent is on the right
		if (!parentNewNode.isLeftChild()) {

			// child is in the right side: opposite
			if (!newRed.isLeftChild()) {
				return true;
			}

			// child is in the left side: same
			if (newRed.isLeftChild()) {
				return false;
			}
		}

		// Case2: parent is on the left
		else {
			// child is in the right side: same
			if (!newRed.isLeftChild()) {
				return false;
			}

			// child is in the left side: opposite
			if (newRed.isLeftChild()) {
				return true;
			}
		}

		return false; // default

	}

	/**
	 * Get the size of the tree (its number of nodes).
	 * 
	 * @return the number of nodes in the tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Method to check if the tree is empty (does not contain any node).
	 * 
	 * @return true of this.size() return 0, false if this.size() > 0
	 */
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	/**
	 * Checks whether the tree contains the value *data*.
	 * 
	 * @param data the data value to test for
	 * @return true if *data* is in the tree, false if it is not in the tree
	 */
	public boolean contains(T data) {
		// null references will not be stored within this tree
		if (data == null)
			throw new NullPointerException("This RedBlackTree cannot store null references.");
		return this.containsHelper(data, root);
	}

	/**
	 * Recursive helper method that recurses through the tree and looks for the
	 * value *data*.
	 * 
	 * @param data    the data value to look for
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
	 * comma separated string within brackets (similar to many implementations of
	 * java.util.Collection, like java.util.ArrayList, LinkedList, etc). Note that
	 * this RedBlackTree class implementation of toString generates an inorder
	 * traversal. The toString of the Node class class above produces a level order
	 * traversal of the nodes / values of the tree.
	 * 
	 * @return string containing the ordered values of this tree (in-order
	 *         traversal)
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

	private String toInOrderStringHelper(String str, Node<T> node) {
		if (node == null) {
			return str;
		}
		str = toInOrderStringHelper(str, node.leftChild);
		str += (((IGame) (node.data)).getName() + ", ");
		str = toInOrderStringHelper(str, node.rightChild);
		return str;
	}

	/**
	 * This method performs a level order traversal of the tree rooted at the
	 * current node. The string representations of each data value within this tree
	 * are assembled into a comma separated string within brackets (similar to many
	 * implementations of java.util.Collection). Note that the Node's implementation
	 * of toString generates a level order traversal. The toString of the
	 * RedBlackTree class below produces an inorder traversal of the nodes / values
	 * of the tree. This method will be helpful as a helper for the debugging and
	 * testing of your rotation implementation.
	 * 
	 * @return string containing the values of this tree in level order
	 */
	public String toLevelOrderString() {
		String output = "[ ";
		if (this.root != null) {
			LinkedList<Node<T>> q = new LinkedList<>();
			q.add(this.root);
			while (!q.isEmpty()) {
				Node<T> next = q.removeFirst();
				if (next.leftChild != null)
					q.add(next.leftChild);
				if (next.rightChild != null)
					q.add(next.rightChild);
				output += next.data.toString();
				if (!q.isEmpty())
					output += ", ";
			}
		}
		return output + " ]";
	}

	public String toString() {
		return "level order: " + this.toLevelOrderString() + "\nin order: "
				+ this.toInOrderString();
	}



	public T find(String gameName) {
		// null references will not be stored within this tree
		if (gameName == null)
			throw new NullPointerException("This RedBlackTree cannot store null references.");

		//System.out.println(toInOrderString());
		//System.out.println(((IGame) (root.data)).getName());
		return findHelper(gameName, root).data;

	}

	/**
	 * Recursive helper method that recurses through the tree and looks for the
	 * value *data*.
	 * 
	 * @param gameName    the data value to look for
	 * @param subtree the subtree to search through
	 * @return true of the value is in the subtree, false if not
	 */
	private Node<T> findHelper(String gameName, Node<T> subtree) {

		if (subtree == null) {
			//System.out.println("omg");
			// we are at a null child, value is not in tree
			return null;
		} else {
			int compare = gameName.compareTo(((IGame) (subtree.data)).getName());
			if (compare < 0) {
				// go left in the tree
				return findHelper(gameName, subtree.leftChild);
			} else if (compare > 0) {
				// go right in the tree
				return findHelper(gameName, subtree.rightChild);
			} else {
				// we found it :)
				return subtree;
			}
		}

	}

	@Override
	public boolean remove(String name){
		T target = find(name);

		return remove2(target);
	}

	public boolean remove2(T key) {

		// node to be removed
		Node node = root;

		// search node to be removed
		while (node != null && node.data != key) {

			// repeat this until it find the node
			if (key.compareTo((T) node.data) < 0) {

				// found
				node = node.leftChild;

			} else {

				// found
				node = node.rightChild;
			}
		}

		// unsuccessful to find
		if (node == null) {
			return false;
		}

		// node to be up
		Node nodeUp;

		// get the color of node to be removed
		int colorToBeDeleted;

		// Case1) Node has less then 2 child
		if (node.leftChild == null || node.rightChild == null) {

			// remove using case1
			nodeUp = removeLessThanTwoChild(node);
			colorToBeDeleted = node.blackHeight;
		}

		// Case2) Node to be removed has exactly two children
		else {
			// Find minimum node to be replaced
			Node successorNode = findMinimum(node.rightChild);

			// Copy inorder successor's data to current node (keep its color!)
			node.data = successorNode.data;

			// remove successor
			nodeUp = removeLessThanTwoChild(successorNode);

			// get the new color of current node
			colorToBeDeleted = successorNode.blackHeight;
		}

		// If the color is black
		if (colorToBeDeleted == 1) {

			// fix the violation here
			fixTreeRemoveViolation(nodeUp);

			// delete to the original nodes
			if (nodeUp.getClass() == NodeDeleteHelperClass.class) {

				// change structure here
				changeParentChild(nodeUp.parent, nodeUp, null);
			}
		}
		return true;

	}

	private Node removeLessThanTwoChild(Node node) {

		// Case1) left child is there
		if (node.leftChild != null) {

			// fix the tree
			changeParentChild(node.parent, node, node.leftChild);

			return node.leftChild;
		}

		// Case2) right child is there
		else if (node.rightChild != null) {

			// Fix the tree here
			changeParentChild(node.parent, node, node.rightChild);

			return node.rightChild;
		}

		// node color is red, then delete
		// node is black, then use helper class
		else {
			Node newChild = node.blackHeight == 1 ? new NodeDeleteHelperClass() : null;

			// Fix the tree here
			changeParentChild(node.parent, node, newChild);
			return newChild;
		}
	}

	/**
	 * This class helps the delete method
	 * 
	 * @author suzukiyorozurin
	 *
	 */
	private static class NodeDeleteHelperClass extends Node {
		private NodeDeleteHelperClass() {
			
			// recolor
			super(0);
			this.blackHeight = 1;
		}
	}

	/**
	 * Help to find the min node to delete
	 * 
	 * @param node
	 * @return
	 */
	private Node findMinimum(Node node) {
		while (node.leftChild != null) {
			
			// move to left
			node = node.leftChild;
		}
		return node;
	}

	/**
	 * Fix after delete method
	 * 
	 * @param node
	 */
	private void fixTreeRemoveViolation(Node node) {
		
		// Case1) root is the current node
		if (node == root) {
			return;
		}

		// get sibling here
		Node sibling = getSibling(node);

		// Case 2) sibling is red
		if (sibling.blackHeight == 0) {
			
			// call helper and chnage setting
			redSiblingHelper(node, sibling);
			
			// go next level
			sibling = getSibling(node); // Get new sibling for fall-through to cases 3-6
		}

		// Cases3) all the black
		if (sibling.leftChild.blackHeight == 1 && sibling.rightChild.blackHeight == 1) {
			sibling.blackHeight = 0;

			// Case1) red parent
			if (node.parent.blackHeight == 0) {
				
				// re color
				node.parent.blackHeight = 1;
			}

			// Case 2) black parent
			else {
				
				// fix the tree
				fixTreeRemoveViolation(node.parent);
			}
		}

		// Case 4)with red children
		else {
			// call the helper to change the sibling.
			removeHleperWithChildren(node, sibling);
		}
	}

	/**
	 * This method returns the sibling to help to remove method
	 * @param node
	 * @return
	 */
	private Node getSibling(Node node) {
		
		// get the parent
		Node parent = node.parent;
		if (node == parent.leftChild) {
			
			// change the sibling
			return parent.rightChild;
		} else if (node == parent.rightChild) {
			
			// change the sibling
			return parent.leftChild;
		} else {
			throw new IllegalStateException("");
		}
	}

	/**
	 * To help to remove node with red sibling
	 * @param node
	 * @param sibling
	 */
	private void redSiblingHelper(Node node, Node sibling) {
		
		// change the color
		sibling.blackHeight = 1;
		node.parent.blackHeight = 0;

		// fix tree
		if (node == node.parent.leftChild) {
			
			// fix tree
			rotate(node, node.parent); // TODO
		} else {
			rotate(node, node.parent); // TODO
		}
	}
	
	/**
	 * remove method helper to delete node with child
	 * @param node
	 * @param sibling
	 */
	private void removeHleperWithChildren(Node node, Node sibling) {
		boolean nodeIsLeftChild = node == node.parent.leftChild;

		// delete node with child
		//Case1) the color is 1
		if (nodeIsLeftChild && (sibling.rightChild.blackHeight == 1)) {
			
			// setting up
			sibling.leftChild.blackHeight = 1;
			sibling.blackHeight = 0;
			
			// fix tree
			rotateRight(sibling);
			
			// fix tree
			sibling = node.parent.rightChild;
			
		} else if (!nodeIsLeftChild && (sibling.leftChild.blackHeight == 1)) {
			
			//Case2 
			//change the relation
			sibling.rightChild.blackHeight = 1;
			sibling.blackHeight = 0;
			
			// tree fixing
			rotateLeft(sibling);
			sibling = node.parent.leftChild;
		}

		// with red child
		sibling.blackHeight = node.parent.blackHeight;
		node.parent.blackHeight = 1;
		
		// change the child of left
		if (nodeIsLeftChild) {
			
			// change the sibling setting
			sibling.rightChild.blackHeight = 1;
			rotate(node, node.parent); // TODO
		} else {
			
			// change the sibling setting
			sibling.leftChild.blackHeight = 1;
			rotate(node, node.parent); // TODO
		}
	}

	/**
	 * Swap parent and child
	 * @param parent
	 * @param oldChild
	 * @param newChild
	 */
	private void changeParentChild(Node parent, Node oldChild, Node newChild) {
		
		//1)Case 1 parent does not exist
		if (parent == null) {
			root = newChild;
			
		} else if (parent.leftChild == oldChild) {
			
			// leftchild has relation
			parent.leftChild = newChild;
			
		} else if (parent.rightChild == oldChild) {
			
			// right child has relation
			parent.rightChild = newChild;
		} else {
			
			
			throw new IllegalStateException("");
		}

		// if there is no child case3)
		if (newChild != null) {
			newChild.parent = parent;
		}
	}

	/**
	 * Rotate by the right
	 * 
	 * @param node
	 */
	private void rotateLeft(Node node) {

		// change the tree structure
		Node rightChild = node.rightChild;
		Node parent = node.parent;

		// rotate child here
		node.rightChild = rightChild.leftChild;
		if (rightChild.leftChild != null) {

			// the chnage the parent relation
			rightChild.leftChild.parent = node;
		}

		// fix again
		rightChild.leftChild = node;
		node.parent = rightChild;

		// repeat this again
		changeParentChild(parent, node, rightChild);
	}

	/**
	 * Rotate by the left
	 * 
	 * @param node
	 */
	private void rotateRight(Node node) {

		// get the child and parent first
		Node leftChild = node.leftChild;

		Node parent = node.parent;

		// change the structure
		node.leftChild = leftChild.rightChild;

		if (leftChild.rightChild != null) {
			// parent will be changed
			leftChild.rightChild.parent = node;
		}

		// change the tree after fixing
		leftChild.rightChild = node;
		node.parent = leftChild;

		// repeat this process
		changeParentChild(parent, node, leftChild);
	}
}
