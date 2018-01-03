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
 * Servlet implementation class ModifyVolume
 */
@WebServlet("/ModifyVolume")
public class ModifyVolume extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyVolume() {
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
		String branch = request.getParameter("branch");
		String[] copyno = request.getParameterValues("copyno");
		String[] docid = request.getParameterValues("docid");
		String[] position = request.getParameterValues("position");
		String[] jvol = request.getParameterValues("jvol");
		String[] issue_no = request.getParameterValues("issue_no");
		String[] modify = request.getParameterValues("modify");
		String[] delete = request.getParameterValues("delete");
		Statement stmt = DBconnection.getConnection();
		Statement stmt1 = DBconnection.getConnection();
		Statement stmt2 = DBconnection.getConnection();
		Statement stmt3 = DBconnection.getConnection();
		int cnt =0;
		int cnt1 =0;
		if(modify!=null && modify.length>0){
		for(int i=0;i<modify.length;i++){
		try {
			System.out.println("docid:"+copyno[i]+"pdate:"+position[i]+"Branch"+branch);
			  if (modify[i].trim().equals("yes") && !copyno[i].trim().isEmpty()){
				  String query = "update copy set position='"+position[i]+"' where LIBID = '"+branch+"'and copyno='"+copyno[i]+"';";
				  String query1 = "update journal_volume set jvolume ='"+jvol[i]+"' where docid='"+docid[i]+"';";
				  String query2 = "update journal_issue set issue_no ='"+issue_no[i]+"' where docid='"+docid[i]+"';";
				  System.out.println(query);
				  System.out.println(query1);
				  stmt.executeUpdate(query);
				  stmt2.executeUpdate(query1);
				  stmt3.executeUpdate(query2);
				  cnt++;
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				stmt2.close();
				stmt3.close();
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
				  if (!delete[i].trim().equals("no") && !copyno[i].trim().isEmpty()){
					  String query = "delete from copy where LIBID = '"+branch+"'and copyno='"+delete[i]+"';";
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
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		}
		if(cnt>0 || cnt1>0){
		pw.println ("<html><body><script>alert('"+cnt+"No of records updated and'"+cnt1+"No of record deleted');</script></body></html>");
		}
		HttpSession session=request.getSession();
		   String sessionfname = (String)session.getAttribute ( "sessionfname" );
		   String sessionlname = (String)session.getAttribute( "sessionlname" );
		   session.setAttribute( "sessionfname", sessionfname );
		   session.setAttribute( "sessionlname", sessionlname );
		request.getRequestDispatcher("Document.jsp").forward(request, response);
		  
	}
}
