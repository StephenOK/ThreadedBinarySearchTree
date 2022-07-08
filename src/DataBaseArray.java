/**
 * DATABASEARRAY CLASS:
 * 
 * An object that holds the list of DataBaseRecords
 */
public class DataBaseArray {
	
	//initialize list
	private int size;
	private int max = 100;
	private DataBaseRecord [] data;
	
	//default constructor of DataBaseRecord
	public DataBaseArray(){
		data = new DataBaseRecord [max];
		size = 0;
	}
	
	//constructor of DataBaseRecord with custom maximum capacity
	public DataBaseArray(int x){
		max = x;
		data = new DataBaseRecord [max];
		size = 0;
	}
	
	/**
	 * Adds new DataBaseRecord into the array
	 * 
	 * @param i		ID input
	 * @param f		First name input
	 * @param l		Last name input
	 * @param x		If x < 0, adds new item to end of array
	 * 				If x >= 0, adds new item at data[x]
	 */
	public void addData (String i, String f, String l, int x) {
		
		if (x >= 0)  //replaceable item found in array
			
			//add new data at location
			data [x] = new DataBaseRecord(i, f, l);
		
		else {  //all data in array still referenced
			
			if (size < max)  //runs if space is available
				
				//add item to end of array
				data[size++] = new DataBaseRecord(i, f, l);
			
			else  //notify user of array status
				System.out.println("The array is full, and the data was not added.\n");
		}
	}
	
	/**
	 * Allows access to methods in the DataBaseRecord class
	 * 
	 * @param x		Location of the specific DataBaseRecord within the array
	 * 
	 * @return		The desired DataBaseRecord
	 */
	public DataBaseRecord dbaAccess (int x) {
		return data[x];
	}
}
