package vehicles.environment;

/**
 *
 * @author Niall O'Hara
 */
public class Grid {
    
	private int width;
	private int height;
    private String desc;

	public Grid(int w, int h, String d) {
		this.width = w;
		this.height = h;
        this.desc = d;
	}
	public Grid(){
		
	}

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
        return this.width + " " + this.height;
    }
}
