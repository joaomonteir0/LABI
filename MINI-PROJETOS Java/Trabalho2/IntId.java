/**
 * IntId - an abstract data type (ADT) constituted by a value (integer) and 
 * an ordinal number (also integer) generated automatically and that is 
 * used to identify different elements with the same value.
 * 
 * The value serves as the key to insert the element in an ordered data
 * structure like a linked list or a tree. Therefore this ADT implements
 * the Comparable interface.
 * 
 * @author António Manuel Adrego da Rocha
 * @version 9th May of 2022
 */

public class IntId implements Comparable <IntId>
{
	private static int size = 0;

	private int elem;
	private int id;

	/**
	* The constructor creates an element with the given value.
	* And an ordinal generated automatically according with the number of elements created before it. 
	* @param v the value.
	*/
	public IntId (int v)
	{
		size++;  
		elem = v;
		id = size;
	}

	/**
	* The relational operator (method that compares only the attribute elem).
	* @param e the element to compare with.
	* @return {@code positive value} if v is bigger than e, {@code negative value} if v is smaller than  e and {@code 0} if v is equal to e.
	*/ 
	public int compareTo (IntId e)
	{
		return this.elem - e.elem;
	}

	/**
	* The method checks if two values are equal (but only compares the attribute elem).
	* @return {@code true} in affirmative case, otherwise {@code false}.
	*/ 
	public boolean equals (Object obj)
	{
		if (this == obj) return true;
		if (!(obj instanceof IntId)) return false;
    
		IntId o = (IntId) obj;
		return o.elem == this.elem;
	}

	/**
	* The method creates a string representing the complete element (value and order).
	* @return time as a string in the format (elem/id).
	*/  
	public String toString ()
	{
		return "(" + elem + "/" +  id + ")";
	}

	/**
	* The method creates a string representing the element value.
	* @return time as a string in the format (elem).
	*/  
	public String toStringElement ()
	{
		return "(" + elem + ")";
	}
}


