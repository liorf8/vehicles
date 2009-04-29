package vehicles.environment;

import java.io.FileOutputStream;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * This class Represents EnvironmentElements. It is a super class used to create
 * and type of environment element
 */

public class EnvironmentElement {
	/*** Element Types ***/
	public static final int NotSet = 0;;
	public static final int LightSource = 1;
	public static final int HeatSource = 2;
	public static final int WaterSource = 3;
	public static final int PowerSource = 4;

	protected String fileLocation = null;//The XML file location of this element
	protected String author = null, lastModified = null;

	//Environment Element attributes
	protected String name = null;
	protected int type = 0; //type of element (see above)
	protected int radius = 10;
	protected int strength = 50;


	protected Point position = new Point(0,0);
	protected Document xmldoc = null; //the XML document we are creating, stored as an object in memory
	protected Element root = null;//the root element of the document
	protected boolean xmlMade = false;

	/**
	 * An empty constructor
	 */
	public EnvironmentElement() {
		xmldoc = new DocumentImpl();
		root = xmldoc.createElement("EnvironmentElement");
	}

	/**
	 * Constructor for element type only
	 */
	public EnvironmentElement(int p_type) {
		this.type = p_type;
		switch(this.type){
		case EnvironmentElement.HeatSource :
			this.name = "Heat";
			break;
		case EnvironmentElement.LightSource:
			this.name = "Light";
			break;
		case EnvironmentElement.PowerSource:
			this.name = "Power";
			break;
		case EnvironmentElement.WaterSource:
			this.name = "Water";
			break;
		}
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("EnvironmentElement");
	}

	public boolean comparePoint(Point other){
		return this.position.compareTo(other);
	}

	/**
	 * An constructor for new environment-elements
	 */
	public EnvironmentElement(String filelocation, boolean New){
		this.fileLocation = filelocation;
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("EnvironmentElement");
	}


	/**
	 * Constructor that reads in an environment element XML file
	 * @param filelocation The Environment Element xml file to read in
	 */
	public EnvironmentElement(String filelocation){
		this.fileLocation = filelocation;
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("EnvironmentElement");
	}

	/**
	 * Constructor that takes in a filename, point and name
	 * @param n The name of the element
	 * @param fl The file location of the element
	 * @param p The point of this element
	 */
	public EnvironmentElement(String n, String fl, Point p){
		this.name = n;
		this.fileLocation= fl;
		this.position = p;
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("EnvironmentElement");
	}	
	/**
	 * A "copy" constructor
	 * @param other The EnvironmentElement to copy attributes from(passed by value in Java, yay :D ) 
	 * In C You could have a new pointer pointing at the original, then you'd always have the exact copy
	 * cos no matter what they'd always point at the same thing...but that's just an aside, back to work!
	 */
	public EnvironmentElement(EnvironmentElement other){
		this.name = other.name;
		this.fileLocation = other.fileLocation;
		this.author = other.author;
		this.lastModified = other.lastModified;
		this.position = other.position;
		this.radius = other.radius;
		this.root = other.root;
		this.strength = other.strength;
		this.type = other.type;
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("EnvironmentElement");
	}

	/**
	 * Maybe constructor to use in the editor?
	 * @param type
	 * @param position
	 */
	public EnvironmentElement(int type,Point position){
		this.type = type;
		this.position = position;
		switch(this.type){
		case EnvironmentElement.HeatSource :
			this.name = "Heat";
			break;
		case EnvironmentElement.LightSource:
			this.name = "Light";
			break;
		case EnvironmentElement.PowerSource:
			this.name = "Power";
			break;
		case EnvironmentElement.WaterSource:
			this.name = "Water";
			break;

		}
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("EnvironmentElement");
	}



	/**** Setter Methods ****/


	public void setStrength(int strength) {
		this.strength = strength;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuthor(String aut){
		this.author = aut;
	}

	public void setLastModified(String timeStamp){
		this.lastModified = timeStamp;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public void setType(int type) {
		this.type = type;
		switch(this.type){
		case EnvironmentElement.HeatSource :
			this.name = "heat";
			break;
		case EnvironmentElement.LightSource:
			this.name = "light";
			break;
		case EnvironmentElement.PowerSource:
			this.name = "power";
			break;

		case EnvironmentElement.WaterSource:
			this.name = "water";
			break;

		}
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public void setXpos(double xpos){
		if(this.position == null){
			this.position = new Point();
			this.position.setXPos(xpos);
		}else{
			this.position.setXPos(xpos);
		}
	}

	public void setYpos(double ypos){
		if(this.position == null){
			this.position = new Point();
			this.position.setYPos(ypos);
		}else{
			this.position.setYPos(ypos);
		}
	}

	/**** Getter Methods ****/

	public String getName() {
		return name;
	}
	public int getRadius() {
		return radius;
	}

	public int getStrength() {
		return strength;
	}
	public String getAuthor(){
		return this.author;
	}

	public String getLastModified(){
		return this.lastModified;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public int getType() {
		return type;
	}

	public Point getPosition() {
		return position;
	}
	public double getXpos()	{
		return this.position.getXpos();
	}
	public double getYpos()	{
		return this.position.getYpos();
	}
	/**** Other Methods ****/

	public boolean isSaveable(){
		return (this.type != 0 && this.name != null && this.fileLocation != null);
	}

	/**** XML Methods ****/

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
	 * Get the root of this environment element, call this method to get the component for inclusion
	 * in an Environment XML document 
	 * @return The root element of this EnvironmentElement
	 */
	public Element getRootElement(){
		if(!xmlMade){
			this.toInternalXML();
		}
		return root;
	}

	/**
	 * Convert this object into a XML representation in RAM(overrride superclass EnvironmentElement)
	 */
	public void toInternalXML(){
		if(this.name != null){
			this.writeXMLEntry("name",name,xmldoc);
		}
		if(this.type != 0){
			this.writeXMLEntry("type",Integer.toString(type),xmldoc);
		}
		if(this.position != null){
			this.writeXMLEntry("Xpos",Double.toString(this.position.getXpos()),xmldoc);
			this.writeXMLEntry("Ypos",Double.toString(this.position.getYpos()),xmldoc);
		}
		if(this.radius != 0){
			this.writeXMLEntry("radius",Integer.toString(radius),xmldoc);
		}
		if(this.strength != 0){
			this.writeXMLEntry("strength",Integer.toString(strength),xmldoc);
		}
		//xmldoc.appendChild(root);
		xmlMade = true;
	}

	/**
	 * Save this element to file -DO NOT CALL EVER!!!!!!!!!
	 */
	public void saveEnvironmentElement(){
		try{
			/*Now take the file in RAM and write it out to disk*/
			FileOutputStream fos = new FileOutputStream(fileLocation);
			OutputFormat of = new OutputFormat("XML","ISO-8859-1",true);
			of.setLineWidth(Integer.MAX_VALUE);
			XMLSerializer serializer = new XMLSerializer(fos,of);//prepare a serialiser for
			//generating XML documents
			// As a DOMSerializer
			serializer.asDOMSerializer();
			serializer.serialize( xmldoc.getDocumentElement() );//get the root element and start writing
			fos.close();
		}catch(Exception e ){
			e.printStackTrace();
		}


	}

	@Override
	public String toString(){
		//return(this.name + " " +this.type + " "+ this.radius + " " + this.strength + this.getXpos() + "," + this.getYpos());
		return(this.name);
	}
	public String tostring(){
		return("name:" + this.name + " type: " +this.type + " radius: "+ this.radius + " strength: " + this.strength + " pos: "+this.getXpos() + "," + this.getYpos());

	}
}
