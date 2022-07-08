/**
 * O'Keefe Stephen
 * E01259496
 * COSC 311 Fall 2020
 * Program #3
 * 
 * This program utilizes a threaded binary search tree
 * 
 * Main driver program that gives user interface and accesses the appropriate
 * functions in the DataBase class
 * 
 * Utilizes a deleteStack, which keeps a record of items that are no longer
 * referenced by the data tree and replaces these items in the DataBaseArray
 * when a new item is added.
 * 
 * Program makes a reference to a specific file, so if any test is to be done,
 * this needs to be appropriately taken into account.
 */

import java.io.FileNotFoundException;
import java.util.*;

public class COSC311Driver {
	public static void main(String[] args) {
		
		//initialize database, user input, and keyboard
		DataBase d = new DataBase();
		int response;
		Scanner reader;
		
		//retrieve file
		//If testing, file names must match
		java.io.File startData = new java.io.File("data.html");
		
		//initialize data for initial inputs
		String inID, inFname, inLname;
		
		//initialize value for loop check in try block
		String endCheck = "";
		
		try {
			
			//try attaching reader to file
			reader = new Scanner(startData);
			
			//set reader to the student information
			while (endCheck.compareTo("Dunn") != 0)
				endCheck = reader.next();
			
			//loop through student data
			while (endCheck.compareTo("</pre>") != 0) {
				
				//retrieve data from file without iterating past info
				inLname = endCheck;
				
				//retrieve data and iterate through
				inFname = reader.next();
				inID = reader.next();
				
				//move to next item to check for loop end
				endCheck = reader.next();
				
				//add data to list
				d.initAdd(inID, inFname, inLname);
			}
			
			//stop reader
			reader.close();
			
		} catch (FileNotFoundException e) { //catch missing file error
			e.printStackTrace();
		}
		
		//initialize user input
		Scanner keyboard = new Scanner (System.in);
		
		do {
			//prompt for action to take
			System.out.println("Input a number and perform an action:");
            System.out.println("1 Add a new student");
            System.out.println("2 Delete a student");
            System.out.println("3 Find a student by ID");
            System.out.println("4 List students by ID increasing");
            System.out.println("5 List students by first name increasing");
            System.out.println("6 List students by last name increasing");
            System.out.println("7 List students by ID decreasing");
            System.out.println("8 List students by first name decreasing");
            System.out.println("9 List students by last name decreasing");
            System.out.println();
            System.out.println("0 End");
            
            //record action
            response = keyboard.nextInt();
            
            switch (response){
            	
            	case 1:  //add student
            		d.addIt();
            		break;
            
            	case 2:  //delete student
            		d.deleteIt();
            		break;
                
            	case 3:  //find student by ID
            		d.findIt();
            		break;
                
            	case 4:  //print by ascending ID order
            		d.ListByIDAscending();
            		break;
                
            	case 5:  //print by ascending first name order
            		d.ListByFirstAscending();
            		break;
                
            	case 6:  //print by ascending last name order
            		d.ListByLastAscending();
            		break;
            
            	case 7:  //print by descending ID order
            		d.ListByIDDescending();
            		break;
                
            	case 8:  //print by descending first name order
            		d.ListByFirstDescending();
            		break;
                
            	case 9:  //print by descending last name order
            		d.ListByLastDescending();
            		break;
                
            	case 0:  //terminate loop
            		break;
            
            	default:  //catch invalid integer input
            		System.out.println("Not a valid input.");
            }
		} while (response != 0);
		
		//notify of ending
		System.out.println("Stopping program.");
	}
	
}
