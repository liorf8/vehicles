/*package test.environment;

import java.util.*;
import vehicles.environment.*;

public class EnviromentTest {
<<<<<<< .mine
	public static void value(double e) throws valueOutOfBounds{
		if (e <0.0 || e>100.0){
		throw new valueOutOfBounds("HeatIntensity "+e+" out of bounds.\nValue must be greater than 0\n" +
				" or less than or equal to 100\n");
		}
	}
	public static void valueOne(double e) throws valueOutOfBounds{
		if (e <0.0 || e>100.0){
		throw new valueOutOfBounds("LightIntensity "+e+" out of bounds.\nValue must be greater than 0\n" +
				" or less than or equal to 100\n");
		}
	}
	public static void valueTwo(double e) throws valueOutOfBounds{
		if (e <0.0 || e>100.0){
		throw new valueOutOfBounds("WaterDepth "+e+" out of bounds.\nValue must be greater than 0\n" +
				" or less than or equal to 100\n");
		}
	}
	public static void valueThree(double e) throws valueOutOfBounds{
		if (e <0.0 || e>100.0){
		throw new valueOutOfBounds("OrganicCapacity "+e+" out of bounds.\nValue must be greater than 0\n" +
				" or less than or equal to 100\n");
		}
	}
	public static void valueFour(double e) throws valueOutOfBounds{
		if (e <0.0 || e>10.0){
		throw new valueOutOfBounds("OrganicRecharge "+e+" out of bounds.\nValue must be greater than 0\n" +
				" or less than or equal to 10\n");
		}
	}
	public static void valueFive(double e) throws valueOutOfBounds{
		if (e <0.0 || e>10.0){
		throw new valueOutOfBounds("TerrainFriction "+e+" out of bounds.\nValue must be greater than 0\n" +
				" or less than or equal to 10\n");
		}
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Environment e = null;
		Vector v = new Vector();
		double x=0.0; double y = 0.0;
		Point p;
		double i=0.0,r;
		HeatSource first;
		LightSource second;
		OrganicSource third;
		WaterSource four;
		Terrain five;
=======
>>>>>>> .r49

<<<<<<< .mine
		boolean ans = false;
		String name, fileLocation, Ip;
		int choice;
		terminal = new Terminal("");		
		
		System.out.println("Create a new");
		System.out.println("New environment :\n");
		name =terminal.readString("enter name :");
		fileLocation =terminal.readString("Location : ");
		e =new Enviroment(name, fileLocation);
		do{
			terminal.println("HeatSource = 1");
			terminal.println("LightSource = 2");
			terminal.println("OrganicSource = 3");
			terminal.println("WaterSource = 4 ");
			terminal.println("Terrain = 5");
			terminal.println("Display = 6");
			terminal.println("Exit = 7");
			choice = terminal.readInt("\nEnter choice : ");
		
			switch (choice){
			case 1:
			    	terminal.println("\n"); 
					name = terminal.readString("Enter Name: ");
					fileLocation = terminal.readString("Enter FileLocation: ");
					x = terminal.readDouble("Enter xpos: ");
					y = terminal.readDouble("Enter ypos: ");
					p = new Point(x,y); 
					do{
						try{
							i = terminal.readDouble("Enter heatIntensity: ");
							value(i);
							ans = true;
						}catch(valueOutOfBounds de){
							terminal.println("");
							terminal.println("Exception  value out of bounds :\n"+de.getMessage());
						}
					}while(ans == false);
					r = terminal.readDouble("Enter heatRange: ");
					first = new heatSource(name,fileLocation,p, i, r);
					e.saveElement(first);
			break;
			case 2:
				terminal.println("\n"); 
				name = terminal.readString("Enter Name: ");
				fileLocation = terminal.readString("Enter FileLocation: ");
				x = terminal.readDouble("Enter xpos: ");
				y = terminal.readDouble("Enter ypos: ");
				p = new Point(x,y);
				do{
					try{
						i = terminal.readDouble("Enter lightIntensity: ");
						valueOne(i);
						ans = true;
					}catch(valueOutOfBounds de){
						terminal.println("");
						terminal.println("Exception  value out of bounds :\n"+de.getMessage());
					}
				}while(ans == false);
				r = terminal.readDouble("Enter lightRange: ");
				second = new lightSource(name,fileLocation,p, i, r);
				e.saveElement(second);
				v.add(second); 
			break;
			case 3:
				terminal.println("\n"); 
				name = terminal.readString("Enter Name: ");
				fileLocation = terminal.readString("Enter FileLocation: ");
				x = terminal.readDouble("Enter xpos: ");
				y = terminal.readDouble("Enter ypos: ");
				p = new Point(x,y);
				do{
					try{
						i = terminal.readDouble("Enter OrganicCapacity: ");
						valueThree(i);
						ans = true;
					}catch(valueOutOfBounds de){
						terminal.println("");
						terminal.println("Exception  value out of bounds :\n"+de.getMessage());
					}
				}while(ans == false);
				r = 0.0;
				do{
					try{
						r = terminal.readDouble("Enter OrganicRecharge: ");
						valueFour(r);
						ans = true;
					}catch(valueOutOfBounds de){
						terminal.println("");
						terminal.println("Exception  value out of bounds :\n"+de.getMessage());
					}
				}while(ans == false);
				third = new organicSource(name,fileLocation,p, i, r);
				e.saveElement(third);
				v.add(third); 
				break;
			case 4:
				terminal.println("\n"); 
				name = terminal.readString("Enter Name: ");
				fileLocation = terminal.readString("Enter FileLocation: ");
				x = terminal.readDouble("Enter xpos: ");
				y = terminal.readDouble("Enter ypos: ");
				p = new Point(x,y);
				do{
					try{
						i = terminal.readDouble("Enter Depth: ");
						 valueTwo(i);
					}catch(valueOutOfBounds de){
				
						terminal.println("Exception value out of bounds :\n"+de.getMessage());
					}
				}while(ans == false);
				i = terminal.readDouble("Enter Depth: ");
				four = new waterSource(name,fileLocation,p, i);
				e.saveElement(four);
				v.add(four); 
				break;
			case 5:
				terminal.println("\n"); 
				name = terminal.readString("Enter Name: ");
				fileLocation = terminal.readString("Enter FileLocation: ");
				x = terminal.readDouble("Enter xpos: ");
				y = terminal.readDouble("Enter ypos: ");
				p = new Point(x,y);
				do{
					try{
						i = terminal.readDouble("Enter Friction: ");
						 valueFive(i);
						 ans = true;
					}catch(valueOutOfBounds de){
				
						terminal.println("Exception value out of bounds :\n"+de.getMessage());
					}
				}while(ans == false);
				Ip = terminal.readString("Enter ImagePath: ");
				five = new Terrain(name,fileLocation,p, i, Ip);
				e.saveElement(five);
				v.add(five); 
				break;
				   
			case 6:
				e.Display(v);
				e.Display(e.getVectorElement());
				break;
			  default : System.exit(7);  
			}
			
		}while((choice >0)&&(choice<8));
		

		
	}		
=======
>>>>>>> .r49
}
*/