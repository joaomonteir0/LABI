/**
 * Simulaton program for testing the methods of the parametric RAM Memory for Time elements
 * 
 * @author António Manuel Adrego da Rocha
 * 
 * @version 31th March of 2022
 */

import static java.lang.System.*;
import java.util.*;
import java.io.*;

public class SimMemoryTime
{
	public static void main (String[] args)
	{
		Scanner input = new Scanner(System.in);
		int num, index, size, option; String filename, answer;
		Memory<Time> memory = null;
		Time time, smaller, bigger; // element being processed

		do
		{
			if (memory == null) option = initialMenu ();
			else option = fullMenu ();

			if (option < 3 && memory != null)
			{
				out.printf("Do you want to save the current memory on a file (y/n)? ");
				answer = input.nextLine();
				if (answer.charAt(0) == 'y')
					try
					{ saveMemoryFile (memory); }
					catch (IOException e)
					{ out.printf ("%s\n", e.getMessage ()); }
			}

			switch (option)
			{
				case 1 :	try
							{
								out.printf("Memory dimension? ");
								size = input.nextInt(); 
								memory = new Memory<Time> (size);
							}
							catch (NegativeArraySizeException e)
							{ out.printf ("%s\n", e.getMessage ()); }
							break;

				case 2 : 	try
							{ memory = loadMemoryFile (); }
							catch (IOException e)
							{ out.printf ("%s\n", e.getMessage ()); }
							break;

				case 3 :	try
							{ saveMemoryFile (memory); }
							catch (IOException e)
							{ out.printf ("%s\n", e.getMessage ()); }
							break;

				case 4 :	out.printf("Insert in the end of the memory (y/n)? ");
							answer = input.nextLine();
							if (answer.charAt(0) == 'y')
								try
								{
									time = readTime ();
									memory.insert (time);
									out.printf ("Time %s inserted in the end of the memory\n", time.toString());
								}
								catch (NullPointerException e)
								{ out.printf ("%s\n", e.getMessage ()); }
								catch (MemoryFullException e)
								{ out.printf ("%s\n", e.getMessage ()); }
							else
							{
								index = readInteger ("Index of the element to insert? ");
								try
								{
									time = readTime ();
									memory.insertPos (time, index);
									out.printf ("Time %s inserted in the position %d of the memory\n", time.toString(), index);
								}
								catch (NullPointerException e)
								{ out.printf ("%s\n", e.getMessage ()); }
								catch (MemoryFullException e)
								{ out.printf ("%s\n", e.getMessage ()); }
								catch (IndexOutOfBoundsException e)
								{ out.printf ("%s\n", e.getMessage ()); }
							}
							break;

				case 5 :	out.printf("Delete in the end of the memory (y/n)? ");
							answer = input.nextLine();
							if (answer.charAt(0) == 'y')
								try
								{
									time = memory.delete ();
									out.printf ("Time %s deleted from the end of the memory\n", time.toString ());
								}
								catch (MemoryEmptyException e)
								{ out.printf ("%s\n", e.getMessage ()); }
							else
							{
								index = readInteger ("Index of the element to delete? ");
								try
								{
									time = memory.deletePos (index);
									out.printf ("Time %s deleted from the position %d of the memory\n", time.toString (), index);
								}
								catch (MemoryEmptyException e)
								{ out.printf ("%s\n", e.getMessage ()); }
								catch (IndexOutOfBoundsException e)
								{ out.printf ("%s\n", e.getMessage ()); }						
							}	
							break;

				case 6 :	index = readInteger ("Index of the element to change? ");
							try
							{
								time = memory.getElement (index);
								out.printf ("Current element %s\n", time.toString ());
							}
							catch (IndexOutOfMemoryException e)
							{ out.printf ("%s\n", e.getMessage ()); }
							try
							{
								out.printf ("New element\n");
								time = readTime ();
								memory.setElement (index, time);	
							}
							catch (NullPointerException e)
							{ out.printf ("%s\n", e.getMessage ()); }
							catch (IndexOutOfMemoryException e)
							{ out.printf ("%s\n", e.getMessage ()); }
							break;

				case 7 :	try
							{
								time = readTime ();
								index = memory.search (time);
								if (index != -1)
									out.printf ("The element is stored in position %d\n", index);
								else
									out.printf ("The element is not in the memory\n");
							}
							catch (NullPointerException e)
							{ out.printf ("%s\n", e.getMessage ()); }
							catch (MemoryEmptyException e)
							{ out.printf ("%s\n", e.getMessage ()); }
							break;

				case 8 :	try
							{
								smaller = memory.smallerElement ();
								bigger = memory.biggerElement ();
								out.printf ("Smaller time in memory is %s", smaller.toString());
								out.printf (" and Bigger time in memory is %s\n", bigger.toString());
							}
							catch (MemoryEmptyException e)
							{ out.printf ("%s\n", e.getMessage ()); }
							break;


				case 9 :	printMemory (memory);
			}
			if (option != 0)
			{
				out.printf("Click enter to continue ");
				input.nextLine();
			}
		} while (option != 0);
	}

	// auxiliary functions

	public static int fullMenu ()
	{
		Scanner input = new Scanner(System.in);
		int option;

		// clean the screen
		clearScreen();
		out.printf (" 1 - Create an empty memory\n");
		out.printf (" 2 - Retrieve the memory from a file\n");
		out.printf (" 3 - Store the memory in a file\n");
		out.printf (" 4 - Insert a new element in the memory\n");
		out.printf (" 5 - Delete an element from the memory\n");
		out.printf (" 6 - Change an element in the memory\n");
		out.printf (" 7 - Search an element in the memory\n");
		out.printf (" 8 - Smaller and Bigger elements of the memory\n");
		out.printf (" 9 - Print the memory on the screen\n");
		out.printf (" 0 - Exit program\n");

		do   //  read the menu option with validation
		{
			option = readInteger ("Option? ");

		} while ((option < 0) || (option > 9));

		return option;
	}

	public static int initialMenu ()
	{
		Scanner input = new Scanner(System.in);
		int option;

		// clean the screen
		clearScreen();
		out.printf (" 1 - Create an empty memory\n");
		out.printf (" 2 - Retrieve the memory from a file\n");
		out.printf (" 0 - Exit program\n");

		do   //  read the menu option with validation
		{
			option = readInteger ("Option? ");
		} while ((option < 0) || (option > 2));

		return option;
	}

	/* Using the Memory toString method */
	public static void printMemory (Memory<Time> pMem)
	{
		if (pMem == null) { out.printf ("Memory was not created yet\n"); return; }
		out.printf ("%s\n", pMem.toString ());
	}


	/* If the file is corrupted and being impossible to load the memory with all is useful elements the memory will not be created. */
	public static Memory<Time> loadMemoryFile () throws IOException
	{
		Scanner input = new Scanner(System.in);
		out.printf("Filename to acquire the memory? ");
		String filename = input.nextLine();		
		
		Memory<Time> memory;
		Scanner stream = new Scanner (new File (filename)); // opening the file
		int size = Integer.parseInt (stream.nextLine ()); // reading the file dimension (first line)
		int ne = Integer.parseInt (stream.nextLine ()); // reading the number of useful elements (second line)

		try
		{ memory = new Memory<Time> (size); } // creating the empty memory with the given size
		catch (NegativeArraySizeException e)
		{ out.printf ("%s\n", e.getMessage ()); stream.close (); return null; }

		for (int i = 0; i < ne; i++) // reading the time information (remaining lines)
		{
			// reading each time information and creating the Time object
			String line = stream.nextLine ();
			String [] timevalues = line.split (":");
			if (timevalues.length != 3)
			{
				stream.close ();
				out.printf ("File format incorrect\n");
				return null;
			}
			
			int hours = Integer.parseInt (timevalues[0]);
			int minutes = Integer.parseInt (timevalues[1]);
			int seconds = Integer.parseInt (timevalues[2]);

			Time time = null;
			try
			{ time = new Time (hours, minutes, seconds); }
			catch (InvalidValueException e)
			{ out.printf ("%s\n", e.getMessage ()); stream.close (); return null; }

			try
			{ memory.insert (time); } // inserting in the end of the memory
			catch (MemoryFullException e)
			{ out.printf ("%s\n", e.getMessage ()); stream.close (); return null;}
			catch (NullPointerException e)
			{ out.printf ("%s\n", e.getMessage ()); stream.close (); return null;}
		}

		stream.close (); // closing the file
		return memory; // returning the memory
	}

	/* If an exception is raised in the writing cycle that means the memory is corrupted. In this case the file is closed and erased. */
	public static void saveMemoryFile (Memory<Time> pMem) throws IOException
	{
		Scanner input = new Scanner(System.in);

		if (pMem == null) return;
		
		out.printf("Filename to save the memory? ");
		String filename = input.nextLine();

		PrintWriter stream = new PrintWriter (new FileWriter (filename));
		stream.printf ("%d\n", pMem.getSize ()); // writing the dimension (first line)
		int ne = pMem.getNElem ();
		stream.printf ("%d\n", ne); // writing the number of useful elements (second line)

		try  // acessing the memory elements and writing the hours, minutes and seconds (remaining lines)
		{
			for (int i = 0; i < ne; i++)
			{
				Time time = pMem.getElement (i); // acessing the element in the memory

				// writing the time (hours minutes seconds) in the file in one line separated by spaces
				stream.printf ("%2d:%2d:%2d\n", time.getHours(), time.getMinutes(), time.getSeconds());
			}
		}
		catch (IndexOutOfMemoryException e)
		{   // close and erase the file
			out.printf ("%s\n", e.getMessage ()); stream.close ();
			File file = new File (filename); file.delete ();
		}

		stream.close (); // fechar o ficheiro
	}
 
	// function to create and return a time object from the keyboard
	public static Time readTime ()
	{
		Scanner input = new Scanner(System.in);

		// reading hours with validation from the keyboard
		int hours;
		do
		{
			out.printf("Hours? ");
			hours = input.nextInt();
		} while (hours < 0 || hours > 23);

		// reading minutes with validation from the keyboard
		int minutes;
		do
		{
			out.printf("Minutes? ");
			minutes = input.nextInt();
		} while (minutes < 0 || minutes > 59);

		// reading seconds with validation from the keyboard
		int seconds;
		do
		{
			out.printf("Seconds? ");
			seconds = input.nextInt();
		} while (seconds < 0 || seconds > 59);

		// create and return the time read from terminal
		try
		{
			Time time = new Time (hours, minutes, seconds);
			return time;
		}
		catch (InvalidValueException e)
		{
			out.printf ("%s\n", e.getMessage ());
			return null;
		}	
	}

	public static int indexMemory (String pMsg)
	{
		Scanner input = new Scanner(System.in);

		out.printf("Index of the element to %s? ", pMsg);
	
		String index = input.nextLine();
		return Integer.parseInt (index);
	}

	public static int readInteger (String pMsg)
	{
		Scanner input = new Scanner(System.in);
		int value = 0; boolean check;

		do
		{
			check = true;
			out.printf ("%s", pMsg);
			String answer = input.nextLine ();
	
			try
			{ value = Integer.parseInt (answer); }
			catch (NumberFormatException e)
			{ check = false; }
			catch (NullPointerException e)
			{ check = false; }
		} while (!check);

		return value;
	}

	public static void clearScreen() {  
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
	}
}
