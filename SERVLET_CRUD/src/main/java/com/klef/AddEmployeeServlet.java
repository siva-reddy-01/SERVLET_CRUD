package com.klef;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings({ "serial", "unused" })
@WebServlet("/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet 
{
	
	public  void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int id=(int)(Math.random()+99999)+1;
		String name=request.getParameter("name");
		String gender=request.getParameter("gender");
		String email=request.getParameter("email");
		String pwd=request.getParameter("pwd");
		String department=request.getParameter("department");
		String location=request.getParameter("location");
		
		
		try
		{
			Connection con=null;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver class loaded");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","root");
			System.out.println("Connetion established");
			
			String qry="insert into employee values(?,?,?,?,?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(qry);
			
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, gender);
			pstmt.setString(4, email);
			pstmt.setString(5, pwd);
			pstmt.setString(6, department);
			pstmt.setString(7, location);
			
			int n=pstmt.executeUpdate();
			
			if(n>0)
			{
				RequestDispatcher rd=request.getRequestDispatcher("ViewEmployeeServlet");
				rd.forward(request, response);
				out.println("Records insert sucussesfull");
			}
			else
			{
				out.println("unable to insert the Employee Records");
			}
			con.close();
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

}
