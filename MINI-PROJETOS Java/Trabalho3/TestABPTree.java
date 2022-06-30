import static java.lang.System.*;
import java.util.*;
import java.io.*;

public class TestABPTree
{
	public static void main (String[] args)
	{
		boolean teste; int value, min, max, result; String str;
		Scanner input = new Scanner(System.in);
		ABPTree tree = new ABPTree ();
		int[] values = new int[]{50, 80, 30, 60, 70, 40, 10, 90, 20, 90, 5, 85, 15, 25, 35, 50};

		out.printf ("Testing with a tree or with an empty tree [tree/ ]? ");
		str = input.nextLine();
		if (str.equals ("tree"))
		{
		
			for (int i = 0; i < values.length; i++)
			{
				try { tree.insert (values[i]);	}
				catch (RepeatedElementException e)
				{ out.printf ("%d %s\n", values[i], e.getMessage ()); }
			}
		}

		out.printf ("Hierachical Tree Representation\n\n");
		out.printf ("%s\n", tree.toString());
		out.printf ("\nStatistics of the Tree\n\n");
		out.printf ("Number of nodes -> %d\n", tree.size ());
		out.printf ("High -> %d\n", tree.height ());

		try
		{
			min = tree.min();
			out.printf ("Smaller -> %d\n", min);
			assert ((Integer) min).toString().equals("5");
		}
		catch (EmptyTreeException e)
		{ out.printf ("%s\n", e.getMessage ()); }

		try
		{
			max = tree.max();
			out.printf ("Bigger -> %d\n", max);
			assert ((Integer) max).toString().equals("90");
		}
		catch (EmptyTreeException e)
		{ out.printf ("%s\n", e.getMessage ()); }

		out.printf ("\nTree Traversals\n\n");
		out.printf ("%s", tree.traversal (1));
		out.printf ("%s", tree.traversal (2));
		out.printf ("%s", tree.traversal (3));

		out.printf ("\nResults of the Implemented Methods\n\n");
		
		// Testing recSumTotal
		try
		{
			result = tree.sumTotal();
			out.printf ("Total sum -> %d\n", result);
			assert ((Integer) result).toString().equals("615");
		}
		catch (EmptyTreeException e)
		{ out.printf ("%s\n", e.getMessage ()); }

		try
		{
			result = tree.countEnd5();
			out.printf ("Elements ending in 5 -> %d\n", result);
			assert ((Integer) result).toString().equals("5");
		}
		catch (EmptyTreeException e)
		{ out.printf ("%s\n", e.getMessage ()); }

		try
		{
			result = tree.sumEven();
			out.printf ("Even elements sum -> %d\n", result);
			assert ((Integer) result).toString().equals("450");
		}
		catch (EmptyTreeException e)
		{ out.printf ("%s\n", e.getMessage ()); }

		try
		{
			result = tree.countMult3();
			out.printf ("Multiples of 3 -> %d\n", result);
			assert ((Integer) result).toString().equals("4");
		}
		catch (EmptyTreeException e)
		{ out.printf ("%s\n", e.getMessage ()); }
		 
		try
		{
			result = tree.countLefties();
			out.printf ("Elements in left subtrees -> %d\n", result);
			assert ((Integer) result).toString().equals("7");
		}
		catch (EmptyTreeException e)
		{ out.printf ("%s\n", e.getMessage ()); }
		
  }
}
