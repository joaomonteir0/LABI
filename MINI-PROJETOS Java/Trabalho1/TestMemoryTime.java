/**
 * Unitary Tests for testing the correctness of Memory class methods
 * 
 * @author Nome e NMec do aluno 1
 * @author Nome e NMec do aluno 2
 * 
 * @version data
 */

import static java.lang.System.*;
import java.io.*;
import java.util.*;

public class TestMemoryTime
{
	public static void main (String[] args)
	{
		Scanner input = new Scanner(System.in);

		Memory<Time> memory = null;
		
		Time time; // element being processed

		/* Make a test with times.csv (8 times in a memory with size 10)
		/* Make a test with empty.csv (empty memory - 0 times in a memory with size 10 */
		out.printf ("\nMaking tests with times.csv and with empty.csv\n");
		try
		{
			memory = loadMemoryFile ();
		}
		catch (IOException e)
		{
			out.printf ("%s\n", e.getMessage ());
			exit (1);
		}

		out.printf ("\nMemory read from the file\n");
		out.printf ("%s\n", memory.toString());

		// Testin the method insertPos(object, pos)
		out.printf ("\nTesting the insertPos method\n");
		time = null;
		try
			{ time = new Time (10,10,20); } // element to be inserted
			catch (InvalidValueException e)
			{ out.printf ("%s\n", e.getMessage ());}

		try
			{ memory.insertPos (time,2); } // inserting in a given POS in the memory
			catch (MemoryFullException e)
			{ out.printf ("%s\n", e.getMessage ()); }
			catch (NullPointerException e)
			{ out.printf ("%s\n", e.getMessage ());}
			catch (IndexOutOfBoundsException e)
			{ out.printf ("%s\n", e.getMessage ());}
		System.out.println(memory.toString());

		// Testing the method delete()
		out.printf ("\nTesting the delete method\n");
		try{
			memory.delete();
			System.out.println("After delete : "+ memory.toString());
		}catch(MemoryEmptyException e){
			out.printf ("%s\n", e.getMessage ());
		}


		// Testin the method deletePos(pos)
		out.printf ("\nTesting the deletePos method\n");
		time = null;
		try
			{ time = new Time (10,10,20); } // element to be inserted
			catch (InvalidValueException e)
			{ out.printf ("%s\n", e.getMessage ());}

		try
			{ 
				memory.deletePos (6); // delete in a given POS in the memory [0, nelem-1]
				System.out.println(memory.toString());
			} 
			catch (MemoryEmptyException e)
			{ out.printf ("%s\n", e.getMessage ());}
			catch (IndexOutOfBoundsException e)
			{ out.printf ("%s\n", e.getMessage ());}
		
		// Testing the smallerElement method
		out.printf ("\nTesting the smallerElement method\n");
		Time smaller = null;
		try
		{
			smaller = memory.smallerElement ();
			out.printf("Smaller time in memory is %s\n", smaller.toString());
			assert smaller.toString().equals("00:00:00") : "Problems in the smaller method";
		}
		catch (MemoryEmptyException e)
		{
			out.printf ("%s\n", e.getMessage ());
		}

		// Testing the biggerElement method
		out.printf ("\nTesting the biggerElement method\n");
		Time bigger = null;
		try
		{
			bigger = memory.biggerElement ();
			out.printf("Bigger time in memory is %s\n", bigger.toString());
		}
		catch (MemoryEmptyException e)
		{
			out.printf ("%s\n", e.getMessage ());
		}
		
		// implement tests to verify the remaining methods
		// Testing the search method
		out.printf ("\nTesting the search method\n");
		Time toSearch = null;
		Time toSearch2 = null;
		int pos;
		try
		{
			toSearch = new Time(12,23,55); // 1st element to be searched
			toSearch2 = new Time(19,12,0); // 2nd element to be searched
		}catch (InvalidValueException e){
			out.printf ("%s\n", e.getMessage ());
		}

		try
		{
			System.out.println("Searching " + toSearch.toString());
			out.printf("Position of the searched element (-1 if not stored in mem): %d\n", memory.search(toSearch));

			System.out.println("Searching " + toSearch2.toString());
			out.printf("Position of the searched element (-1 if not stored in mem): %d\n", memory.search(toSearch2));
		}
		catch (MemoryEmptyException e)
		{
			out.printf ("%s\n", e.getMessage ());
		}
		catch (NullPointerException e)
		{
			out.printf ("%s\n", e.getMessage ());
		}

		// TESTAR COPIAR MEMORIA (AINDA TENHO DE TERMINAR)
		out.printf ("\nTesting the copy method\n");
		Memory<Time> copia =  memory.copy();
		System.out.println(copia);
		
		// Testing the clear method
		out.printf ("\nTesting the clear method\n");
		try{
			memory.clear(); // call clear method
			out.printf ("Memory cleaned : %d elements in memory\n", (memory.getNElem()));
		}
		catch (MemoryEmptyException e)
		{
			out.printf ("%s\n", e.getMessage ());
		}
		
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
}
