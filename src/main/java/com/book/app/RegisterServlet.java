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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{	
		final String query = "insert into book (BOOKNAME,BOOKEDITION,BOOKPRICE) values(?,?,?) ";
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","1234");
			PreparedStatement ps = con.prepareStatement(query);
			String BOOKNAME = req.getParameter("bookName");
			String BOOKEDITION = req.getParameter("bookEdition");
			
			String price = req.getParameter("bookPrice");
			float BOOKPRICE = Float.parseFloat(price);
			
			ps.setString(1,BOOKNAME);
			ps.setString(2, BOOKEDITION);
			ps.setFloat(3, BOOKPRICE);
			
			int count = ps.executeUpdate();
			if(count==1)
			{
				pw.println("<h1>Record is Registered Sucessfully</h1>");
			}
			else
			{
				pw.println("<h1>Record is Not Registered Sucessfully</h1>");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
		pw.println("<h4><a href='home.html'>Home</a></h4><br>");
		pw.println("<a href='bookList'>Book List</a><br>");
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		doGet(req,res);
	}
}
