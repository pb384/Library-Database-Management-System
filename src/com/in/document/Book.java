package com.in.document;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.in.database.DBconnection;

/**
 * Servlet implementation class Book
 */
@WebServlet("/Book")
public class Book extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Book() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		ResultSet rs1=null;
		int flag=1;
		Integer cnt =0;
		Integer cn =0;
		List<String> copyno = new ArrayList<String>();
		List<String> position = new ArrayList<String>();
		PrintWriter pw=response.getWriter();
		String docid = request.getParameter("docid");
		String branch = request.getParameter("branch");
		System.out.printf(docid+","+branch);
		List<String> branchid = new ArrayList<String>();
		List<String> branchname = new ArrayList<String>();
		List<String> aname = new ArrayList<String>();
		Statement stmt = DBconnection.getConnection();
		Statement stmt1 = DBconnection.getConnection();
		  try {
			  if (!docid.trim().isEmpty()){
				  String query = "select copyno,position,aname from copy natural join writes natural join author where docid='"+docid+"' and LIBID='"+branch+"';";
				  rs=stmt.executeQuery(query);
				  String query1 = "select  DISTINCT(LIBID),LNAME from branch natural join copy where docid='"+request.getParameter("docid")+"';";
				  rs1 = stmt1.executeQuery(query1);
				  flag=0;
				  
			  }
			  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			  
			while(rs.next())
			  {
			   
				copyno.add(rs.getString(1));
				position.add(rs.getString(2));
				aname.add(rs.getString(3));
				
			    
			    cnt++;
			    
			  }
			while(rs1.next())
			  {
			   branchid.add(rs1.getString(1));
			   branchname.add(rs1.getString(2));
			   cn++;
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
				rs1.close();
				stmt1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		  if(flag==1)
		  {
		   response.sendRedirect("login.jsp");
		  }
		  else
		  {  
			  	request.setAttribute("copyno", copyno);
			    request.setAttribute("position", position);
			    request.setAttribute("cnt", cnt);
			    request.setAttribute("aname", aname);
			    request.setAttribute("branchid", branchid);
				request.setAttribute("branchname", branchname);
				request.setAttribute("cn", cn);
				HttpSession session=request.getSession();
				   String sessionfname = (String)session.getAttribute ( "sessionfname" );
				   String sessionlname = (String)session.getAttribute( "sessionlname" );
				   session.setAttribute( "sessionfname", sessionfname );
				   session.setAttribute( "sessionlname", sessionlname );
			  request.getRequestDispatcher("Book.jsp").forward(request, response);
		  }
		
		
		
	}

}
