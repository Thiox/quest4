package quest4.map;


import java.awt.*;
import java.awt.image.BufferedImage;

import quest4.*;
import quest4.entity.*;

public class Map {
	
    public static BufferedImage mapdata = Easel.load("/mapdata.png");
    public static BufferedImage test2 = Easel.load("/test2.png");
    public static BufferedImage test = Easel.load("/test.png");

    
	public static final int HEIGHT = mapdata.getHeight();
	public static final int WIDTH = mapdata.getWidth();
    
	public Tile[][] tiles = new Tile[WIDTH][HEIGHT];
	
	public Map() {
		
		int[] pixels = new int[WIDTH * HEIGHT];
		
		//int h = mapdata.getHeight();
		//int w = mapdata.getWidth();
		
		mapdata.getRGB( 0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
		
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				tiles[i][j] = new Tile(pixels[ i + WIDTH * j]);
				//System.err.println(i);
			}
		}
	}
	
    //public void removed() {}

	//functions to be overloaded in inherited classes?
    public void render(Graphics g, Camera c) {
    	
    	g.setColor( Color.BLACK);
    	g.fillRect( 0, 0, c.width, c.height);
    	
    	g.translate( (int)-c.p.x, (int)-c.p.y);
    	
    	/*
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				//optimize this
				g.drawImage( tiles[i][j].texture(), i * Tile.TILE_WIDTH, j * Tile.TILE_HEIGHT, null);

			}
		}//*/
		
    	int iStart = (int)c.p.x / Tile.TILE_WIDTH;  
    	int iEnd = iStart + (c.width / Tile.TILE_WIDTH) + 1;
    	
    	if (iStart < 0) {iStart = 0;}
    	if (iStart > WIDTH) {iStart = WIDTH;}
    	if (iEnd < 0) {iEnd = 0;}
    	if (iEnd > WIDTH) {iEnd = WIDTH;}
    	
    	int jStart = (int)c.p.y / Tile.TILE_HEIGHT;
    	int jEnd = jStart + (c.height / Tile.TILE_HEIGHT) + 1;
    	
    	if (jStart < 0) {jStart = 0;}
    	if (jStart > HEIGHT) {jStart = HEIGHT;}
    	if (jEnd < 0) {jEnd = 0;}
    	if (jEnd > HEIGHT) {jEnd = HEIGHT;}
    	
    	//Debugging information
    	//System.err.printf("loaded tiles: %d, %d; %d, %d\n", iStart, iEnd , jStart,  jEnd );
    	//System.err.printf("loaded tiles: %d\n", (int)(jEnd-jStart)*(iEnd-iStart) );
    	
		for (int i = iStart; i < iEnd; i++) {
			for (int j = jStart; j < jEnd; j++) {
				g.drawImage( tiles[i][j].texture(), i * Tile.TILE_WIDTH, j * Tile.TILE_HEIGHT, null);

			}
		}//*/
		
		
    	/*SCALING CODE
    	
    	Point p = c.getCenter();
    	
    	Graphics2D g2 = (Graphics2D)g;
    	
    	double scale = 1.1;
    	
    	int newW = (int)(test.getWidth() * scale);
        int newH = (int)(test.getHeight() * scale);
         
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        
        g2.drawImage(test2, (int) scale * p.x / 2 + p.x, (int) scale * p.y / 2 + p.y, newW, newH, null);
    	//*/
    }

	public boolean isFree( double px, double py, double vx, double vy, Entity e) {
		
        int x0 = (int) ((px + 1) / Tile.TILE_WIDTH);
        int x1 = (int) ((px + e.hitbox.width - 2) / Tile.TILE_WIDTH);        

        int y0 = (int) ((py + 1) / Tile.TILE_HEIGHT);
        int y1 = (int) ((py + e.hitbox.height - 2) / Tile.TILE_HEIGHT);
        //System.out.printf("%d, %d\n%d, %d\n", x0, x1, y0, y1);
        
        for (int x = x0; x <= x1; x++) {
        	System.out.println("\n");
            for (int y = y0; y <= y1; y++) {
                if (x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT) {
                	if (tiles[x][y].isSolid()) { return false; }
                }
            }
        }
		//*/
		return true;
	}
   
	

}
