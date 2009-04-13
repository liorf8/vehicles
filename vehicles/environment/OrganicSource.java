package vehicles.environment;


public class OrganicSource extends EnvironmentElement {
	
	private double capacity;
	private double recharge;
	
	public OrganicSource(String n, String fl, Point p, double c, double r){
		super( n, fl ,p);
		this.capacity = c;
		this.recharge = r;
	}
	public double getCapacity(){
		return this.capacity;
	}
	public double getRecharge(){
		return this.recharge;
	}
	public void modifyCapacity(double capacity){
		this.capacity = capacity;
	}
	public void modifyRange(double recharge){
		this.recharge = recharge;
	}
	
}
