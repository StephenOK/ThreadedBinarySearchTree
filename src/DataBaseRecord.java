/**
 * DATABASERECORD CLASS:
 * 
 * Holds all data pertaining to a student: ID, first name,
 * and last name.
 */
public class DataBaseRecord {
	
	//initialize record data of student
	private String ID, first, last;
	
	//constructor with input information
	public DataBaseRecord(String i, String f, String l){
		ID = i;
		first = f;
		last = l;
	}
	
	//getter method for the first name
	public String getFname() {
		return first;
	}
	
	//getter method for the last name
	public String getLname() {
		return last;
	}
	
	//displays data field as a string
	public String toString() {
		
		//format entries to display as columns
		String output = String.format("%6s %11s %11s", ID, first, last);
		return output;
	}
}
