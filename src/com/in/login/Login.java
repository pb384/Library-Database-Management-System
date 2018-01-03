package com.in.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.sql.*;

import org.sqlite.*;

import com.in.database.DBconnection;;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		ResultSet rs=null;
		int flag=0;
		int flag1 = 0;
		
		PrintWriter pw=response.getWriter();
		String uid = request.getParameter("username");
		String Pwd = request.getParameter("password");
		String rid = request.getParameter("readerid");
		String tab = request.getParameter("tabs");
		System.out.println(uid+","+Pwd+","+rid+","+tab);
		Statement stmt = DBconnection.getConnection();
		  try {
			  if(tab.equals("employee")){
			rs=stmt.executeQuery("Select * from login where LIBID = '"+uid+"';");
			try {
				while(rs.next())
				  {
				   if(uid.equals(rs.getString(1)) && Pwd.equals(rs.getString(2)))
				   {
				    String tempFname=rs.getString(4);
				    String tempLname=rs.getString(5);
				    String tempEmail=rs.getString(6);

					session.setAttribute("sessionfname",tempFname);
				    session.setAttribute("sessionlname",tempLname);
				     flag=0;
				     flag1 =1;
				    break;
				   }
				   else
				   {
				    flag=1;
				    
				   }
				  }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				try {
					rs.close();
					stmt.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			  }else if(tab.equals("reader")){
				  rs=stmt.executeQuery("Select * from reader where readerid = '"+rid+"';");
				  try { flag=1;
						while(rs.next())
						  {
						    String readerid=rs.getString(1);
						    String readername=rs.getString(2);

							session.setAttribute("sessionreaderid",readerid);
						    session.setAttribute("sessionrname",readername);
						    flag1 =2;
						     flag=0;
						    break;
						   }
		
						  }
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally{
						try {
							rs.close();
							stmt.close();
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  if(flag==1 || flag1 == 0)
		  {
		   response.sendRedirect("login.jsp");
		  }
		  else
		  {  
		   if(flag1 == 1){
		   pw.print("welcome jaya");
		   response.sendRedirect("Document.jsp");
		   }else if(flag1 == 2){
			   response.sendRedirect("ReservedBooks.jsp");
		   }
		  }
		 
		 }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
