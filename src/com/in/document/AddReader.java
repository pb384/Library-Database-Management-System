package com.in.document;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.in.database.DBconnection;

/**
 * Servlet implementation class AddReader
 */
@WebServlet("/AddReader")
public class AddReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		int flag=1;
		
		PrintWriter out=response.getWriter();
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String add = request.getParameter("add");
		Statement stmt = DBconnection.getConnection();
		Statement stmt1 = DBconnection.getConnection();
		try {
			  if (!name.trim().isEmpty()){
				  String query = "insert into Reader(RNAME,RTYPE,ADDRESS)values('"+name+"','"+type+"','"+add+"');";
				  stmt.executeUpdate(query);
				  rs = stmt.getGeneratedKeys();
				  request.setAttribute("customerID",rs.getString(1));
				  
				  
			  }
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		HttpSession session=request.getSession();
		   String sessionfname = (String)session.getAttribute ( "sessionfname" );
		   String sessionlname = (String)session.getAttribute( "sessionlname" );
		   session.setAttribute( "sessionfname", sessionfname );
		   session.setAttribute( "sessionlname", sessionlname );
		  request.getRequestDispatcher("Add_Reader.jsp").forward(request, response);
	  
	
	}

}
