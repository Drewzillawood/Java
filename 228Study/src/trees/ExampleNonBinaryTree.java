package trees;

import java.util.LinkedList;
import java.util.Queue;
// import javax.swing.JTree; 


public class ExampleNonBinaryTree
{
  public static void main(String[] args)
  {
    // Constructs the following tree using child-sibling pointers:
    //
    //         A
    //       / | \
    //      B  C  D
    //     / \    |
    //    E   F   G
    //      / | \
    //     H  I  J
    //   
    CSNode<String>  n9 = new CSNode<String>("J");
    CSNode<String>  n8 = new CSNode<String>("I", null, n9);
    CSNode<String>  n7 = new CSNode<String>("H", null, n8);
    CSNode<String> n6 = new CSNode<String>("G");
    CSNode<String> n5 = new CSNode<String>("F", n7, null);
    CSNode<String> n4 = new CSNode<String>("E", null, n5);
    CSNode<String> n3 = new CSNode<String>("D", n6, null);
    CSNode<String> n2 = new CSNode<String>("C", null, n3);
    CSNode<String> n1 = new CSNode<String>("B", n4, n2);
    CSNode<String> root = new CSNode<String>("A", n1, null);
    
    traversePreorder(root);
    System.out.println(); 
    traversePostorder(root);
    System.out.println();
    traverseLevelorder(root);
    System.out.println();
    System.out.println("arity = " + arity(root)); 
  }
  
  public static void traversePreorder(CSNode<?> root)
  {
	  System.out.print(root.getData().toString() + " ");
	  if (root.getChild() != null)
	  {
		  CSNode<?> current = root.getChild();
		  while (current != null)
		  {
			  traversePreorder(current);
			  current = current.getSibling();
		  }
	  }
  }
  
  public static void traversePostorder(CSNode<?> root)
  {
    if (root.getChild() != null)
    {
      CSNode<?> current = root.getChild();
      while (current != null)
      {
        traversePostorder(current);
        current = current.getSibling();
      }
    }
    System.out.print(root.getData().toString() + " ");
  }
  
  public static void traverseLevelorder(CSNode<?> node)
  {
    Queue<CSNode<?>> q = new LinkedList<CSNode<?>>();
    if (node != null)
    {
      q.add(node);
    }
    while (!q.isEmpty())
    {
      CSNode<?> current = q.remove();
      CSNode<?> child = current.getChild();
      while (child != null)
      {
        q.add(child);
        child = child.getSibling();
      }
      System.out.print(current.getData().toString() + " ");
    }
  }

  public static int arity(CSNode<?> root)
  {
	  int amax = 0;
	  int k = 0;
	  
	  if (root.getChild() != null)
	  {
		  CSNode<?> current = root.getChild();
		  while (current != null)
		  {
			  k++;
			  int temp = arity(current);
			  
			  if (temp > amax)
				  amax = temp;
			  current = current.getSibling();
		  }
	  }
	  if (amax > k)
		  return amax;
	  else
		  return k;
  }
}
