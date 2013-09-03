package quest4.entity;

public class Hitbox {
	public int width, height;
	//no position vector, the hitbox is always centered on the entity coordinate
	
	public Hitbox( int w, int h) {
		this.width = w;
		this.height = h;
	}
}
