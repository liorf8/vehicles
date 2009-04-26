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
	private Triple<Double, Double, Integer>[] t; 
	private Vector<Triple<Double, Double, Integer>> curr_learning; 
	private int time_to_learn;
	private int size;
	private static int curr_index;
	private boolean mem_full;
	
	/**
	 * Constrcutor for new memory units
	 * @param l The amount of time it takes to learn something
	 * @param size The amount of memory the unit can hold
	 */
	public MemoryUnit(int l, int s){
		this.time_to_learn = l;
		this.size = s;
		t = new Triple[this.size];
		curr_learning = new Vector<Triple<Double, Double, Integer>>();
		this.resetMem();
	}

	/**
	 * Constructor for new memory units
	 * time to learn is defaulted to 7
	 * memory sixe is defaulted to 10 
	 */
	public MemoryUnit(){
		this.time_to_learn = 7;
		this.size = 10;
		t = new Triple[this.size];
		curr_learning = new Vector<Triple<Double, Double, Integer>>();
		this.resetMem();
	}
	
	/**
	 * Constructor for new memory units
	 * time to learn is defaulted to 7
	 * @param size The amount of memory the unit can hold
	 */
	public MemoryUnit(int s, boolean temp){
		this.time_to_learn = 7;
		this.size = s;
		t = new Triple[this.size];
		curr_learning = new Vector<Triple<Double, Double, Integer>>();
		this.resetMem();
	}
	
	/**
	 * Constructor for new memory units
	* memory sixe is defaulted to 10 
	 * @param l The amount of time it takes to learn something
	 */
	public MemoryUnit(int l){
		this.time_to_learn = l;
		this.size = 10;
		t = new Triple[size];
		curr_learning = new Vector<Triple<Double, Double, Integer>>();
		this.resetMem();
	}
	
	public void resetMem(){
		for (int i = 0; i < this.size; i++){
			t[i] = Tuple.from(0.0, 0.0, 0);
		}
		curr_learning.clear();
		curr_index = 0;
		this.mem_full = false;
	}
	
	
	public boolean containsPoint(Point p){
		double x, y;
		for(int i = 0; i < this.size; i++){
			x = Tuple.get1(t[i]);
			y = Tuple.get2(t[i]);
			if(x == p.getXpos() && y == p.getYpos()){
				return true;
			}
		}
		return false;
	}
	
	public void addElement(EnvironmentElement e){
		if(this.mem_full){
			System.out.println("Memory full, nothing new can be learned!");
			return;
		}
		double x, y;
		int type;
		for(int i = 0; i < this.size; i++){
			x = Tuple.get1(t[i]);
			y = Tuple.get2(t[i]);
			if(x == e.getXpos() && y == e.getYpos()){
				
				
			}
		}
	}
	
	
	
	
	
	
}
