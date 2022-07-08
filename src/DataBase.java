/**
 * DATABASE CLASS:
 * 
 * Operates on DataBaseArray, all NodeTrees, and stack of deleted
 * entries as directed by the user from the main driver.
 */

import java.util.*;

public class DataBase {

	//initialize data array
	private DataBaseArray myDB;
	
	//initialize individual NodeTrees
	private NodeTree ID, fname, lname;
	
	//initialize deleted items list
	private Stack deleteStack;
	
	Scanner keyboard = new Scanner(System.in);
	
	//default constructor class
	public DataBase() {
		myDB = new DataBaseArray(100);
		ID = new NodeTree();
		fname = new NodeTree();
		lname = new NodeTree();
		deleteStack = new Stack(100);
	}
	
	/**
	 * Inserts new data entry into DataBaseArray and each NodeTree
	 */
	public void addIt() {
		
		//prompt for new data input
		System.out.println("Enter the new student's info: ");
		System.out.print("ID: ");
		String newID = keyboard.next();
		
		if(ID.findNode(newID, -1) >= 0)  //if ID found in tree
			
			//notify user
			System.out.println("ID already exists on this list.\n");
		
		else {  //ID is not used
			
			//continue prompting
			System.out.print("First Name: ");
			String newFname = keyboard.next();
			
			System.out.print("Last Name: ");
			String newLname = keyboard.next();
			
			System.out.println();
			
			if (!deleteStack.isEmpty()) {  //DataBaseRecord available to be replaced
				
				//obtain location from stack
				int location = deleteStack.pop();
				
				//replace item in DataBaseRecord
				myDB.addData(newID, newFname, newLname, location);
				
				//add item with appropriate where item
				ID.addNode(newID, location);
				fname.addNode(newFname, location);
				lname.addNode(newLname, location);
			}
			
			else {  //no replaceable index in DataBaseArray
				
				//add to end of DataBaseArray
				myDB.addData(newID, newFname, newLname, -1);
				
				//add individual data to the appropriate NodeTree
				ID.addNode(newID, -1);
				fname.addNode(newFname, -1);
				lname.addNode(newLname, -1);
			}
			
			//notify user of success
			System.out.println("Item successfully added.\n");
		}
	}
	
	/**
	 * Removes item from the trees and the DataBaseArray
	 */
	public void deleteIt() {
		
		//prompt and find location in DataBaseArray
		int findSpot = toFind();
		
		if (findSpot < 0)  //if ID not found in array
			
			//notify user
			System.out.println("Item not found to be removed.\n");
		
		else {  //if ID located
			
			//push deleted ID's location in DataBaseArray to deleteStack
			deleteStack.push(findSpot);
			
			//remove from lists
			ID.deleteNode();
			fname.deleteNode();
			lname.deleteNode();
			
			//notify user of completion
			System.out.println("Item successfully removed.\n");
		}
	}
	
	/**
	 * Takes an inputed ID and locates instance of it in DataBaseArray
	 */
	public void findIt() {
		
		//prompt and find in DataBaseArray
		int spot = toFind();
		
		if (spot >= 0)  //object exists in array
			
			//prints all data connected to ID
			System.out.println(myDB.dbaAccess(spot).toString() + "\n");
		
		else  //object does not exist in array
			
			//notify user
			System.out.println("Student not found in database.\n");
	}
	
	/**
	 * Lists all entries in the DataBaseArray in order of ID number in
	 * increasing order by referencing the ID NodeTree
	 */
	public void ListByIDAscending() {
		ListAscending(ID);
	}
	
	/**
	 * Lists all entries in the DataBaseArray in order of first name
	 * alphabetically by referencing the fname NodeTree
	 */
	public void ListByFirstAscending() {
		ListAscending(fname);
	}
	
	/**
	 * Lists all entries in the DataBaseArray in order of last name
	 * alphabetically by referencing the lname NodeTree
	 */
	public void ListByLastAscending() {
		ListAscending(lname);
	}
	
	/**
	 * Lists all entries in the DataBaseArray in order of ID number in
	 * decreasing order by referencing the ID NodeTree
	 */
	public void ListByIDDescending() {
		ListDescending(ID);
	}
	
	/**
	 * Lists all entries in the DataBaseArray in order of first name in
	 * reverse alphabetical order by referencing the fname NodeTree
	 */
	public void ListByFirstDescending() {
		ListDescending(fname);
	}
	
	/**
	 * Lists all entries in the DataBaseArray in order of last name in
	 * reverse alphabetical order by referencing the lname NodeTree
	 */
	public void ListByLastDescending() {
		ListDescending(lname);
	}
	
	/**
	 * Adds the initial data for the database.
	 * Gives neither prompts nor user notification as other add method does
	 * 
	 * @param i	ID of new input
	 * @param f	First name of new input
	 * @param l	Last name of new input
	 */
	public void initAdd(String i, String f, String l) {
		
		if (ID.findNode(i, -1) < 0) {  //if ID doesn't already exist on tree
			
			//add info to database
			myDB.addData(i,  f,  l,  -1);
			
			//add info to every IndexArray
			ID.addNode(i, -1);
			fname.addNode(f, -1);
			lname.addNode(l, -1);
		}
	}
	
	/**
	 * Prompts for the user's target ID and finds its location in
	 * the DataBaseArray. Also sets rover in fname and lname trees for
	 * potential delete operations
	 * 
	 * @return		The given ID's location in DataBaseArray
	 */
	public int toFind() {
		
		//prompt and record user's desired ID
		System.out.print("Please give the ID you want: ");
		String seekID = keyboard.next();
		
		//find location from ID tree
		int location = ID.findNode(seekID, -1);
		
		if (location >= 0) {  //if given ID is found in tree
			
			//set rovers in place for fname and lname in case of deletion
			fname.findNode(myDB.dbaAccess(location).getFname(), location);
			lname.findNode(myDB.dbaAccess(location).getLname(), location);
		}
		
		//output the found index or -1
		return location;
	}
	
	/**
	 * Prints data from the DataBaseArray in order by the given NodeTree
	 * 
	 * @param 	N	The NodeTree that is referenced
	 */
	public void ListAscending(NodeTree N) {
		
		//place the NodeTree's rover at the front Node
		N.setRoverFront();

		if (N.getRover() != null) {  //if the NodeTree is not empty
			
			//retrieve data from the DataBaseArray and print it
			System.out.println(myDB.dbaAccess(N.getRover().getWhere()).toString());
		
			while (N.getRover() != N.getRover().getRight()) {  //rover has not yet reached end node
				
				if (N.getRover().getRightThread()) {  //if rover's right node is a thread
					
					//move rover over the thread
					N.roverRight();
					
					//retrieve and print DataBaseRecord
					System.out.println(myDB.dbaAccess(N.getRover().getWhere()).toString());
				}
			
				else {  //if rover's right node is not a thread
					
					//find next sequential node
					N.findNext();
					
					//set rover to next sequential node
					N.makeStitch();
					
					//retrieve and print DataBaseRecord
					System.out.println(myDB.dbaAccess(N.getRover().getWhere()).toString());
				}
			}
		}
		
		System.out.println();
	}
	
	/**
	 * Prints data from the DataBaseArray in reverse order by the given NodeTree
	 * 
	 * @param 	N	The NodeTree that is referenced
	 */
	public void ListDescending(NodeTree N) {
		
		//place the rover at the end Node
		N.setRoverEnd();
		
		if (N.getRover() != null) {  //if NodeTree is not empty
			
			//retrieve index from the DataBaseArray and print it
			System.out.println(myDB.dbaAccess(N.getRover().getWhere()).toString());
		
			while (N.getRover() != N.getRover().getLeft()) {  //rover has not reached front node
				
				if (N.getRover().getLeftThread()) {  //if rover's left node is a thread
					
					//move rover over thread
					N.roverLeft();
					
					//retrieve and print DataBaseRecord
					System.out.println(myDB.dbaAccess(N.getRover().getWhere()).toString());
					
				}
				
				else {  //if rover's left node is not a thread
					
					//find previous sequential node
					N.findPrev();
					
					//set rover to previous sequential node
					N.makeStitch();
					
					//retrieve and print DataBaseRecord
					System.out.println(myDB.dbaAccess(N.getRover().getWhere()).toString());
				}
			}
		}
		
		System.out.println();
	}
}
