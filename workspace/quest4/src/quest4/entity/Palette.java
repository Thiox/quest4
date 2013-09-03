package quest4.entity;

import java.awt.Color;
import java.awt.Graphics;

import quest4.map.*;

public class Palette {
	
	public static final int WIDTH = 3;
	public static final int HEIGHT = 11;
	
	Tile[][] tiles = new Tile[WIDTH][HEIGHT];
	
	public int x, y;
	
	public Palette() {	
		x = WIDTH / 2;
		y = HEIGHT / 2;
		
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
                	tiles[i][j] = new Tile();
			}
		}
		refresh();
	}
	
	public void refresh() {
		int iStart = x - WIDTH / 2;
		int jStart = y - HEIGHT / 2;
		
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
                if (i + iStart >= 0 && j + jStart >= 0 && i < 16 - iStart && j < 16 - jStart) {
					tiles[i][j].set( 16 * (i + iStart) + j + jStart , 0);
				}
	            else {
	            	tiles[i][j].set(15, 15);
	            }
			}
		}
	}//*/

	public void render(Graphics g, Camera c) {
		g.translate( (int)c.p.x, (int)c.p.y);
		
		int iStart = c.width - (Tile.TILE_WIDTH + 1) * WIDTH ;
		int jStart = c.height - (Tile.TILE_HEIGHT + 1) * HEIGHT ;
		
    	g.setColor( Color.BLACK);
    	g.fillRect( iStart - 1, jStart - 1, c.width, c.height);
		
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				g.drawImage( tiles[i][j].texture(), iStart + i * (Tile.TILE_WIDTH + 1), 
							 jStart + j * (Tile.TILE_HEIGHT + 1), null);
			}
		}
		
		g.setColor(Color.RED);
		g.drawRect( iStart + (WIDTH - 1) * (Tile.TILE_WIDTH + 1) / 2 - 1, 
					jStart + (HEIGHT - 1) * (Tile.TILE_HEIGHT + 1) / 2 - 1,
					Tile.TILE_WIDTH + 1, Tile.TILE_HEIGHT + 1);

		g.translate( (int)-c.p.x, (int)-c.p.y);
		
	}

	public void move(int i, int j) {
		x+=i;
		y+=j;

    	if (x < 0) 	{x = 0; }
    	if (x > 15) {x = 15;}
    	if (y < 0) 	{y = 0; }
    	if (y > 15) {y = 15;}
	}

	public Tile selection() {
		return tiles[WIDTH / 2][HEIGHT / 2];
	}
}
