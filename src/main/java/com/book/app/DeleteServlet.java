package com.book.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{	
		final String query = "delete from book where id=?";
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","1234");
			PreparedStatement ps = con.prepareStatement(query);
			int id = Integer.parseInt(req.getParameter("id"));
			ps.setInt(1, id);
			int count = ps.executeUpdate();
			if(count==1)
			{
				pw.println("<h1><marquee bgcolor='yellow'>Record Deleted Sucessfully</marquee></h1>");
			}
			else
			{
				pw.println("<h1><marquee bgcolor='red'>Record Is Not Deleted</marquee></h1>");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
		pw.println("<center><h4><a href='home.html'>Home</a></h4>");
		pw.println("<center><h4><a href='bookList'>Book List</a></h4>");
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		doGet(req,res);
	}

}
