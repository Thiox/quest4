package quest4.entity;

import java.awt.*;

import quest4.*;
import quest4.map.*;

public class Entity {
	public Vect v;
	public Vect p;
    //public double x, y;
    
	public Map map;
    public Hitbox hitbox;
    
    public Entity(double x, double y, Map m) {
    	p = new Vect( x, y);
    	v = new Vect(); 
    	map = m;
    }
    
	public void tick() {}
	
	public void tryMove() {
		if (hitbox == null || !inBounds()) {
			p.add(v);

    		System.err.println("focusable");
		}
		else {
			Vect r = new Vect(v.x, 0);
			
			while (r.mag() > 1) {				
				if (map.isFree(p.x + r.x, p.y, r.x, 0, this)) {
					p.x += r.x;
					r.x = 0;
				}
				else {r.scale(0.7);}
			}
			r.set(0, v.y);
			
			while (r.mag() > 1) {				
				if (map.isFree(p.x, p.y + r.y, 0, r.y, this)) {
					p.y += r.y; 
					r.y = 0;
				}
				else {r.scale(0.7);}
			}
		}
	}
	
	public boolean inBounds() {
		return (p.x > 0 && p.x + hitbox.width < Tile.TILE_WIDTH * Map.WIDTH &&
				p.y > 0 && p.y + hitbox.height < Tile.TILE_HEIGHT * Map.HEIGHT);
	}
	
	public Vect getCenter() {return new Vect(p);}

	public void render(Graphics g, Camera c) {}
}
