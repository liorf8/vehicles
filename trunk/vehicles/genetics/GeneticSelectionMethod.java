/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vehicles.genetics;

/**
 * Used by the UI in dropdowns
 * @author Niall O'Hara
 */
public class GeneticSelectionMethod {
	private int value;
	private String name;

	public GeneticSelectionMethod(int val) {
		this.value = val;
		switch(value){
		case 0:
            name = "None";
            break;
		case 1:
			name = "Roulette";
            break;
		case 2:
			name = "Tournament";
            break;
		case 3:
			name = "Top N Percent";
            break;
		case 4:
			name = "Best";
            break;
		case 5:
			name = "Random";
            break;
		default:
			name = "None";
            break;
		}
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    public String toString(){
        return this.name;
    }
}
