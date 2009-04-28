package vehicles.simulation;
import vehicles.util.UtilMethods;

public class SimulationLog {
	private String logMessage;
	private String lastMessage;
	
	public SimulationLog(){
		this.logMessage = "";
		this.lastMessage = "";
	}
	
	public void addToLog(String s){
		this.lastMessage = s;
		this.logMessage = UtilMethods.getTimeStamp() + "\n" + this.logMessage.concat("\n").concat(s);
	}
	
	public void resetLog(){
		this.logMessage = "";
	}
	
	public String getLog(){
		return this.logMessage;
	}
	
	public String getLastLog(){
		return this.lastMessage;
	}
}
