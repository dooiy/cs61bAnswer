public class Planet{
	static final double G = 6.67e-11;

	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;

	public Planet(double xP, double yP, double xV,double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double r;
		r = Math.sqrt((p.xxPos - this.xxPos) * (p.xxPos - this.xxPos) + (p.yyPos - this.yyPos) * (p.yyPos - this.yyPos));
		return r;
	}

	public double calcForceExertedBy(Planet p){
		double f;
		f = G * this.mass * p.mass / (this.calcDistance(p) * this.calcDistance(p));
		return f;
	}

	public double calcForceExertedByX(Planet p){
		double fx;
		fx = this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
		return fx;
	}

	public double calcForceExertedByY(Planet p){
		double fy;
		fy = this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
		return fy;
	}

	public boolean equals(Planet p){
		if(this.xxPos == p.xxPos && this.yyPos == p.yyPos){
			return true;
		}
		return false;
	}

	public double calcNetForceExertedByX(Planet[] all){
		double sum = 0;
		for (Planet p : all){
			if (this.equals(p)){
				continue;
			}
			sum += this.calcForceExertedByX(p);
		}
		return sum;
	}

	public double calcNetForceExertedByY(Planet[] all){
		double sum = 0;
		for (Planet p : all){
			if (this.equals(p)){
				continue;
			}
			sum += this.calcForceExertedByY(p);
		}
		return sum;
	}

	public void update(double dt, double fX, double fY){
		double ax;
		double ay;

		ax = fX / this.mass;
		ay = fY / this.mass;
		this.xxVel += dt * ax;
		this.yyVel += dt * ay;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	public void draw(){
  String img = "./images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, img);
	}

}
