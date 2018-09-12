
public class Body {
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	public Body(double xp, double yp, double xv, 
			double yv, double mass, String filename) {	
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}
	public Body(Body b) {
		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myMass = b.getMass();
		myFileName = b.getName();
	}
	public double getX() {
		return myXPos;
	}
	public double getY() { 
		return myYPos;
	}
	public double getXVel() {
		return myXVel;
	}
	public double getYVel() {
		return myYVel;
	}
	public double getMass() {
		return myMass;
	}
	public String getName() {
		return myFileName;
	}
	public double calcDistance(Body b) {
		double difX = (b.getX() - myXPos);
		double difY = (b.getY() - myYPos);
		double distance = Math.sqrt((Math.pow(difX, 2.0) + Math.pow(difY, 2.0)));
		return distance;
	}
	public double calcForceExertedBy(Body b) {
		double massproduct = myMass * b.getMass();
		double gravityC = 6.67 * Math.pow(10, -11);
		double force = gravityC*massproduct/(Math.pow(calcDistance(b), 2.0));
		return force;
	}
	public double calcForceExertedByX(Body b) {
		double totalForce = calcForceExertedBy(b);
		double difX = (b.getX() - myXPos);
		double xForce = (totalForce * difX)/(calcDistance(b));
		return xForce;
	}
	public double calcForceExertedByY(Body b) {
		double totalForce = calcForceExertedBy(b);
		double difY = (b.getY() - myYPos);
		double yForce = (totalForce * difY)/(calcDistance(b));
		return yForce;
	}
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
	public void update(double deltaT, 
			double xforce, double yforce) {
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

