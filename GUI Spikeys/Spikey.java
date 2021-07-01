/**
 * This program call Spikey.java creates alot of clases and subroutines that allow a spikey
 * to be drawn in a panel.
 *@author Sergio Perez 
 *@version 1.0
 */
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * A "Spikey" represents a geometric figure consisting of disk with lines radiating
 * out from the disk.  A Spikey can draw itself in a Graphics2D graphics context.
 *    The radiating lines are meant to grow and shrink over time, with the length
 * ranging from 0 up to some maximum.  For the animation to occur, the Spikey's
 * timeStep() function must be called before each frame of the animation.
 */
public class Spikey {
	
	//------------------ Properties for which getter and setter methods exist --------
	
	private Color ballColor;  // ball Color, set by default to a random spectral color
	private Color spikeColor; // spike Color, set by default to the complement of ballColor
	
	private double centerX, centerY;  // Position of the center of the Spikey
	
	private double ballRadius;     // Radius of the disk at the center of the Spikey.
	private double maxSpikeLength; // Spikes coming out of the border of the disk will
	                               // vary in length between 0 and this value.  This is
	                               // the length starting from the border, not the center.
	
	private double startAngle; // Clockwise angle that the first spike makes with the horizontal.
	private double spikeWidth; // Width of spikes in pixels; minimum allowed is 0.5.
	
	
	//--------- Arrays for internal use, to support the spike animation -------------
	
	private double[] spikeLengths; // Current length of each spike; size of array is number of spikes.
	
	private double[] spikeSpeeds;  // Rate at which the length of each spike is currently changing;
	                               //   The spikeSpeed is added to the spikeLength each time the
	                               //   timeStep method is called.  A negative value means that
	 int s;                              //   the spike is currently shrinking.
	 int palos;
	
	/**
	 * Constructor creates a Spikey with a specified size and number of spikes.
	 * The ballRadius and maxSpikeLength properties are set based on the size.
	 * Other properties are set to default or random values (center at (0,0), startAngle = 0,
	 * spikeWidth = 2, ballColor = random hue, spikeColor = complement of ballColor);
	 * these properties can be changed by calling their setter methods.  The initial
	 * spikeLengths and spikeSpeeds are set to random values.
	 * @param size The overall radius of the Spikey, representing the maximum
	 *    distance from the center that can be reached by the tips of the spikes.
	 *    The ballRadius is set to 1/5 of the size while the maxSpikeLength is
	 *    set to 4/5 of the size.
	 * @param spikeCount The number of spikes.  Spikes are spread evenly around the
	 *    the ball, with the first spike being drawn at startAngle.
	 */
	
	private ArrayList<Spikey> list  = new ArrayList<Spikey>();
	/**
	 *A subroutine that creates a Spikey
	 *of nodes in the left and right subtree of the tree.
	 *@param size takes a double.
	 *@param spikeCount takes an int
	 *@param level takes an int
	 *
	 */
	public Spikey(double size,int spikeCount, int level) {
		s = level;
		palos = spikeCount;
		if(level == 0) {
			ballRadius = 0.2*size;
			maxSpikeLength = 0.8*size;
			spikeLengths = new double[spikeCount];
			spikeSpeeds = new double[spikeCount];
			for (int i = 0; i < spikeCount; i++) {
				spikeLengths[i] = maxSpikeLength * Math.random(); // random size, up to maxSpikeLength
				spikeSpeeds[i] = (.5 + 2*Math.random())/100 * maxSpikeLength; 
			    // random speed, between .5 and 2.5 percent of maxSpikeLength
				if (Math.random() > 0.5) { // 50% chance that the spike is shrinking
					spikeSpeeds[i] = -spikeSpeeds[i];
				}
			}
			startAngle = Math.PI * 2 * Math.random();
			spikeWidth = 2;
			randomHue();
		}else{
			ballRadius = 0.2*size;
			maxSpikeLength = 0.8*size;
			spikeLengths = new double[spikeCount];
			spikeSpeeds = new double[spikeCount];
			for (int i = 0; i < spikeCount; i++) {
				spikeLengths[i] = maxSpikeLength * Math.random(); // random size, up to maxSpikeLength
				spikeSpeeds[i] = (.5 + 2*Math.random())/100 * maxSpikeLength; 
				// random speed, between .5 and 2.5 percent of maxSpikeLength
				if (Math.random() > 0.5) { // 50% chance that the spike is shrinking
					spikeSpeeds[i] = -spikeSpeeds[i];
				}
			}
			startAngle = Math.PI * 2 * Math.random();
			spikeWidth = 2;
			randomHue();
			for(int i = 0; i < spikeCount; i++){
				Spikey spikey = new Spikey(size/2.5,spikeCount,level-1);
				list.add(spikey);
			}	
		}		
	}
	
	/**
	 * Assigns a random spectral color (that is, one with saturation and brightness
	 * set to 1) to the ball.  The spike color is set to the complementary color of
	 * the ball color.
	 */
	/**
	 *A subroutine that creates a random color
	 *
	 */
	public void randomHue() {
		float ball_hue = (float)Math.random();
		float spike_hue = ball_hue + 0.5F;
		if (spike_hue > 1)
			spike_hue -= 1;
		ballColor = Color.getHSBColor(ball_hue,1,1);
		spikeColor = Color.getHSBColor(spike_hue,1,1);
	}

	/**
	 * Draw the Spikey in the graphics context g.
	 */
	//Draws the Spikeys in the panel. Also draws Spikeys recurseveley
	public void draw(Graphics2D g) {
		if(s == 0){
			g.setColor(spikeColor);
			g.setStroke(new BasicStroke((float)spikeWidth));
			double angleSpacing = Math.PI*2 / spikeLengths.length;
			double angle = startAngle;
			for (int i = 0; i < spikeLengths.length; i++) {
				double cos = Math.cos(angle);
				double sin = Math.sin(angle);
				double x1 = centerX + ballRadius*cos;
				double y1 = centerY + ballRadius*sin;
				double x2 = centerX + (ballRadius + spikeLengths[i])*cos;
				double y2 = centerY + (ballRadius + spikeLengths[i])*sin;
				g.draw(new Line2D.Double(x1,y1,x2,y2));
				angle += angleSpacing;
			}
			g.setColor(ballColor);
			g.fill( new Ellipse2D.Double(centerX-ballRadius,centerY-ballRadius,2*ballRadius,2*ballRadius) );
		
		
		} else {
			g.setStroke(new BasicStroke((float)spikeWidth));
			double angleSpacing = Math.PI*2 / spikeLengths.length;
			double angle = startAngle;
			for (int i = 0; i < spikeLengths.length; i++) {
				g.setColor(spikeColor);
				double cos = Math.cos(angle);
				double sin = Math.sin(angle);
				double x1 = centerX + ballRadius*cos;
				double y1 = centerY + ballRadius*sin;
				Spikey newS = list.get(i);
				double x2 = centerX + (ballRadius + spikeLengths[i])*cos;
				newS.setCenterX(x2);
				double y2 = centerY + (ballRadius + spikeLengths[i])*sin;
				newS.setCenterY(y2);
				g.draw(new Line2D.Double(x1,y1,x2,y2));
				angle += angleSpacing;
				newS.draw(g);
			}
			g.setColor(ballColor);
			g.fill( new Ellipse2D.Double(centerX-ballRadius,centerY-ballRadius,2*ballRadius,2*ballRadius) );
		}
	}
	
	/**
	 * Adjusts the length of each of the spikes by adding the speed for that spike to
	 * the current length.  If the spike grows beyond the maximum spike length, then the
	 * speed is set to be negative so that the spike will start shrinking instead; similarly.
	 * if the length becomes negative, then the speed is set to be positive so that the
	 * the spike will start growing.  Furthermore, there is a small change that the 
	 * direction will reverse spontaneously, and a small chance that the speed will be
	 * reset to an entirely new random value.
	 */
	public void timeStep() {
		if(s == 0){
			for (int i = 0; i < spikeLengths.length; i++) {
				spikeLengths[i] += spikeSpeeds[i];
				if (spikeLengths[i] < 0) { // not allowed for more than one step; change speed to positive.
					spikeSpeeds[i] = Math.abs(spikeSpeeds[i]);
				}
				else if (spikeLengths[i] > maxSpikeLength) { // not allowed for more than one step; change speed to negative
					spikeSpeeds[i] = -Math.abs(spikeSpeeds[i]);
				}
				else if (Math.random() < 0.01) { // reverse direction 1% of the time
					spikeSpeeds[i] = -spikeSpeeds[i];
				}
				else if (Math.random() < 0.005) { // new random speed 0.5% of the time
					spikeSpeeds[i] = (.5 + 2*Math.random())/100 * maxSpikeLength; 
					if (Math.random() > 0.5)
						spikeSpeeds[i] = -spikeSpeeds[i];
					}
			}
		} else {
			for (int i = 0; i < spikeLengths.length; i++) {
				spikeLengths[i] += spikeSpeeds[i];
				if (spikeLengths[i] < 0) { // not allowed for more than one step; change speed to positive.
					spikeSpeeds[i] = Math.abs(spikeSpeeds[i]);
				}
				else if (spikeLengths[i] > maxSpikeLength) { // not allowed for more than one step; change speed to negative
					spikeSpeeds[i] = -Math.abs(spikeSpeeds[i]);
				}
				else if (Math.random() < 0.01) { // reverse direction 1% of the time
					spikeSpeeds[i] = -spikeSpeeds[i];
				}
				else if (Math.random() < 0.005) { // new random speed 0.5% of the time
					spikeSpeeds[i] = (.5 + 2*Math.random())/100 * maxSpikeLength; 
					if (Math.random() > 0.5)
						spikeSpeeds[i] = -spikeSpeeds[i];
				}
				
				list.get(i).timeStep();
			}	
		}
	}
	//--------------------------------------------------------------------------------------
	//----------- Getter and Setter methods for various properties.  Note that an ----------
	//----------- to set a color to null or a length to a non-positive value ---------------
	//----------- will be silently ignored. ------------------------------------------------
	//--------------------------------------------------------------------------------------

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	public Color getBallColor() {
		return ballColor;
	}
	
	/**
	 *A subroutine that sets the color of the ball of the spikeys
	 *@param ballColor that takes a Color.
	 *
	 */
	public void setBallColor(Color ballColor) {
		if(s == 0){
			if (ballColor != null)
				this.ballColor = ballColor;
			}
		else {			
			for(int i = 0; i < list.size(); i++){
				if (ballColor != null)
					this.ballColor = ballColor;
					list.get(i).setBallColor(ballColor);
			}
		}		
	}
	
	public Color getSpikeColor() {
		return spikeColor;
	}

	/**
	 *A subroutine that sets the color of all the spikeys in the spikeis ball.
	 *@param spikeColor that takes a color as a parameter
	 *
	 */
	public void setSpikeColor(Color spikeColor) {
		if(s == 0){
			if (spikeColor != null)	
			this.spikeColor = spikeColor;
		}
		else {
			for(int i = 0; i < palos; i++ ){
				if (spikeColor != null)	
					this.spikeColor = spikeColor;
					list.get(i).setSpikeColor(spikeColor);
					//System.out.println(spikeColor);
			}
		}
	}

	public double getBallRadius() {
		return ballRadius;
	}

	public void setBallRadius(double ballRadius) {
		if (ballRadius > 0)
			this.ballRadius = ballRadius;
	}

	public double getMaxSpikeLength() {
		return maxSpikeLength;
	}

	public void setMaxSpikeLength(double maxSpikeLength) {
		if (maxSpikeLength > 0)
			this.maxSpikeLength = maxSpikeLength;
	}

	public double getSpikeWidth() {
		return spikeWidth;
	}

	public void setSpikeWidth(double spikeWidth) {
		if (spikeWidth >= 0.5)
			this.spikeWidth = spikeWidth;
	}

	public double getStartAngle() {
		return startAngle;
	}

	public void setStartAngle(double startAngle) {
		this.startAngle = startAngle;
	}

}