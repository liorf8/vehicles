package vehicles.environment;

import java.io.FileOutputStream;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/* This class could be changed to an abstract class if required
 * Might need to add in an area method
 * Added a point class instead of using xpos and ypos in this class
 * */
public class EnvironmentElement {
	protected String name;
	protected String fileLocation;
	protected String type; //the type of EnvironmentElement this is(LightSource,HeatSource,OrganicSource,Terrain)
	
	protected Point position;
	protected Document xmldoc; //the XML document we are creating, stored as an object in memory
	protected Element root;//the root element of the document

	public EnvironmentElement(){
		//We depend on these definitely being null, so do this initialisation
		this.name = null;
		this.fileLocation = null;
		this.position = null;
		this.type = null;
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("EnvironmentElement");
	}

	public EnvironmentElement(String n, String fl, Point p){
		this.name = n;
		this.fileLocation= fl;
		this.position = p;

	}	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	//BEGIN XML METHODS

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
