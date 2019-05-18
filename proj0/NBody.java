public class NBody {
    public static double readRadius(String fileName) {
	In in = new In(fileName);
	int N = in.readInt();
	double radius = in.readDouble();
	return radius;
    }

    public static Body[] readBodies(String fileName) {
	In in = new In(fileName);

	int N = in.readInt();
	Body[] NBodies = new Body[N];

	double radius = in.readDouble();

	for (int i = 0; i < N; i++) {
	    double xxPos = in.readDouble();
	    double yyPos = in.readDouble();
	    double xxVel = in.readDouble();
	    double yyVel = in.readDouble();
	    double mass = in.readDouble();
	    String img = in.readString();
            
	    NBodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, img);
	}
	
	return NBodies;
    }

    public static void main(String[] args) {
	double T = Double.parseDouble(args[0]);
	double dt = Double.parseDouble(args[1]);
	String filename = args[2];
        //System.out.println(T);
	//System.out.println(dt);
	//System.out.println(filename);
	Body[] NBodies = readBodies(filename);

	double radius = readRadius(filename);

	StdDraw.enableDoubleBuffering();
	StdDraw.setScale(-radius, radius);

 	int N = NBodies.length;

	for (double t = 0; t <= T; t += dt) {
	    double[] xForces = new double[N];
	    double[] yForces = new double[N];
	    StdDraw.picture(0, 0, "images/starfield.jpg");
	    for (int i = 0; i < N; i++) {
		xForces[i] = NBodies[i].calcNetForceExertedByX(NBodies);
		yForces[i] = NBodies[i].calcNetForceExertedByY(NBodies);
		NBodies[i].update(dt, xForces[i], yForces[i]);
		NBodies[i].draw();
	    }
	    StdDraw.show();
	    StdDraw.pause(50);
	}

    }

}
