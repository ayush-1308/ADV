package com.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class UserDaoImpl implements UserDao{
	
	Connection dbConnect;
	PreparedStatement create;
	PreparedStatement listAll;	

	public UserDaoImpl() {
		
		try {
			Properties prop = new Properties();
				prop.load(new FileInputStream("application.properties"));
				String dbUrl = prop.getProperty("connection.dbUrl");
				String dbUsername = prop.getProperty("connection.dbUsername");
				String dbPassword = prop.getProperty("connection.dbPassword");
				
				String sqlCreateUser = prop.getProperty("sql.createUser");
				String sqlAllUser = prop.getProperty("sql.allUser");
				
				
				dbConnect = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
				dbConnect.prepareStatement(sqlCreateUser);
				dbConnect.prepareStatement(sqlAllUser);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch(SQLException s) {
				s.printStackTrace();
			}
	}
	
	@Override
	public boolean createUser(User userOBJ) {
		try {
			create.setInt(1, userOBJ.getId());
			create.setString(2, userOBJ.getName());
			create.setString(3, userOBJ.getEmail());
			create.setString(4, userOBJ.getPassword());
		} catch (SQLException s){
			s.printStackTrace();
		}
		return true;
	}

	@Override
	public Iterator<User> listAll() {
		List<User> list = new ArrayList<>();
		try (ResultSet result = listAll.executeQuery();) {
			while(result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				String email = result.getString(3);
				String password = result.getString(4);
				
				User user = new User(id, name, email, password);
				list.add(user);
			}
		} catch(SQLException s) {
			s.printStackTrace();
		}
		return list.iterator();
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int id = sc.nextInt();
		sc.nextLine();
		
		String name = sc.next();
		String email = sc.next();
		String password = sc.next();
		User user = new User(id, name, email, password);
		
		UserDao call = new UserDaoImpl();
		//System.out.println(call.listAll());
		call.createUser(user);
	}
}


/*
 * 
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("application.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("File Readed Successfully!");
		
		String dbUrl = prop.getProperty("connection.dbUrl");
		String dbUsername = prop.getProperty("connection.dbUsername");
		String dbPassword = prop.getProperty("connection.dbPassword");
		
		try(Connection dbConnect = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			PreparedStatement create = dbConnect.prepareStatement("insert into users values(?, ?, ?, ?)");
			Scanner sc = new Scanner(System.in);){
			
			System.out.println("Enter user id:");
			int id = sc.nextInt();
			sc.nextLine();
			
			System.out.println("Enter username:");
			String username = sc.next();
			
			System.out.println("Enter email:");
			String email = sc.next();
			
			System.out.println("Enter password:");
			String password = sc.next();
			
			create.setInt(1, id);
			create.setString(2, username);
			create.setString(3, email);
			create.setString(4, password);
			
			create.executeUpdate();
			
			return true;
		} catch (SQLException s) {
			
			s.printStackTrace();
 * */


