package com.usermanagement.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.usermanagement.dao.UserDAO;
import com.usermanagement.model.User;


@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
   
    public UserServlet() {	
    	
    	this.userDao=new UserDAO();
       
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getServletPath();
		switch(action){
			case "/new": 
			try {
				showNewForm(request,response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			case "/insert":
			try {
				insertUser(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			case "/delete":
			try {
				deleteUser(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;	
			case "/edit":
			try {
				showEditForm(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			case "/update":
			try {
				updateUser(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			default:
				//list
				listUsers(request, response);
				break;
		}
		
		
	}
	
	
	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<User> usersList=userDao.getAllUsers();
		request.setAttribute("usersList", usersList);
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("user-list.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("user-form.jsp");
		requestDispatcher.forward(request, response);
	}
	
	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name= request.getParameter("name");
		String email= request.getParameter("email");
		String country= request.getParameter("country");
		
		User newUser= new User(name,email,country);

		userDao.createUser(newUser);

		response.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int id=Integer.parseInt(request.getParameter("id"));
	
		User existingUser= userDao.getUserById(id); 
		
		request.setAttribute("user", existingUser);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("user-form.jsp");
		requestDispatcher.forward(request, response);
		
	
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		int id =Integer.parseInt(request.getParameter("id"));
		userDao.deleteUser(id);
		response.sendRedirect("list");
	
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id =Integer.parseInt(request.getParameter("id"));
		String name= request.getParameter("name");
		String email= request.getParameter("email");
		String country= request.getParameter("country");
		if(!name.isEmpty()&&!email.isEmpty()&&!country.isEmpty())
		{
			User newUser= new User(id,name,email,country);
		
		
		userDao.updateUser(newUser);
		response.sendRedirect("list");

		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	

	

}
