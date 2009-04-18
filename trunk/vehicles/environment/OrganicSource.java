package vehicles.environment;


public class OrganicSource extends EnvironmentElement {
	
	private double capacity;
	private double recharge;
	
	public OrganicSource(String n, String fl, Point p, double c, double r){
		super( n, fl ,p);
		this.capacity = c;
		this.recharge = r;
	}
	public double organicCapacity(){
		return this.capacity;
	}
	public double organicRecharge(){
		return this.recharge;
	}
	public void modifyCapacity(double capacity){
	
		try{
			 value(capacity);
		}catch(valueOutOfBounds de){
	
		}
		this.capacity = capacity;
	}
	public void modifyRecharge(double recharge){

		try{
			 valueOne(recharge);
		}catch(valueOutOfBounds de){
	
		}
		this.recharge = recharge;
	}
	public void value(double e) throws valueOutOfBounds{
		if (e <0.0 || e>100.0){
		throw new valueOutOfBounds("OrganicCapacity "+e+" out of bounds.\nValue must be greater than 0\n" +
				" or less than or equal to 100\n");
		}
	}
	public void valueOne(double e) throws valueOutOfBounds{
		if (e <0.0 || e>10.0){
		throw new valueOutOfBounds("OrganicCapacity "+e+" out of bounds.\nValue must be greater than 0\n" +
				" or less than or equal to 100\n");
		}
	}

}
