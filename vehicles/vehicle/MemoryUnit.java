package vehicles.vehicle;

import com.mallardsoft.tuple.*;
import vehicles.environment.*;
import java.util.Vector;


/**
 * This classed is used by vehicle objects. 
 * A memory unit is created in memory for each vehicle in a simualion and it is used to 
 * store rules that a vehicle should try to follow.
 * These rules should be based on past experiences
 * @author graysr
 *
 */
public class MemoryUnit {
	private Vector<Quadruple<Double, Double, String, Integer>> real_memory;
	private Vector<Quintuple<Double, Double, Integer, String, Integer>> temp_memory; 
	private int time_to_learn, max;


	/**
	 * Constructor for new memory units
	 * time to learn is defaulted to 7
	 */
	public MemoryUnit(){
		this.time_to_learn = 5;
		this.max = 50; //max amount of items to remember
		real_memory = new Vector<Quadruple<Double, Double, String, Integer>>();
		temp_memory = new Vector<Quintuple<Double, Double, Integer, String, Integer>>();
	}

	/**
	 * Constructor for new memory units
	 * @param l The amount of time it takes to learn something
	 */
	public MemoryUnit(int l, int max){
		this.time_to_learn = l;
		this.max = max;
		real_memory = new Vector<Quadruple<Double, Double, String, Integer>>();
		temp_memory = new Vector<Quintuple<Double, Double, Integer, String, Integer>>();
	}

	public void resetMem(){
		this.real_memory.clear();
		this.temp_memory.clear();
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
	

	/**
	 * Method to add an element into memory
	 * The element is stored in the vehicle memory as the x and y pos of the element and its type
	 * @param e The element to add into memory
	 */
	public void addElement(EnvironmentElement e){
		if(this.remembersElementAt(e.getXpos(), e.getYpos())){
			System.out.println("Already learned that elements position and type!");
			return;
		}
		if(this.real_memory.size() >= this.max){
			//if memory is at full capacity, clear contents of temporary memory and return
			System.out.println("Memory unit for this vehicle is at full capacity!");
			this.temp_memory.clear();
			return;
		}
		double x, y, el_xPos, el_yPos;
		String name;
		el_xPos = e.getXpos();
		el_yPos = e.getYpos();
		int size = this.temp_memory.size();
		int times_learned;
		for(int i = 0; i < size; i++){
			x = Tuple.get1(this.temp_memory.elementAt(i));
			y = Tuple.get2(this.temp_memory.elementAt(i));
			if(x == el_xPos && y == el_yPos){
				times_learned = Tuple.get5(this.temp_memory.elementAt(i));
				name = Tuple.get4(this.temp_memory.elementAt(i));
				times_learned++;
				if(times_learned == this.time_to_learn){
					this.real_memory.add(Tuple.from(el_xPos, el_yPos, name, e.getType()));
					this.temp_memory.removeElementAt(i);
				}
				else{
					temp_memory.setElementAt(Tuple.from(el_xPos, el_yPos, e.getType(), name, times_learned), i);
				}
				return;
			}
		}
		this.temp_memory.add(Tuple.from(el_xPos, el_yPos, e.getType(), e.getName(), 1));
	}
	
	/**
	 * Print details about all items in memory
	 *
	 */
	public void printMemory(){
		int len = this.real_memory.size();
		double x, y;
		int type;
		String name;
		for(int i = 0; i < len; i++){
			name = Tuple.get3(this.real_memory.elementAt(i));
			x = Tuple.get1(this.real_memory.elementAt(i));
			y = Tuple.get2(this.real_memory.elementAt(i));
			type = Tuple.get4(this.real_memory.elementAt(i));
			System.out.println("Name '" + name + "' | Position: (" + x + "," + y + ") | Type: " + type);
		}
		System.out.println(len + " elements in memory");
	}






}
