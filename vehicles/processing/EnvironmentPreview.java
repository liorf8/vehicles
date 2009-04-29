package vehicles.processing;
import processing.core.*;
import vehicles.environment.Environment;

/**
 *
 * @author Niall O'Hara, Karl Reid
 */
public class EnvironmentPreview extends PApplet {
	PImage ground; //background image
	Environment e;

	public EnvironmentPreview(Environment env){
		this.e = env;
	}
	public void setEnvironment(Environment env){
		this.e = env;
	}
	@Override
	public void setup() {
		size(e.getWidth(),e.getHeigth());
		ground = new PImage(width, height);
		image(ground, 0, 0);
	}

	@Override
	public void draw() {
		image(ground, 0, 0);
		updateGround();
	}

	
	public void updateGround() {
			float sum;
			int c, r, g, b;
			int px = 1;

			ground = new PImage(width, height);
			for (int i = 0; i < width; i += px) {
				for (int k = 0; k < height; k += px) { //process every pixel in the image

					sum = 0;
				r = 0;
				g = 0;
				b = 0;
				for (int m = 0; m < e.getElements().size(); m++) {
					ProcessingEnviroElement temp = 
							new ProcessingEnviroElement(this,e.getElements().elementAt(m),0);
					//pass this pixel's position
					sum += temp.getIntensityAtPoint(i, k);
					r += temp.getRedAtPoint(i, k);
					g += temp.getGreenAtPoint(i, k);
					b += temp.getBlueAtPoint(i, k);
					//sum up intensity of elementVector until it reachrs one
					if (sum >= 1) {
						break;
					}

				}

				c = (int) min(sum * 255, 255);


				for (int p = 0; p < px; p++) {
					for (int q = 0; q < px; q++) {
						//agh, horrible code
						ground.set(i + p, k + q,
								color(r, g, b / 8, c)); //r,g,b
					}
				}

				}
			}

		}

	}
