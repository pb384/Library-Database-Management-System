package com.in.empreader;

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
 * Servlet implementation class FindReader
 */
@WebServlet("/FindReader")
public class FindReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindReader() {
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
		List<String> _resnumber = new ArrayList<String>();
		List<String> _readerid = new ArrayList<String>();
		List<String> _docid = new ArrayList<String>();
		List<String> _copyno = new ArrayList<String>();
		List<String> _rname = new ArrayList<String>();
		List<String> _rtype = new ArrayList<String>();
		List<String> _lname = new ArrayList<String>();
		List<String> _add = new ArrayList<String>();
		List<String> _status = new ArrayList<String>();
		List<String> _libid = new ArrayList<String>();
		PrintWriter pw=response.getWriter();
		String rid = request.getParameter("rid");
		String rno = request.getParameter("rno");
		Statement stmt = DBconnection.getConnection();
		  try {
			  if (!rid.trim().isEmpty() && !rno.trim().isEmpty()){
				  String query = "select  resnumber,readerid,docid,copyno,rname,rtype,lname,address,status,libid from reserves natural join RESERVATION_STATUS natural join reader natural join branch  where resnumber = '"+rno+"' and readerid = '"+rid+"';";
				  rs=stmt.executeQuery(query);
				  flag=0;
			  }
			  else if(!rid.trim().isEmpty()){
				  String query = "select  resnumber,readerid,docid,copyno,rname,rtype,lname,address ,status,libid from reserves natural join RESERVATION_STATUS natural join reader natural join branch  where readerid = '"+rid+"';";
				  rs=stmt.executeQuery(query);
				  flag=0;
			  }
			  else if(!rno.trim().isEmpty()){
				  String query = "select  resnumber,readerid,docid,copyno,rname,rtype,lname,address,status,libid from reserves natural join RESERVATION_STATUS natural join reader natural join branch  where resnumber = '"+rno+"';";
				  rs=stmt.executeQuery(query);
				  flag=0;
			  }
			  else{
				  String query = "select  resnumber,readerid,docid,copyno,rname,rtype,lname,address,status,libid from reserves natural join RESERVATION_STATUS natural join reader natural join branch ;";
				  rs=stmt.executeQuery(query);
				  flag=0;
			  }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			  
			while(rs.next())
			  {
			   
				_resnumber.add(rs.getString(1));
				_readerid.add(rs.getString(2));
				_docid.add(rs.getString(3));
				_copyno.add(rs.getString(4));
				_rname.add(rs.getString(5));
				_rtype.add(rs.getString(6));
				_lname.add(rs.getString(7));
				_status.add(rs.getString(9));
				_add.add(rs.getString(8));
				_libid.add(rs.getString(10));
			    
			    
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
			  	request.setAttribute("_resnumber", _resnumber);
			    request.setAttribute("_readerid", _readerid);
			    request.setAttribute("_docid", _docid);
			    request.setAttribute("_copyno", _copyno);
			    request.setAttribute("_rname", _rname);
			    request.setAttribute("_rtype", _rtype);
			    request.setAttribute("_lname", _lname);
			    request.setAttribute("_status", _status);
			    request.setAttribute("_add", _add);
			    request.setAttribute("_libid", _libid);
			    request.setAttribute("cnt", cnt);
			    HttpSession session=request.getSession();
				   String sessionfname = (String)session.getAttribute ( "sessionfname" );
				   String sessionlname = (String)session.getAttribute( "sessionlname" );
				   session.setAttribute( "sessionfname", sessionfname );
				   session.setAttribute( "sessionlname", sessionlname );
			  request.getRequestDispatcher("Reader.jsp").forward(request, response);
		  }
		
		
		

	}


}
