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

@WebServlet("/EditServlet2")
public class EditServlet2 extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{	
		final String query = "update book set BOOKNAME=?,BOOKEDITION=?,BOOKPRICE=? where id=?";
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","1234");
			PreparedStatement ps = con.prepareStatement(query);
			int id = Integer.parseInt(req.getParameter("id"));
			
			String bookName = req.getParameter("bookName");
			String bookEdition = req.getParameter("bookEdition");
			String price = req.getParameter("bookPrice");
			float bookPrice = Float.parseFloat(price);
			
			ps.setString(1, bookName);
			ps.setString(2, bookEdition);
			ps.setFloat(3, bookPrice);
			ps.setInt(4, id);
			
			int count = ps.executeUpdate();
			if(count==1)
			{
				pw.println("<marquee bgcolor='green' size='40'>Record Updated Sucessfully</marquee>");
			}
			else
			{
				pw.println("<marquee bgcolor='red' size='6'>Record Is Not Updated</marquee>");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
		pw.println("<h4><a href='home.html'>Home</a></h4>");
		pw.println("<h4><a href='bookList'>Book List</a></h4>");
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		doGet(req,res);
	}
}
