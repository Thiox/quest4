package quest4.entity;

import java.awt.Color;
import java.awt.Graphics;

import quest4.*;
import quest4.map.*;

public class Player extends Entity {

	public double speed = 1.3;
	
	public Player(double x, double y, Map m) {
		super(x, y, m);
		
		hitbox = new Hitbox( 30, 30);
	}
	
	public void tick(Input i) {
    	if (Quest4.debugmode) {}
    	else {
    		Vect a = new Vect();
    		
        	if (i.buttons[Input.UP]) 	{ a.y--;} 
    		if (i.buttons[Input.LEFT])  { a.x--;}
    		if (i.buttons[Input.RIGHT]) { a.x++;}
    		if (i.buttons[Input.DOWN])  { a.y++;}
    		
    		if (i.buttons[Input.JUMP])  { 
    			speed += 0.1;
    			System.err.printf("speed: %.2f\n", speed );
    		}
    		if (i.buttons[Input.SHOOT]) { 
    			speed -= 0.1;
    			System.err.printf("speed: %.2f\n", speed );
    		}
    		
    		a.unit();
    		a.scale(speed);
    		
    		v.scale( 0.7);
    		v.add(a);
    		
    		//if (v.mag() > 0) {v.debug("v");}
    		//if (a.mag() > 0) {a.debug("a");}

    		tryMove();
    	}
    }
	
	public void render(Graphics g, Camera c) {
		g.setColor(Color.RED);
		g.fillRect((int)p.x, (int)p.y, 30, 30);
		
		g.drawOval((int)c.getCenter().x - 80, (int)c.getCenter().y - 80, 160, 160);
		g.drawOval((int)c.getCenter().x - 120, (int)c.getCenter().y - 120, 240, 240);
	}
	
	public Vect getCenter() {
    	return new Vect( p.x + hitbox.width / 2, p.y + hitbox.height / 2);
    }

}
