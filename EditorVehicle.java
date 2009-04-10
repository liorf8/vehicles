import java.io.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.Locator;
import org.xml.sax.helpers.*;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.NodeImpl;
import org.apache.xerces.framework.XMLAttrList;
import org.apache.xerces.parsers.DOMParser;
import org.apache.xerces.utils.QName;
import org.apache.xerces.readers.MIME2Java;
import org.apache.xml.serialize.*;
/**
 * A vehicle class for use in the Vehicle Editor, essentially a wrapper around some
 * XML generation and manipulation. 
 * @author Karl
 *
 */
public class EditorVehicle extends Vehicle{
	Document  xmldoc; //the XML document we are creating, stored as an object in memory
	Element root;//the root element of the document
	String filename;// filename to associate the XML document with
	
	
	/**
	 * Constructor
	 * @param fileName filename to use for this object
	 */
	public EditorVehicle(String fileName){
		filename = fileName;
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("Vehicle");
	}
	/**
	 * Add a vehicle name attribute to the XML document being created
	 * @param name The name to use for this Vehicle
	 */
	public void addVehicleName(String name){
		Element nameElement = xmldoc.createElement("name");
        Text nameText = xmldoc.createTextNode(name);
        nameElement.appendChild(nameText);
        root.appendChild(nameElement);
	}
	/**
	 * Write out the current Vehicle to a file specified by the filename attribute
	 */
	public void serialiseXMLDoc(){
		try{
			xmldoc.appendChild(root);
			FileOutputStream fos = new FileOutputStream(filename);
			OutputFormat of = new OutputFormat("XML","ISO-8859-1",true);
			of.setIndent(1);
			of.setIndenting(true);
			XMLSerializer serializer = new XMLSerializer(fos,of);
			// As a DOMSerializer
			serializer.asDOMSerializer();
			serializer.serialize( xmldoc.getDocumentElement() );
			fos.close();
		}catch(Exception e ){
			e.printStackTrace();
		}
	}
	
	

}
