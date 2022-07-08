/**
 * NODE CLASS:
 * 
 * Holds a String of information as well as where it shows up
 * in the DataBaseArray. Also contains child nodes and indicates
 * whether these children are threads.
 */

public class Node {
	
	//initialize String and location
	private String key;
	private int where;
	
	//initialize child nodes
	private Node left;
	private Node right;
	
	//initialize booleans indicated child nodes as threads
	private boolean isLeftThread;
	private boolean isRightThread;
	
	//constructor with values for key and where
	public Node (String s, int x) {
		
		//input node values
		key = s;
		where = x;
		
		//child nodes declared later
		left = null;
		right = null;
		
		//new node will always be end of branch, so children are initially threads
		isLeftThread = true;
		isRightThread = true;
	}
	
	//setter method to change left
	public void setLeft(Node L) {
		left = L;
	}
	
	//setter method to change right
	public void setRight(Node R) {
		right = R;
	}
	
	//setter method to change isLeftThread
	public void setLeftThread(boolean n) {
		isLeftThread = n;
	}
	
	//setter method to change isRightThread
	public void setRightThread(boolean n) {
		isRightThread = n;
	}
	
	//getter method for key
	public String getKey() {
		return key;
	}
	
	//getter method for where
	public int getWhere() {
		return where;
	}
	
	//getter method for left
	public Node getLeft() {
		return left;
	}
	
	//getter method for right
	public Node getRight() {
		return right;
	}
	
	//getter method for left thread indicator
	public boolean getLeftThread() {
		return isLeftThread;
	}
	
	//getter method for right thread indicator
	public boolean getRightThread() {
		return isRightThread;
	}
}
