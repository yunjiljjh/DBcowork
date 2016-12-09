package dbdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main {

	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION_URL = "jdbc:postgresql://127.0.0.1/DBname";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "dddd";
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName(DB_DRIVER); //Driver class is loaded to memory
		System.out.println("Driver loaded");
		Properties connProps = new Properties();

		/* Setting Connection Info */
		connProps.setProperty("user", 		DB_USER);
		connProps.setProperty("password", 	DB_PASSWORD);
		System.out.println("connection set");

		/* Connect! */
		Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, connProps);
		System.out.println("connection success");
		
		Statement st = conn.createStatement();
		
		/* Create Table SQL */
		String CreateTableSQL = "CREATE TABLE student_table " +
								"(ID int, " +
								"name varchar(20) not null, " +
								"address varchar(50) not null," +
								"department_ID int," +
								"primary key (ID))";
	 
		st.executeUpdate(CreateTableSQL);
				
		/* Insert Row using Statement */
		String InsertSQL_1 = "INSERT INTO student_table values(1, 'Brandt', 'addr1', 1)";
		st.executeUpdate(InsertSQL_1);
		
		/* Insert Row using PreparedStatement */
		String InsertSQL_2 = "INSERT INTO student_table (ID, name, address, department_ID) values(?, ?, ?, ?)";

		PreparedStatement preparedStmt = conn.prepareStatement(InsertSQL_2);
		preparedStmt.setInt(1, 2);
		preparedStmt.setString(2, "Chavez");
		preparedStmt.setString(3, "addr2");
		preparedStmt.setInt(4, 2);
		
		preparedStmt.execute();
		ResultSet rs = st.executeQuery("SELECT ID, name, address, department_ID FROM student_table");

		System.out.println("============ RESULT ============");
		while (rs.next()) {
		    System.out.print("ID : " + rs.getString(1) + ", ");
		    System.out.print("Name : " + rs.getString(2) + ", ");
		    System.out.print("Address : " + rs.getString(3) + ", ");
		    System.out.print("Department_ID : " + rs.getString(4));
		    System.out.println();
		}

		/* Update Row */
		String UpdateSQL = "UPDATE student_table SET address = ? where ID = ?";
		
		preparedStmt = conn.prepareStatement(UpdateSQL);
		preparedStmt.setString(1, "addr3");
		preparedStmt.setInt(2, 2);		
		preparedStmt.executeUpdate();

		rs = st.executeQuery("SELECT ID, name, address, department_ID FROM student_table");

		System.out.println("============ RESULT ============");
		while (rs.next()) {
		    System.out.print("ID : " + rs.getString(1) + ", ");
		    System.out.print("Name : " + rs.getString(2) + ", ");
		    System.out.print("Address : " + rs.getString(3) + ", ");
		    System.out.print("Department_ID : " + rs.getString(4));
		    System.out.println();
		}
		/* Delete Row */
		String DeleteSQL = "DELETE FROM student_table where ID = 2";
		st.executeUpdate(DeleteSQL);
		
		rs = st.executeQuery("SELECT ID, name, address, department_ID FROM student_table");

		System.out.println("============ RESULT ============");
		while (rs.next()) {
		    System.out.print("ID : " + rs.getString(1) + ", ");
		    System.out.print("Name : " + rs.getString(2) + ", ");
		    System.out.print("Address : " + rs.getString(3) + ", ");
		    System.out.print("Department_ID : " + rs.getString(4));
		    System.out.println();
		}
		
		/* Drop table */
		String DropTableSQL = "DROP TABLE student_table";
		st.executeUpdate(DropTableSQL);
		
		preparedStmt.close();
		st.close();
		rs.close();
 
	}

}
