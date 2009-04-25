package vehicles.environment;

/**
 *
 * @author Niall O'Hara
 */
public class Grid {
    
	private int width;
	private int height;

	public Grid(int w, int h) {
		this.width = w;
		this.height = h;
	}

	public Grid(){
		
	}

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String toString(){
        return this.width + " x " + this.height;
    }
}
