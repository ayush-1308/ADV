package exampleJDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcExample {
	public static void main(String[] args) {
//		Connection dbConnection = null;
		
//		try {
//			Class.forName("com.mysql.cj.jdbc.driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		try(Connection dbConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbcDemo","root","Coolpad360@");
				Scanner sc = new Scanner(System.in);
				Statement statement = dbConnection.createStatement();
				 ) {
			System.out.println("Connection Success!!");
			
			String insert = "Insert into users values(1, 'Ayush', 'xyz@gamil.com', '123')";
			statement.executeUpdate(insert);
			ResultSet res = statement.executeQuery("Select * from users;");

			while(res.next()) {
				
				String result = res.getString(2 );
				System.out.println(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
//		catch(ClassNotFoundException c) {
//			c.printStackTrace();
//		}
	}
}
