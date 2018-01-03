
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
 * Servlet implementation class Modify
 */
@WebServlet("/Modify")
public class Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modify() {
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
		String[] docid = request.getParameterValues("docid");
		String[] title = request.getParameterValues("title");
		String[] pdate = request.getParameterValues("pdate");
		String[] pubid = request.getParameterValues("pubid");
		String[] pubname = request.getParameterValues("pubname");
		String[] modify = request.getParameterValues("modify");
		String[] delete = request.getParameterValues("delete");
		Statement stmt = DBconnection.getConnection();
		Statement stmt1 = DBconnection.getConnection();
		Statement stmt2 = DBconnection.getConnection();
		int cnt =0;
		int cnt1 =0;
		if(modify!=null && modify.length>0){
		for(int i=0;i<modify.length;i++){
		try {
			System.out.println("docid:"+docid[i]+"title:"+title[i]+"pdate:"+pdate[i]);
			  if (modify[i].trim().equals("yes") && !docid[i].trim().isEmpty()){
				  String query = "update document set title ='"+title[i]+"',pdate='"+pdate[i]+"' where docid='"+docid[i]+"';";
				  String query1 ="update publisher set pubname ='"+pubname[i]+"' where PUBLISHERID ='"+pubid[i]+"';";
				  System.out.println(query);
				  stmt.executeUpdate(query);
				  System.out.println(query1);
				  stmt2.executeUpdate(query1);
				  cnt++;
			  }
		} catch (SQLException e) {
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
		}
		}
		if(delete!=null && delete.length>0){
		for(int i=0;i<delete.length;i++){
			try {
				System.out.println("docid:"+docid[i]+"title:"+title[i]+"pdate:"+pdate[i]);
				  if (!delete[i].trim().equals("no") && !docid[i].trim().isEmpty()){
					  String query = "delete from document where docid='"+delete[i]+"';";
					  System.out.println(query);
					  stmt1.executeUpdate(query);
					  cnt1++;
				  }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					stmt1.close();
					stmt2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		}
		HttpSession session=request.getSession();
		   String sessionfname = (String)session.getAttribute ( "sessionfname" );
		   String sessionlname = (String)session.getAttribute( "sessionlname" );
		   session.setAttribute( "sessionfname", sessionfname );
		   session.setAttribute( "sessionlname", sessionlname );
		request.getRequestDispatcher("Document.jsp").forward(request, response);
		  
		
	}

}
