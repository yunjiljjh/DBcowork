package dbdb;

import java.util.Scanner;

public class UI {
	public UI(){}
	public void initial(){
		int input;
        Scanner scan = new Scanner(System.in);
		System.out.println("Please input the instruction number (1: Import from CSV, 2: Export to CSV, 3: Manipulate Data, 4: Exit) :");
		input = scan.nextInt(); //get num from keyboard
		String msg;
		switch(input){
			case 1: {System.out.println("[Import from CSV]");
							System.out.println("Please specify the filename for table description : "); 
							msg = scan.nextLine();
							//createTable(filename,infoArray)
							System.out.println("Table is newly created as described in the file.");
							} break;
							

			case 2: {} break;
			case 3: {} break;
			case 4: {} break;
		}
	}
}
