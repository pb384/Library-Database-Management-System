package com.in.reader;

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

import com.in.database.DBconnection;

/**
 * Servlet implementation class RDocument
 */
@WebServlet("/RDocument")
public class RDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RDocument() {
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
		List<String> _pubname = new ArrayList<String>();
		List<String> _wrtby = new ArrayList<String>();
		PrintWriter pw=response.getWriter();
		String wrtby = request.getParameter("wrtby");
		String title = request.getParameter("title");
		String type = request.getParameter("change");
		pw.printf(wrtby+","+title+","+type);
		Statement stmt = DBconnection.getConnection();
		  try {
			  if(type.equalsIgnoreCase("Book"))
			  {
				  if (!wrtby.trim().isEmpty() && !title.trim().isEmpty()){
					  String query = "select  DOCID,TITLE, PUBNAME, ANAME from document a natural join publisher p natural join writes w natural join author b where aname LIKE '%"+wrtby+"%' or title LIKE '%"+title+"%';";
					  rs=stmt.executeQuery(query);
					  flag=0;
				  }
				  else if(!wrtby.trim().isEmpty()){
					  String query = "select  DOCID,TITLE, PUBNAME, ANAME from document a natural join publisher p  natural join writes w natural join author b where aname LIKE '%"+wrtby+"%';";
					  rs=stmt.executeQuery(query);
					  flag=0;
				  }	  
				  else if(!title.trim().isEmpty()){
					  String query = "select  DOCID,TITLE, PUBNAME, ANAME from document a natural join publisher p natural join writes w natural join author b where title LIKE '%"+title+"%';";
					  rs=stmt.executeQuery(query);
					  flag=0;
				  }
				  else{
					  rs=stmt.executeQuery("select  DOCID,TITLE, PUBNAME, ANAME from document a natural join publisher p natural join writes w natural join author b where docid in (select DOCID from document where type LIKE '%"+type+"%');");
					  flag=0;  
					  
					    }
		  }
		  else if(type.equalsIgnoreCase("JOURNAL_VOLUME"))
		  {
			  if (!wrtby.trim().isEmpty() && !title.trim().isEmpty()){
				  String query = "select  DOCID,TITLE, PUBNAME, ENAME from document a natural join publisher p natural join JOURNAL_VOLUME j natural join CHIEF_EDITOR c where ENAME LIKE '%"+wrtby+"%' or title LIKE '%"+title+"%';";
				  rs=stmt.executeQuery(query);
				  flag=0;
			  }
			  else if(!wrtby.trim().isEmpty()){
				  String query = "select  DOCID,TITLE, PUBNAME, ENAME from document a natural join publisher p  natural join writes JOURNAL_VOLUME j natural join CHIEF_EDITOR c where ENAME LIKE '%"+wrtby+"%';";
				  rs=stmt.executeQuery(query);
				  flag=0;
			  }	
			  else if(!title.trim().isEmpty()){
				  String query = "select  DOCID,TITLE, PUBNAME, ENAME from document a natural join publisher p natural join JOURNAL_VOLUME j natural join CHIEF_EDITOR c where title LIKE '%"+title+"%';";
				  rs=stmt.executeQuery(query);
				  flag=0;
			  }
			  else{
				  rs=stmt.executeQuery("select  DOCID,TITLE, PUBNAME, ENAME from document a natural join publisher p natural join JOURNAL_VOLUME j natural join CHIEF_EDITOR c where docid in (select DOCID from document where type LIKE '%"+type+"%');");
				  flag=0;  
				  
				    }
			  
		  }
		  
		  else if(type.equalsIgnoreCase("Proceedings"))
		  {
			  if (!wrtby.trim().isEmpty() && !title.trim().isEmpty()){
				  String query = "select  DOCID,TITLE, PUBNAME, CEDITOR from document a natural join publisher p natural join PROCEEDINGS p where CEDITOR LIKE '%"+wrtby+"%' or title LIKE '%"+title+"%';";
				  rs=stmt.executeQuery(query);
				  flag=0;
			  }
			  else if(!wrtby.trim().isEmpty()){
				  String query = "select  DOCID,TITLE, PUBNAME, CEDITOR from document a natural join publisher p  natural join PROCEEDINGS p where CEDITOR LIKE '%"+wrtby+"%';";
				  rs=stmt.executeQuery(query);
				  flag=0;
			  }	   
			  else if(!title.trim().isEmpty()){
				  String query = "select  DOCID,TITLE, PUBNAME, CEDITOR from document a natural join publisher p natural join PROCEEDINGS p where title LIKE '%"+title+"%';";
				  rs=stmt.executeQuery(query);
				  flag=0;
			  }
			  else{
				  rs=stmt.executeQuery("select  DOCID,TITLE, PUBNAME, CEDITOR from document a natural join publisher p natural join PROCEEDINGS p where docid in (select DOCID from document where type LIKE '%"+type+"%');");
				  flag=0;  
				  
				    }
		  }
		 
		  }
		 
	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			  
			while(rs.next())
			  {
			   
			    _docid.add(rs.getString(1));
			    _title.add(rs.getString(2));
			    _pubname.add(rs.getString(3));
			    _wrtby.add(rs.getString(4));
			    
			    
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
			    request.setAttribute("_pubname", _pubname);
			    request.setAttribute("_wrtby", _wrtby);
			    request.setAttribute("cnt", cnt);
			  request.getRequestDispatcher("RDocument.jsp").forward(request, response);
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
		doGet(request, response);
	}

}
