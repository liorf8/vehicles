package vehicles.vehicle;

import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Colour {
	private int red = 0;
	private int blue = 0;
	private int green = 0;

	Document  xmldoc; //the XML document we are creating, stored as an object in memory
	Element root;//the root element of the document


	/* Getters and setters*/
	public int getRed() {
		return red;
	}
	public void setRed(int red) {
		this.red = red;
	}
	public int getBlue() {
		return blue;
	}
	public void setBlue(int blue) {
		this.blue = blue;
	}
	public int getGreen() {
		return green;
	}
	public void setGreen(int green) {
		this.green = green;
	}
	/*Constructors*/
	public Colour(){
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("Colour");
	}
	public Colour(int r,int b,int g){
		this.red = r;
		this.blue = b;
		this.green = g;
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("Colour");
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
	 * Finalise and return the xml tree of this Colour
	 * @return the root element of this Colour's xml tree
	 */
	public Element getRootElement(){
		this.writeXMLEntry("red", Integer.toString(red), xmldoc);
		this.writeXMLEntry("blue", Integer.toString(blue), xmldoc);
		this.writeXMLEntry("green", Integer.toString(green), xmldoc);
		xmldoc.appendChild(root);
		return root;
	}

    @Override
	public String toString(){
		return (this.red + " " +this.blue + " " + this.green);
	}
}
