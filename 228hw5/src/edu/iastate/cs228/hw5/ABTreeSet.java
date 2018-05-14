package edu.iastate.cs228.hw5;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author drewu
 */
public class ABTreeSet<E extends Comparable<? super E>> extends AbstractSet<E> {

	final class ABTreeIterator implements Iterator<E> {
		// TODO add private fields here

		private Node current;
		private Node pending;
		
		ABTreeIterator() {
			
			current = rootNode;
			if(current != null) {
				
				while(current.left() != null) {
					
					current = current.leftChild;
					
				}
				
			}

		}

		@Override
		public boolean hasNext() {
			
			return current != null;
			
		}

		@Override
		public E next() {
	
			if(!hasNext()) throw new NoSuchElementException();
			pending = current;
			current = (Node)successor(current);
			return pending.data;
			
		}

		@Override
		public void remove() {
			
			if(pending.leftChild != null && pending.rightChild != null) {
				
				current = pending;
				
			}
			unlinkNode(pending);
			pending = null;

		}
	}

	final class Node implements BSTNode<E> {
		
		private Node leftChild;
		private Node rightChild;
		private Node parent;
		private int count;
		private E data;

		Node(E data) {
			
			this.data = data;
			count = 1;

		}

		@Override
		public int count() {
			
			if(leftChild != null && rightChild == null) {
				
				count = leftChild.count + 1;
			
			} 
			
			if(rightChild != null && leftChild == null) {
				
				count = rightChild.count + 1;
				
			} 
			
			if(rightChild != null && leftChild != null) {
				
				count = rightChild.count + leftChild.count + 1;
				
			}
			
			return count;
			
		}

		@Override
		public E data() {

			return data;
			
		}

		@Override
		public BSTNode<E> left() {

			return leftChild;
			
		}

		@Override
		public BSTNode<E> parent() {

			return parent;
			
		}

		@Override
		public BSTNode<E> right() {

			return rightChild;
			
		}

		@Override
		public String toString() {
			
			return data.toString();
			
		}
		
	}

	// TODO add private fields here
	private Node rootNode;
	private List<BSTNode<E>> ioList;
	private List<BSTNode<E>> poList;
 	private int size;
	private int tops;
	private int bottoms;
	private double alpha;
	private boolean isBalancing;

	/**
	 * Default constructor. Builds a non-self-balancing tree.
	 */
	public ABTreeSet() {

		this(false);

	}

	/**
	 * If <code>isSelfBalancing</code> is <code>true</code> <br>
	 * builds a self-balancing tree with the default value alpha = 2/3.
	 * <p>
	 * If <code>isSelfBalancing</code> is <code>false</code> <br>
	 * builds a non-self-balancing tree.
	 * 
	 * @param isSelfBalancing
	 */
	public ABTreeSet(boolean isSelfBalancing) {
		
			this(isSelfBalancing, 2, 3);

	}

	/**
	 * If <code>isSelfBalancing</code> is <code>true</code> <br>
	 * builds a self-balancing tree with alpha = top / bottom.
	 * <p>
	 * If <code>isSelfBalancing</code> is <code>false</code> <br>
	 * builds a non-self-balancing tree (top and bottom are ignored).
	 * 
	 * @param isSelfBalancing
	 * @param top
	 * @param bottom
	 * @throws IllegalArgumentException
	 *             if (1/2 < alpha < 1) is false
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	public ABTreeSet(boolean isSelfBalancing, int top, int bottom) {

		ioList = new ArrayList();
		poList = new ArrayList();
		tops = top;
		bottoms = bottom;
		isBalancing = isSelfBalancing;
		rootNode = null;
		size = 0;
		
		if(isSelfBalancing) {
			
			tops = top;
			bottoms = bottom;
			alpha = (double)top/bottom;
			
			if(!(0.5 < alpha && alpha < 1)) {
				
				throw new IllegalArgumentException();
				
			}
			
		}

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws NullPointerException
	 *             if e is null.
	 */
	@Override
	public boolean add(E e) {
		
		if(e == null) {
			
			throw new NullPointerException();
			
		}
		
		if(rootNode == null) {
			
			rootNode = new Node(e);
			size++;
			return true;
		
		} 
		
		if(this.contains(e)) {
			
			return false;
			
		}
		
		Node current = rootNode;
		while(true) {
			
			int comp = current.data.compareTo(e);
			if(comp == 0) {
				
				// Node is already in the tree
				return false;
				
			} else if(comp > 0) {
				
				if(current.leftChild != null) {
					
					current.count++;
					current = current.leftChild;
					
				} else {
					
					current.count++;
					current.leftChild = new Node(e);
					current.leftChild.parent = current;
					size++;
					
					if(isBalancing) {
						
						rebalance(findNextToBalance(current));
						
					}
					
					return true;
					
				}
				
			} else {
				
				if(current.rightChild != null) {
					
					current.count++;
					current = current.rightChild;
					
				} else {
					
					current.count++;
					current.rightChild = new Node(e);
					current.rightChild.parent = current;
					size++;
					
					if(isBalancing) {
						
						rebalance(findNextToBalance(current));
						
					}
					
					return true;
					
				}
				
			}
			
		}
		
	}
	
	private Node findNextToBalance(Node e) {
		
		Node temp = null;
		Node bal = null;
		
		if(e.parent != null) {
			
			temp = e.parent;
		
		} else {
			
			temp = e;
			
		}
		
		while(temp.parent != null) {
			
			if(temp.leftChild != null) {
				
				if(temp.leftChild.count * bottoms > size * tops) {
					
					bal = temp;
					
				}
				
			}
			
			if(temp.rightChild != null) {
				
				if(temp.rightChild.count * bottoms > size * tops) {
					
					bal = temp;
					
				}
				
			}
			
			temp = temp.parent;
			
		}
		
		return bal;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean contains(Object o) {
		
		E e = (E)o;
		return getBSTNode(e) != null;
		
	}

	/**
	 * @param e
	 * @return BSTNode that contains e, null if e does not exist
	 */
	public BSTNode<E> getBSTNode(E e) {
		
		Node current = rootNode;
		while(current != null) {
			
			int comp = current.data.compareTo(e);
			if(comp == 0) {
				
				return current;
				
			} else if(comp > 0) {
				
				current = current.leftChild;
				
			} else if(comp < 0) {
				
				current = current.rightChild;
				
			}
		
		}

		return null;
		
	}

	/**
	 * Returns an in-order list of all nodes in the given sub-tree.
	 * 
	 * @param root
	 * @return an in-order list of all nodes in the given sub-tree.
	 */
	@SuppressWarnings({"rawtypes","unchecked"})
	public List<BSTNode<E>> inorderList(BSTNode<E> root) {
		
			if(!ioList.isEmpty()) {
				
				ioList = new ArrayList();
				
			}
		
			return inOrderRec(root);
	
	}
	
	private List<BSTNode<E>> inOrderRec(BSTNode<E> e) {
		
		Node node = (Node)e;
		if(node != null) {
				
			inOrderRec(e.left());
			ioList.add(node);
			inOrderRec(e.right());
			
		}
		
		return ioList;
		
	}

	@Override
	public Iterator<E> iterator() {
		
		ABTreeIterator iter = new ABTreeIterator();
		
		return iter;
		
	}

	/**
	 * Returns an pre-order list of all nodes in the given sub-tree.
	 * 
	 * @param root
	 * @return an pre-order list of all nodes in the given sub-tree.
	 */
	@SuppressWarnings({"rawtypes","unchecked"})
	public List<BSTNode<E>> preorderList(BSTNode<E> root) {

		if(!poList.isEmpty()) {
			
			poList = new ArrayList();
			
		}
		
		return preOrderRec(root);
		
	}
	
	private List<BSTNode<E>> preOrderRec(BSTNode<E> e) {
		
		Node node = (Node)e;
		if(node != null) {
				
			preOrderRec(e.left());
			poList.add(node);
			preOrderRec(e.right());
			
		}
		
		return poList;
		
	}

	/**
	 * Performs a re-balance operation on the subtree rooted at the given node.
	 * 
	 * @param bstNode
	 */
	public void rebalance(BSTNode<E> bstNode) {
		
		List<BSTNode<E>> ordered = inorderList(bstNode);
		int mid = (ordered.size()-1)/2;
		rootNode = (Node)ordered.get(mid);
		rootNode.parent = null;
		rootNode.leftChild = rebalanceHelper(ordered, 0, mid-1, rootNode);
		rootNode.rightChild = rebalanceHelper(ordered, mid+1, ordered.size()-1, rootNode);
		rootNode.count = rootNode.rightChild.count + rootNode.leftChild.count + 1;

	}
	
	private Node rebalanceHelper(List<BSTNode<E>> inorder, int start, int end, Node parental) {
		
		int mid = (start + end)/2;
		
		if(start == end) {
			
			Node ret = (Node)inorder.get(mid);
			ret.rightChild = null;
			ret.leftChild = null;
			ret.parent = parental;
			ret.count = 1;
			
			return ret;
			
		} else if(end < start) {
			
			return null;
			
		}
		
		Node temp = (Node)inorder.get(mid);
		temp.leftChild = rebalanceHelper(inorder, start, mid-1, temp);
		temp.rightChild = rebalanceHelper(inorder, mid+1, end, temp);
		temp.parent = parental;
		temp.count = end-start+1;
		return temp;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object o) {
		
		// This cast may cause comparator to throw ClassCastException at runtime,
	    // which is the expected behavior
	    E key = (E) o;
	    Node n = (Node)getBSTNode(key);
	    if (n == null) {
	    	
	      return false;
	      
	    }
	    
	    unlinkNode(n);
	    return true;
		
	}
	
	private void unlinkNode(Node n) {
		
		boolean flag = false;
		// first deal with the two-child case; copy
	    // data from successor up to n, and then delete successor 
	    // node instead of given node n
	    if (n.leftChild != null && n.rightChild != null) {
	    	
	      Node s = (Node)successor(n);
	      n.data = s.data;
	      n = s; // causes s to be deleted in code below
	      
	    }
	    
	    // n has at most one child
	    Node replacement = null;    
	    if (n.leftChild != null) {
	    	
	      replacement = n.leftChild;
	      
	    } else if (n.rightChild != null) {
	    	
	      replacement = n.rightChild;
	      
	    }
	    
	    // link replacement into tree in place of node n 
	    // (replacement may be null)
	    if (n.parent == null) {
	    	
	      rootNode = replacement;
	      
	    } else {
	      
	    	if (n == n.parent.leftChild) {
	    		
	    		n.parent.leftChild = replacement;
	    		
	    	} else {
	    		
	    		n.parent.rightChild = replacement;
	    		
	    	}
	    	
	    	Node temp = null;
	    	
	    	if(n.data.equals(rootNode.data) && replacement == null) {
	    		
	    		temp = n;
	    		
	    	} else if(n.leftChild == null && n.rightChild == null) {
	    		
	    		temp = n;
	    		
	    	} else if(n.leftChild == null && n.rightChild != null) {
	    		
	    		temp = n;
	    	
	    	} else {
	    		
	    		temp = n.parent;
	    		
	    	}
	    	
	    	while(temp.parent != null) {
	    		
	    		temp.parent.count--;
	    		temp = temp.parent;
	    		
	    	}
	    	
	    	flag = true;
	    	
	    }
	    
	    if (replacement != null && !flag) {
	    	
	      Node temp = replacement.parent;
	      
	      while(temp.parent != null && temp.parent.rightChild != null) {
	    	  
	    	  temp.parent.count--;
	    	  temp = temp.parent;
	    	  
	      }
	    	
	      replacement.parent = n.parent;
	      
	    }
	    
	    if(rootNode.leftChild == null && rootNode.rightChild == null) {
	    	
	    	rootNode.count = 1;
	    	
	    }
	    
	    --size;
		
	}

	/** 
	 * Returns the root of the tree.
	 * 
	 * @return the root of the tree.
	 */
	public BSTNode<E> root() {

		return rootNode;
		
	}

	public void setSelfBalance(boolean isSelfBalance) {
		
		isBalancing = isSelfBalance;

	}

	@Override
	public int size() {

		return size;
		
	}

	public BSTNode<E> successor(BSTNode<E> node) {
		
		Node current = (Node)node;
		
		if(current.rightChild != null) {
		
			current = current.rightChild;
			
			while(current.leftChild != null) {
				
				current = current.leftChild;
				
			}
			
		} else {
			
			current = current.parent;
			Node child = (Node)node;
			
			while(current != null && current.rightChild == child) {
				
				if(current.parent.leftChild.equals(current)) {
					
					child = current;
					current = current.parent;
					
				}
				
				
			}
			
		}
		
		return current;
		
	}

	@Override
	public String toString() {
	
		StringBuilder sb = new StringBuilder();
		toStringRec(rootNode, sb, 0);
		return sb.toString();
		
	}
	
	private void toStringRec(Node n, StringBuilder sb, int depth) {
		
		for (int i = 0; i < depth; ++i) {
			
	      sb.append("    ");
	      
	    }
	    
	    if (n == null) {
	    	
	      sb.append("null\n");
	      return;
	      
	    }
	    
	    sb.append(n.data.toString());
	    sb.append("\n");
	    
	    if (n.leftChild != null || n.rightChild != null) {
	    	
	      toStringRec(n.leftChild, sb, depth + 1);   
	      toStringRec(n.rightChild, sb, depth + 1);
	      
	    }
		
	}

}
