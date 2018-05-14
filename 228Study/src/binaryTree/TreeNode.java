package binaryTree;

/**
 * Simple node type for a binary tree.
 */
public class TreeNode<E>
{
  protected TreeNode<E> left;
  protected TreeNode<E> right;
  protected E data;
  
  public TreeNode(){}
  
  public TreeNode(E data)
  {
    this(data, null, null);
  }
  
  public TreeNode(E data, TreeNode<E> left, TreeNode<E> right)
  {
    this.left = left;
    this.right = right;
    this.data = data;
  }
  
  public boolean isLeaf()
  {
    return left == null && right == null;
  }
  
  public TreeNode<E> left()
  {
    return left;
  }
  
  public void setLeft(TreeNode<E> left)
  {
    this.left = left;
  }
  
  public TreeNode<E> right()
  {
    return right;
  }
  
  public void setRight(TreeNode<E> right)
  {
    this.right = right;
  }
  
  public E data()
  {
    return data;
  }
  
  public void setData(E data)
  {
    this.data = data;
  }
}
