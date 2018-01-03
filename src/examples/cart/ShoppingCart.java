package examples.cart;
import java.util.*;


public class ShoppingCart implements java.io.Serializable{
	// The shopping cart items are stored in a Vector.
    protected Vector<Item> items;

    public ShoppingCart()
    {
        items = new Vector<Item>();
    }

/** Returns a Vector containing the items in the cart. The Vector
 *  returned is a clone, so modifying the vector won't affect the
 *  contents of the cart.
 */
    public Vector<Item> getItems()
    {
        return (Vector) items.clone();
    }

    public void addItem(Item newItem)
    {
        items.addElement(newItem);
    }

    public void removeItem(int itemIndex)
    {
        items.removeElementAt(itemIndex);
    }
    public void removeAllItem()
    {
        items.removeAllElements();
    }

// Warning! This order number is reset every time the server is
// restarted. This technique of generating an order number is
// just for demonstration.
    protected static int nextOrderNumber = 1;
    
}
