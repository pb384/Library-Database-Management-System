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
 * Servlet implementation class Document
 */
@WebServlet("/Document")
public class Document extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Document() {
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
		Integer cnt =0;
		List<String> _docid = new ArrayList<String>();
		List<String> _title = new ArrayList<String>();
		List<String> _pdate = new ArrayList<String>();
		List<String> _pubid = new ArrayList<String>();
		List<String> _pubname = new ArrayList<String>();
		PrintWriter pw=response.getWriter();
		String docid = request.getParameter("docid");
		String title = request.getParameter("title");
		String type = request.getParameter("change");
		pw.printf(docid+","+title+","+type);
		Statement stmt = DBconnection.getConnection();
		  try {
			  if (!docid.trim().isEmpty() && !title.trim().isEmpty()){
				  String query = "select  DOCID,TITLE,PDATE, PUBNAME,PUBLISHERID from document a natural join publisher p where docid LIKE '%"+docid+"%' or title LIKE '%"+title+"%';";
				  rs=stmt.executeQuery(query);
				  flag=0;
			  }
			  else if(!docid.trim().isEmpty()){
				  String query = "select  DOCID,TITLE,PDATE, PUBNAME,PUBLISHERID from document a natural join publisher p  where docid LIKE '%"+docid+"%';";
				  rs=stmt.executeQuery(query);
				  flag=0;
			  }
			  else if(!title.trim().isEmpty()){
				  String query = "select  DOCID,TITLE,PDATE, PUBNAME,PUBLISHERID from document a natural join publisher p where title LIKE '%"+title+"%';";
				  rs=stmt.executeQuery(query);
				  flag=0;
			  }
			  else
			  {
				  rs=stmt.executeQuery("select  DOCID,TITLE,PDATE, PUBNAME,PUBLISHERID from document  natural join publisher where docid in (select DOCID from "+type+");");
				  flag=0;
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			  
			while(rs.next())
			  {
			   
			    _docid.add(rs.getString(1));
			    _title.add(rs.getString(2));
			    _pdate.add(rs.getString(3));
			    _pubname.add(rs.getString(4));
			    _pubid.add(rs.getString(5));
			    
			    
			    cnt++;
			    
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
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
			  	request.setAttribute("_docid", _docid);
			    request.setAttribute("_title", _title);
			    request.setAttribute("_pdate", _pdate);
			    request.setAttribute("_pubname", _pubname);
			    request.setAttribute("_pubid", _pubid);
			    request.setAttribute("cnt", cnt);
			    HttpSession session=request.getSession();
				   String sessionfname = (String)session.getAttribute ( "sessionfname" );
				   String sessionlname = (String)session.getAttribute( "sessionlname" );
				   session.setAttribute( "sessionfname", sessionfname );
				   session.setAttribute( "sessionlname", sessionlname );
			  request.getRequestDispatcher("Document.jsp").forward(request, response);
		  }
		
		
		

	}

	private void elseif() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
