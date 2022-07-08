/**
 * STACK CLASS:
 * 
 * Last in first out structure that, in the context of this program,
 * holds the record spots where deleted items were in the DataBaseArray
 */
public class Stack {

	int size;
	int max;
	int [] data;
	
	//default constructor
	public Stack () {
		size = 0;
		max = 100;
		data = new int [100];
	}
	
	//constructor with maximum parameter determined by the user
	public Stack (int x) {
		size = 0;
		max = x;
		data = new int [x];
	}
	
	/**
	 * Method to check if Stack is full
	 * 
	 * @return		True if full
	 * 				False if space in list
	 */
	public boolean isFull() {
		return size == max;
	}
	
	/**
	 * Method to check if Stack is empty
	 * 
	 * @return		True if no entries available
	 * 				False if one or more entries present
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Method that inputs data into the stack
	 * 
	 * @param 	x	integer to input
	 */
	public void push (int x) {
		data[size++] = x;
	}
	
	/**
	 * Method that outputs data from the stack
	 * 
	 * @return		Data within the top stack entry
	 */
	public int pop() {
		return data[--size];
	}
}
