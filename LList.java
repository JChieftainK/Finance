/**
A linked implementation of the ADT list.
@author Frank M. Carrano
 */
public class LList < T > implements ListInterface < T >
{
	private Node firstNode; // reference to first node
	private Node lastNode; //reference to last node
	private int numberOfEntries;
	public LList ()
	{
		clear ();
	} // end default constructor


	public final void clear ()
	{
		firstNode = null;
		lastNode = null;
		numberOfEntries = 0;
	} // end clear


	/*Implementations of the public methods , remove, replace, getEntry, contains,
    getLength, isEmpty, and toArray go here.
    . . .
	 */

	public void add (T newEntry)
	{
		Node newNode = new Node (newEntry);
		if (isEmpty ())
			firstNode = newNode;
		else
			lastNode.setNextNode (newNode);
		lastNode = newNode;
		numberOfEntries++;
	} // end add


	public boolean add (int newPosition, T newEntry)
	{
		boolean isSuccessful = true;
		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
		{
			Node newNode = new Node (newEntry);
			if (isEmpty ())         
			{
			                
				firstNode = newNode;
				lastNode = newNode; 
			}                       
			else if (newPosition == 1)
			{
				newNode.setNextNode (firstNode);
				firstNode = newNode;
			}
			else if (newPosition == numberOfEntries + 1)
			{                                           
				lastNode.setNextNode (newNode);         
				lastNode = newNode;                     
			}                                          
			else
			{
				Node nodeBefore = getNodeAt (newPosition - 1);
				Node nodeAfter = nodeBefore.getNextNode ();
				newNode.setNextNode (nodeAfter);
				nodeBefore.setNextNode (newNode);
			} // end if
			numberOfEntries++;
		}


		else
			isSuccessful = false;
		return isSuccessful;
	} // end add


	public boolean isEmpty ()
	{
		boolean result;
		if (numberOfEntries == 0) // or getLength() == 0
				{
			assert firstNode == null;
			result = true;
				}
		else
		{
			assert firstNode != null;
			result = false;
		} // end if
		return result;
	} // end isEmpty


	public T [] toArray ()
	{
		// the cast is safe because the new array contains null entries
		@ SuppressWarnings ("unchecked")
		T [] result = (T []) new Object [numberOfEntries];
		int index = 0;
		Node currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null))
		{
			result [index] = currentNode.getData ();
			currentNode = currentNode.getNextNode ();
			index++;
		} // end while
			return result;
	} // end toArray

	public int getLength() {
		return numberOfEntries;
	}

	public T remove (int givenPosition)
	{
		T result = null; // return value
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			assert !isEmpty ();
			if (givenPosition == 1) // case 1: remove first entry
			{
				result = firstNode.getData (); // save entry to be removed
				firstNode = firstNode.getNextNode ();
				if (numberOfEntries == 1)                         
					lastNode = null; // solitary entry was removed
			}
			else // case 2: not first entry
			{
				Node nodeBefore = getNodeAt (givenPosition - 1);
				Node nodeToRemove = nodeBefore.getNextNode ();
				Node nodeAfter = nodeToRemove.getNextNode ();
				nodeBefore.setNextNode (nodeAfter);
				result = nodeToRemove.getData (); // save entry to be removed
				if (givenPosition == numberOfEntries)              
					lastNode = nodeBefore; // last node was removed
			} // end if
			numberOfEntries--;
		} // end if


		return result; // return removed entry, or
		// null if operation fails
	} // end remove

	public boolean replace (int givenPosition, T newEntry)
	{
		boolean isSuccessful = true;
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			assert !isEmpty ();
			Node desiredNode = getNodeAt (givenPosition);
			desiredNode.setData (newEntry);
		}

		else
			isSuccessful = false;
		return isSuccessful;
	} // end replace

	public T getEntry (int givenPosition)
	{
		T result = null; // result to return
		{
			assert !isEmpty ();
			result = getNodeAt (givenPosition).getData ();
		} // end if

		return result;
	} // end getEntry

	public boolean contains (T anEntry)
	{
		boolean found = false;
		Node currentNode = firstNode;
		while (!found && (currentNode != null))
		{
			if (anEntry.equals (currentNode.getData ()))
				found = true;
			else
				currentNode = currentNode.getNextNode ();
		} // end while
			return found;
	} // end contains


	// Returns a reference to the node at a given position.
	// Precondition: List is not empty;
	// 1 <= givenPosition <= numberOfEntries.
	private Node getNodeAt (int givenPosition)
	{
		assert (firstNode != null) &&
		(1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;
		// traverse the chain to locate the desired node
		for (int counter = 1 ; counter < givenPosition ; counter++)
			currentNode = currentNode.getNextNode ();
		assert currentNode != null;
		return currentNode;
	} // end getNodeAt


	private class Node // private inner class
	{
		private T data; //Entry in bag
		private Node next; //Call itself to keep track of next entry

		/** @param theEntry is the entry to be saved*/
		private Node(T theEntry){ //First node call
			this(theEntry, null); //Calls a polymorphed method of the same name
		}

		/**	@param theEntry is used to set current Node
		 *	@param nextNode is the used to keep track of chain*/
		private Node(T theEntry, Node nextNode){ //Sets next node and current Node
			data = theEntry; //Sets the entry
			next = nextNode; //Sets the next entry
		}

		/**	@return T is the data
		 *	Accessor method */
		private T getData(){
			return data;
		}

		/**	@param newData used to set data
		 *	Mutator method */
		private void setData(T newData){
			data = newData;
		}

		/**	@return Node is the next node in chain
		 *	Accessor method */		
		private Node getNextNode(){
			return next;
		}

		/**	@param nextNode used to set next node in chain
		 *	Mutator method */
		private void setNextNode(Node nextNode){
			next = nextNode;
		}
	} // end Node
} // end LList
