package com.in.reader;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.in.database.DBconnection;

import examples.cart.ShoppingCart;

/**
 * Servlet implementation class AddReservation
 */
@WebServlet("/AddReservation")
public class AddReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReservation() {
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
		HttpSession session=request.getSession();
		String sessionreaderid = (String)session.getAttribute ( "sessionreaderid" );
		ShoppingCart cart = (ShoppingCart)session.getAttribute ( "ShoppingCart" );
		String sessionrname = (String)session.getAttribute( "sessionrname" );
		session.setAttribute( "sessionreaderid", sessionreaderid );
		session.setAttribute( "sessionrname", sessionrname );
		PrintWriter pw=response.getWriter();
		String[] docid = request.getParameterValues("docid");
		String[] branchid = request.getParameterValues("branch");
		String[] copyno = request.getParameterValues("copyno");
		Statement stmt = DBconnection.getConnection();
		Statement stmt1 = DBconnection.getConnection();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String resDate = dateFormat.format(cal.getTime());
		int cnt =0;
		
		if(docid!=null && docid.length>0){
			System.out.println(docid.length);
		for(int i=0;i<docid.length;i++){
		try {
			
			  if (!docid[i].trim().isEmpty() && !branchid[i].trim().isEmpty() && !copyno[i].trim().isEmpty()){
				  String query = "insert into RESERVES(READERID,DOCID, COPYNO, LIBID,DTIME)values('"+sessionreaderid.trim()+"','"+docid[i]+"','"+copyno[i]+"','"+branchid[i]+"','"+resDate+"');";
				  System.out.println(query);
				  stmt.executeUpdate(query);
				  rs = stmt.getGeneratedKeys();
				  String query1 ="insert into reservation_status(resnumber,status)values('"+rs.getString(1)+"','RESERVED');";
				  stmt1.executeUpdate(query1);
				  cnt++;
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				stmt1.close();
				cart.removeAllItem();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		}
	
		
		request.getRequestDispatcher("RDocument.jsp").forward(request, response);
		  
		
	}

}

