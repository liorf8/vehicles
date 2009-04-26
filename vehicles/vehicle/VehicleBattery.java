package vehicles.vehicle;

import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class VehicleBattery {
	private int max_capacity, curr_capacity;

	

	Document  xmldoc; //the XML document we are creating, stored as an object in memory
	Element root;//the root element of the document

	public VehicleBattery(){
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("battery");
	}
	public int getMax_capacity() {
		return max_capacity;
	}

	public void setMax_capacity(int max_capacity) {
		this.max_capacity = max_capacity;
	}

	public int getCurr_capacity() {
		return curr_capacity;
	}

	public void setCurr_capacity(int curr_capacity) {
		this.curr_capacity = curr_capacity;
	}
	
	/*XML geneneration*/
	/**
	 * Write an element into an XML file
	 * @param elemName The name of the attribute
	 * @param elemValue The value for this attribute
	 * @param xmldoc The document to write into
	 */
	public void writeXMLEntry(String elemName, String elemValue, Document xmldoc){
		Element nameElement = xmldoc.createElement(elemName);
		Text nameText = xmldoc.createTextNode(elemValue);
		nameElement.appendChild(nameText);//add in the text to the element
		root.appendChild(nameElement);//and add this new element to the document
	}

	/**
	 * Finalise and return the xml tree of this VehicleColour
	 * @return the root element of this VehicleColour's xml tree
	 */
	public Element getRootElement(){
		this.writeXMLEntry("max", Integer.toString(this.max_capacity), xmldoc);
		this.writeXMLEntry("current", Integer.toString(this.curr_capacity), xmldoc);
		xmldoc.appendChild(root);
		return root;
	}


}
