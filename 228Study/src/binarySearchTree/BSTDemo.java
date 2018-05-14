package binarySearchTree;

import java.util.Iterator;

/**
 * Short demo of the BST implementation.
 */
public class BSTDemo
{
  public static void main(String[] args)
  {
    
    BSTSet<Integer> tree = new BSTSet<Integer>();
    tree.add(20);
    tree.add(3);
    tree.add(5);
    tree.add(15);
    tree.add(25);
    tree.add(24);
    //tree.add(26);
    tree.add(30);
    tree.add(28);
    tree.add(29);
    
    System.out.print("Elements: ");
    for (Integer i : tree)
    {
      System.out.print(i + " ");
    }
    System.out.println();
    System.out.println();
    System.out.println(tree.toString());
    System.out.println();
    
    tree.remove(3);
    tree.remove(15);
    tree.remove(25);
    
    System.out.println();
    System.out.println("After removing 3, 15, and 25 using remove() method");
    for (Integer i : tree)
    {
      System.out.print(i + " ");
    }
    System.out.println();
    System.out.println();
    System.out.println(tree.toString());
    System.out.println();

    tree = new BSTSet<Integer>();
    tree.add(20);
    tree.add(3);
    tree.add(5);
    tree.add(15);
    tree.add(25);
    tree.add(24);
    //tree.add(26);
    tree.add(30);
    tree.add(28);
    tree.add(29);
    
    Iterator<Integer> iter = tree.iterator();
    while (iter.hasNext())
    {
      Integer i = iter.next();
      if (i == 3 || i == 15 || i == 25)
      {
        iter.remove();
      }
    }

    System.out.println();
    System.out.println("After removing 3, 15, and 25 using iterator.remove() ");
    for (Integer i : tree)
    {
      System.out.print(i + " ");
    }
    System.out.println();
    System.out.println();
    System.out.println(tree.toString());
    System.out.println();
    
  }
}