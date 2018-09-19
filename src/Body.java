
public class Body {
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	
	//This is a constructor which will create an object in the
	//class Body with all the necessary components for the simulation,
	//including x and y position, x and y velocities, mass, and name.
	public Body(double xp, double yp, double xv, 
			double yv, double mass, String filename) {	
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}
	
	//This is a constructor which will create an object in class body
	//based on the paramter object, and its components
	public Body(Body b) {
		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myMass = b.getMass();
		myFileName = b.getName();
	}
	
	//This function allows the instance variable "myXPos" to be accessed
	//outside of the class Body, which allows for it to be utilized in different methods
	public double getX() {
		return myXPos;
	}
	
	//This function allows the instance variable "myYPos" to be accessed
	//outside of the class Body, which allows for it to be utilized in different methods
	public double getY() { 
		return myYPos;
	}
	
	//This function allows the instance variable "myXVel" to be accessed
			//outside of the class Body, which allows for it to be utilized in different methods
	public double getXVel() {
		return myXVel;
	}
	
	//This function allows the instance variable "myYVel" to be accessed
	//outside of the class Body, which allows for it to be utilized in different methods
	public double getYVel() {
		return myYVel;
	}
	
	//This function allows the instance variable "myMass" to be accessed
	//outside of the class Body, which allows for it to be utilized in different methods
	public double getMass() {
		return myMass;
	}
	
	//This function allows the instance variable "myFileName" to be accessed
	//outside of the class Body, which allows for it to be utilized in different methods
	public String getName() {
		return myFileName;
	}
	
	//Calculates the distance between the current body and another body, b, which
	//is passed through as a parameter. Uses the distance formula and both body's starting
	//x and y coordinates
	public double calcDistance(Body b) {
		double difX = (b.getX() - myXPos);
		double difY = (b.getY() - myYPos);
		double distance = Math.sqrt((Math.pow(difX, 2.0) + Math.pow(difY, 2.0)));
		return distance;
	}
	
	//Calculates the total gravitational force exerted on a body by another
	//body, b, which is passed as a parameter. 
	public double calcForceExertedBy(Body b) {
		double massproduct = myMass * b.getMass();
		double gravityC = 6.67 * Math.pow(10, -11);
		double force = gravityC*massproduct/(Math.pow(calcDistance(b), 2.0));
		return force;
	}
	
	//Calculates the gravitational force exerted on a body by another
	//body, b, in the x direction. The other body is passed as a parameter. 	
	public double calcForceExertedByX(Body b) {	
		double totalForce = calcForceExertedBy(b);
		double difX = (b.getX() - myXPos);
		double xForce = (totalForce * difX)/(calcDistance(b));
		return xForce;
	}
	
	//Calculates the gravitational force exerted on a body by another
	//body, b, in the y direction. The other body is passed as a parameter. 
	public double calcForceExertedByY(Body b) {
		double totalForce = calcForceExertedBy(b);
		double difY = (b.getY() - myYPos);
		double yForce = (totalForce * difY)/(calcDistance(b));
		return yForce;
	}
	
	//
	public double calcNetForceExertedByX(Body[] bodies) {
		double sumForcesX = 0.0;
		for(Body b : bodies) {
			if(! b.equals(this)) {
				sumForcesX += calcForceExertedByX(b);
			}
		}
		return sumForcesX;
	}
	public double calcNetForceExertedByY(Body[] bodies) {
		double sumForcesY = 0.0;
		for(Body b : bodies) {
			if(! b.equals(this)) {
				sumForcesY += calcForceExertedByY(b);
			}
		}
		return sumForcesY;
	}
	public void update(double deltaT, double xforce, double yforce) {
		double xAccel = xforce/myMass;
		double yAccel = yforce/myMass;
		double nvx = myXVel + deltaT*xAccel;
		double nvy = myYVel + deltaT*yAccel;
		double nx = myXPos + deltaT*nvx;
		double ny = myYPos + deltaT*nvy;
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
	}
	public void draw() {
		StdDraw.picture(myXPos,  myYPos,  "images/"+myFileName);;
	}
}	

