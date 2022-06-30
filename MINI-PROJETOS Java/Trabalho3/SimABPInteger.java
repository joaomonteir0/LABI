import static java.lang.System.*;
import java.util.*;
import java.io.*;

public class SimABPInteger
{
	public static void main (String[] args)
	{
		int option, value, min, max, del, search;
		String str; String[] operations = new String []{"insert", "delete", "search"};
		ABPTree tree = new ABPTree ();
		Scanner input = new Scanner(System.in);

		/* COISAS QUE EU PUS AQUI */
		int result;

		out.printf ("%s", tree);

		while (true)
		{
			out.printf ("\nOperation [0 stop / 1 insert / 2 delete / 3 search]? ");
			option = input.nextInt();
			if (option < 0 || option > 3) continue;
			else if (option == 0) break;
			
			out.printf ("Element to %s in the tree [0 to stop]\n", operations[option-1]);
			out.printf ("Value? ");
			value = input.nextInt();

			switch (option)
			{	
				case 1:	try
						{
							tree.insert (value);
							out.printf ("Value %d inserted in the tree\n\n", value);
							out.printf ("%s\n", tree.toString());
						}
						catch (RepeatedElementException e)
						{ out.printf ("%s\n", e.getMessage ()); }
						break;
				case 2: try
						{
							del = tree.delete (value);
							out.printf ("\nValue %d deleted from the tree\n\n", del);
							out.printf ("%s\n", tree.toString());
						}
						catch (EmptyTreeException e1)
						{ out.printf ("%s\n", e1.getMessage ()); }		
						catch (NoSuchElementException e2)
						{ out.printf ("%s\n", e2.getMessage ()); }
						break;
				case 3:	try
						{
							search = tree.search (value);
							out.printf ("\nvalue %d stored in the tree\n\n", value);
						}
						catch (EmptyTreeException e1)
						{ out.printf ("%s\n", e1.getMessage ()); }
						catch (NoSuchElementException e2)
						{ out.printf ("%s\n", e2.getMessage ()); }
						break;
				default: out.printf ("Wrong operation");			
			}

			out.printf ("Number of nodes in the tree = %d\n", tree.size ());
			out.printf ("High of the tree = %d\n", tree.height ());
			try
			{
				min = tree.min();
				out.printf ("Smaller element of the tree = %d\n", min);
			}
			catch (EmptyTreeException e)
			{ out.printf ("%s\n", e.getMessage ()); }

			try
			{
				max = tree.max();
				out.printf ("Larger element of the tree = %d\n", max);
			}
			catch (EmptyTreeException e)
			{ out.printf ("%s\n", e.getMessage ()); }

			// PUS PARA TESTAR O recSumTotal
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
		
		}

		out.printf ("\nTree Traversals\n\n");
		out.printf ("%s", tree.traversal (1));
		out.printf ("%s", tree.traversal (2));
		out.printf ("%s", tree.traversal (3));
	}					
}

