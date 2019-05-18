import java.lang.Math;

public class Body {
    private static final double G = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV,
	    double yV, double m, String img) {
        xxPos = xP;
	yyPos = yP;
	xxVel = xV;
	yyVel = yV;
	mass = m;
	imgFileName = img;
    }

    public Body(Body b) {
	xxPos = b.xxPos;
	yyPos = b.yyPos;
	xxVel = b.xxVel;
	yyVel = b.yyVel;
	mass = b.mass;
	imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
	double dx = xxPos - b.xxPos;
	double dy = yyPos - b.yyPos;
	double distance = Math.sqrt(dx*dx + dy*dy);
	return distance;
    }

    public double calcForceExertedBy(Body b) {
	double r2 = Math.pow(this.calcDistance(b), 2);
	double F = Body.G * mass * b.mass / r2;
	return F;
    }

    public double calcForceExertedByX(Body b) {
	double dx = b.xxPos - xxPos;
	double r = this.calcDistance(b);
	double F = this.calcForceExertedBy(b);
	double Fx = F*dx/r;
	return Fx;
    }

    public double calcForceExertedByY(Body b) {
	double dy = b.yyPos - yyPos;
	double r = this.calcDistance(b);
	double F = this.calcForceExertedBy(b);
	double Fy = F*dy/r;
	return Fy;
    }

    public double calcNetForceExertedByX(Body[] allBodys){ 
	int N = allBodys.length;
	double netForceX = 0.0;
	for (int i = 0; i < N; i++) {
	    if (this.equals(allBodys[i])) continue;
	    netForceX += this.calcForceExertedByX(allBodys[i]);
	}
	return netForceX;
    }

    public double calcNetForceExertedByY(Body[] allBodys) {
	int N = allBodys.length;
	double netForceY = 0.0;
	for (int i = 0; i < N; i++) {
	    if (this.equals(allBodys[i])) continue;
	    netForceY = this.calcForceExertedByY(allBodys[i]);
	}
	return netForceY;
    }

    public void update(double dt, double fX, double fY) {
	double ax = fX/mass;
	double ay = fY/mass;
	xxVel += ax*dt;
	yyVel += ay*dt;
	xxPos += xxVel*dt;
	yyPos += yyVel*dt;
    }

    public void draw() {
	StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }

}
