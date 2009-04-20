package vehicles.environment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class LightSource extends EnvironmentElement{

	private double intensity;
	private double range;

	public LightSource(String n, String fl, Point p, double i, double r){
		super(n, fl ,p);
		this.intensity = i;
		this.range = r;
	}
	public LightSource(){
		super();
		this.range = 0.0;
		this.intensity = 0.0;
	}
	
	
	
	public double getIntensity() {
		return intensity;
	}
	public void setIntensity(double intensity) {
		this.intensity = intensity;
	}
	public double getRange() {
		return range;
	}
	public void setRange(double range) {
		this.range = range;
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
	public void toInternalXML(){
		if(this.name != null){
			this.addEnvironmentElementName(name);
		}
		if(this.type != null){
			this.addEnvironmentElementType(type);
		}
		if(this.position != null){
			this.addEnvironmentElementPosition(position);
		}
		if(this.range != 0){
			this.addLightSourceRange(Double.toString(range));
		}
		if(this.intensity != 0){
			this.addLightSourceIntensity(Double.toString(intensity));
		}
		xmldoc.appendChild(root);
	}
	
	
	
}
