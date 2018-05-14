package binaryTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Traversal demonstrations for a binary tree.
 */
public class ExampleTree
{

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    // Creates the tree:
    //
    //       A
    //      / \
    //     B   C
    //    / \
    //   D   E
    //      / \
    //     F   G
    //
    TreeNode<String> l1 = new TreeNode<String>("D");
    TreeNode<String> l2 = new TreeNode<String>("F");
    TreeNode<String> l3 = new TreeNode<String>("G");
    TreeNode<String> l4 = new TreeNode<String>("C");
    TreeNode<String> sub1 = new TreeNode<String>("E", l2, l3); 
    TreeNode<String> sub2 = new TreeNode<String>("B", l1, sub1); 
    TreeNode<String> root = new TreeNode<String>("A", sub2, l4); 
    
    // Try a traversal algorithm
    traverseLevelorder(root);
    System.out.println();

    traverseInorderAlt(root);
    System.out.println();

    
    String s = postorder(root);
    System.out.println(s);
    
    System.out.println(height(root));
    System.out.println(size(root));
  }

 
  public static void traversePostorder(TreeNode<?> node)
  {
    if (node == null) return;
    traversePostorder(node.left());
    traversePostorder(node.right());
    System.out.print(node.data().toString() + " ");
  }
  
  
  public static void traversePreorder(TreeNode<?> node)
  {
    if (node == null) return;
    System.out.print(node.data().toString() + " ");
    traversePreorder(node.left());
    traversePreorder(node.right());
  }

  public static void traverseInorder(TreeNode<?> node)
  {
    if (node == null) return;
    traverseInorder(node.left());
    System.out.print(" " + node.data().toString() + " ");
    traverseInorder(node.right());
  }
  
  public static void traverseLevelorder(TreeNode<?> node)
  {
    Queue<TreeNode<?>> q = new LinkedList<TreeNode<?>>();
    if (node != null)
    {
      q.add(node);
    }
    while (!q.isEmpty())
    {
      TreeNode<?> current = q.remove();
      if (current.left() != null) q.add(current.left());
      if (current.right() != null) q.add(current.right());
      System.out.print(current.data().toString() + " ");
    }
  }

  public static int height(TreeNode<?> node)
  {
    if (node == null) return -1;
    int leftHeight = height(node.left());
    int rightHeight = height(node.right());
    return 1 + Math.max(leftHeight, rightHeight);
  }
  
  public static int size(TreeNode<?> node)
  {
    if (node == null) return 0;
    return 1 + size(node.left()) + size(node.right());
  }
  

//  private static class StackEntry
//  {
//    TreeNode<?> node;
//    boolean leftChildDone;
//    public StackEntry(TreeNode<?> node, boolean leftChildDone)
//    {
//      this.node = node;
//      this.leftChildDone = leftChildDone;
//    }
//  }

  public static void traverseInorderAlt(TreeNode<?> node)
  {
    class StackEntry
    {
      TreeNode<?> node;
      boolean leftChildDone;
      public StackEntry(TreeNode<?> node, boolean leftChildDone)
      {
        this.node = node;
        this.leftChildDone = leftChildDone;
      }
    }
    
    Deque<StackEntry> q = new LinkedList<StackEntry>();
    
    if (node != null)
    {
      q.push(new StackEntry(node, false));
    }
    while (!q.isEmpty())
    {
      StackEntry entry = q.pop();
      if (entry.node == null) continue;
      if (entry.leftChildDone)
      {
        System.out.print(entry.node.data().toString() + " ");
        if (entry.node.right() != null)
        {
          q.push(new StackEntry(entry.node.right(), false));
        }
      }
      else
      {
        q.push(new StackEntry(entry.node, true));
        if (entry.node.left() != null)
        {
          q.push(new StackEntry(entry.node.left(), false));
        }
      }
    }
  }

  // returns a preorder traversal of the tree as a String
  public static String preorderString(TreeNode<?> node)
  {
    StringBuilder sb = new StringBuilder();
    makePreorderString(sb, node);
    return sb.toString();
  }
  
  private static void makePreorderString(StringBuilder sb, TreeNode<?> node)
  {
    sb.append(node.data().toString() + " ");
    makePreorderString(sb, node.left());
    makePreorderString(sb, node.right());
  }

  public static String postorder(TreeNode<?> node)
  {
    StringBuilder sb = new StringBuilder();
    postorderRec(sb, node);
    return sb.toString();
  }

  private static void postorderRec(StringBuilder sb, TreeNode<?> node)
  {
    if (node == null) return;
    postorderRec(sb, node.left());
    postorderRec(sb, node.right());
    sb.append(node.data().toString() + " ");
  }

}