package vehicles.processing;
import processing.core.*;
import vehicles.environment.*;
/**
 *
 * @author Niall O'Hara
 */
 public class ElementBrush  {
	 EnvironmentElement currentlySelected;
	 
	 public ElementBrush(){
		 this.currentlySelected = new EnvironmentElement();
	 }
	 
	 /**
	  * Get the current "paint" on the brush
	  * @return the enviornment element currently in use
	  */
	public EnvironmentElement getCurrentlySelected() {
		return currentlySelected;
	}
	/**
	 * Apply "paint" to the brush
	 * @param currentlySelected the environment element to set
	 */
	public void setCurrentlySelected(EnvironmentElement currentlySelected) {
		this.currentlySelected = currentlySelected;
	}
	 
	 
     
 }

