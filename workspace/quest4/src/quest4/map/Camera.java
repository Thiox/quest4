package quest4.map;


import quest4.*;
import quest4.entity.*;

public class Camera {
	
	public Vect p;
	public Vect v;
    public int width, height;
    
    public boolean free;
    
    private int camspeed;
    private Entity focus;

	private boolean sleep = false;
    
    public Camera(int w, int h, Entity e) {
    	this.p = new Vect();
    	this.v = new Vect();
    	this.camspeed = 5;
        this.width = w;
        this.height = h;
        this.focus = e;
        
        this.free = true;
        
	    if (e != null) {
	    	free = false;
	        p = e.getCenter();
	        p.x = p.x - w / 2;
	        p.y = p.y - h / 2;
        }
    }
	
	public Vect getCenter() {
    	return new Vect( p.x + width / 2, p.y + height / 2);
    }
    
    public void setFocus(Entity e) {
    	focus = e;
    	wake();
    }
	
    private void wake() { sleep  = false;	}//System.err.printf("wake\n" );}
    private void nap() 	{ sleep = true;		}//System.err.printf("sleep\n" );}

	public void tick(Input i) {
    	if (free) { freecam(i);} 
    	else { followcam();}
    }
    
    public void freecam(Input i) {
    	
    	v.set(0, 0);
    	if (i.buttons[Input.UP]) 	{ v.y--;} 
		if (i.buttons[Input.LEFT])  { v.x--;}
		if (i.buttons[Input.RIGHT]) { v.x++;}
		if (i.buttons[Input.DOWN])  { v.y++;}
		//if (i.buttons[Input.SHOOT]) { camspeed++;}
		//if (i.buttons[Input.JUMP])  { camspeed--;}
		
		v.unit();
		v.scale(camspeed);
		
		p.add(v);
    }
	
	private void check() {
		Vect c = getCenter();
    	Vect pc = focus.getCenter();
    	
		double r =  Math.pow( (c.x - pc.x ), 2) + Math.pow( (c.y - pc.y), 2);
		//System.err.printf("r: %.2f\n", r );
		
		if (r > 6400 & sleep) { wake();}	//6400 = (80 pixel radius)^2 
		if (r < 525 & !sleep) { nap();}		// 525 = (25 pixel radius)^2 
	}
    
    private void followcam() {
    	check();
    	
    	v.scale(0.95);
    	
    	if (!sleep) {
	    	Vect c = getCenter();
	    	Vect pc = focus.getCenter();
	    	Vect a = new Vect(pc.x - c.x, pc.y - c.y);

	    	//System.err.printf("r: %.2f, %.2f;  ", a.x, a.y );
	    	//a.unit();
	    	//System.err.printf("%.2f, %.2f\n", a.x, a.y );
	    	
	    	//if (focus.v.mag() > 0.1) 	{ a.scale( 0.05 * focus.v.mag()); }
	    	//else 						{ a.scale( 0.05 * focus.v.mag() + 0.1); }

	    	a.scale( 0.00025 * focus.v.mag() + 0.001); 
	    	
	    	v.add(a);
    	}
    	p.add(v);
	}
}
