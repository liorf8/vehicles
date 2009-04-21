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
	public static int LightSource = 1;
	public static int HeatSource = 2;
	public static int OrganicSource = 3;
	public static int Terrain = 4;
	
	protected String fileLocation = null;//The XML file location of this element
	
	//Environment Element attributes
	protected String name = null;
	protected int type = 0; //type of element (see above)
	
	protected Point position = null;
	//protected Document xmldoc = null; //the XML document we are creating, stored as an object in memory
	//protected Element root = null;//the root element of the document

	/**
	 * Empty Constructor
	 */
	public EnvironmentElement(){
		//xmldoc= new DocumentImpl();
		//root = xmldoc.createElement("EnvironmentElement");
	}

	/**
	 * Constructor that reads in an environment element XML file
	 * @param filelocation The Environment Element xml file to read in
	 */
	public EnvironmentElement(String filelocation){
		//TODO extract variables from XML file
		this.fileLocation = filelocation;
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
	}	

	/**** Setter Methods ****/

	public void setName(String name) {
		this.name = name;
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

	/**** Getter Methods ****/
	
	public String getName() {
		return name;
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
		this.root.appendChild(nameElement);//and add this new element to the document
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
	public void addEnvironmentElementType(String type){
		writeXMLEntry("type", type, xmldoc);
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
		//sub-classes must implement
	}
	/**
	 * Get the root of this environment element, call this method to get the component for inclusion
	 * in an Environment XML document 
	 * @return The root element of this EnvironmentElement
	 */
	public Element getRootElement(){
		return root;
	}
	
	/**
	 * Save this element to file- FOR DEBUGGING ONLY!!!!
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
}
