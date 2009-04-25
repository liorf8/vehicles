package vehicles.environment;

public class LightSource extends EnvironmentElement{

	//private double 
	

	public LightSource(String n, String fl, Point p, double i, double r){
		super(n, fl ,p);
		this.strength = i;
		this.radius = r;
	}
	public LightSource(){
		super();
		this.name = "light";
		this.radius = 0.0;
		this.strength = 0.0;
	}
	public LightSource(EnvironmentElement e){
		super(e);
	}
	
	
	
	public double getIntensity() {
		return strength;
	}
	public void setIntensity(double intensity) {
		this.strength = intensity;
	}
	public double getRange() {
		return radius;
	}
	public void setRange(double range) {
		this.radius = range;
	}
	/**
	 * Add an light source intensity entry to the XML document being created
	 * @param intensity The intensity of this light source
	 */
	public void addLightSourceIntensity(String intensity){
		writeXMLEntry("lightIntensity",intensity, xmldoc);
	}
	
	/**
	 * Add an light source range entry to the XML document being created
	 * @param range The range of this light source
	 */
	public void addLightSourceRange(String range){
		writeXMLEntry("lightRange",range, xmldoc);
	}
	
	/**
	 * Convert this object into a XML representation in RAM(overrride superclass EnvironmentElement)
	 */
	@Override
	public void toInternalXML(){
		if(this.name != null){
			this.addEnvironmentElementName(name);
		}
		if(this.type != 0){
			this.addEnvironmentElementType(type);
		}
		if(this.position != null){
			this.addEnvironmentElementPosition(position);
		}
		if(this.radius != 0){
			this.addLightSourceRange(Double.toString(radius));
		}
		if(this.strength != 0){
			this.addLightSourceIntensity(Double.toString(strength));
		}
		//xmldoc.appendChild(root);
	}
	
	
	
}
