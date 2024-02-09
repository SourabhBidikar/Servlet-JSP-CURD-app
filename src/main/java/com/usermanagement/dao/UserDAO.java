package com.usermanagement.dao;

import java.sql.*;
import java.util.*;

import com.usermanagement.model.User;

public class UserDAO {

	//DAO means data access object
	//it is a design-pattern to separate all database related operations from our business logic
	
	static final String DB_URL="jdbc:mysql://localhost:3306/mydb";
	static final String USER="root";
	static final String PWD="root";
	
	private static final String INSERT_USERS="Insert into users"+"(name,email,country) values"+
					"(?,?,?)";

	private static final String SELECT_ALL_USERS="select * from users";
	private static final String SELECT_USER_BY_ID="Select * from users where id=?;";
	private static final String DELETE_USER="Delete from users where id=?;";
	private static final String UPDATE_USERS="update users set name=?,email=?,country=? where id=?;";
	
	
	
	
	
	//establish connection
	public Connection getConnection() {
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(DB_URL,USER,PWD);
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		return con;
		
	}
	
	
	//create or insert user 
	public void createUser(User user) {
		
		try(Connection con=getConnection();
				PreparedStatement pstmt=con.prepareStatement(INSERT_USERS);){
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getCountry());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	//update user
	public boolean updateUser(User user) {
		boolean rowUpdated=false;
		try(Connection con=getConnection();
				PreparedStatement pstmt=con.prepareStatement(UPDATE_USERS);){
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getCountry());
			pstmt.setInt(4, user.getId());
			
			 rowUpdated=pstmt.executeUpdate()>0;
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rowUpdated;
	}
	
	
//	get  User By Id
	
	public User getUserById(int id) {
		
		 User user = null;
		try(Connection con=getConnection();
				PreparedStatement pstmt=con.prepareStatement(SELECT_USER_BY_ID);)
		{
		
			 pstmt.setInt(1,id);
			
			 ResultSet rs=pstmt.executeQuery();
			 
			 while(rs.next()) {
				 
				 String name=rs.getString("name");
				 String email=rs.getString("email");
				 String country=rs.getString("country");
				 
				user= new User(id, name, email, country);
			 }
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return user;
	}
	
	//select all users
	public List<User> getAllUsers(){
		List<User> users= new ArrayList<User>();
		
		try(Connection con=getConnection();
				Statement stmt=con.createStatement();)
		{
		
			
			
			 ResultSet rs=stmt.executeQuery(SELECT_ALL_USERS);
			 
			 while(rs.next()) {
				 int id= rs.getInt("id");
				 String name=rs.getString("name");
				 String email=rs.getString("email");
				 String country=rs.getString("country");
				 
				 users.add(new User(id, name, email, country));
			 }
			 
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return users;
	
	}


//delete user

	public boolean deleteUser(int id) {	
		
		boolean rowDeleted=false;
		try(Connection con=getConnection();
				PreparedStatement pstmt=con.prepareStatement(DELETE_USER);)
		{
			
				pstmt.setInt(1, id);
				rowDeleted=pstmt.executeUpdate()>0;
			
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return rowDeleted;
	}

}