import java.util.ArrayList;

// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add additional methods and fields)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

/**
 * BST Implementation
 * @author drewu
 *
 */
public class BinaryST
{
	/**
	 * Root node of BST
	 */
	private Node root;
	
	/**
	 * List to hold in order traversal
	 */
	private ArrayList<Node> ioTraversal;
	
	/**
	 * List to hold pre order traversal
	 */
	private ArrayList<Node> poTraversal;
	
	/**
	 * Default Constructor for BST
	 */
	public BinaryST()
	{
		this(null);
	}
	
	/**
	 * Constructor for BST with assigned String array
	 * @param s
	 */
	public BinaryST(String[] s)
	{
		if(s == null) return;
		for(int i = 0; i < s.length; i++)
		{
			add(s[i]);
		}
	}
	
	/**
	 * Reports distinct size of current tree
	 * Excludes repetitions
	 * @return
	 * 	Distinct size of BST
	 */
	public int distinctSize()
	{
		return root.distinctCount();
	}
	
	/**
	 * Reports size of current Tree
	 * includes repetitions
	 * @return
	 * 	Size of BST
	 */
	public int size()
	{
		return root.count();
	}
	
	/**
	 * Reports height of current Tree
	 * @return
	 * 	Height of tree
	 */
	public int height()
	{
		return heightRec(root);
	}
	
	/**
	 * Helper method for height
	 * @param n
	 * @return
	 * 	Height of subtree
	 */
	private int heightRec(Node n)
	{
		if(n == null)
		{
			return 0;
		}
		int left = heightRec((n.leftChild != null) ? n.leftChild : null);
		int right = heightRec((n.rightChild != null) ? n.rightChild : null);
		return Math.max(left, right) + 1;
	}
	
	/**
	 * Add s to our BST, increment occurrences of node if it already exists
	 * @param s
	 */
	public void add(String s)
	{
		// Establish Root node
		if(root == null)
		{
			root = new Node(s);
		}
		// Add the node in the necessary spot
		else
		{
			Node current = root;
			while(current != null)
			{
				int comp = s.compareTo(current.data);
				
				// Node already exists, add to number of occurrences
				if(comp == 0)
				{
					current.occurrences++;
					break;
				}
				else
				{
					// Node goes to left subtree
					if(comp < 0)
					{
						if(current.leftChild != null)
						{
							current = current.leftChild;
						}
						else
						{
							current.leftChild = new Node(s);
							current.leftChild.parent = current;
							break;
						}
					}
					// Node goes to right subtree
					else 
					{
						if(current.rightChild != null)
						{
							current = current.rightChild;
						}
						else
						{
							current.rightChild = new Node(s);
							current.rightChild.parent = current;
							break;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Finds if s is contained within our BST
	 * @param s
	 * @return
	 *  Whether or not s is found
	 */
	public boolean search(String s)
	{
		return retrieveNode(s) != null;
	}
	
	/**
	 * Finds and reports the number of times the node is repeated
	 * @param s
	 * @return
	 * 	Number of occurrences of s
	 */
	public int frequency(String s)
	{
		Node n = retrieveNode(s);
		return (n != null) ? n.occurrences : 0;
	}
	
	/**
	 * Removes node from tree, if it exists
	 * @param s
	 * @return
	 * 	Whether or not the node existed and was removed
	 */
	public boolean remove(String s)
	{
		Node n = retrieveNode(s);
		if(n == null)
		{
			return false;
		}
		else
		{
			// Node has repeat occurrences, just decrement
			if(n.occurrences > 1)
			{
				n.occurrences--;
			}
			else
			{
				// Node has no children, just remove
				if(n.leftChild == null && n.rightChild == null)
				{
					unlink(n);
				}
				// Node has right child, find successor
				else if(n.rightChild != null)
				{
					Node successor = n.successor();
					if(successor.rightChild != null)
					{
						successor.rightChild.parent = successor.parent;
						successor.parent.rightChild = successor.rightChild;
					}
					else
					{
						unlink(successor);
					}
					n.data = successor.data;
				}
				// Node has no right child, find predecessor
				else if(n.leftChild != null)
				{
					Node predecessor = n.predecessor();
					if(predecessor.leftChild != null)
					{
						predecessor.leftChild.parent = predecessor.parent;
						predecessor.parent.leftChild = predecessor.leftChild;
					}
					else
					{
						unlink(predecessor);
					}
					n.data = predecessor.data;
				}
			}
			return true;
		}
	}
	
	/**
	 * Helper method to unlink a node from its parent
	 * @param n
	 */
	private void unlink(Node n)
	{
		if(n.parent.rightChild != null)
		{
			if(n.parent.rightChild.equals(n))
				n.parent.rightChild = null;
		}
		else
		{
			n.parent.leftChild = null;
		}
	}
	
	/**
	 * In order traversal of tree
	 * @return
	 * 	String array listing in order traversal
	 */
	public String[] inOrder()
	{
		ioTraversal = new ArrayList<Node>();
		inOrderRec(root);
		return toStringArray(ioTraversal);
	}
	
	/**
	 * Recursive helper method for ioTraversal
	 * @param n
	 * @return
	 * 	String array listing in order traversal
	 */
	private void inOrderRec(Node n)
	{
		if(n != null)
		{
			inOrderRec(n.leftChild);
			ioTraversal.add(n);
			inOrderRec(n.rightChild);
		}
	}
	
	/**
	 * Pre order traversal of tree
	 * @return
	 * 	String array listing pre order traversal
	 */
	public String[] preOrder()
	{
		poTraversal = new ArrayList<Node>();
		preOrderRec(root);
		return toStringArray(poTraversal);
	}
	
	/**
	 * Pre order recursive helper method
	 * @param n
	 * @return
	 * 	String array listing pre order traversal
	 */
	private void preOrderRec(Node n)
	{
		if(n != null)
		{
			poTraversal.add(n);
			preOrderRec(n.leftChild);
			preOrderRec(n.rightChild);
		}
	}
	
	private String[] toStringArray(ArrayList<Node> list)
	{
		String[] arr = new String[list.size()];
		for(int i = 0; i < arr.length; i++)
		{
			arr[i] = list.get(i).data;
		}
		return arr;
	}
	
	/**
	 * Report the rank of the node
	 * @param s
	 * @return
	 * 	Number of nodes less than s
	 */
	public int rankOf(String s)
	{
		return rank(s, root);	
	}
	
	/**
	 * Recursive helper method for finding rank of specified node
	 * @param s
	 * @param n
	 * @return
	 * 	Number of nodes strictly smaller than current node
	 */
	private int rank(String s, Node n)
	{
		if(n == null) return -1;
		int comp = s.compareTo(n.data);
		if(comp < 0 && n.leftChild != null)
		{
			return rank(s, n.leftChild);
		}
		else if (comp > 0 && n.rightChild != null) 
		{
			return n.occurrences + n.leftChild.count() + rank(s, n.rightChild);
		}
		else
		{
			return (n.leftChild != null) ? n.leftChild.count() : 0;
		}
	}
	
	/**
	 * Helper method to find and return specified node
	 * @param s
	 * @return
	 * 	Node inside the tree, if it exists
	 */
	private Node retrieveNode(String s)
	{
		Node current = root;
		while(current != null)
		{
			int comp = s.compareTo(current.data);
			if(comp == 0)
			{
				return current;
			}
			else
			{
				current = (comp < 0) ? current.leftChild : current.rightChild;
			}
		}
		
		return null;
	}
	
	/**
	 * Class to assist in BST implementation
	 * 
	 * @author drewu
	 *
	 */
	@SuppressWarnings("unused")
	final class Node 
	{
		
		/**
		 * Current node's String
		 */
		private String data;
		
		/**
		 * Parent node of current node
		 */
		private Node parent;
		
		/**
		 * Left Child of current node
		 */
		private Node leftChild;

		/**
		 * Right Child of current node
		 */
		private Node rightChild;

		/**
		 * Number of children of the current node
		 */
		private int count;

		/**
		 * Number of distinct children of the current node
		 */
		private int distinctCount;
		
		/**
		 * Number of times this Node is repeated
		 */
		private int occurrences;

		/**
		 * Construct Node with specified String
		 * 
		 * @param data
		 */
		Node(String data) {
			this.data = data;
			distinctCount = 1;
			count = 1;
			occurrences = 1;
		}

		/**
		 * Get the total Nodes attached to the current Node
		 * 
		 * @return Total Nodes inclusive
		 */
		public int count() {
			int lCount = (leftChild != null) ? leftChild.count() : 0;
			int rCount = (rightChild != null) ? rightChild.count() : 0;
			return lCount + rCount + occurrences;
		}

		/**
		 * Get the total Nodes attached to the current Node Excludes repetitions
		 * 
		 * @return 
		 * 	Total Nodes without repeats
		 */
		public int distinctCount() {
			int lDistinctCount = (leftChild != null) ? leftChild.distinctCount() : 0;
			int rDistinctCount = (rightChild != null) ? rightChild.distinctCount() : 0;
			return lDistinctCount + rDistinctCount + 1;
		}
		
		/**
		 * Method for finding successor to current node
		 * @return
		 * 	Successor to this node
		 */
		public Node successor()
		{
			Node successor = rightChild;
			while(successor != null)
			{
				if(successor.leftChild == null)
				{
					break;
				}
				successor = successor.leftChild;
			}
			return successor;
		}	
		
		/**
		 * Method for finding predecessor to current node
		 * @return
		 * 	Predecessor to this node
		 */
		public Node predecessor()
		{
			Node predecessor = leftChild;
			while(predecessor != null)
			{
				if(predecessor.rightChild == null)
				{
					break;
				}
				predecessor = predecessor.rightChild;
			}
			return predecessor;
		}
	}
}