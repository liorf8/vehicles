package vehicles.processing;
import processing.core.*;
import vehicles.environment.*;
/**
 *
 * @author Niall O'Hara
 */
 public class ElementBrush extends EnvironmentElement {
	 EnvironmentElement currentlySelected;
	 
	 public ElementBrush(EnvironmentElement p_ele){
		 this.currentlySelected = p_ele;
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

