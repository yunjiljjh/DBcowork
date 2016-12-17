package dbdb;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

public class Main {

	private static  String DB_DRIVER = "org.postgresql.Driver";
	private static  String DB_CONNECTION_URL;
	private static  String DB_USER;
	private static  String DB_PASSWORD;
	private static FileReader fr;
	private static BufferedReader br;
	
	static Statement st;
	static PreparedStatement preparedStmt;
	static ResultSet rs;
	static Properties connProps;
	static Connection conn;
	
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		
		Class.forName(DB_DRIVER); //Driver class is loaded to memory
		System.out.println("Driver loaded");
		connProps = new Properties();

		//read connection text in
		setConnection();

		/*********************
		System.out.println("============ RESULT ============");
		while (rs.next()) {
		    System.out.print("ID : " + rs.getString(1) + ", ");
		    System.out.print("Name : " + rs.getString(2) + ", ");
		    System.out.print("Address : " + rs.getString(3) + ", ");
		    System.out.print("Department_ID : " + rs.getString(4));
		    System.out.println();
		}
***************/
		/* Update Row */
		//String UpdateSQL = "UPDATE student_table SET address = ? where ID = ?";
		
		//preparedStmt = conn.prepareStatement(UpdateSQL);
		//preparedStmt.setString(1, "addr3");
		//preparedStmt.setInt(2, 2);		
		//preparedStmt.executeUpdate();

		//rs = st.executeQuery("SELECT ID, name, address, department_ID FROM student_table");

		/******************
		System.out.println("============ RESULT ============");
		while (rs.next()) {
		    System.out.print("ID : " + rs.getString(1) + ", ");
		    System.out.print("Name : " + rs.getString(2) + ", ");
		    System.out.print("Address : " + rs.getString(3) + ", ");
		    System.out.print("Department_ID : " + rs.getString(4));
		    System.out.println();
		}
		*******************/
		/* Delete Row */
		//String DeleteSQL = "DELETE FROM student_table where ID = 2";
		//st.executeUpdate(DeleteSQL);
		
		//rs = st.executeQuery("SELECT ID, name, address, department_ID FROM student_table");

	}

	public static void setConnection()throws SQLException{
		String connPath = Main.class.getResource("").getPath();
		connPath = connPath + "connection.txt";
		System.out.println("connected with info from"+connPath+"connection.txt");
		System.out.println(connPath);
		
		/*read the text file*/

		try{
			FileReader fr=new FileReader(connPath);
			BufferedReader br=new BufferedReader(fr);
			
			System.out.println("read the text file");
			
			String tmp=null;
			
			//read IP address
			String s = null;
			s = br.readLine();
			s=s.replaceAll(" ", "");
			System.out.println(">"+s+"<");
			String[] line = s.split(":");
			tmp = "jdbc:postgresql://"+line[1] + "/";
			
			//read DBname
			line = null;
			s =null;
			s = br.readLine();
			s=s.replaceAll(" ", "");
			System.out.println(">"+s+"<");
			line = s.split(":");
			tmp = tmp+ line[1];
			System.out.println("DB_CONNECTION_URL is "+tmp);
			DB_CONNECTION_URL = tmp;
			
			//read ID
			line = null;
			s =null;
			tmp=null;
			s = br.readLine();
			s = br.readLine();
			s=s.replaceAll(" ", "");
			System.out.println(">"+s+"<");
			line = s.split(":");
			tmp = line[1];
			System.out.println("DB_USER is "+tmp);
			DB_USER = tmp;
			
			//read Password
			line = null;
			s =null;
			tmp=null;
			s = br.readLine();
			s=s.replaceAll(" ", "");
			System.out.println(">"+s+"<");
			line = s.split(":");
			tmp = line[1];
			System.out.println("DB_PASSWORD is "+tmp);
			DB_PASSWORD = tmp;
			
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(br != null){
				try{br.close();}
				catch(IOException e){}
			}
			if(fr != null){
				try{fr.close();}
				catch(IOException e){}
			}
		}			
	
		
		/* Setting Connection Info */
		connProps.setProperty("user", 		DB_USER);
		connProps.setProperty("password", 	DB_PASSWORD);
		System.out.println("connection set");

		/* Connect! */
		conn = DriverManager.getConnection(DB_CONNECTION_URL, connProps);
		System.out.println("connection success");
		
		st = conn.createStatement();
	}
	
	public void mainMsg() throws IOException,FileNotFoundException, SQLException{
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
							createTable(tableName,fileName);
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
							case 4: {insertTuple();
											break;}
							case 5: {//delete();
											break;}
							case 6: {//update();
											break;}
							case 7: {dropTable();
											break;}
							case 8: {//back to main -> do nothing and return to the main home
											break;}
							}
								break;}
			case 4: {} break;
			} //end of switch
		}
	
	public void createTable(String tableName, String fileName) throws IOException, FileNotFoundException, SQLException {		
		String CreateTableSQL = "CREATE TABLE tableName ";
		String filePath;
	    //filePath = fileName으로 경로 만들어서
		fr=new FileReader(fileName);
		br=new BufferedReader(fr);
		String s = br.readLine();
		String[] a = s.split(",");
		
		CreateTableSQL +=
				"(" + a[0]+"변수타입"+", " + //읽어들인 파일로 나머지 string 만들고
				"primary key ("+ "프라이머리 키 인 attribute"+"))";
		st.executeUpdate(CreateTableSQL);
		}
	
	public void dropTable() throws SQLException{
		/* Drop table */
		String DropTableSQL = "DROP TABLE student_table";
		st.executeUpdate(DropTableSQL);
		
		preparedStmt.close();
		st.close();
		rs.close();
	}
	
	public void insertTuple() throws SQLException{
		
		/* Insert Row using Statement 
		 * 모든 정보가 한번에 다 있을 때*/
		String InsertSQL_1 = "INSERT INTO student_table values(1, 'Brandt', 'addr1', 1)";
		st.executeUpdate(InsertSQL_1);
		
		/* Insert Row using PreparedStatement
		 * 정보를 하나씩 하나씩 넣을 때 */
		String InsertSQL_2 = "INSERT INTO student_table (ID, name, address, department_ID) values(?, ?, ?, ?)";

		preparedStmt = conn.prepareStatement(InsertSQL_2);
		preparedStmt.setInt(1, 2);
		preparedStmt.setString(2, "Chavez");
		preparedStmt.setString(3, "addr2");
		preparedStmt.setInt(4, 2);
		
		preparedStmt.execute();
		
		rs = st.executeQuery("SELECT ID, name, address, department_ID FROM student_table");
	}
	
}
