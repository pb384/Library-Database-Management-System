package com.in.reader;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.in.database.DBconnection;

/**
 * Servlet implementation class Rredirect
 */
@WebServlet("/Rredirect")
public class Rredirect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rredirect() {
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
		  Statement stmt = DBconnection.getConnection();
		  Statement stmt1 = DBconnection.getConnection();
		  String docid = request.getParameter("docid");
		  String title = request.getParameter("title");
		  List<String> branchid = new ArrayList<String>();
		  List<String> branchname = new ArrayList<String>();
		  String type = null;
		  PrintWriter pw=response.getWriter();
		  Integer cn =0;
		  try {
			  if (!docid.trim().isEmpty()){
				  String query = "select TYPE from DOCUMENT where docid='"+docid+"';";
				  rs=stmt.executeQuery(query);
				  String query1 = "select  DISTINCT(LIBID),LNAME from branch natural join copy where docid='"+request.getParameter("docid")+"';";
				  rs1=stmt1.executeQuery(query1);
			  }} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  try {
					while(rs.next())
					  {
					   type = rs.getString(1);
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
				  request.setAttribute("branchid", branchid);
				  request.setAttribute("branchname", branchname);
				  request.setAttribute("cn", cn);
				  System.out.println(type);
				  if(type.equalsIgnoreCase("Book")){
					  request.getRequestDispatcher("RBook.jsp").forward(request, response);
				  }
				  else if(type.equalsIgnoreCase("JOURNAL_VOLUME")){
					  request.getRequestDispatcher("RVolume.jsp").forward(request, response);
					  
				  }else if(type.equalsIgnoreCase("Proceedings")){
					  request.getRequestDispatcher("RProceedings.jsp").forward(request, response);
				  }
		 
	
	}

}
