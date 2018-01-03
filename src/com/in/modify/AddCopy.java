package com.in.modify;

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
 * Servlet implementation class AddBook
 */
@WebServlet("/AddCopy")
public class AddCopy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCopy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		int flag=1;
		PrintWriter pw=response.getWriter();
		String docid = request.getParameter("docid");
		String branch = request.getParameter("branch");
		String pos = request.getParameter("pos");
		String cpno = request.getParameter("cpno");
		Statement stmt = DBconnection.getConnection();
		try {
			  if (!docid.trim().isEmpty()){
				  String query = "insert into copy(DOCID,COPYNO,LIBID,POSITION)values('"+docid+"','"+cpno+"','"+branch+"','"+
						  pos+"');";
				  stmt.executeUpdate(query);
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
		  request.getRequestDispatcher("Add_Copy.jsp").forward(request, response);
	  
	
}


}
