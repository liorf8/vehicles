package vehicles.simulation;
import vehicles.util.UtilMethods;

public class SimulationLog {
	private String logMessage;
	
	public SimulationLog(){
		this.logMessage = "";
	}
	
	public void addToLog(String s){
		this.logMessage = this.logMessage.concat("\n").concat(s);
	}
	
	public void resetLog(){
		this.logMessage = "";
	}
	
	public String getLog(){
		return this.logMessage;
	}
}
