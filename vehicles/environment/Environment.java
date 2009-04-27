package vehicles.environment;

import vehicles.util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Vector;
import java.util.Enumeration;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.parsers.DOMParser;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * Class representing an environment, both in RAM and providing methods to read and write to an XML file.
 * 
 * @author James, Karl, Shaun
 *
 */
public class Environment {
	protected String xmlLocation = null; //XML file location for this environment
	protected String fileName = null;

	//Attibutes of an environment
	public String name = null, lastModified = null, author = null, description = null;
	protected Vector<EnvironmentElement> elementVector;
	protected Document xmldoc = null; //the XML document we are creating, stored as an object in memory
	protected Element root = null;//the root element of the document
	protected Grid grid = null;

	/**
	 * Constructor for creating a new environment with no file location or envName to start
	 *
	 */
	public Environment(){
		this.elementVector = new Vector<EnvironmentElement>();
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("Environment");
		this.grid = new Grid();
	}

	/**
	 * Constructor for new environment
	 * @param na The envName this environment wil have
	 * @param fl The file location of this environment
	 */
	public Environment(String na, String fl){
		this.name = na;
		this.elementVector = new Vector<EnvironmentElement>();
		this.xmlLocation = fl;
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("Environment");
		this.setFileName();
		this.grid = new Grid();

	}

	/**
	 * Constructor for creating a new environment from an xml file
	 * @param filename The file to use to create the environment
	 */
	public Environment(String filename){
		try{
			DOMParser p = new DOMParser();
			p.parse(filename); //get a parsed version of the file into memory
			Document dom = p.getDocument();

			/*Get the root node, check this xml file IS an environment file
			 * if it is not an environment file, print an error and return
			 */
			Node rootElem = dom.getDocumentElement(); //get the root element from the document
			if(!rootElem.getNodeName().equals("Environment")){
				System.err.println("The file '" + filename + "' is NOT an Environment file.");
				return;
			}

			/*If valid Environment file, continue*/
			xmlLocation = filename;
			xmldoc = new DocumentImpl();
			this.root = xmldoc.createElement("Environment");
			this.elementVector = new Vector<EnvironmentElement>();
			this.grid = new Grid();

			/* The following recursive method is used to get the values of all attributes
			 * apart from environment paths
			 */	
			handleNode(rootElem); //recursive function to handle the nodes*/

			/* Fill the vector with the elements in the file */
			NodeList elementPaths = dom.getElementsByTagName("EnvironmentElement");
			processElementPaths(elementPaths);

			this.setFileName();
			
		}
		catch(Exception e){
			System.err.println("An error occurred while creating an Environment from the file '" +
					filename + "'. Please check that this file exists.");
			e.printStackTrace();
		}
	}



	/**
	 * Take a NodeList representing paths to enviro-elements and creates 
	 * enviro-element objects from these paths and adds them to the vector
	 * @param elementList A nodelist containg enviro-element paths to create and add
	 * to the vector
	 */
	//TODO Fix environment element so this will work
	private void processElementPaths(NodeList elementList){
		int length = elementList.getLength();
		EnvironmentElement ee = new EnvironmentElement(); // a current enviro-element to create for each entry, then add to the vector
		for(int i = 0; i < length; i++){//process enviro-element by element
			ee = new EnvironmentElement();
			NodeList childElems = elementList.item(i).getChildNodes(); //get the attributes of a single environment editor
			int len = childElems.getLength();
			for(int j= 0; j < len; j++){ //process element by element
				Node curr = childElems.item(j);
				if(curr.getNodeName().contains("name")){//enviro-elem envName
					ee.setName(curr.getFirstChild().getNodeValue());
				}
				if(curr.getNodeName().contains("type")){//enviro-elem type
					ee.setType(Integer.parseInt(curr.getFirstChild().getNodeValue()));
				}
				if(curr.getNodeName().contains("Xpos")){//enviro-elem xPos
					ee.setXpos(Double.parseDouble(curr.getFirstChild().getNodeValue()));
				}
				if(curr.getNodeName().contains("Ypos")){//enviro-elem yPos
					ee.setYpos(Double.parseDouble(curr.getFirstChild().getNodeValue()));
				}
				if(curr.getNodeName().contains("radius")){//enviro-elem range/radius
					ee.setRadius(Integer.parseInt(curr.getFirstChild().getNodeValue()));					
				}
				if(curr.getNodeName().contains("strength")){//enviro-elem intensity/strength
					ee.setStrength(Integer.parseInt(curr.getFirstChild().getNodeValue()));					
				}
			}
			/*Now the object has been created, save it
			 * 
			 */
			this.addElement(ee);

		} //end of an single element for loop iteration here

	}
	public Environment(Environment e){
		this.author = e.author;
		this.description = e.description;
		this.elementVector = e.elementVector;
		this.fileName = e.fileName;
		this.grid = e.grid;
		this.name = e.name;
		this.xmlLocation = e.xmlLocation;
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("Environment");
	}

	/**
	 * Take in a node from an XML document and use it to instansiate parts of the object- for 
	 * internal class use only by other methods
	 * @param node The node to handle
	 */
	private void handleNode(Node node){
		int type = node.getNodeType();
		String envName, node_value;
		switch (type) { //depending on the type of node, perform different actions
		case Node.ELEMENT_NODE: 
			NodeList children = node.getChildNodes();
			int len = children.getLength();
			for (int i=0; i<len; i++)
				handleNode(children.item(i));
			break;
		case Node.TEXT_NODE:
			envName = node.getParentNode().getNodeName();
			node_value = node.getNodeValue();

			if(envName.contains("name") && this.name == null){ //avoids us catching names of environment elements here
				this.setName(node_value);
			}
			else if(envName.equals("author")){
				this.setAuthor(node_value);
			}
			else if(envName.equals("description")){
				this.setDescription(node_value);
			}
			else if(envName.equals("LastModified")){
				this.setLastModified(node_value);
			}
			else if(envName.equals("width")){
				this.grid.setWidth(Integer.parseInt(node_value));
			}
			else if(envName.equals("height")){
				this.grid.setHeight(Integer.parseInt(node_value));
			}
			else break;
		}
	}
	/**
	 * Write an element into an XML file
	 * @param elemName The envName of the attribute
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
	 * Add The Last Modified Time Stamp to the Simulation's document
	 */
	public void writeTimeStamp(Document xmldoc){
		String t = UtilMethods.getTimeStamp();
		writeXMLEntry("LastModified", t, xmldoc);
		this.setLastModified(t);
	}


	/**
	 * Save this environment to file
	 */
	public void saveEnvironment(){
		try{
			//Maybe not needed? I added anyway
			if(this.xmlLocation == null){
				System.err.print("Specify a filepath to save to!");
				return;
			}

			FileOutputStream fos;
			if(this.name != null){
				this.writeXMLEntry("name", name, xmldoc);
			}
			//Maybe not needed? I added anyway
			else{
				System.err.print("Environment needs a name!");
				return;
			}
			if(this.author != null){
				this.writeXMLEntry("author", author, xmldoc);
			}
			//Write a timestamp
			this.writeTimeStamp(xmldoc);

			if(this.description != null){
				this.writeXMLEntry("description", description, xmldoc);
			}
			if(this.grid.getWidth() != 0){
				this.writeXMLEntry("width", Integer.toString(grid.getWidth()), xmldoc);
			}
			if(this.grid.getHeight() != 0){
				this.writeXMLEntry("height", Integer.toString(grid.getHeight()), xmldoc);
			}
			if(this.elementVector != null){
				Iterator<EnvironmentElement> it = this.elementVector.iterator();
				while(it.hasNext()){ //process every element in the vector
					EnvironmentElement curr = it.next();
					/*

					if(o == null){ //if not, populate it(convert object attributes to xml tree)
						curr.toInternalXML();
					}
					 */									
					//now add the xml tree from this element to the whole environment's xml tree
					this.addEnvironmentElement(curr);
				}
			}

			xmldoc.appendChild(root); //finalise the XML document
			/*Now take the file in RAM and write it out to disk*/
			try{
				fos = new FileOutputStream(xmlLocation);
			}catch(FileNotFoundException e ){ //the file doesn't exist yet
				File f = new File(xmlLocation);
				f.createNewFile(); //so create it
				fos = new FileOutputStream(xmlLocation);
			}

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
	/**
	 * Add an evironment element XML entry into this Vehicle's document
	 * @param vc The vehicle component to add
	 */
	public void addEnvironmentElement(EnvironmentElement ee){
		root.appendChild(xmldoc.adoptNode(ee.getRootElement().cloneNode(true)));

	}
	/***** Getter Methods *****/

	public String getName(){
		return this.name;
	}

	public String getAuthor(){
		return this.author;
	}

	public String getDescription(){
		this.description = this.description.replace('\n', ' ');
		return this.description;
	}

	public String getLastModified(){
		return this.lastModified;
	}

	public int getWidth(){
		return this.grid.getWidth();
	}

	public int getHeigth(){
		return this.grid.getHeight();
	}

	public String getFileLocation(){
		return this.xmlLocation;
	}

	public Vector<EnvironmentElement> getElements(){
		return elementVector;
	}
	
	

	/***** Setter Methods *****/

	public void setName(String name){
		this.name = name;
	}

	public void setAuthor(String aut){
		this.author = aut;
	}

	public void setDescription(String d){
		this.description = d;
	}

	public void setLastModified(String timeStamp){
		this.lastModified = timeStamp;
	}

	public void setWidth(int width){
		this.grid.setWidth(width);
	}

	public void setHeight(int height){
		this.grid.setHeight(height);
	}

	public void setXMLLocation(String location){
		this.xmlLocation = location;
		this.setFileName();
	}

	/**
	 * A method to add an environment element to the current environment
	 * @param e The element to add 
	 */
	public void addElement(EnvironmentElement e){
		this.elementVector.add(e);
	}




	/**** Other Methods ****/

	public boolean isSaveable(){
		return (this.name != null);
	}


	/*Might be best to add an area method to the environmentElement class and
	 * use it here*/
	public boolean occupied(double xPos, double yPos, double width, double height){
		Vector<EnvironmentElement> v = new Vector<EnvironmentElement>();
		v = elementVector;
		boolean j=true;
		Enumeration<EnvironmentElement> n = v.elements();
		while (n.hasMoreElements()){
			EnvironmentElement obj = (EnvironmentElement)n.nextElement();
			if((obj.getPosition().getXpos()==(xPos))&&(obj.getPosition().getYpos()==yPos)){
				j= true;
			}else{
				j= false;
			}
		}
		return j;
	}


	/*An element has been created and saved in the Vector
	 * so using the envName of the element we can retrieve it*/
	public EnvironmentElement loadElement(String elementName){
		EnvironmentElement e ;
		String envName = null;
		String FileLocation = null;
		double x=0.0;
		double y=0.0;
		Point p = new Point(x,y);
		Enumeration<EnvironmentElement> n = elementVector.elements();
		while (n.hasMoreElements()){
			EnvironmentElement obj = (EnvironmentElement)n.nextElement();
			if (obj.getName().equalsIgnoreCase(elementName)){
				envName = obj.getName();
				FileLocation = obj.getFileLocation();
				p = obj.getPosition();
			}
		}
		e = new EnvironmentElement(envName, FileLocation, p);
		return e;
	}

	public void printDetails(){
		System.out.println("Printing Details for environment at: " + this.xmlLocation);
		System.out.println("Name\t" + this.name);
		System.out.println("Author\t" + this.author);
		System.out.println("Last Modified\t" + this.lastModified);
		System.out.println("Description\t" + this.description);
		System.out.println("Width\t" + this.grid.getWidth());
		System.out.println("Heigth\t" + this.grid.getHeight());

	}

	public void setFileName(){
		String filename = this.xmlLocation;
		String sep = File.separator;
		String[] parts = filename.split("\\" + sep);
		this.fileName = parts[parts.length - 1];
	}

	public String toString(){
		return this.name + " (" + this.fileName + ")";
	}


}
