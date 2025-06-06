package exampleJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Assignment2 {
	public static void main(String[] args) {
		try(Connection dbConnect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbcdemo", "root", "Coolpad360@");
			Scanner sc = new Scanner(System.in);) {
			
			int choice = 0;
			String ch = "";
			StringBuilder string = new StringBuilder(); 
			int i = 0;
			
			System.out.println("Enter the table name");
			String tableName = sc.next();
			
			do {
				
				System.out.println("Do you want to add more columns [Y/N]?");
				ch = sc.next().toLowerCase();
				System.out.println(ch);
				
				if(ch.equals("y")) {
					if(i != 0) {
						string.append(",");
					}
					System.out.println("Enter the column:");
					String column = sc.next();
					string.append(column);
					string.append(" ");
					i++;
				}
				
				System.out.println("Choose the dataType: \n 1. varChar \n 2. int \n 3. float \n 4. Save");
				choice = sc.nextInt();
				sc.nextLine();
				
				if(choice == 1) {
					System.out.println("Enter varchar length:");
					int value = sc.nextInt();
					sc.nextLine();
					string.append("varchar(").append(value).append(")");
				} else if(choice == 2) {
					string.append("int");
				} else if(choice == 3){
					string.append("float");
				}
			} while (choice != 4);
			
			String columnData = new String(string);
			System.out.println(string);
			
			String query = "create table " + tableName + " (" + columnData + ");";
			System.out.println(query);
			try(PreparedStatement create = dbConnect.prepareStatement(query);) {
				create.executeUpdate();				
			}
			
		} catch (SQLException s) {
			s.printStackTrace();
		} 
	}
}
