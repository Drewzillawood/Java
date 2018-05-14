package binaryTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Example of evaluating expression trees using a postorder traversal. The
 * method makeFromPostfix() illustrates building up a tree using a stack of
 * subtrees.
 */
public class ExpressionTree
{
	public static void main(String[] args)
	{
		try
		{

			TreeNode<String> root = makeExample1(); // (2 + 3) * 4
			System.out.println("(2 + 3) * 4");
			System.out.println(evaluate(root));

			/*
			 * String expr = "23+4*"; //20 root = makeFromPostfix(expr);
			 * System.out.println(expr); System.out.println(evaluate(root));
			 * expr = "23+45+*6+"; //51 root = makeFromPostfix(expr);
			 * System.out.println(expr); System.out.println(evaluate(root));
			 * expr = "23-4-"; // -5 root = makeFromPostfix(expr);
			 * System.out.println(expr); System.out.println(evaluate(root));
			 */
		}
		catch(ExpressionFormatException e)
		{
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Evaluates an expression tree with the given root.
	 * 
	 * @param node
	 *            root of the subtree to evaluate
	 * @return value of the subtree
	 * @throws ExpressionFormatException
	 */
	public static int evaluate(TreeNode<String> node) throws ExpressionFormatException
	{
		if(node.isLeaf())
		{
			try
			{
				return Integer.parseInt(node.data());
			}
			catch(NumberFormatException e)
			{
				throw new ExpressionFormatException("Unable to parse integer: " + node.data());
			}
		}
		else
		{
			if(node.left() == null || node.right() == null)
			{
				throw new ExpressionFormatException("Unexpected null subtree");
			}
			int lhs = evaluate(node.left());
			int rhs = evaluate(node.right());
			String operator = node.data();
			if("+".equals(operator))
			{
				return lhs + rhs;
			}
			else if("-".equals(operator))
			{
				return lhs - rhs;
			}
			else if("*".equals(operator))
			{
				return lhs * rhs;
			}
			else if("/".equals(operator))
			{
				return lhs / rhs;
			}
			else
			{
				throw new ExpressionFormatException("Unexpected operator " + operator);
			}

		}
	}

	// (2 + 3) * 4
	private static TreeNode<String> makeExample1()
	{
		TreeNode<String> root = new TreeNode<String>("*",
				new TreeNode<String>("+", new TreeNode<String>("2"), new TreeNode<String>("3")),
				new TreeNode<String>("4"));
		return root;
	}

	/**
	 * Creates an expression tree from an arithmetic expression in postfix form.
	 * Note the format of the expression is highly restricted, numbers can only
	 * be one digit long and can't be negative.
	 * 
	 * @param expr
	 * @return
	 */
	@SuppressWarnings("unused")
	private static TreeNode<String> makeFromPostfix(String expr)
	{
		// Each element of stack is a subtree representing an operand
		Deque<TreeNode<String>> s = new LinkedList<TreeNode<String>>();
		for(int i = 0; i < expr.length(); ++i)
		{
			String c = expr.substring(i, i + 1);
			if(" ".equals(c))
				continue;
			if("+".equals(c) || "*".equals(c) || "-".equals(c) || "/".equals(c))
			{
				TreeNode<String> temp = new TreeNode<String>(c);
				temp.setRight(s.pop());
				temp.setLeft(s.pop());
				s.push(temp);
			}
			else
			{
				s.push(new TreeNode<String>(c));
			}
		}
		return s.pop();
	}

	/**
	 * Exception to be thrown in case of an invalid expression.
	 */
	@SuppressWarnings("serial")
	public static class ExpressionFormatException extends Exception
	{
		public ExpressionFormatException()
		{
			super();
		}

		public ExpressionFormatException(String msg)
		{
			super(msg);
		}
	}

}