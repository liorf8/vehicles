package vehicles.environment;

import java.util.Vector;
import java.util.Enumeration;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class representing an environment, both in RAM and providing methods to read and write to an XML file.
 * 
 * @author James, Karl, Shaun
 *
 */
public class Environment {
	protected String xmlLocation = null; //XML file location for this environment

	//Attibutes of an environment
	public String name = null, lastModified = null, author = null;
	protected Vector<EnvironmentElement> elementVector;
	protected double width = 0.0;
	protected double height = 0.0;
	
	/**
	 * Constructor for creating a new environment with no file location or name to start
	 *
	 */
	public Environment(){
		this.elementVector = new Vector<EnvironmentElement>(); 
	}
	
	/**
	 * Constructor for new environment
	 * @param na The name this environment wil have
	 * @param fl The file location of this environment
	 */
	public Environment(String na, String fl){
		this.name = na;
		this.elementVector = new Vector<EnvironmentElement>();
		this.xmlLocation = fl;
	}

	/**
	 * Constructor for creating a new environment from an xml file
	 * @param filename The file to use to create the environment
	 */
	public Environment(String filename){
		try{
			DOMParser p = new DOMParser();
			System.out.println("Opening Environment file: " + filename);
			p.parse(filename); //get a parsed version of the file into memory
			Document dom = p.getDocument();

			/*Get the root node, check this xml file IS an environment file
			 * if it is not an environment file, print an error and return
			 */
			Node root = dom.getDocumentElement(); //get the root element from the document
			if(!root.getNodeName().equals("Environment")){
				System.err.println("The file '" + filename + "' is NOT an Environment file.");
				return;
			}

			/*If valid Environment file, continue*/
			xmlLocation = filename;
			this.elementVector = new Vector<EnvironmentElement>();
			
			/* The following recursive method is used to get the values of all attributes
			 * apart from environment paths
			 */	
			handleNode(root); //recursive function to handle the nodes*/
			
			/* Fill the vector with the elements pointed at in the file */
			NodeList elementPaths = dom.getElementsByTagName("elementPath");
			processElementPaths(elementPaths);
		}
		catch(Exception e){
			System.err.println("An error occurred while creating an Environment from the file '" +
					filename + "'. Please check that this file exists.");
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
		String element_location = null;
		for(int i = 0; i < length; i++){
			element_location = elementList.item(i).getChildNodes().item(0).getNodeValue();
			this.elementVector.add(new EnvironmentElement());
		}
	}
	
	/**
	 * Take in a node from an XML document and use it to instansiate parts of the object- for 
	 * internal class use only by other methods
	 * @param node The node to handle
	 */
	private void handleNode(Node node){
		int type = node.getNodeType();
		String name, node_value;
		switch (type) { //depending on the type of node, perform different actions
		case Node.ELEMENT_NODE: 
			NodeList children = node.getChildNodes();
			int len = children.getLength();
			for (int i=0; i<len; i++)
				handleNode(children.item(i));
			break;
		case Node.TEXT_NODE:
			name = node.getParentNode().getNodeName();
			node_value = node.getNodeValue();
			if(name.equals("Name")){
				this.setName(node_value);
			}
			else if(name.equals("Author")){
				this.setAuthor(node_value);
			}
			else if(name.equals("LastModified")){
				this.setLastModified(node_value);
			}
			else if(name.equals("numX")){
				this.setWidth(Integer.parseInt(node_value));
			}
			else if(name.equals("numY")){
				this.setHeight(Integer.parseInt(node_value));
			}
			else break;
		}
	}
	
	/***** Getter Methods *****/
	
	public String getName(){
		return this.name;
	}
	
	public String getAuthor(){
		return this.author;
	}
	
	public String getLastModified(){
		return this.lastModified;
	}
	
	public double getWidth(){
		return this.width;
	}
	
	public double getHeigth(){
		return this.height;
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
	
	public void setLastModified(String timeStamp){
		this.lastModified = timeStamp;
	}
	
	public void setWidth(double width){
		if(width > 0.0){
			this.width = width;
		}
		else {
			System.err.println("Width must be greater than 0..setting width to be 100.0");
			this.width = 100.0;
		}
	}
	
	public void setHeight(double height){
		if(height > 0.0){
			this.height= height;
		}
		else {
			System.err.println("Height must be greater than 0..setting height to be 100.0");
			this.height = 100.0;
		}
	}
	
	public void setXMLLocation(String location){
		this.xmlLocation = location;
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
	
	
	
	/*public void saveElementTwo(String name, String path){
		//not sure what this is to contain
		String n = null; String file = null;
		double x= 0.0; double y = 0.0;
		Point p = new Point(x,y);
		EnvironmentElement e = new EnvironmentElement(n, file, p);
		e.changeName(name);
		e.changeFileLocation(path);
	}
	*/
	/*An element has been created and saved in the Vector
	 * so using the name of the element we can retrieve it*/
	public EnvironmentElement loadElement(String elementName){
		EnvironmentElement e ;
		String name = null;
		String FileLocation = null;
		double x=0.0;
		double y=0.0;
		Point p = new Point(x,y);
		Enumeration<EnvironmentElement> n = elementVector.elements();
		   while (n.hasMoreElements()){
			   EnvironmentElement obj = (EnvironmentElement)n.nextElement();
			   if (obj.getName() == elementName){
				   name = obj.getName();
				   FileLocation = obj.getFileLocation();
				   p = obj.getPosition();
			   }
		  }
		   e = new EnvironmentElement(name, FileLocation, p);
		   return e;
	}
	/*
	 * not to sure if this is what is required?
	 *
	public void environmentElement(String fileLocation){
		String name = null; String file = null;
		double x= 0.0; double y = 0.0;
		Point p = new Point(x,y);
		EnvironmentElement e = new EnvironmentElement(name, file, p);
		e.changeFileLocation(fileLocation);

	}
	*/
	/*
	 * Not what is needed, maybe useful for debugging, but the XML is enough for that purpose
	 
	@SuppressWarnings("unchecked")
	public void Display(Vector v){
	
		for (int i=0; i<v.size(); i++){

			//t.println("\nProcessing....   "+v.elementAt(i).toString());

			if (v.elementAt(i) instanceof HeatSource){

				HeatSource n =(HeatSource)v.elementAt(i);
				//t.println("\nheatSource : "+n.getName()+" "+n.getFileLocation()+"\nIntensity"+n.heatIntensity()
				//		+"\nRange : "+n.heatRange());

			}else{
				if (v.elementAt(i) instanceof LightSource){

					LightSource n =(LightSource)v.elementAt(i);
					//t.println("\nlightSource : "+n.getName()+" "+n.getFileLocation()+"\nIntensity"+n.lightIntensity()
							//+"\nRange : "+n.lightIntensity());
				}else{
					if (v.elementAt(i) instanceof OrganicSource){

						OrganicSource n =(OrganicSource)v.elementAt(i);
						//t.println("\norganicSource : "+n.getName()+" "+n.getFileLocation()+"\nRecharge"+n.organicRecharge()
							//+"\nCapacity : "+n.organicCapacity());
					}else{
						if (v.elementAt(i) instanceof WaterSource){

							WaterSource n =(WaterSource)v.elementAt(i);
							//t.println("\nWaterSource : "+n.getName()+" "+n.getFileLocation()+"\nDepth"+n.waterDepth()
									//+"\n");
						}else{
							if (v.elementAt(i) instanceof Terrain){

								Terrain n =(Terrain)v.elementAt(i);
								//t.println("\nheatSource : "+n.getName()+" "+n.getFileLocation()+"\nFriction"+n.terrainFriction()
								//				+"\nImagePath : "+n.terrainImagePath());
							}
						}
					}
				}
			}
    	}

	}
	*/
	
	
	
}
