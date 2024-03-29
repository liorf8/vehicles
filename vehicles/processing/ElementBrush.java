package vehicles.processing;

import vehicles.environment.*;

/**
 *
 * @author Niall O'Hara
 */
public class ElementBrush {

    EnvironmentElement currentlySelected;

    public ElementBrush() {
    }

    public ElementBrush(EnvironmentElement p_ele) {
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
        //System.out.println("setCurrentlySelectes() : change to "+this.getCurrentlySelected());

        this.currentlySelected = new EnvironmentElement(currentlySelected);
    }
}

