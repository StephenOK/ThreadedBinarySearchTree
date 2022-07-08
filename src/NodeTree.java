/**
 * NODETREE CLASS:
 * 
 * Holds a set of nodes of a particular type of student data
 * (either ID#, first name, or last name) and allows the
 * DataBase class to alter it.
 */

public class NodeTree {
	
	//initialize NodeTree statuses
	private Node top;
	private int size;
	
	//initialize tracking nodes
	private Node rover;
	private Node follower;
	
	//initialize boolean for rover's movement
	private boolean lastLeft;
	
	//initialize additional tracking nodes
	private Node stitch;
	private Node followStitch;
	
	//NodeArray constructor
	public NodeTree() {
		top = null;
		size = 0;
	}
	
	/**
	 * Adds a new node to the tree
	 * 
	 * @param s	String added as the node's key
	 * @param x	Integer potentially added as the node's where
	 */
	public void addNode(String s, int x) {
		
		//initialize added node
		Node newNode;
		
		//input values into new node
		
		if (x >= 0)  //if value given from deleteStack
			
			newNode = new Node(s, x);
		
		else  //if deleteStack is empty
			
			newNode = new Node(s, size);
		
		if (top == null) {  //if first entry in tree
			
			//declare newNode as top
			top = newNode;
			
			//self-reference at threads
			newNode.setLeft(top);
			newNode.setRight(top);
		}
		
		else {  //if node is a branch
			
			//sets rover to top and clears followers
			resetRover();
			
			while (true) {  //loops until indicated break
				
				if(rover.getKey().compareTo(s) < 0) {  //new entry String comes after current rover value
					
					//move rover to the right, iterate follower, and indicate a right step is made
					roverRight();
					
					if (follower.getRightThread()) {  //follower's right node is a thread
						
						//make previous node as appropriate newNode's thread
						newNode.setLeft(follower);
						
						if(follower != follower.getRight())  //if follower is not the end node
							
							//move previous node's thread to newNode
							newNode.setRight(follower.getRight());
						
						else {  //newNode is the new end node
							
							//self-reference in right thread node
							newNode.setRight(newNode);
						}
						
						//change follower thread to a branch reference
						follower.setRight(newNode);
						follower.setRightThread(false);
						
						//finish while loop
						break;
					}
				}
				
				else {  //new entry String comes before or has the same value as current rover value
					
					//move rover to the left, iterate follower, and indicate a left step is made
					roverLeft();
					
					if (follower.getLeftThread()) {  //follower's left node is a thread
						
						//make previous node as appropriate newNode's thread
						newNode.setRight(follower);
						
						if(follower != follower.getLeft())  //if follower is not the first node
							
							//move previous node's thread to newNode
							newNode.setLeft(follower.getLeft());
						
						else {  //newNode is the new first node
							
							//self-reference in left thread node
							newNode.setLeft(newNode);
						}
						
						//change follower thread to a branch reference
						follower.setLeft(newNode);
						follower.setLeftThread(false);
						
						//finish while loop
						break;
					}
				}
			}
		}
		
		//increase size appropriately
		size++;
	}
	
	/**
	 * Locates a node in the NodeTree and returns where it is in the DataBaseArray
	 * 
	 * @param 	s	Desired String user wants to find	
	 * @param	x	If x >= 0, where location to find correct node for both name trees
	 * 				If x == -1, initial run of findNode through unique IDs
	 * 
	 * @return		Location in DataBaseArray if String s exists on it
	 */
	public int findNode(String s, int x) {
		
		//set rover to top
		resetRover();
		
		while (true) {  //loops until broken
			
			if (rover == null) break;  //if tree is empty
			
			else if (rover.getKey().compareTo(s) == 0) {  //if String found in rover
				
				if (x < 0 ||  //findNode running in unique ID tree
						
					rover.getWhere() == x)  //findNode in name tree, but found instance with corresponding where
					
					//produce where value in rover
					return rover.getWhere();
				
				else  //findNode in name tree and found instance with wrong where value
					
					//identical names would be left of rover
					roverLeft();
			}
			
			else if (rover.getKey().compareTo(s) < 0) {  //rover's contents would be alphabetically before target String
				
				if(rover.getRightThread()) break;  //end if no more objects at right
				
				//move rover right
				roverRight();
			}
			
			else {  //rover's contents would be alphabetically after target String
				
				if(rover.getLeftThread()) break;  //end if no more objects at left
				
				//move rover left
				roverLeft();
			}
		}
		
		//if object not found in tree
		return -1;
	}
	
	/**
	 * Removes a node from the tree and shifts remaining nodes and references appropriately.
	 */
	public void deleteNode() {
		
		//findNode method must run before this method is called
		//therefore, rover, followers, and boolean checks are already set in place
		
		if (rover.getLeftThread() && rover.getRightThread()){  //if node has only threads attached
			
			if (rover == top)  //if rover is top, and therefore only, node
				top = null;
			
			else {  //if rover is any other node
				
				//remove reference from follower node
				
				if (lastLeft) {  //if follower's left
					
					if (rover == rover.getLeft())  //if removed node is first on the thread
						
						//follower now self-references as front node
						follower.setLeft(follower);
					
					else  //any middle node
						
						//preserve rover's left thread in follower
						follower.setLeft(rover.getLeft());
					
					//change to thread
					follower.setLeftThread(true);
					
				}
				
				else {  //if follower's right
					
					if (rover == rover.getRight())  //if removed node is last on the thread
						
						//follower now self references as end node
						follower.setRight(follower);
					
					else  //any middle node
						
						//preserve rover's right thread in follower
						follower.setRight(rover.getRight());
					
					//change to thread
					follower.setRightThread(true);
				}
			}
		}
		
		else if(!rover.getLeftThread() && !rover.getRightThread()) {  //if node has two nodes attached
			
			if (rover.getRight().getLeftThread()) {  //if right branch has left as thread
			
				//shift right branch into rover's place
				
				//change right branch's left thread to rover's left node
				rover.getRight().setLeft(rover.getLeft());
				rover.getRight().setLeftThread(false);
				
				if (rover == top) {  //if deleted node was top
					
					//indicate change in top
					top = rover.getRight();
					
					//shift rover to new top
					rover = top;
				}
				
				else {  //if deleted node was not top
					
					//change follower's reference to rover's right node
					
					if (lastLeft) {  //if rover is left of follower
						
						//place node as follower's left
						follower.setLeft(rover.getRight());
						
						//shift rover to follower's new node reference
						rover = follower.getLeft();
					}
					
					else {  //if rover is right of follower
						
						//place node as follower's right
						follower.setRight(rover.getRight());
						
						//shift rover to follower's new node reference
						rover = follower.getRight();
					}
				}
			}
			
			else {  //if right branch has a branch at left 
				
				//find next sequential number (stitch)
				findNext();
				
				//temporarily remove stitch from record
				
				if (stitch.getRightThread())  //if stitch has thread at right
				
					//change follower to thread, but don't change node
					followStitch.setLeftThread(true);
				
				else  //if stitch has branch at right
				
					//change follower to stitch's old branch
					followStitch.setLeft(stitch.getRight());
				
				//change stitch's references into rover's references
				stitch.setLeft(rover.getLeft());
				stitch.setLeftThread(false);
				
				stitch.setRight(rover.getRight());
				
				if (stitch.getRightThread())  //if stitch didn't previously have a branch
					
					stitch.setRightThread(false);
				
				if (rover == top)  //if deleted node was top
					
					//change top to stitch
					top = stitch;
			
				else {  //if deleted node was not top
					
					//change follower's reference to nStitch
					
					if (lastLeft)  //if rover is at follower's left
						follower.setLeft(stitch);
					
					else  //if rover is at follower's right
						follower.setRight(stitch);
				}
				
				//shift rover to stitch
				rover = stitch;
			}
			
			//change reference to old rover into new rover value
			
			if (rover.getLeft().getRightThread())  //if left branch has right as thread
			
				//change right to new rover
				rover.getLeft().setRight(rover);
			
			else {  //if left branch has node at right
				
				//find the previous sequential node from rover (stitch)
				findPrev();
				
				//change stitch's right thread to new rover
				stitch.setRight(rover);
			}
		}
		
		else {  //if deleted node has one branch and one thread
			
			if (rover.getRightThread()) {  //if left branch is node
				
				if (rover == top) {  //if removed node is top
					
					//make top rover's left
					top = rover.getLeft();
					
					
					if (top.getRightThread())  //if new top holds a thread at right
						
						//new top's right references self
						top.setRight(top);
					
					else {  //if new top holds a node at right
						
						//find new end node
						findPrev();
						
						//set right to self-reference
						stitch.setRight(stitch);
					}	
				}
				
				else {  //if removed node is below top
					
					//find next previous sequential node (stitch)
					findPrev();
					
					if (rover == rover.getRight())  //if rover is end of list
						
						//change stitch's right thread to self
						stitch.setRight(stitch);
						
					else  //if rover is not end of list
						
						//change stitch's right thread to rover's right thread
						stitch.setRight(rover.getRight());
			
					if (lastLeft)  //if rover is at follower's left
						
						//change follower's reference to rover's left
						follower.setLeft(rover.getLeft());
						
					else  //if rover is at follower's right
						
						//change follower's reference to rover's left
						follower.setRight(rover.getLeft());
					
				}
			}
			
			else {  //if right branch is node
				
				if (rover == top) {  //if removed node is top
					
					//make top rover's right
					top = rover.getRight();
					
					if (top.getLeftThread())  //if new top has thread at left
						
						//new top's left references self
						top.setLeft(top);
					
					else {  //if new top has node at left
						
						//find next sequential node from rover (stitch)
						findNext();
						
						//set left to self-reference
						stitch.setLeft(stitch);
					}
				}
				
				else {  //if removed node is below top
					
					//find next sequential node from rover (stitch)
					findNext();
			
					if (rover == rover.getLeft())  //if rover is front of list
						
						//change stitch's left thread to self
						stitch.setLeft(stitch);
						
					else  //if rover is not front of list
						
						//change stitch's left thread to rover's left thread
						stitch.setLeft(rover.getLeft());
			
					if (lastLeft)  //if rover is at follower's left
						
						//change follower's reference to rover's right
						follower.setLeft(rover.getRight());
						
					else  //if rover is at follower's right
						
						//change follower's reference to rover's right
						follower.setRight(rover.getRight());
				}
			}
		}
	}
	
	/**
	 * Sets stitch to the next sequential node from rover and
	 * maintains a follower for stitch
	 */
	public void findNext() {
		
		//put stitch and follower at initial positions
		followStitch = rover;
		stitch = rover.getRight();
		
		while(!stitch.getLeftThread()) {  //while stitch cannot go left to a new Node
			
			//iterate follower and stitch to the left
			followStitch = stitch;
			stitch = stitch.getLeft();
		}
	}
	
	/**
	 * Sets stitch to the previous sequential node from rover
	 * and maintains a follower for stitch
	 */
	public void findPrev() {
		
		//put stitch and follower at initial positions
		followStitch = rover;
		stitch = rover.getLeft();
		
		while(!stitch.getRightThread()) {  //while stitch cannot go right to a new Node
			
			//iterate follower and stitch to the right
			followStitch = stitch;
			stitch = stitch.getRight();
		}
	}
	
	/**
	 * Gives access to the tree's rover and its contents
	 * 
	 * @return		Node rover is currently at
	 */
	public Node getRover() {
		return rover;
	}
	
	/**
	 * Allows another class to change the rover into the stitch node
	 */
	public void makeStitch() {
		rover = stitch;
	}
	
	/**
	 * Sets rover to the top of the NodeTree, clears follower, and
	 * sets lastLeft to false
	 */
	public void resetRover() {
		rover = top;
		follower = null;
		lastLeft = false;
	}
	
	/**
	 * Iterates the follower onto rover, moves rover to the left,
	 * and indicates that the rover is currently at follower's left
	 */
	public void roverLeft() {
		follower = rover;
		rover = rover.getLeft();
		
		lastLeft = true;
	}
	
	/**
	 * Iterates the follower onto rover, moves rover to the right,
	 * and indicates that the rover is currently at follower's right
	 */
	public void roverRight() {
		follower = rover;
		rover = rover.getRight();
		
		lastLeft = false;
	}
	
	/**
	 * Sets the rover into the first alphabetic node in the tree
	 */
	public void setRoverFront() {
		
		//puts rover at the top node
		resetRover();
		
		if (rover != null) {  //if the tree is not empty
			
			while(!rover.getLeftThread())  //while rover's left is not a thread
				
				//move rover to the left
				rover = rover.getLeft();
		}
	}
	
	/**
	 * Sets the rover into the last alphabetic node in the tree
	 */
	public void setRoverEnd() {
		
		//puts rover at the top node
		resetRover();
		
		if (rover != null) {  //if the tree is not empty
			
			while(!rover.getRightThread())  //while rover's right is not a thread
				
				//move rover to the right
				rover = rover.getRight();
		}
	}
}
