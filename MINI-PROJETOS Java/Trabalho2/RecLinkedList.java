/**
 * Parametric Recursive Single Linked List implemented with the modular 
 * programming paradigm using defensive programming with exceptions
 * 
 * @author skeleton by António Manuel Adrego da Rocha
 * 
 * @author Nome e NMec do aluno 1
 * @author Nome e NMec do aluno 2
 * 
 * @version data
 */

import static java.lang.System.*;
import java.util.*;

public class RecLinkedList<T extends Comparable<T>>
{
	private Node<T> first = null;
	private Node<T> last = null;
	private int size = 0;
	
	/* 
	  position of the last successful search of a given element (due to 
	  use of searchFirst and/or searchNext methods) - otherwise null
	*/
	private Node<T> preview = null;
 
	/* private class for the list node */
	private static class Node<T>
	{
		public T elem;
		public Node<T> next;

		/* initialization constructor for a node somewhere in the list */
		public Node (T e, Node<T> n)
		{ elem = e; next = n; }

		/* initialization constructor for a node in the end of the list */
		public Node(T e)
		{ elem = e; next = null; }
	}

	/* private class for the suport node of the deletion methods */
	private class DelNode<T>
	{
		public Node<T> del;	  /* the deleted node */
		public Node<T> list;  /* the remaining list */

		/* initialization constructor */
		public DelNode (Node<T> delNode, Node<T> remList)
		{ del = delNode; list = remList; }
	}

	/**
	* The constructor creates an empty list.
	* {@code RecLinkedList} the empty constructor.
	*/
	public RecLinkedList () { }

	/**
	* The method returns the number of elements stored in the list.
	* @return the number of elements in the list.
	*/
	public int size () { return size; }

	/**
	* The method checks if the list is empty.
	* @return  {@code true} if list empty, otherwise {@code false}.
	*/
	public boolean isEmpty() { return first == null; }

	/**
	* The method returns the first element in the list.
	* @return the first element in the list.
	* @throws EmptyListException if the list is empty.
	*/
	public T first () throws EmptyListException
	{
		if (first != null) return first.elem;
		else throw new EmptyListException ("Empty List");
	}

	/**
	* The method returns the last element in the list.
	* @return the last element in the list.
	* @throws EmptyListException if the list is empty.
	*/
	public T last () throws EmptyListException
	{
		if (last != null) return last.elem;
		else throw new EmptyListException ("Empty List");
	}

	/**
	* The method inserts a new element in the begin of the list.
	* @param e the element to insert.
	*/
	public void insertFirst (T e)
	{
		first = new Node<T> (e, first);
		if (first == null) last = first;
		size++;
	}

	/**
	* The method inserts a new element in the end of the list.
	* @param e the element to insert.
	*/
	public void insertLast (T e)
	{
		Node<T> newest = new Node<T> (e);
		if (first == null){
			first = newest; 
		} else{
			last.next = newest;
		}
		last = newest;
		size++;
	}

	/**
	* The method inserts a new element in the given position of the list.
	* @param e the element to insert.
	* @param pos the position, an integer between {@code 0} and {@code size()-1}.
	* @throws IndexOutOfBoundsException if the the position is invalid.
	*/
	public void insertPos (T e, int pos) throws IndexOutOfBoundsException
	{
		
		if (pos < 0 || pos > size) // pos < 0 || pos > size)
			throw new IndexOutOfBoundsException ("Wrong Index");
		first = insertPos (e, pos, first);
		size++;
	}

	/* private recursive method for inserting the element */
	private Node<T> insertPos (T e, int pos, Node<T> n)
	{
		// e -> conteúdo a ser inserido
		// pos -> posição onde vai ser inserido
		// n = first <-> head (primeiro da lista)
		if (pos == 0) {
			return new Node<T>(e, n);
		}
		n.next = insertPos(e, pos - 1, n.next);
		
		return n;
		/* implement this auxiliary function */
		//return newNode;
	}

	/**
	* The method removes and returns the first element in the list.
	* @return the element removed
	* @throws EmptyListException if the list is empty.
	*/
	public T removeFirst () throws EmptyListException
	{
		if (first == null) throw new EmptyListException ("Empty List");
		Node<T> node = first;
		first = first.next;
		size--;
		if (first == null) last = null;
		return node.elem;
	}

	/**
	* The method removes and returns the last element in the list.
	* @return the element removed
	* @throws EmptyListException if the list is empty.
	*/
	public T removeLast () throws EmptyListException
	{
		if (first == null) throw new EmptyListException ("Empty List");  
		DelNode<T> node = removePosElement (first, size-1); 
		first = node.list;
		if (first == null) last = null;
		return node.del.elem;
	}

	/**
	* The method removes all elements in the list.
	* @throws EmptyListException if the list is empty.
	*/
	public void clear () throws EmptyListException
	{
		if (first == null) throw new EmptyListException ("Empty List"); 
		first = last = preview = null; size = 0;
	}

	/**
	* The method returns a copy of the list.
	* @return the copy constructor.
	* @throws EmptyListException if the list is empty.
	*/
	public RecLinkedList<T> copy () throws EmptyListException
	{
		if (first == null) throw new EmptyListException ("Empty List");
		return copy (first);
	}

	/* private recursive method for copy the elements up-down in the list */
	private RecLinkedList<T> copy (Node<T> n)
	{
		/* implement this auxiliary function */
		RecLinkedList<T> cloneList = new RecLinkedList<T>();
		if(n.next == null){
			cloneList.insertFirst(n.elem);
			return cloneList;
		}		
		if(n.next != null){
			cloneList = cloneList.copy(n.next);
		}
		cloneList.insertFirst(n.elem);
		return cloneList;
	}

	/**
	* The method returns a reverse copy of the list.
	* @return the reverse copy constructor.
	* @throws EmptyListException if the list is empty.
	*/
	public RecLinkedList<T> reverse () throws EmptyListException
	{
		if (first == null) throw new EmptyListException ("Empty List");
		return reverse(first);
	}

	/* private recursive method for copy down-up the elements in the list */
	private RecLinkedList<T> reverse (Node<T> n)
	{
		/* implement this auxiliary function */
		RecLinkedList<T> reversedList = new RecLinkedList<T>();
		if(n.next == null){
			reversedList.insertLast(n.elem);
			return reversedList;
		}		
		if(n.next != null){
			reversedList = reversedList.reverse(n.next);
		}
		reversedList.insertLast(n.elem);
		//System.out.println(reversedList.toString(" "));
		return reversedList;
	}

	/**
	* The method returns the element in the given position of the list.
	* @param pos the position, an integer between {@code 0} and {@code size()-1}.
	* @return the element in the given position of the list.
	* @throws EmptyListException if the list is empty.
	* @throws IndexOutOfBoundsException if the the position is invalid.
	*/
	public T get (int pos) throws EmptyListException, IndexOutOfBoundsException
	{
		if (first == null) throw new EmptyListException ("Empty List");
		if (pos < 0 || pos >= size)
			throw new IndexOutOfBoundsException ("Wrong Index");
		return get (first, pos).elem;
	}

	/* private recursive method for search the element in the given position */
	private Node<T> get (Node<T> n, int pos)
	{
		if (pos == 0) return n;
		return get (n.next, pos-1);
	}

	/**
	* The method changes the element in the given position and returns the old element.
	* @param e the element to put in the given position of the list.
	* @param pos the position, an integer between {@code 0} and {@code size()-1}.
	* @return the old element in the given position of the list.
	* @throws EmptyListException if the list is empty.
	* @throws IndexOutOfBoundsException if the the position is invalid.
	*/
	public T set (T e, int pos) throws EmptyListException, IndexOutOfBoundsException
	{
		if (first == null) throw new EmptyListException ("Empty List");
		if (pos < 0 || pos >= size)
			throw new IndexOutOfBoundsException ("Wrong Index");
		
		Node<T> node = get (first, pos);
		T temp = node.elem;
		node.elem = e;
		return temp;
	}

	/**
	* The method checks if the element exists in the list.
	* @param e an element.
	* @return {@code true} if the element exists and {@code false} otherwise.
	* @throws EmptyListException if the list is empty.
	*/
	public boolean exists (T e) throws EmptyListException
	{
		if (first == null) throw new EmptyListException ("Empty List");
		return exists_down (first, e);
	}

	/* private recursive boolean method for search an element in the list */
	private boolean exists_down (Node<T> n, T e)
	{
		if (n == null) return false;
		if (n.elem.equals(e)) return true; 
		return exists_down (n.next, e);
	}

	/**
	* The method searchs the first element in the list.
	* @param e an element.
	* @return the first ocurrency of the given element in the list.
	* @throws EmptyListException if the list is empty.
	* @throws NoSuchElementException if the element does not exist.
	*/   
	public T searchFirst (T e) throws EmptyListException, NoSuchElementException
	{
		if (first == null) throw new EmptyListException ("Empty List");
		return searchDown (first, e);
	}

	/* private recursive method for search down the element in the list */
	private T searchDown (Node<T> n, T e) throws NoSuchElementException
	{
		/* implement this auxiliary function */
		if (n.next == null){
			if(n.elem == e){
				return n.elem;
			}else{
				throw new NoSuchElementException("No Such Element");
			}
		}
 
        if (n.elem == e)
            return n.elem;
 
		return searchDown(n.next, e);
	}

	/**
	* The method searchs the next element in the list.
	* @param e an element.
	* @return the next ocurrency of the given element in the list.
	* @throws EmptyListException if the list is empty.
	* @throws NoSuchElementException if the element does not exist.
	*/   
	public T searchNext (T e) throws EmptyListException, NoSuchElementException
	{
		if (first == null) throw new EmptyListException ("Empty List");	
		if (preview == null || !preview.elem.equals(e)) return searchDown (first, e);
		else return searchDown (preview.next, e);
	}

	/**
	* The method searchs the last element in the list.
	* @param e an element.
	* @return the last ocurrency of  the given element in the list.
	* @throws EmptyListException if the list is empty.
	* @throws NoSuchElementException if the element does not exist.
	*/   
	public T searchLast (T e) throws EmptyListException, NoSuchElementException
	{
		if (first == null) throw new EmptyListException ("Empty List");
		
		Node<T> node = searchUp (first, e);
		if (node.next == null) throw new NoSuchElementException ("No Such Element");
		else return node.elem;
	}

	/* private recursive method for search up the element in the list */
	private Node<T> searchUp (Node<T> n, T e) throws NoSuchElementException
	{
		/* implement this auxiliary function */
		if(size < 2){ // size = 1 && list not empty, posições = [0]
			if(n.elem == e){
				preview = null; // resetar o preview
				return n;
			}
		}
		int p = 0;
		
		while(n.next != preview){ // preview começa a null, se n.next != null ou do ultimo valor
			if(p < size){ // manter dentro do size da lista
				n = n.next;
				p++;
			}
		}
		preview = n; // salvar a ultima posição encontrada (vai descendo)		
		if(preview.elem  == e){
			return preview;

		}else if(preview.elem != e ){
			if(first.next == preview){
				if(first.elem == e){
					preview = null; // resetar o preview
					return preview;
				}
			}else{
				return searchUp(first, e);
			}
		}else{
			return null;
		}
		if(preview == e){
			return preview;	
		}
		return preview;		
	}

	/**
	* The method removes all occurrences of the element in the list.
	* @param e an element.
	* @throws EmptyListException if the list is empty.
	*/
	public void removeAllElements (T e) throws EmptyListException
	{
		if (first == null) throw new EmptyListException ("Empty List");
		first = removeAllElements (first, e);
		if (first == null) last = null;
	}

	/* private recursive method for delete the elements of the list */
	private Node<T> removeAllElements (Node<T> n, T e)
	{
		/* implement this auxiliary function */
		if(n.next == null){
			n.elem = null;
			return n;
		}		
		if(n.next != null){
			n.elem = null;
			removeAllElements(n.next, n.next.elem);
		}
		return n;
	}

	/**
	* The method removes and returns the first occurrence of the element in the list.
	* @param e an element.
	* @return the element removed
	* @throws EmptyListException if the list is empty.
	*/
	public T removeFirstElement (T e) throws EmptyListException
	{
		if (first == null) throw new EmptyListException ("Empty List");
		DelNode<T> node = removeFirstElement (first, e);
		first = node.list;
		if (first == null) last = null;
		if (node.del != null) return node.del.elem;
		else throw new NoSuchElementException ("No Such Element");
	}

	/* private recursive method for delete the first element of the list */
	private DelNode<T> removeFirstElement (Node<T> n, T e)
	{
		Node<T> temp, current;
		/* implement this auxiliary function */
		if(n.next != null){
			if(n.elem != e){
				return removeFirstElement (n.next, e);
			}
			if(n.elem == e){
				temp = first;
				current = null;
				Node<T> del = new Node<T>(n.elem);
				int p = firstIndexOf(first, del.elem, 0);

				for(int i = 0; i < p; i++){  
					current = temp;  
					temp = temp.next;  
				}

				if(temp.next != null){
					current.next = temp.next;
				}
				temp = null;
				size--;
				DelNode<T> elemento = new DelNode<>(del, first);
				return elemento;
			}
		}

		if(n.next == null){
			if(n.elem == e){
				temp = first;
				current = null;
				Node<T> del = new Node<T>(n.elem);
				for(int i = 0; i < size-1; i++){  
					current = temp;  
					temp = temp.next;  
				}
				if(temp.next != null){
					current.next = temp.next;
				}
				temp = null;
				size--;
				DelNode<T> elemento = new DelNode<>(del, first);
				return elemento;
			}
		}
		return null;
	}

	/**
	* The method removes and returns the last occurrence of the element in the list.
	* @param e an element.
	* @return the element removed
	* @throws EmptyListException if the list is empty.
	*/
	public T removeLastElement (T e) throws EmptyListException
	{
		if (first == null) throw new EmptyListException ("Empty List");	
		DelNode<T> node = removeLastElement (first, e);
		first = node.list;
		if (first == null) last = null;
		if (node.del != null) return node.del.elem;
		else throw new NoSuchElementException ("No Such Element");
	}

	/* private recursive method for delete the last element of the list */
	private DelNode<T> removeLastElement (Node<T> n, T e)
	{
		Node<T> temp, current;
		/*System.out.println("Current n = "+n.elem);
		 implement this auxiliary function */
		/*if(n.next != null){
			if(n.elem != e){
				return removeLastElement (n.next, e);
			}
			if(n.elem == e){
				temp = first;
				current = null;
				Node<T> del = new Node<T>(n.elem);
				int p = lastIndexOf(first, n.elem, 0);
				System.out.println(p);
				for(int i = 0; i < p; i++){  
					current = temp;  
					temp = temp.next;  
				}
				if(temp.next != null){
					current.next = temp.next;
				}
				temp = null;
				size--;
				DelNode<T> elemento = new DelNode<>(del, first);

				return elemento;
			}
		}
		if(n.next == null){
			if(n.elem == e){
				temp = first;
				current = null;
				for(int i = 0; i < size-1; i++){  
					current = temp;  
					temp = temp.next;  
				}
				if(temp.next != null){
					current.next = temp.next;
				}
				temp = null;
				size--;
				DelNode<T> elemento = new DelNode<>(n, first);
				return elemento;
			}
		}*/
		DelNode<T> elemento = new DelNode<>(n, first);
		return elemento;
		//return null;
	}

	/**
	* The method removes and returns the element in the given position of the list.
	* @param pos the position, an integer i between {@code 0} and {@code size()-1}.
	* @return the element removed.
	* @throws EmptyListException if the list is empty.
	* @throws IndexOutOfBoundsException if the the position is invalid.
	*/
	public T removePosElement (int pos)
		throws EmptyListException, IndexOutOfBoundsException
	{
		if (first == null) throw new EmptyListException ("Empty List");	
		if (pos < 0 || pos >= size)
			throw new IndexOutOfBoundsException ("Wrong Index");
	
		DelNode<T> node = removePosElement (first, pos);
		first = node.list;
		if (first == null) last = null;
		return node.del.elem;
	}

	/* private recursive method for delete the element in the given position */
	private DelNode<T> removePosElement (Node<T> n, int pos)
	{
		if (pos == 0)
		{
			DelNode<T> node = new DelNode<T> (n, n.next);
			n.next = null; size--;
			return node;
		}
		DelNode<T> node = removePosElement (n.next, pos - 1);
		n.next = node.list; node.list = n;
		if (n.next == null) last = n;
		return node;
	}

	/**
	* The method finds and returns the position of the first occurrence of the element in the list.
	* @param e the element to search for.
	* @return the position of the element if exists.
	* @throws EmptyListException if the list is empty.
	* @throws NoSuchElementException if the element does not exist.
	*/
	public int firstIndexOf (T e) throws EmptyListException, NoSuchElementException
	{
		if (first == null) throw new EmptyListException ("Empty List");
		return firstIndexOf (first, e, 0);
	}

	/* private recursive method to find the position of the first occurrence of the given element */  
	private int firstIndexOf (Node<T> n, T e, int p) throws NoSuchElementException
	{
		
		/* implement this auxiliary function */
        if (n.next == null){
			if(n.elem == e){
				return p;
			}else{
				throw new NoSuchElementException("No Such Element");
			}
		}
 
        if (n.elem == e)
            return p;
 
        return firstIndexOf(n.next, e, ++p);

	}

	/**
	* The method finds and returns the position of the last occurrence of the element.
	* @param e the element to search for.
	* @return the position of the element if exists.
	* @throws EmptyListException if the list is empty.
	* @throws NoSuchElementException if the element does not exist.
	*/
	public int lastIndexOf (T e) throws EmptyListException, NoSuchElementException
	{
		if (first == null) throw new EmptyListException ("Empty List");
		int pos = lastIndexOf (first, e, 0);
		if (pos == -1) throw new NoSuchElementException ("No Such Element");
		return pos;
	}

	/* private recursive method to find the position of the first occurrence of the given element */  
	private int lastIndexOf (Node<T> n, T e, int p) throws NoSuchElementException
	{
		//System.out.println(("Searching "+e+" / current "+n.elem));
		/* implement this auxiliary function */
		if(size < 2){ // size = 1 && list not empty, posições = [0]
			if(n.elem == e){
				preview = null; // resetar o preview
				return 0;
			}
		}
		while(n.next != preview){ // preview começa a null, se n.next != null ou do ultimo valor
			if(p < size){ // manter dentro do size da lista
				n = n.next;
				p++;
			}
		}
		preview = n; // salvar a ultima posição encontrada (vai descendo)		
		if(preview.elem  == e){
			preview = null;
			return p;
		}else if(preview.elem != e ){
			if(first.next == preview){
				if(first.elem == e){
					preview = null; // resetar o preview
					return 0;
				}else{
					preview = null; // resetar o preview
					return -1;
				}
			}else{
				return lastIndexOf(first, e, 0);
			}
		}else{
			preview = null; // resetar o preview
			return -1;
		}

	}
	
	/**
	* The method creates a string representing the contents of the list.
	* preceded by a message and its size.
	* @param msg the message to customize the printing.
	* @return the string representing the list.
	*/
	public String toString (String msg)
	{
		String str = "List " + msg +  " with " + size + " elements : ";
		for (Node<T> node = first; node != null; node = node.next)
			str += node.elem.toString() + " ";
		return str;
	}

	/**
	* The method checks if two lists have the same size and elements, despite they could be in diferent places of the lists.
	* @param obj the object to be compared with that must be of list kind.
	* @return  {@code true} if lists are the same or equal, otherwise {@code false}.
	*/
 @Override
	@SuppressWarnings ("unchecked")
	public boolean equals (Object obj)
	{
		if (this == obj) return true;
		
		RecLinkedList<T> list = (RecLinkedList<T>) obj;

		if (this.size() != list.size()) return false;
		
		for (Node<T> node = this.first; node != null; node = node.next)
		{
			boolean found = false;
			try
			{
				found = list.exists (node.elem);
			}
			catch (EmptyListException e) {  }

			if (!found) return false;
		}
		return true;
	}
}
