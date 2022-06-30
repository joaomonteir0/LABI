/**
 * Binary Search Tree in increasing order implemented with the modular 
 * programming paradigm using defensive programming with exceptions
 * 
 * @author skeleton by Antonio Manuel Adrego da Rocha
 * 
 * @author Nome e NMec do aluno 1
 * @author Nome e NMec do aluno 2
 * 
 * @version data
 */

import static java.lang.System.*;
import java.util.*;

public class ABPTree
{
	private ABPNode root = null;	// tree root

	/* private class for the tree node */
	private static class ABPNode
	{
		public ABPNode left; // left subtree
		public ABPNode right; // right subtree
		public int elem; // element of the tree

		public ABPNode (int e) // tree node constructor
		{ elem = e; left = right = null; }
	}

	/* private class for the suport node of the deletion method */
	private class DelNode
	{
		public Integer del;	  /* the deleted element */
		public ABPNode tree;  /* the remaining tree */

		/* initialization constructor */
		public DelNode (Integer delElement, ABPNode remTree)
		{ del = delElement; tree = remTree; }
	}

	/**
	* The constructor creates an empty tree.
	* {@code ABPTree} the empty constructor.
	*/
	public ABPTree () // empty tree constructor
	{ root = null; }

	/**
	* The method returns the number of elements stored in the tree.
	* @return the number of elements in the tree.
	*/
	public int size () { return recSize (root); }

	/* private recursive method for calculating the size of the tree */
	private int recSize (ABPNode root)
	{
		if (root == null) return 0; // an empty tree has size zero
		else return 1 + recSize (root.left) + recSize (root.right);
	}

	/**
	* The method checks if the tree is empty.
	* @return  {@code true} if tree is empty, otherwise {@code false}.
	*/
	public boolean isEmpty () { return root == null; }

	
	/**
	* The method returns the height in the tree.
	* @return the tree height.
	*/
	public int height () { return recHeight (root); }

	/* private recursive method for calculating the height of the tree */
	private int recHeight (ABPNode root)
	{
		if (root == null) return 0; // external node at level 0
		else return 1 + Math.max (recHeight (root.left), recHeight (root.right));
	}

	/**
	* The method creates a string representing the contents of the tree,
	* in a hierarchical fashion.
	* @return the string representing the tree or "Empty Tree".
	*/
	public String toString ()
	{
		if (root == null) return "Empty Tree\n";
		else return recString (root, 1);
	}

	/* private recursive method for creating the hierarchical representation of the tree */
	private String recString (ABPNode root, int height)
	{
		String str = "";

		if (root == null) // external node put * in the proper level and finishing
		{
			for (int i = 0; i < height; i++) str += "\t";
			str += "*\n";
			return str;
		}

		str += recString (root.right, height+1); // right subtree

		for (int i = 0; i < height; i++) str += "\t"; // internal node
			str += root.elem + "\n"; // put the element in the proper level

		str += recString (root.left, height+1); // lefct subtree

		return str;
	}

	/**
	* The method checks if the element exists in the tree.
	* @param e an element.
	* @return {@code true} if the element exists and {@code false} otherwise.
	* @throws EmptyTreeException if the tree is empty.
	*/
	public boolean exists (int e) throws EmptyTreeException
	{
		if (root == null) throw new EmptyTreeException ("Empty Tree");
		return recSearch (root, e) != null;
	}
	
	/* private recursive method for search an element in the tree */
	private ABPNode recSearch (ABPNode root, int e)
	{
		if (root == null) return null; // unsuccessful search

		int comp = root.elem -e;
		if (comp == 0) return root; // successful search
		else if (comp > 0) return recSearch (root.left, e); // left subtree
		else return recSearch (root.right, e); // right subtree
	}

	/**
	* The method searchs and returns the element in the tree.
	* @param e an element.
	* @return the element in the tree.
	* @throws EmptyTreeException if the tree is empty.
	* @throws NoSuchElementException if the element does not exist.
	*/
	public int search (int e) throws EmptyTreeException, NoSuchElementException
	{
		if (root == null) throw new EmptyTreeException ("Empty Tree");
		ABPNode node = recSearch (root, e);
		if (node == null) throw new NoSuchElementException ("Element not stored in the tree");
		else return node.elem;
	}

	/**
	* The method returns the minimal element in the tree.
	* @return the minimal element in the tree.
	* @throws EmptyTreeException if the tree is empty.
	*/	
	public int min () throws EmptyTreeException
	{
		if (root == null) throw new EmptyTreeException ("Empty Tree");
		else return recMin (root).elem;
	}

	/* private recursive method for obtain the minimal element in the tree */
	private ABPNode recMin (ABPNode root)
	{
		if (root.left == null) return root;
		else return recMin (root.left);
	}

	/**
	* The method returns the maximal element in the tree.
	* @return the maximal element in the tree.
	* @throws EmptyTreeException if the tree is empty.
	*/	
	public int max () throws EmptyTreeException
	{
		if (root == null) throw new EmptyTreeException ("Empty Tree");
		else return recMax (root).elem;
	}

	/* private recursive method for obtain the maximal element in the tree */
	private ABPNode  recMax (ABPNode root)
	{
		if (root.right == null) return root;
		else return recMax (root.right);
	}

	/**
	* The method inserts a new element in the proper place of the tree.
	* @param e the element to insert.
	* @throws RepeatedElementException if the element is already in the tree.
	*/
	public void insert (int e) throws RepeatedElementException
	{
		try
		{ root = recInsert (root, e); }
		catch (RepeatedElementException exception)
		{ throw exception; }
	}

	/* private recursive method for inserting the element in the tree */
	private ABPNode recInsert (ABPNode root, int e) throws RepeatedElementException
	{
		// empty tree - insert node in this tree root
		if (root == null) root = new ABPNode (e);
		else if (root.elem > e) // insert in the left subtree?
				root.left = recInsert (root.left, e);
			else if (root.elem < e) // insert in the right subtree?
					root.right = recInsert (root.right, e);
				 else throw new RepeatedElementException ("Element already in the tree");

		return root;
	}

	/**
	* The method removes and returns the element in the tree.
	* @param e an element.
	* @return the element removed
	* @throws EmptyTreeException if the tree is empty.
	* @throws NoSuchElementException if the element does not exist.
	*/
	public int delete (int e) throws EmptyTreeException, NoSuchElementException
	{
		if (root == null) throw new EmptyTreeException ("Empty Tree");

		DelNode node = recDelete (root, e);
		if (node.del == null) throw new NoSuchElementException ("Element not stored in the tree");
		root = node.tree;
		return node.del;
	}

	/* private recursive method for removing the element from the tree */
	private DelNode recDelete (ABPNode root, int e)
	{
		if (root == null) return new DelNode (null, null); // element does not exist

		DelNode node;
		int comp = root.elem - e;

		if (comp > 0) { node = recDelete (root.left, e); root.left = node.tree; }
		else	if (comp < 0) { node = recDelete (root.right, e); root.right = node.tree; }
				else { node = deleteNode (root); root = node.tree; }

		node.tree = root;
		return node;
	}

	/* private recursive method for removing effectively the element from the tree */
	private DelNode deleteNode (ABPNode root)
	{
		DelNode node = new DelNode ((Integer) root.elem, null);

		if (root.left == null && root.right == null) return node;
		else	if (root.left == null) node.tree = root.right; // connect to right subtree
				else	if (root.right == null) node.tree = root.left; // connect to left subtree
						else // with both subtrees - replace the element
						{
							int del = root.elem;
							root.elem = findMin (root.right);
							root.right = recDelete (root.right, root.elem).tree;
							node.del = (Integer) del;
							node.tree = root;
						}
		return node;
	}

	/* private method for removing effectively the element from the tree */
	private int findMin (ABPNode root)
	{
		ABPNode node = root;

		while (node.left != null) node = node.left;
		return node.elem;
	}

	/**
	* The method presents the tree traversed in preorder, inorder or postorder.
	* @param trav the desired traversal (1 preorder / 2 inorder / 3 postorder).
	* @return the string representing the desired traversal of the tree.
	*/
	public String traversal (int trav)
	{
	 if (root == null) return "Empty Tree\n";
	 String str = "";
	 switch (trav)
	 {
	  case 1 : str = "Preorder  -> ";
		str = preOrderRec (root,str); break;
	  case 2 : str = "Inorder   -> ";
		str = inOrderRec (root, str); break;
	  case 3 : str = "Postorder -> ";
		str = postOrderRec (root, str); break;
	 }
	 return str + "\n";
	}

	private String preOrderRec (ABPNode root, String string)
	{
		if (root != null)
		{
			string += "  " + root.elem;
			string = preOrderRec (root.left, string);
			string = preOrderRec (root.right, string);
		}
		return string;
	}

	private String inOrderRec (ABPNode root, String string)
	{
		if (root != null)
		{
			string = inOrderRec (root.left, string);
			string += "  " + root.elem;
			string = inOrderRec (root.right, string);
		}
		return string;
	}

	private String postOrderRec (ABPNode root, String string)
	{
		if (root != null)
		{
			string = postOrderRec (root.left, string);
			string = postOrderRec (root.right, string);
			string += "  " + root.elem;
		}
		return string;
	}

	/* New Methods */

	/**
	* The method returns the sum of all elements stored in the tree.
	* @return the sumation of all elements.
	* @throws EmptyTreeException if the tree is empty.
	*/
	public int sumTotal () throws EmptyTreeException
	{
		if (root == null) throw new EmptyTreeException ("Empty Tree");
		return recSumTotal (root);
	}

	/* private recursive method for summing the elements */
	private int recSumTotal (ABPNode root)
	{

		/* implement this auxiliary function */
		if(root == null){ return 0; }
        return root.elem + recSumTotal(root.left) + recSumTotal(root.right);
	}

	/**
	* The method returns the number of elements with the right digit equal to 5 stored in the tree.
	* @return the number of elements ending in 5.
	* @throws EmptyTreeException if the tree is empty.
	*/
	public int countEnd5 () throws EmptyTreeException
	{
		if (root == null) throw new EmptyTreeException ("Empty Tree");
		return recCountEnd5 (root);
	}

	/* private recursive method for counting the elements ending in 5 */
	private int recCountEnd5 (ABPNode root)
	{
		/* implement this auxiliary function */
		if(root == null){
			return 0;
		}

		int count = 0;

		if(root.elem % 10 == 5){
			count++;
		}
		count += recCountEnd5(root.left) + recCountEnd5(root.right);
		return count;
	}

	/**
	* The method returns the sum of the even elements stored in the tree.
	* @return the sumation of even elements.
	* @throws EmptyTreeException if the tree is empty.
	*/
	public int sumEven () throws EmptyTreeException
	{
		if (root == null) throw new EmptyTreeException ("Empty Tree");
		return recSumEven (root);
	}

	/* private recursive method for summing the even elements */
	private int recSumEven (ABPNode root)
	{
		/* implement this auxiliary function */

		// POR TERMINAR
		if(root == null){
			return 0;
		}

		int count = 0;

		if(root.elem % 2 == 0){
			count = root.elem;
		}
		count += recSumEven(root.left) + recSumEven(root.right);
		return count;
	}

	/**
	* The method returns the number of elements multiples of 3 stored in the tree.
	* @return the number of elements multiples of 3.
	* @throws EmptyTreeException if the tree is empty.
	*/
	public int countMult3 () throws EmptyTreeException
	{
		if (root == null) throw new EmptyTreeException ("Empty Tree");
		return recCountMult3 (root);
	}

	/* private recursive method for counting the elements multiples of 3 */
	private int recCountMult3 (ABPNode root)
	{
		/* implement this auxiliary function */
		if(root == null){
			return 0;
		}

		int count = 0;
	
		if(root.elem % 3 == 0){
			count++;
		}
		count += recCountMult3(root.left) + recCountMult3(root.right);
		return count;
	}

	/**
	* The method returns the number of elements in the left subtrees of the tree.
	* @return the number of lefties elements.
	* @throws EmptyTreeException if the tree is empty.
	*/
	
	public int countLefties () throws EmptyTreeException
	{
		if (root == null) throw new EmptyTreeException ("Empty Tree");
		return recCountLefties (root, 0);
	}

	/* private recursive method for counting the elements in left subtrees */
	private int recCountLefties (ABPNode root, int val)
	{
		/* implement this auxiliary function */
		if(root == null) {
			return 0;
		}
		if (root.left == null){
			return recCountLefties(root.right, val);
		} else{
			return recCountLefties(root.left, val++) + recCountLefties(root.right, val++) + 1;
		}
	}
}

