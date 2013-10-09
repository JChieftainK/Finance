public interface LinkedBag <T> implements BagInterface <T>{
	private Node firstNode; //References to the first node
	private int numberOfEntries; //Number of entries in bag
	
	public LinkedBag(){ //Default Constructor
		firstNode = null;
		numberOfEntries = 0;
	}
	
	/** Gets the current number of entries in this bag.
	*	@return the integer number of entries currently in the bag*/
	public int getCurrentSize(){
		
	}
	
	/** Sees whether this bag is full.
	*	@return true if the bag is full, or false if not*/
	public boolean isFull(){
		
	}
	
	/** Sees whether this bag is empty
	*	@return true if the bag is empty, or false if not*/
	public boolean isEmpty(){
		
	}
	
	/** Adds a new entry to this bag.
	*	@param newEntry the object to be added as a new entry
	*	@return true if the addition is successful, or false if not*/
	public boolean add(T newEntry){
		Node newNode = new Node(newEntry); //Add to the beginning of chain.
		newNode.next = firstNode; //new node references rest of chain, new node is at beginning
		
		firstNode = newNode;
		numberOfEntries++;
		return true;
	}
	
	/** Removes one unspecified entry from this bag, if possible.
	*	@return either the removed entry,  if the removal was successful, or null*/
	public T remove(){
		
	}
	
	/** Removes one occurrence of a given entry from this bag, if possible.
	*	@param anEntry the entry to be removed
	*	@return true if the removal was successful, or false if not*/
	public boolean remove(T anEntry){
		
	}
	
	/**Removes all entries from this bag*/
	public void clear(){
		
	}
	
	/**	Counts the number of times a given entry appears in this bag.
	*	@param anEntry the entry to locate
	*	@return true if the bag contains anEntry, or false otherwise*/
	public int getFrequencyOf(T anEntry){
		
	}
	
	/** Tests whether this bag contains a given entry.
	*	@param anEntry the entry to locate
	*	@return true if the bag contains anEntry, or false otherwise*/
	public boolean contains(T anEntry){
		
	}
	
	/** Creates an array of all entries that are in this bag.
	*	@return a newly allocated array of all the entries in the bag*/
	public T[] toArray(){
		@SuppressWarnings("unchecked") //cast is safe because the new array contains null entries
			T[] result = (T[])new Object[numberOfEntries]; //unchecked cast;
		int index = 0;
		Node currentNode = firstNode; //create a new node to temp keep track of current node
		while((index < numberOfEntries) && (currentNode != null)){ //Makes sure that the code only traverses the chain
			result[index] = currentNode.data; //sets array index to current node
			index++; //increments index
			currentNode = currentNode.next; //sets current node to next node
		} //Doesn't use for loop because of two cases
		
		return result;
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
	}
}