import java.util.ArrayList;

class FindResultRef
{
	public boolean found;
	public BSTNodeRef node;

	public FindResultRef(boolean found_, BSTNodeRef node_)
	{
		found = found_;
		node = node_;
	}
}

class BSTNodeRef
{
	public BSTNodeRef parent;
	public BSTNodeRef left;
	public BSTNodeRef right;
	
	public boolean isLeftChild;
	public boolean isRightChild;
	
	public String value;
	public int freq;
	public int subtreeHeight;
	public int subtreeSize;
	
	public BSTNodeRef(String value_)
	{
		value = value_;
		freq = 1;
		subtreeHeight = 1;
		subtreeSize = 1;
	}

	public void print()
	{
		System.out.println("================================");
		System.out.println("node " + value + ", freq " + freq);
		System.out.println("parent " + (parent == null ? "NULL" : parent.value));
		System.out.println("left child " + (left == null ? "NULL" : left.value));
		System.out.println("right child " + (right == null ? "NULL" : right.value));
	}
	
}

public class BinarySTRef
{
	// member fields and methods
	
	BSTNodeRef root;

	int size;
	int distinctSize;
	int height;
	
	public BinarySTRef()
	{		
		size = 0;
		distinctSize = 0;
	}
	
	public BinarySTRef(String[] s)
	{
		this();
		
		for(String str : s)
		{
			add(str);
		}
	}
	
	public int distinctSize()
	{
		return distinctSize;
	}
	
	public int size()
	{
		return size;
	}
	
	public int height()
	{
		return height;
	}
	
	// finds the node containing a string or the appropriate location for one
	// assumes root is not null
	// height is the height of the node returned
	FindResultRef find(String s)
	{
		BSTNodeRef prev = root; // hack to stop the compiler from complaining
		BSTNodeRef current = root;
		while(current != null)
		{
			prev = current;
			if(s.compareTo(current.value) == 0)
			{
				return new FindResultRef(true, current);
			}
			else if(s.compareTo(current.value) < 0)
			{
				current = current.left;
			}
			else
			{
				current = current.right;
			}
		}

		return new FindResultRef(false, prev);
	}

	// updates the subtreeHeight and subtreeSize parameters in node and all ancestors
	// really we should be safer here, as root.parent is null
	void update(BSTNodeRef node)
	{
		BSTNodeRef currentNode = node;

		while(true)
		{
			currentNode.subtreeSize = currentNode.freq;
			currentNode.subtreeHeight = 1;

			if(currentNode.left != null)
			{
				currentNode.subtreeSize += currentNode.left.subtreeSize;
				currentNode.subtreeHeight = Math.max(currentNode.subtreeHeight, currentNode.left.subtreeHeight + 1);
			}
			if(currentNode.right != null)
			{
				currentNode.subtreeSize += currentNode.right.subtreeSize;
				currentNode.subtreeHeight = Math.max(currentNode.subtreeHeight, currentNode.right.subtreeHeight + 1);
			}

			if(currentNode == root)  // should be able to rely on pointer equality here
			{
				break;
			}
			
			currentNode = currentNode.parent;
		}

		size = root.subtreeSize;
		height = root.subtreeHeight;
	}

	public void add(String s)
	{		
		if(root == null)
		{
			root = new BSTNodeRef(s);
			size = 1;
			distinctSize = 1;
			update(root);
			return;
		}

		FindResultRef fr = find(s);

		if(fr.found)
		{
			fr.node.freq++;
			fr.node.subtreeSize++;
			update(fr.node);
		}
		else
		{
			BSTNodeRef n = new BSTNodeRef(s);
			n.parent = fr.node;

			if(s.compareTo(fr.node.value) < 0)
			{
				fr.node.left = n;
				n.isLeftChild = true;
			}
			else
			{
				fr.node.right = n;
				n.isRightChild = true;
			}

			update(n);
			distinctSize++;
		}
	}
	
	// this could be O(1) avg. with hashtable implementation
	public boolean search(String s)
	{
		return frequency(s) > 0;
	}
	
	public int frequency(String s)
	{
		FindResultRef fr = find(s);

		if(!fr.found)
		{
			return 0;
		}

		return fr.node.freq;
	}
	
	void swap(BSTNodeRef a, BSTNodeRef b)
	{
		String valueSwap = a.value;
		int freqSwap = a.freq;

		a.value = b.value;
		a.freq = b.freq;
		b.value = valueSwap;
		b.freq = freqSwap;

		update(a); // one of these is uneccesary
		update(b);
	}
	
	void removeNode(BSTNodeRef n)
	{

		// base case
		if(n.left == null && n.right == null)
		{
			if(n.isLeftChild)
			{
				n.parent.left = null;
			}
			if(n.isRightChild)
			{
				n.parent.right = null;
			}

			if(n == root)
			{
				size = 0;
				height = 0;

				root = null;
			}
			else
			{
				update(n.parent);
			}
		}
		// we must swap in a descendant
		else
		{
			BSTNodeRef currentNode;
			if(n.left != null)
			{
				currentNode = n.left;
				while(currentNode.right != null)
				{
					currentNode = currentNode.right;
				}
			}
			else
			{
				currentNode = n.right;
				while(currentNode.left != null)
				{
					currentNode = currentNode.left;
				}
			}

			swap(n, currentNode);
			removeNode(currentNode);
		}
		
		
	}
	
	public boolean remove(String s)
	{
		FindResultRef fr = find(s);

		if(!fr.found)
		{
			return false;
		}

		if(fr.node.freq > 1)
		{
			fr.node.freq--;
			update(fr.node);
		}
		else
		{
			removeNode(fr.node);
			distinctSize--;
		}

		return true;
	}
	
	void inOrderTraverse(BSTNodeRef node, ArrayList<String> list)
	{
		if(node.left != null)
		{
			inOrderTraverse(node.left, list);
		}
		for(int i = 0; i < node.freq; i++)
		{
			list.add(node.value);
		}
		if(node.right != null)
		{
			inOrderTraverse(node.right, list);
		}
	}

	public String[] inOrder()
	{
		if(root == null)
		{
			return new String[0];
		}

		ArrayList<String> list = new ArrayList<String>();
		inOrderTraverse(root, list);
		return list.toArray(new String[list.size()]);
	}

	void preOrderTraverse(BSTNodeRef node, ArrayList<String> list)
	{
		for(int i = 0; i < node.freq; i++)
		{
			list.add(node.value);
		}
		if(node.left != null)
		{
			preOrderTraverse(node.left, list);
		}
		if(node.right != null)
		{
			preOrderTraverse(node.right, list);
		}
	}
	
	public String[] preOrder()
	{
		if(root == null)
		{
			return new String[0];
		}

		ArrayList<String> list = new ArrayList<String>();
		preOrderTraverse(root, list);
		return list.toArray(new String[list.size()]);
	}
	
	public int rankOf(String s)
	{
		if(root == null)
		{
			return -1;
		}

		int numSmaller = 0;
		BSTNodeRef current = root;
		while(current != null)
		{
			if(s.compareTo(current.value) < 0)
			{
				current = current.left;
			}
			else
			{
				if(current.left != null)
				{
					numSmaller += current.left.subtreeSize;
				}

				if(s.compareTo(current.value) == 0)
				{
					return numSmaller;
				}
				else
				{
					numSmaller += current.freq;
					current = current.right;
				}
			}
		}

		return numSmaller;
	}

	void verifyHelper(BSTNodeRef node)
	{
		// parent-child pointer relationships
		// size
		// height
		// distinct
	}

	public void verify()
	{
		verifyHelper(root);
	}


	public void print()
	{
		printRec(root);
	}

	void printRec(BSTNodeRef node)
	{
		if(node == null) return;
		printRec(node.left);
		node.print();
		printRec(node.right);
	} 

	public static void main(String[] args)
	{
		BinarySTRef bst = new BinarySTRef();

		bst.add("a");
		bst.add("d");
		bst.add("g");
		bst.add("h");
		bst.add("s");
		bst.add("w");
		bst.add("g");
		bst.add("t");
		bst.add("h");
		bst.add("j");
		bst.add("u");
		bst.add("j");
		bst.add("k");
		bst.add("v");
		bst.add("s");
		bst.add("z");
		bst.add("f");
		bst.add("g");
		bst.add("j");
		bst.add("k");

		// bst.add("c");
		// bst.add("b");
		// bst.add("d");

		System.out.println("size: " + bst.size);
		System.out.println("distinctSize: " + bst.distinctSize);
		System.out.println("height: " + bst.height);
		System.out.println("frequency(g): " + bst.frequency("g"));
		System.out.println("frequency(s): " + bst.frequency("s"));
		System.out.println("rankOf(w): " + bst.rankOf("w"));
		System.out.println("rankOf(k): " + bst.rankOf("k"));
		System.out.println("rankOf(d): " + bst.rankOf("d"));
		System.out.println("removing g, h, s, s ================");

		bst.remove("g");
		bst.remove("h");
		bst.remove("s");
		bst.remove("s");

		System.out.println("size: " + bst.size);
		System.out.println("distinctSize: " + bst.distinctSize);
		System.out.println("height: " + bst.height);
		System.out.println("frequency(g): " + bst.frequency("g"));
		System.out.println("rankOf(w): " + bst.rankOf("w"));
		System.out.println("rankOf(k): " + bst.rankOf("k"));
		System.out.println("rankOf(s): " + bst.rankOf("s"));


	}

}