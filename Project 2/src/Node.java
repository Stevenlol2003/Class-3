	/**
	 * This class represents a node holding a single value within a binary tree the
	 * parent, left, and right child references are always maintained.
	 */
	public class Node<T>{
		public T data;
		public Node<T> parent; // null for root node
		public Node<T> leftChild;
		public Node<T> rightChild;
		public int blackHeight;

		public Node(T data) {
			this.data = data;
		}

		/**
		 * @return true when this node has a parent and is the left child of that
		 *         parent, otherwise return false
		 */
		public boolean isLeftChild() {
			return parent != null && parent.leftChild == this;
		}

	}