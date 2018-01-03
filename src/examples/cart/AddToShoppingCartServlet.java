package examples.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToShoppingCartServlet
 */
@WebServlet("/AddToShoppingCartServlet")
public class AddToShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request,
	        HttpServletResponse response)
	        throws IOException, ServletException
	    {
		HttpSession session=request.getSession();
		String sessionreaderid = (String)session.getAttribute ( "sessionreaderid" );
		String sessionrname = (String)session.getAttribute( "sessionrname" );
		session.setAttribute( "sessionreaderid", sessionreaderid );
		session.setAttribute( "sessionrname", sessionrname );

	// First get the item values from the request.
		String title = request.getParameter("title");
			String docid = request.getParameter("docid");
			String branchid = request.getParameter("branch");
			String[] copyno = request.getParameterValues("copyno");
			String[] reserve = request.getParameterValues("reserve");
			
			// Get the cart.
	        ShoppingCart cart = (ShoppingCart) session.
	            getAttribute("ShoppingCart");
	        
	     // If there is no shopping cart, create one.
	        if (cart == null)
	        {
	            cart = new ShoppingCart();

	            session.setAttribute("ShoppingCart", cart);
	        }


			
			if(reserve!=null && reserve.length>0){
				for(int i=0;i<reserve.length;i++){
					if(reserve[i].equals("YES")){
	// Now create an item to add to the cart.
	        Item item = new Item(sessionreaderid,title,docid,copyno[i] , branchid);
	        cart.addItem(item);
					}}}
	// Now display the cart and allow the user to check out or order more items.
	        response.sendRedirect(response.encodeRedirectURL(
	            "MyCart.jsp"));
	    }
}
