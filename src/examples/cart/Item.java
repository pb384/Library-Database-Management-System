package examples.cart;

public class Item implements java.io.Serializable{
	
	    public String readerid;
	    public String title;
	    public String docid;
	    public String copyno;
	    public String libid;

	    public Item()
	    {
	    }

	    public Item(String aReaderid,String aTitle, String aDocid,
	        String aCopyno, String aLibid)
	    {
	    	readerid = aReaderid;
	    	title = aTitle;
	    	docid = aDocid;
	    	copyno = aCopyno;
	    	libid = aLibid;
	    }

	// Make get/set methods so the attributes will appear
	// as bean attributes.

	    public String getReaderid() { return readerid; }
	    public void setReaderid(String aReaderid) {
	    	readerid = aReaderid; }
	    
	    public String getTitle() { return title; }
	    public void setTitle(String aTitle) {
	    	title = aTitle; }

	    public String getDocid() { return docid; }
	    public void setDocid(String aDocid) {
	    	docid = aDocid; }

	    public String getCopyno() { return copyno; }
	    public void setCopyno(String aCopyno) { copyno = aCopyno; }

	    public String getLibid() { return libid; }
	    public void setLibid(String aLibid) { libid = aLibid; }

}
