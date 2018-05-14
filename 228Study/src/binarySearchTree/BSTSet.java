package binarySearchTree;

import java.util.AbstractSet;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Binary search tree implementation of the Set interface. The contains() and
 * remove() methods of AbstractSet are overridden to search the tree without
 * using the iterator.
 */
public class BSTSet<E extends Comparable<? super E>> extends AbstractSet<E>
{
	protected Node	root;
	protected int	size;

	protected class Node
	{
		public Node	left;
		public Node	right;
		public Node	parent;
		public E	data;

		public Node(E key, Node parent)
		{
			this.data = key;
			this.parent = parent;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object obj)
	{
		// This cast may cause comparator to throw ClassCastException at
		// runtime,
		// which is the expected behavior
		E key = (E)obj;
		return findEntry(key) != null;
	}

	@Override
	public boolean add(E key)
	{
		if(root == null)
		{
			root = new Node(key, null);
			++size;
			return true;
		}

		Node current = root;
		while(true)
		{
			int comp = current.data.compareTo(key);
			if(comp == 0)
			{
				// key is already in the tree
				return false;
			}
			else if(comp > 0)
			{
				if(current.left != null)
				{
					current = current.left;
				}
				else
				{
					current.left = new Node(key, current);
					++size;
					return true;
				}
			}
			else
			{
				if(current.right != null)
				{
					current = current.right;
				}
				else
				{
					current.right = new Node(key, current);
					++size;
					return true;
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object obj)
	{
		// This cast may cause comparator to throw ClassCastException at
		// runtime,
		// which is the expected behavior
		E key = (E)obj;
		Node n = findEntry(key);
		if(n == null)
		{
			return false;
		}
		unlinkNode(n);
		return true;
	}

	/**
	 * Returns the node containing key, or null if the key is not found in the
	 * tree.
	 * 
	 * @param key
	 * @return the node containing key, or null if not found
	 */
	protected Node findEntry(E key)
	{
		Node current = root;
		while(current != null)
		{
			int comp = current.data.compareTo(key);
			if(comp == 0)
			{
				return current;
			}
			else if(comp > 0)
			{
				current = current.left;
			}
			else
			{
				current = current.right;
			}
		}
		return null;
	}

	/**
	 * Returns the successor of the given node.
	 * 
	 * @param n
	 * @return the successor of the given node in this tree, or null if there is
	 *         no successor
	 */
	protected Node successor(Node n)
	{
		if(n == null)
		{
			return null;
		}
		else if(n.right != null)
		{
			// leftmost entry in right subtree
			Node current = n.right;
			while(current.left != null)
			{
				current = current.left;
			}
			return current;
		}
		else
		{
			// we need to go up the tree to the closest ancestor that is
			// a left child; its parent must be the successor
			Node current = n.parent;
			Node child = n;
			while(current != null && current.right == child)
			{
				child = current;
				current = current.parent;
			}
			// either current is null, or child is left child of current
			return current;
		}
	}

	/**
	 * Removes the given node, preserving the binary search tree property of the
	 * tree.
	 * 
	 * @param n
	 *            node to be removed
	 */
	protected void unlinkNode(Node n)
	{
		// first deal with the two-child case; copy
		// data from successor up to n, and then delete successor
		// node instead of given node n
		if(n.left != null && n.right != null)
		{
			Node s = successor(n);
			n.data = s.data;
			n = s; // causes s to be deleted in code below
		}

		// n has at most one child
		Node replacement = null;
		if(n.left != null)
		{
			replacement = n.left;
		}
		else if(n.right != null)
		{
			replacement = n.right;
		}

		// link replacement into tree in place of node n
		// (replacement may be null)
		if(n.parent == null)
		{
			root = replacement;
		}
		else
		{
			if(n == n.parent.left)
			{
				n.parent.left = replacement;
			}
			else
			{
				n.parent.right = replacement;
			}
		}

		if(replacement != null)
		{
			replacement.parent = n.parent;
		}

		--size;
	}

	@Override
	public Iterator<E> iterator()
	{
		return new BSTIterator();
	}

	@Override
	public int size()
	{
		return size;
	}

	/**
	 * Returns a representation of this tree as a multi-line string. The tree is
	 * drawn with the root at the left and children are shown top-to-bottom.
	 * Leaves are marked with a "-" and non-leaves are marked with a "+".
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		toStringRec(root, sb, 0);
		return sb.toString();
	}

	/**
	 * Preorder traversal of the tree that builds a string representation in the
	 * given StringBuilder.
	 * 
	 * @param n
	 *            root of subtree to be traversed
	 * @param sb
	 *            StringBuilder in which to create a string representation
	 * @param depth
	 *            depth of the given node in the tree
	 */
	private void toStringRec(Node n, StringBuilder sb, int depth)
	{
		for(int i = 0; i < depth; ++i)
		{
			sb.append("  ");
		}

		if(n == null)
		{
			sb.append("-\n");
			return;
		}

		if(n.left != null || n.right != null)
		{
			sb.append("+ ");
		}
		else
		{
			sb.append("- ");
		}
		sb.append(n.data.toString());
		sb.append("\n");
		if(n.left != null || n.right != null)
		{
			toStringRec(n.left, sb, depth + 1);
			toStringRec(n.right, sb, depth + 1);
		}
	}

	/**
	 * Iterator implementation for this binary search tree. The elements are
	 * returned in ascending order according to their natural ordering.
	 */
	private class BSTIterator implements Iterator<E>
	{
		/**
		 * Node to be returned by next call to next().
		 */
		private Node	current;

		/**
		 * Node returned by last call to next() and available for removal. This
		 * field is null when no node is available to be removed.
		 */
		private Node	pending;

		/**
		 * Constructs an iterator starting at the smallest element in the tree.
		 */
		public BSTIterator()
		{
			// start out at smallest value
			current = root;
			if(current != null)
			{
				while(current.left != null)
				{
					current = current.left;
				}
			}
		}

		@Override
		public boolean hasNext()
		{
			return current != null;
		}

		@Override
		public E next()
		{
			if(!hasNext())
				throw new NoSuchElementException();
			pending = current;
			current = successor(current);
			return pending.data;
		}

		@Override
		public void remove()
		{
			if(pending == null)
				throw new IllegalStateException();

			// Remember, current points to the successor of
			// pending, but if pending has two children, then
			// unlinkNode(pending) will copy the successor's data
			// into pending and delete the successor node.
			// So in this case we want to end up with current
			// pointing to the pending node.
			if(pending.left != null && pending.right != null)
			{
				current = pending;
			}
			unlinkNode(pending);
			pending = null;
		}
	}

	//
	// Alternate iterator implementation does not use recursion
	//
	@SuppressWarnings("unused")
	private class BSTIterator2 implements Iterator<E>
	{
		private Deque<Node>	stack	= new LinkedList<Node>();

		/**
		 * Node returned by last call to next() and available for removal. This
		 * field is null when no node is available to be removed.
		 */
		private Node		pending;

		/**
		 * Constructs an iterator starting at the smallest element in the tree.
		 */
		public BSTIterator2()
		{
			// start out at smallest value
			Node current = root;
			while(current != null)
			{
				stack.push(current);
				current = current.left;
			}
		}

		@Override
		public boolean hasNext()
		{
			return !stack.isEmpty();
		}

		@Override
		public E next()
		{
			if(!hasNext())
				throw new NoSuchElementException();
			pending = stack.pop();
			Node current = pending.right;
			while(current != null)
			{
				stack.push(current);
				current = current.left;
			}
			return pending.data;
		}

		@Override
		public void remove()
		{
			if(pending == null)
				throw new IllegalStateException();

			// we know that pending is not on the stack, but the stack
			// may contain nodes in its right subtree. That's ok unless
			// pending has two children, in which case the successor
			// of pending (which is currently the top of the stack)
			// is going to be copied into pending. So we have to
			// rearrange the stack so that pending is the top element
			// and nothing in the right subtree is on the stack yet
			if(pending.left != null && pending.right != null)
			{
				if(pending.parent == null)
				{
					stack.clear();
					stack.push(pending);
				}
				else
				{
					// clear off everything in the right subtree of pending
					Node rightChild = pending.right;
					while(stack.pop() != rightChild)
					{
						;
					}
					stack.push(pending);
				}
			}

			unlinkNode(pending);
			pending = null;

		}
	}

}