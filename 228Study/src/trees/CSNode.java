package trees;

/**
 * Child-sibling node type for an n-ary tree.
 */
public class CSNode<E> {
	
	protected CSNode<E> firstChild;
	protected CSNode<E> nextSibling;
	protected E data;

	public CSNode() {
		
	}

	public CSNode(E data) {
		
		this(data, null, null);
		
	}

	public CSNode(E data, CSNode<E> child, CSNode<E> sibling) {
		
		this.firstChild = child;
		this.nextSibling = sibling;
		this.data = data;
		
	}

	public boolean isLeaf() {
		
		return firstChild == null;
		
	}

	public CSNode<E> getChild() {
		
		return firstChild;
		
	}

	public void setChild(CSNode<E> child) {
		
		this.firstChild = child;
		
	}

	public CSNode<E> getSibling() {
		
		return nextSibling;
		
	}

	public void setSibling(CSNode<E> sibling) {
		
		this.nextSibling = sibling;
		
	}

	public E getData() {
		
		return data;
		
	}

	public void setData(E data) {
		
		this.data = data;
		
	}

}