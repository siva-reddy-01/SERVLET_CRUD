package com.klef;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/ListEmployeeID")
public class ListEmployeeID extends HttpServlet 
{
    public void service(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
    {
    	response.setContentType("text/html");
    	PrintWriter out=response.getWriter();
    	
    	
    	
    	try
    	{
    		Connection con=null;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver class loaded");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","root");
			System.out.println("Connetion established");
			
			String qry="select * from employee where id=?";
			PreparedStatement pstmt=con.prepareStatement(qry);
			pstmt.setString(1, "");
			
			ResultSet rs=pstmt.executeQuery();
			out.println("<h2 align=center>Veiw Employee Records By Name</h2><br>");
			out.println("<table align=center border='2'>");
			out.println("<tr bgcolor='red'>");
			out.println("<th>ID</th>");
			out.println("<th>NAME</th>");
			out.println("<th>GENDER</th>");
			out.println("<th>EMAIL</th>");
			out.println("<th>PASSWORD</th>");
			out.println("<th>DEPARTMENT</th>");
			out.println("<th>LOCATION</th>");
			out.println("</tr>");
			
			while(rs.next())
			{
				out.println("<tr>");
				out.println("<td>"+rs.getInt(1)+"</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");
				out.println("<td>"+rs.getString(4)+"</td>");
				out.println("<td>"+rs.getString(5)+"</td>");
				out.println("<td>"+rs.getString(6)+"</td>");
				out.println("<td>"+rs.getString(7)+"</td>");
				out.println("</tr>");
			}
			out.println("</table>");	
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    	
    }
}
