package com.klef;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/DeleteEmployee")
public class DeleteEmployee extends HttpServlet 
{
	
	

	@SuppressWarnings("unused")
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
	
		try
		{
			Connection con=null;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver class loaded");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","root");
			System.out.println("Connetion established");
			
			String qry="delete from employee where name=?";
			PreparedStatement pstmt=con.prepareStatement(qry);
			pstmt.setString(1, "liki");
			int i=pstmt.executeUpdate();
			
			if(i>0)
			{
				RequestDispatcher rd=request.getRequestDispatcher("displayemployee");
				rd.forward(request, response);
			}
			else
			{
				out.println("unable to Delete  employee details");
			}
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
