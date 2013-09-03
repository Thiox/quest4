package quest4;

import java.awt.Point;

public class Vect {
	
	public double x, y;
	
	public Vect() {
		this.x = 0;
		this.y = 0;
	}

	public Vect(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vect(Vect u) { 
		this.x = u.x;
		this.y = u.y;
	}
	
	public Vect inverse() { return new Vect( -x, -y);}
	
	public Vect scale(double factor) {
		x = x * factor;
		y = y * factor;
		return this;
	}
	
	public double mag() {
		return Math.pow(Math.pow( x, 2) + Math.pow( y, 2), 0.5);
	}
	
	public void unit() {
		double m = this.mag();
		if ( m > 0.000001) {
			x = x / m;
			y = y / m;
		}
		else { 
			x = 0;
			y = 0;
		}
	}
	
	public Vect getUnit() {
		double m = this.mag();
		if ( m > 0.000001) {
			return new Vect(x / m, y / m);
		}
		else { return new Vect(); }
	}
	
	public Vect add( Vect v) { 
		x = x + v.x;
		y = y + v.y;
		return this;
	}
	
	public Vect dot( Vect v) { return new Vect( x * v.x, y * v.y);}
	
	public Point shift( Point p) { return new Point( p.x + (int)x, p.y + (int)y);}

	public void debug(String s) {
		System.err.printf("%s: %.2f, %.2f\n", s, x, y);
	}

	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/* not worth it in 2d
	public Vect cross( Vect v) {
		
		return new Vect( x , y );
	}//*/
}
