package com.in.empreader;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.in.database.DBconnection;

/**
 * Servlet implementation class ChangeStatus
 */
@WebServlet("/ChangeStatus")
public class ChangeStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				ResultSet rs=null;
				int flag=1;
				PrintWriter pw=response.getWriter();
				String[] resnumber = request.getParameterValues("resnumber");
				String[] readerid = request.getParameterValues("readerid");
				String[] docid = request.getParameterValues("docid");
				String[] copyno = request.getParameterValues("copyno");
				String[] lname = request.getParameterValues("lname");
				String[] status = request.getParameterValues("status");
				String[] change = request.getParameterValues("change");
				String[] fine = request.getParameterValues("fine");
				Statement stmt = DBconnection.getConnection();
				Statement stmt1 = DBconnection.getConnection();
				Statement stmt2 = DBconnection.getConnection();
				List<String> _bddate = new ArrayList<String>();
				List<String> _rddate = new ArrayList<String>();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String borDate = dateFormat.format(cal.getTime());
				try {
					cal.setTime(dateFormat.parse(borDate));
					cal.add(Calendar.DATE, 20); // Adding 20 days
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // Now use today date.
				
				String rddate = dateFormat.format(cal.getTime());
				int cnt =0;
				int cnt1 =0;
				if(change!=null && change.length>0){
				for(int i=0;i<change.length;i++){
				try {
					
					  if (change[i].trim().equals("BORROWED") && status[i].trim().equals("RESERVED") &&!readerid[i].trim().isEmpty()){
						  String query = "insert into borrows (readerid,docid,copyno,libid,bdtime,rdtime) "
						  		+ "values('"+readerid[i]+"','"+docid[i]+"','"+copyno[i]+"','"+lname[i]+"','"+borDate+"','"+rddate+"');";
						  stmt.executeUpdate(query);
						  rs = stmt.getGeneratedKeys();
						  String query1 ="update reservation_status set status = 'BORROWED',BORNUMBER='"+rs.getString(1)+"',fine='"+fine[i]+"' where resnumber = '"+resnumber[i]+"';";
						  System.out.println(query);
						  
						  stmt1.executeUpdate(query1);
						  cnt++;
					  }
					  if (change[i].trim().equals("RETURNED") && status[i].trim().equals("BORROWED") && !resnumber[i].trim().isEmpty()){
						  String query1 ="update reservation_status set status = 'RETURNED',fine='"+fine[i]+"' where resnumber = '"+resnumber[i]+"';";
						  stmt2.executeUpdate(query1);
						 System.out.println(query1);
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
				HttpSession session=request.getSession();
				   String sessionfname = (String)session.getAttribute ( "sessionfname" );
				   String sessionlname = (String)session.getAttribute( "sessionlname" );
				   session.setAttribute( "sessionfname", sessionfname );
				   session.setAttribute( "sessionlname", sessionlname );
				request.getRequestDispatcher("Reader.jsp").forward(request, response);
				  
			}
	}
}
