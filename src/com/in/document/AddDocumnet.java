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
 * Servlet implementation class AddDocumnet
 */
@WebServlet("/AddDocumnet")
public class AddDocumnet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDocumnet() {
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
		String title = request.getParameter("title");
		String pubn = request.getParameter("pubn");
		String pubid = request.getParameter("pubid");
		String pubadd = request.getParameter("pubadd");
		String pubd = request.getParameter("pubd");
		String cpno = request.getParameter("cpno");
		String pos = request.getParameter("pos");
		String branch = request.getParameter("branch");
		String type = request.getParameter("type");
		String authn = request.getParameter("authn");
		String authid = request.getParameter("authid");
		String isbn = request.getParameter("isbn");
		String jvol = request.getParameter("jvol");
		String scope = request.getParameter("scope");
		String isno = request.getParameter("isno");
		String ediname = request.getParameter("ediname");
		String edid = request.getParameter("edid");
		String prod = request.getParameter("prod");
		String prol = request.getParameter("prol");
		String proe = request.getParameter("proe");
		Statement stmt = DBconnection.getConnection();
		System.out.println("docid:= "+docid+" ,title:= "+title+" ,pubn:= "+pubn+" ,pubid:= "+pubid);
		System.out.println(",pubadd:= "+pubadd+",pubd:= "+pubd+" ,cpno:= "+cpno+" ,pubid:= "+pos);
		System.out.println(",branch:= "+branch+" ,type:= "+type+" ,authn:= "+authn+" ,authid:= "+authid);
		System.out.println(" ,isbn:= "+isbn+" ,jvol:= "+jvol+" ,scope:= "+scope+" ,isno:= "+isno);
		System.out.println(" ,ediname:= "+ediname+" ,edid:= "+edid+" ,prod:= "+prod+" ,prol:="+prol+" ,proe:="+proe);
		  try {
			  if (!docid.trim().isEmpty()){
				  String query = "insert into PUBLISHER(PUBLISHERID,PUBNAME,ADDRESS)values('"+pubid+"','"+pubn+"','"+
						  pubadd+"');";
				  String query1 = "insert into document(DOCID,TITLE,PDATE,PUBLISHERID,TYPE)values('"+docid+"','"+title+"','"+
						  pubd+"','"+pubid+"','"+type+"');";
				  String query2 = "insert into copy(DOCID,COPYNO,LIBID,POSITION)values('"+docid+"','"+cpno+"','"+branch+"','"+
						  pos+"');";
				  stmt.executeUpdate(query);
				  stmt.executeUpdate(query1);
				  stmt.executeUpdate(query2);
			  }
			  if(!docid.trim().isEmpty() && type.trim().equals("BOOK")){
				  String query = "insert into BOOK(DOCID,ISBN)values('"+docid+"','"+isbn+"');";
				  String query1 = "insert into AUTHOR(AUTHORID,ANAME)values('"+authid+"','"+authn+"');";
				  String query2 = "insert into WRITES(DOCID,AUTHORID)values('"+docid+"','"+authid+"');";
				  stmt.executeUpdate(query);
				  stmt.executeUpdate(query1);
				  stmt.executeUpdate(query2);
			  }
			  if(!docid.trim().isEmpty() && type.trim().equals("PROCEEDINGS")){
				  String query = "insert into PROCEEDINGS(DOCID,CDATE,CLOCATION,CEDITOR)values('"+docid+"','"+prod+"','"+prol+"','"+
						  proe+"');";
				  stmt.executeUpdate(query);
			  }
			  if(!docid.trim().isEmpty() && type.trim().equals("JOURNAL_VOLUME")){
				  String query = "insert into JOURNAL_VOLUME(DOCID,JVOLUME,EDITOR_ID)values('"+docid+"','"+jvol+"','"+edid+"');";
				  String query1 = "insert into JOURNAL_ISSUE(DOCID,ISSUE_NO,SCOPE)values('"+docid+"','"+isno+"','"+scope+"');";
				  String query2 = "insert into CHIEF_EDITOR(EDITOR_ID,ENAME)values('"+edid+"','"+ediname+"');";
				  String query3 = "insert into INV_EDITOR(DOCID,ISSUE_NO,INAME)values('"+docid+"','"+isno+"','"+ediname+"');";
				  stmt.executeUpdate(query2);
				  stmt.executeUpdate(query);
				  stmt.executeUpdate(query1);
				  stmt.executeUpdate(query3);
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
		  HttpSession session=request.getSession();
		   String sessionfname = (String)session.getAttribute ( "sessionfname" );
		   String sessionlname = (String)session.getAttribute( "sessionlname" );
		   session.setAttribute( "sessionfname", sessionfname );
		   session.setAttribute( "sessionlname", sessionlname );
			  request.getRequestDispatcher("Add_Document.jsp").forward(request, response);
		  
		
	}

}
