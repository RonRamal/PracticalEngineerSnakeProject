package Control;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection {
	
	private static String url="jdbc:mysql://localhost/snakedatabase?useSSL=false";
	public static Connection conn;
	public static Statement stmt;
	
	public static void open(){
		String user="root";
		String password="do1815ron";
		try{
			conn=DriverManager.getConnection(url,user,password);
			stmt=conn.createStatement();
		}
		catch(Exception e){
		 e.printStackTrace();	
		}	
	}
	public static ResultSet GetResultSet(String query)throws SQLException{
		open();
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}
	public static void close() throws SQLException{
		stmt.close();
		conn.close();
	}
}