package vehicles.environment;

public class WaterSource extends EnvironmentElement{

	
	public WaterSource(String n, String fl, Point p, double i, double r){
		super(n, fl ,p);
		this.strength = i;
		this.radius = r;
	}
	public WaterSource(){
		super();
		this.radius = 0.0;
		this.strength = 0.0;
	}
	public WaterSource(EnvironmentElement e){
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
	 * Add an water source intensity entry to the XML document being created
	 * @param intensity The intensity of this light source
	 */
	public void addWaterSourceIntensity(String intensity){
		writeXMLEntry("waterIntensity",intensity, xmldoc);
	}
	
	/**
	 * Add an water source range entry to the XML document being created
	 * @param range The range of this light source
	 */
	public void addWaterSourceRange(String range){
		writeXMLEntry("waterRange",range, xmldoc);
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
			this.addWaterSourceRange(Double.toString(radius));
		}
		if(this.strength != 0){
			this.addWaterSourceIntensity(Double.toString(strength));
		}
		//xmldoc.appendChild(root);
	}
	
	
	
}
