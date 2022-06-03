import static java.lang.System.*;
import java.io.*;
import java.util.*;	/* NoSuchElementException */

public class TestRecList
{

  public static void main(String[] args) throws IOException
  {
    RecLinkedList<IntId> list = new RecLinkedList<IntId> ();

	list.insertLast (new IntId (40));
    list.insertFirst (new IntId (20));
    list.insertLast (new IntId (20));
    list.insertFirst (new IntId (30));
    list.insertLast (new IntId (20));
    list.insertLast (new IntId (20));
    list.insertLast (new IntId (20));
    list.insertLast (new IntId (40));

    out.println (list.toString ("original"));

	IntId first = null;
	try
	{
		first = list.first();
		assert first.toString().equals("(30/4)");
		out.println ("first = " + first.toString ());
	}
	catch (EmptyListException e)
    { out.printf ("%s\n", e.getMessage ()); }
    
    IntId last = null;
	try
	{
		last = list.last();
		assert last.toString().equals("(40/8)");
		out.println ("last = " + last.toString ());
	}
	catch (EmptyListException e)
    { out.printf ("%s\n", e.getMessage ()); }
	
	out.println ("\nChange element at position 5");
    
    IntId newelem = new IntId (99);
	int pos = 5;
    IntId set = null;
	try
	{
		set = list.set (newelem, pos);
		if (set != null) out.println ("changed element at position " + pos + " " + set.toString () + " -> " + newelem.toString ());
		out.println (list.toString ("after change"));
	}
	catch (EmptyListException e)
    { out.printf ("%s\n", e.getMessage ()); }
    catch (IndexOutOfBoundsException e2)
    { out.printf ("%s\n", e2.getMessage ()); }  

	out.println ("\nRemove last element");

    IntId rem = null;
	try
	{
		rem = list.removeLast ();
		if (rem != null) out.println ("removed element = " + rem.toString ());
		out.println (list.toString ("after remove"));
	}
	catch (EmptyListException e)
    { out.printf ("%s\n", e.getMessage ()); }    
    
	out.println ("\nInsert element after the end");

	IntId i = new IntId (70);
	try
	{
		list.insertPos (i, list.size()+1);
		out.println ("inserted element (out of the end) = " + i.toString ());
		out.println (list.toString ("after insertion"));
	}
	catch (IndexOutOfBoundsException e)
    { out.printf ("%s\n", e.getMessage ()); }	

	out.println ("\nInsert element before the end");

	try
	{
		list.insertPos (i, list.size()-1);
		out.println ("inserted element (before the end) = " + i.toString ());
		out.println (list.toString ("after insertion"));
	}
	catch (IndexOutOfBoundsException e)
    { out.printf ("%s\n", e.getMessage ()); }

	
	out.println ("\nReversed the list");
	RecLinkedList<IntId> reversed_list = new RecLinkedList<IntId> ();
	try{
		reversed_list = list.reverse();
		out.println(reversed_list.toString("reversed"));
	}catch(EmptyListException e){
		out.printf ("%s\n", e.getMessage ());
	}

	out.println ("\nCopy the list");
	RecLinkedList<IntId> clone_list = new RecLinkedList<IntId> ();
	try{
		clone_list = list.copy();
		out.println(clone_list.toString("copied"));
	}catch(EmptyListException e){
		out.printf ("%s\n", e.getMessage ());
	}

	out.println ("\nLook for the index of the first occurrence of an element ");

	// Method FirstIndexOf ---------------------------------
	IntId jf = new IntId (30);
	int firstIndex = -1;
	// testing an invalid element
	try
	{
		firstIndex = list.firstIndexOf(jf);
		out.println ("element " + jf.toStringElement () + " first occurrence at index = " + firstIndex);
	}
	catch (EmptyListException e)
    { out.printf ("%s\n", e.getMessage ()); }
    catch (NoSuchElementException e2)
    { out.printf ("%s\n", e2.getMessage ()); }

	// testing with an element of the list
	try
	{
		firstIndex = list.firstIndexOf(newelem);
		out.println ("element " + newelem.toStringElement () + " first occurrence at index = " + firstIndex);
	}
	catch (EmptyListException e)
    { out.printf ("%s\n", e.getMessage ()); }
    catch (NoSuchElementException e2)
    { out.printf ("%s\n", e2.getMessage ()); }

	// Method LastIndexOf ---------------------------------

	out.println ("\nLook for the index of the last occurrence of a not existent element ");
	list.insertLast(newelem); // inserting one more of the same element in the list to prove that method really works
	IntId jl = new IntId (20);
	int lastIndex = -1;
	// testing an invalid element
	try
	{
		out.println(list.toString("") + " / looking for the pos of the last "+jl);
		lastIndex = list.lastIndexOf(jl);
		out.println ("element " + jl.toStringElement () + " last occurrence at index = " + lastIndex);
	}
	catch (EmptyListException e)
    { out.printf ("%s\n", e.getMessage ()); }	
    catch (NoSuchElementException e2)
    { out.printf ("%s\n", e2.getMessage ()); }

	// testing a valid element
	out.println ("\nLook for the index of the last occurrence of an existent element");
	try
	{
		out.println(list.toString("") + " / looking for the pos of the last "+newelem);
		lastIndex = list.lastIndexOf(newelem);
		out.println ("element " + newelem.toStringElement () + " last occurrence at index = " + lastIndex);
	}
	catch (EmptyListException e)
    { out.printf ("%s\n", e.getMessage ()); }	
    catch (NoSuchElementException e2)
    { out.printf ("%s\n", e2.getMessage ()); }

	// Method SearchDown

	out.println ("\nSearchDown a given element of the list");
	IntId notAListElem = new IntId(10);
	try{
		out.println(list.toString(" "));
		out.printf("Searching down %s: found %s\n",i,list.searchFirst(i));
		out.printf("Searching down %s: found %s\n",notAListElem,list.searchFirst(notAListElem));
	}catch(NoSuchElementException e){
		out.printf ("%s\n", e.getMessage ());
	}catch(EmptyListException e){
		out.printf ("%s\n", e.getMessage ());
	}
	
/* ON MAKING
	out.println ("\nRemove all elements of the list");
	try{
		list.removeAllElements(newelem);
		out.println(clone_list.toString("copied"));
	}catch(EmptyListException e){
		out.printf ("%s\n", e.getMessage ());
	}*/
	out.println ("\nSearchDown for the first occurrence of the element: 1st case - not a list element / 2nd case - a list element");

	IntId k = new IntId (20);
	out.println ("Search for the first occurrence of the element " +  k.toStringElement());
	first = null;
	try
	{
		first = list.searchFirst(k);
		out.println ("first element = " + first.toString ());
	}
	catch (EmptyListException e1)
    { out.printf ("%s\n", e1.getMessage ()); }
    catch (NoSuchElementException e2)
    { out.printf ("%s\n", e2.getMessage ()); }

	out.println ("Search for the first occurrence of the element " +  newelem.toStringElement());
	try
	{
		first = list.searchFirst(newelem);
		out.println ("first element = " + first.toString ());
	}
	catch (EmptyListException e1)
    { out.printf ("%s\n", e1.getMessage ()); }
    catch (NoSuchElementException e2)
    { out.printf ("%s\n", e2.getMessage ()); }

	IntId next = null;
	out.println ("\nSearch for the next occurrence of the element " +  jf.toStringElement());
	// 1st case: non existent element in the list
	try
	{
		next = list.searchNext(jf);
		out.println ("next element = " + next.toString ());
	}
	catch (EmptyListException e1)
    { out.printf ("%s\n", e1.getMessage ()); }
    catch (NoSuchElementException e2)
    { out.printf ("%s\n", e2.getMessage ()); }

	// other case (existent element)
	try
	{
		next = list.searchNext(i);
		out.println ("next element = " + next.toString ());
	}
	catch (EmptyListException e1)
    { out.printf ("%s\n", e1.getMessage ()); }
    catch (NoSuchElementException e2)
    { out.printf ("%s\n", e2.getMessage ()); }


	out.println ("\nSearch for the last occurrence of the element " +  k.toStringElement());
	last = null;
	try
	{
		last = list.searchLast(k);
		out.println ("last element = " + last.toString ());
	}
	catch (EmptyListException e1)
    { out.printf ("%s\n", e1.getMessage ()); }
    catch (NoSuchElementException e2)
    { out.printf ("%s\n", e2.getMessage ()); }

	out.println ("\nRemove the first element of the list");

	IntId firstDel = null;
	try{
		System.out.println(list.toString("before removing"));
		firstDel = list.removeFirstElement(newelem);
		System.out.println("Removed the first element = "+firstDel.toString());
		System.out.println(list.toString("after removing"));
	}catch (EmptyListException e1)
    { out.printf ("%s\n", e1.getMessage ()); }

	IntId testElement = new IntId(40);
	list.insertLast(testElement);
	list.insertLast(testElement);

	IntId lastDel = null;

	out.println ("\nRemove the last element of the list");
	try{
		System.out.println(list.toString("before removing"));
		lastDel = list.removeLastElement(testElement);
		System.out.println("Removed the last element = "+lastDel.toString());
		System.out.println(list.toString("after removing"));
	}catch (EmptyListException e1)
    { out.printf ("%s\n", e1.getMessage ()); }
	
	}
}
