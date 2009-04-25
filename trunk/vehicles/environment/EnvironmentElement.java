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
	public static int NotSet = 0;;
	public static int LightSource = 1;
	public static int HeatSource = 2;
	public static int PowerSource = 3;
	public static int WaterSource = 4;
	
	protected String fileLocation = null;//The XML file location of this element
	protected String author = null, lastModified = null;
	
	//Environment Element attributes
	protected String name = null;
	protected int type = 0; //type of element (see above)
	protected double radius = 0.0;
	protected double strength = 0.0;
	

	protected Point position = null;
	protected Document xmldoc = null; //the XML document we are creating, stored as an object in memory
	protected Element root = null;//the root element of the document

	/**
	 * An empty constructor
	 */
	public EnvironmentElement(){
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("EnvironmentElement");
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
		//TODO extract variables from XML file
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
		this.xmldoc = other.xmldoc;
	}
	
	/**
	 * Maybe constructor to use in the editor?
	 * @param type
	 * @param position
	 */
	public EnvironmentElement(int type,Point position){
		this.type = type;
		this.position = position;
	}
	

	
	/**** Setter Methods ****/


	public void setStrength(double strength) {
		this.strength = strength;
	}
	public void setRadius(double radius) {
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
	public double getRadius() {
		return radius;
	}

	public double getStrength() {
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
	 * Add an environment element name to the XML document being created
	 * @param name The name of this environment element
	 */
	public void addEnvironmentElementName(String name){
		writeXMLEntry("name", name, xmldoc);
	}
	/**
	 * Add an environment element type to the XML document being created
	 * @param type The type of this environment element
	 */
	public void addEnvironmentElementType(int type){
		writeXMLEntry("type", Integer.toString(type), xmldoc);
	}
	/**
	 * Add an environment element position information to the XML document being created
	 * @param point A point object representing this EnvironmentElement's position
	 */
	public void addEnvironmentElementPosition(Point point){
		writeXMLEntry("xPos", Double.toString(point.getXpos()), xmldoc);
		writeXMLEntry("yPos", Double.toString(point.getYpos()), xmldoc);
	}	

	/** 
	 * Write this EnvironmentElement to an XML document in RAm, which can then be written to disk(no need to do this) or appeneded to an Environment XML document in RAM
	 */
	public void toInternalXML(){
		System.out.println("Oh crap"); //subclass overrided version should ONLY be called
	}
	/**
	 * Get the root of this environment element, call this method to get the component for inclusion
	 * in an Environment XML document 
	 * @return The root element of this EnvironmentElement
	 */
	public Element getRootElement(){
		this.toInternalXML();
		return root;
	}
	
	/**
	 * Save this element to file
	 */
	public void saveEnvironmentElement(){
		try{
			/*Now take the file in RAM and write it out to disk*/
			FileOutputStream fos = new FileOutputStream(fileLocation);
			OutputFormat of = new OutputFormat("XML","ISO-8859-1",true);
			of.setIndent(1);
			of.setIndenting(true);
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
		return(this.name + " " +this.type + " "+ this.radius + " " + this.strength );
	}
}
