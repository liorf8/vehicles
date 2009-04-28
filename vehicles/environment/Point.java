package vehicles.environment;


public class Point {
	private double xpos;
	private double ypos;
	
	public Point(){
		
	}
	public Point(double x, double y){
		this.xpos = x;
		this.ypos = y;
	}
	public double getXpos(){
		return this.xpos;
	}
	public double getYpos(){
		return this.ypos;
	}
	public void setXPos(double x){
		this.xpos = x;
	}
	public void setYPos(double y){
		this.ypos = y;
	}
	
	public boolean compareTo(Point other){
		return (other.getXpos() == this.xpos) && (other.getYpos() == this.ypos);
	}
}
