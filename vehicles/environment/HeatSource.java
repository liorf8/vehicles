package vehicles.environment;

public class HeatSource extends EnvironmentElement{

	
	public HeatSource(String n, String fl, Point p, double i, double r){
		super(n, fl ,p);
		this.strength = i;
		this.radius = r;
	}
	public HeatSource(){
		super();
		this.name = "heat";
		this.radius = 0.0;
		this.strength = 0.0;
	}
	public HeatSource(EnvironmentElement e){
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
	 * Add an heat source intensity entry to the XML document being created
	 * @param intensity The intensity of this light source
	 */
	public void addHeatSourceIntensity(String intensity){
		writeXMLEntry("heatIntensity",intensity, xmldoc);
	}
	
	/**
	 * Add an heat source range entry to the XML document being created
	 * @param range The range of this light source
	 */
	public void addHeatSourceRange(String range){
		writeXMLEntry("heatRange",range, xmldoc);
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
			this.addHeatSourceRange(Double.toString(radius));
		}
		if(this.strength != 0){
			this.addHeatSourceIntensity(Double.toString(strength));
		}
		//xmldoc.appendChild(root);
	}
	
	
}
