package dbdb;

import java.util.Scanner;

public class UI {
	public UI(){}
	public void mainMsg(){
		int input;
        Scanner scan = new Scanner(System.in);
		System.out.println("Please input the instruction number (1: Import from CSV, 2: Export to CSV, 3: Manipulate Data, 4: Exit) :");
		input = scan.nextInt(); //get num from keyboard
		
		String tableName, fileName;
		int option;
		switch(input){
			case 1: {System.out.println("[Import from CSV]");
							System.out.println("Please specify the filename for table description : "); 
							tableName = scan.nextLine();
							System.out.println("Please specify the CSV filename : "); 
							fileName = scan.nextLine();
							//createTable(tableName,fileName ,infoArray)
							System.out.println("Table is newly created as described in the file.");
							tableName=null;
							fileName=null;
							break;}
			case 2: {System.out.println("[Export to CSV]");
							System.out.println("Please specify the table name : "); 
							tableName = scan.nextLine();
							System.out.println("Please specify the CSV filename : "); 
							fileName = scan.nextLine();
							//exportTable(tableName,fileName ,infoArray)
							System.out.println("Data export completed.");
							tableName=null;
							fileName=null;
							break;}
			case 3: {
							System.out.println("[Manipulate Data]");
							System.out.println("Please input the instruction number (1: Show Tables, 2: Describe Table, 3: Select, 4: Insert, 5: Delete, 6: Update, 7: Drop Table, 8: Back to main) : "); 
							option=scan.nextInt();
							switch(option){
							case 1: { //showTables();
											break;}
							case 2: {//describeTable();
											break;}
							case 3: {//select();
											break;}
							case 4: {//insert();
											break;}
							case 5: {//delete();
											break;}
							case 6: {//update();
											break;}
							case 7: {//dropTable();
											break;}
							case 8: {//back to main -> do nothing and return to the main home
											break;}
							}
								break;}
			case 4: {} break;
			} //end of switch
		}
	}
