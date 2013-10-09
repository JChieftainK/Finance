public class LinkedBag <T> implements BagInterface <T>{
	private Node firstNode; //References to the first node
	private int numberOfEntries; //Number of entries in bag
	
	public LinkedBag(){ //Default Constructor
		firstNode = null; //sets firstnode in chain to null
		numberOfEntries = 0; //sets number of entries to 0
	}
	
	/** Gets the current number of entries in this bag.
	*	@return the integer number of entries currently in the bag*/
	public int getCurrentSize(){
		return numberOfEntries; 
	}
	
	/** Sees whether this bag is full.
	*	@return true if the bag is full, or false if not*/
	public boolean isFull(){
		return false; //will always be false due to no capacity to hit
	}
	
	/** Sees whether this bag is empty
	*	@return true if the bag is empty, or false if not*/
	public boolean isEmpty(){
		return numberOfEntries == 0; //tests for 0 entries, returns boolean
	}
	
	/** Adds a new entry to this bag.
	*	@param newEntry the object to be added as a new entry
	*	@return true if the addition is successful, or false if not*/
	public boolean add(T newEntry){
		Node newNode = new Node(newEntry); //Add to the beginning of chain.
		newNode.setNextNode(firstNode); //new node references rest of chain, new node is at beginning
		firstNode = newNode; //sets the first node to the current node
		numberOfEntries++; //increments number of entries to reflect chain
		return true; //always true because it has no size limit
	}
	
	/** Removes one unspecified entry from this bag, if possible.
	*	@return either the removed entry,  if the removal was successful, or null*/
	public T remove(){
		T result = null; //creates a null object
		if(firstNode != null){ //tests if empty chain 
			result = firstNode.getData(); //sets result to first node
			firstNode = firstNode.getNextNode(); //sets second node to first
			numberOfEntries--; //decrements number of entries to reflect new chain
		}
		return result; //returns removed entry
	}
	
	/** Removes one occurrence of a given entry from this bag, if possible.
	*	@param anEntry the entry to be removed
	*	@return true if the removal was successful, or false if not*/
	public boolean remove(T anEntry){
		boolean result = false; //assume it is not there or null
		Node nodeN = getReferenceTo(anEntry); //get node if there
		if(nodeN != null){
			nodeN.setData(firstNode.getData());//replace current node with first node
			remove(); //removes first node
			result = true; //successfully removed node
		}
		return result;
	}
	
	/**Removes all entries from this bag*/
	public void clear(){
		while(!isEmpty()){ //runs until empty
			remove(); //clears entries
		}
	}
	
	/**	Counts the number of times a given entry appears in this bag.
	*	@param anEntry the entry to locate
	*	@return true if the bag contains anEntry, or false otherwise*/
	public int getFrequencyOf(T anEntry){
		int frequency = 0; //counter for amount of an entry appearing
		int counter = 0; //counter for while loop
		Node currentNode = firstNode; //temp node starts at firstNode
		while((counter < numberOfEntries) && (currentNode != null)){ //Traverses chain
			if(anEntry.equals(currentNode.getData())){ //Test if objects are the same
				frequency++; //increments frequency
			}
			counter++; //increments while loop counter
			currentNode = currentNode.getNextNode(); //sets node to next node
		}
		return frequency; //returns amount of an object
	}
	
	/** Tests whether this bag contains a given entry.
	*	@param anEntry the entry to locate
	*	@return true if the bag contains anEntry, or false otherwise*/
	public boolean contains(T anEntry){
		boolean found = false; //start off as false until proven otherwise
		Node currentNode = firstNode; //set current node to firstNode
		while(!found && (currentNode != null)){ //traverses chain
			if(anEntry.equals(currentNode.getData())){ //tests objects are same
				found = true; //found data, sets boolean true
			}else{
				currentNode = currentNode.getNextNode(); //set current node to next node
			}
		}
		return found; //return boolean if found or not
	}
	
	/** Creates an array of all entries that are in this bag.
	*	@return a newly allocated array of all the entries in the bag*/
	public T[] toArray(){
		@SuppressWarnings("unchecked") //cast is safe because the new array contains null entries
			T[] result = (T[])new Object[numberOfEntries]; //unchecked cast;
		int index = 0;
		Node currentNode = firstNode; //create a new node to temp keep track of current node
		while((index < numberOfEntries) && (currentNode != null)){ //Makes sure that the code only traverses the chain
			result[index] = currentNode.getData(); //sets array index to current node
			index++; //increments index
			currentNode = currentNode.getNextNode(); //sets current node to next node
		} //Doesn't use FOR loop because of two cases
		return result;
	}
	
	private Node getReferenceTo(T anEntry){
		boolean found = false; //boolean stops loop if found
		Node currentNode = firstNode; //used for temp testing
		while(!found && (currentNode != null)){ //traverse chain
			if(anEntry.equals(currentNode.getData())){ //tests equality
				found = true; //sets true to stop loop if found
			}else{
				currentNode = currentNode.getNextNode(); //set next node to current, keep testing
			}
		}
		return currentNode;
	}
	
	
	private class Node{ //Keeps track of entry and next entry
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
	}
}