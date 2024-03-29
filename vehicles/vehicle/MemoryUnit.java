package vehicles.vehicle;

import com.mallardsoft.tuple.*;
import vehicles.environment.*;
import vehicles.simulation.SimulationLog;
import java.util.Vector;
import java.util.Random;

import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


/**
 * This classed is used by vehicle objects. 
 * A memory unit is created in memory for each vehicle in a simualion and it is used to 
 * store rules that a vehicle should try to follow.
 * These rules should be based on past experiences
 * @author graysr
 *
 */
public class MemoryUnit {
	//real_mem: xpos, yPOs, Intensity, type
	//temp_mem: xpos, ypos, Type, Intensity, times
	private Vector<Quadruple<Double, Double, Integer, Integer>> real_memory;
	private Vector<Quintuple<Double, Double, Integer, Integer, Integer>> temp_memory; 
	private int time_to_learn, max;

	private String name;
	
	Document  xmldoc; //the XML document we are creating, stored as an object in memory
	Element root;//the root element of the document


	/**
	 * Constructor for new memory units
	 * time to learn is defaulted to 7
	 */
	public MemoryUnit(String n){
		this.name = n;
		this.time_to_learn = 5;
		this.max = 50; //max amount of items to remember
		real_memory = new Vector<Quadruple<Double, Double, Integer, Integer>>();
		temp_memory = new Vector<Quintuple<Double, Double, Integer, Integer, Integer>>();
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("memory");
	}

	/**
	 * Constructor for new memory units
	 * @param l The amount of time it takes to learn something
	 */
	public MemoryUnit(int l, int max, String n){
		if(l <= 0) {
			this.time_to_learn = 1;
		}
		else{
			this.time_to_learn = l;
		}
		if(max < 0) {
			max = 0;
		}
		else{
			this.max = max;
		}
		this.name = n;
		real_memory = new Vector<Quadruple<Double, Double, Integer, Integer>>();
		temp_memory = new Vector<Quintuple<Double, Double, Integer, Integer, Integer>>();
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("memory");
	}

	public void resetMem(){
		this.real_memory.clear();
		this.temp_memory.clear();
	}

	public void setMaxMem(int m){
		this.max = m;
	}

	public void setLearningRate(int l){
		this.time_to_learn = l;
	}

	public int getLearningRate(){
		return this.time_to_learn;
	}

	public int getMaxMem(){
		return this.max;
	}

	/**
	 * a method to check if the vehicle remembers an element at the specified co-ordinates
	 * @param xPos The xPos of the element to check
	 * @param yPos The yPos of the element to check
	 * @return a boolean stating whether or not the vehicle remembers an element at the
	 * specified point
	 */
	public boolean remembersElementAt(double xPos, double yPos){
		double x, y;
		int size = this.real_memory.size();
		for(int i = 0; i < size; i++){
			x = Tuple.get1(this.real_memory.elementAt(i));
			y = Tuple.get2(this.real_memory.elementAt(i));
			if(x == xPos && y == yPos){
				return true;
			}
		}
		return false;
	}
	
	public void add_N_Memory(MemoryUnit other, int n){
		int size = this.real_memory.size();
		if(size <= n){
			for(int i = 0; i < size; i++){
				other.real_memory.add(this.real_memory.elementAt(i));
			}
			return;
		}
		Random r = new Random();
		int ran_block_start = r.nextInt(size - n);
		for(int i = ran_block_start; i < n; i++){
			other.real_memory.add(this.real_memory.elementAt(i));
		}
	}

	/**
	 * return the type of the element remebered at the specified co ordinates
	 * @param xPos The xpos of the element
	 * @param yPos The ypos of the element
	 * @return an int representing the type of the element
	 */
	public int getTypeOfElementAt(double xPos, double yPos){
		double x, y;
		int size = this.real_memory.size();
		for(int i = 0; i < size; i++){
			x = Tuple.get1(this.real_memory.elementAt(i));
			y = Tuple.get2(this.real_memory.elementAt(i));
			if(x == xPos && y == yPos){
				return Tuple.get4(this.real_memory.elementAt(i));
			}
		}
		return EnvironmentElement.NotSet;
	}
	
	public int getIntensityOfElementAt(double xPos, double yPos){
		double x, y;
		int size = this.real_memory.size();
		for(int i = 0; i < size; i++){
			x = Tuple.get1(this.real_memory.elementAt(i));
			y = Tuple.get2(this.real_memory.elementAt(i));
			if(x == xPos && y == yPos){
				return Tuple.get3(this.real_memory.elementAt(i));
			}
		}
		return 0;
	}

	public Point[] getPoints(){
		double x, y;
		int size = this.real_memory.size();
		Point[] p = new Point[size];
		for(int i = 0; i < size; i++){
			x = Tuple.get1(this.real_memory.elementAt(i));
			y = Tuple.get2(this.real_memory.elementAt(i));
			p[i] = new Point(x, y);
		}
		return p;
	}

	/**
	 * Method to add an element into memory
	 * The element is stored in the vehicle memory as the x and y pos of the element and its type
	 * @param e The element to add into memory
	 */
	public void addElement(EnvironmentElement e, SimulationLog s){
		if(this.remembersElementAt(e.getXpos(), e.getYpos()) || this.max == 0 || this.time_to_learn == 0){
			return;
		}
		double x, y, el_xPos, el_yPos, x_temp, y_temp;
		int intensity;
		el_xPos = e.getXpos();
		el_yPos = e.getYpos();
		int size = this.temp_memory.size();
		int times_learned;
		String log = "";
		for(int i = 0; i < size; i++){
			x = Tuple.get1(this.temp_memory.elementAt(i));
			y = Tuple.get2(this.temp_memory.elementAt(i));
			if(x == el_xPos && y == el_yPos){
				times_learned = Tuple.get5(this.temp_memory.elementAt(i));
				intensity = Tuple.get4(this.temp_memory.elementAt(i));
				times_learned++;
				if(times_learned >= this.time_to_learn){
					if(this.real_memory.size() >= this.max){
						if(s != null){
							x_temp = Tuple.get1(this.real_memory.elementAt(0));
							y_temp = Tuple.get2(this.real_memory.elementAt(0));
							log += "Vehicle " + this.name + " forgot about the element at (" + x_temp + "," + y_temp + ")";
						}
						this.real_memory.removeElementAt(0);
					}
					log += "Vehicle " + this.name + " learned about the element at (" + el_xPos + "," + el_yPos  + ")"; 
					s.addToLog(log);
					this.real_memory.add(Tuple.from(el_xPos, el_yPos, intensity, e.getType()));
					this.temp_memory.removeElementAt(i);
				}
				else{
					temp_memory.setElementAt(Tuple.from(el_xPos, el_yPos, e.getType(), intensity, times_learned), i);
				}
				return;
			}
		}
		this.temp_memory.add(Tuple.from(el_xPos, el_yPos, e.getType(), e.getStrength(), 1));
	}

	/**
	 * Print details about all items in memory
	 *
	 */
	public void printMemory(){
		int len = this.real_memory.size();
		double x, y;
		int type;
		int intensity;
		for(int i = 0; i < len; i++){
			intensity = Tuple.get3(this.real_memory.elementAt(i));
			x = Tuple.get1(this.real_memory.elementAt(i));
			y = Tuple.get2(this.real_memory.elementAt(i));
			type = Tuple.get4(this.real_memory.elementAt(i));
			//System.out.println("Position: (" + x + "," + y + ") | Type: " + type + " | Intensity: " + intensity);
		}
		//System.out.println(len + " elements in memory");
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
		this.writeXMLEntry("max", Integer.toString(this.max), xmldoc);
		this.writeXMLEntry("learnrate",Integer.toString(this.time_to_learn) , xmldoc);
		
		xmldoc.appendChild(root);
		return root;
	}
	
	public int numItems(){
		return this.real_memory.size();
	}
}
