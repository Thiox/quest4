package quest4.map;

import java.awt.image.BufferedImage;

import quest4.Easel;

public class Tile {
	
	public static final int TILE_WIDTH = 40, TILE_HEIGHT = 40;
	private static final int RIGHT=0, LEFT=1, TOP=2, BOTTOM=3;
	
    private static BufferedImage tileset[][] = Easel.split(Easel.load("/tileset.png"), TILE_WIDTH, TILE_HEIGHT);
    
	public int value, flags;
	
	public Tile() {
		value = 0;
		flags = 0;
	}
	
	public Tile( int pixel) {
		this.value = pixel;
		this.flags = 0;
		
		if (value == 0xffffffff) { flags = flags & 0xffff;}
	}
	
	public Tile( int value, int flags) {
		this.value = value;
		this.flags = flags;
	}
	
	public Tile( Tile t) {
		this.value = t.value;
		this.flags = t.flags;
	}
	
	public void set(int value, int flags) {
		this.value = value;
		this.flags = flags;
	}
	
	/*	flag data	(use byte!?!?!)
	 * 	0x0001	isTransparent()
	 * 	0x0002	mirrored(LEFTRIGHT)
	 *  0x0004	mirrored(TOPBOTTOM)
	 * 	0x0008	
	 * 	0x0010	isOccupied(RIGHT)
	 * 	0x0020	isOccupied(LEFT)
	 * 	0x0040	isOccupied(TOP)
	 * 	0x0080	isOccupied(BOTTOM)
	 * 	0x0100
	 */
	
	public boolean isSolid() { //return (flags & 0x00f0) == 0x00f0;}
		return (value != 0xffffffff);}

	public boolean isSolid(int half) { return !isEmpty(half); }
	
	public boolean isEmpty() { return (flags & 0x00f0) == 0;} 
	
	public boolean isEmpty(int half) {
		if (half == RIGHT) 	{ return (flags & 0x0010) == 0; }
		if (half == LEFT) 	{ return (flags & 0x0020) == 0; }
		if (half == TOP) 	{ return (flags & 0x0040) == 0; }
		if (half == BOTTOM) { return (flags & 0x0080) == 0; }
		return false;
	}
	public boolean isTansparent() { return (flags & 0x0002) == 0;} 
	
	
	public BufferedImage texture() {
		//					    xy
		int i = value & 0x000000ff;
		//				x		y
		return tileset[i / 16][i % 16];
	}
}
