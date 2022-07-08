
public class Tester {
public static void main (String [] args) {
	NodeTree nums = new NodeTree();
	
	nums.addNode("08", -1);
	nums.addNode("12", -1);
	nums.addNode("10", -1);
	nums.addNode("13", -1);
	nums.addNode("14", -1);
	nums.addNode("11", -1);
	
	//nums.printAll();
	System.out.println();
	
	nums.findNode("12", -1);
	nums.deleteNode();
	//nums.printAll();
}
}
