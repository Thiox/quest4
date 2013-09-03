package quest4.entity;

import java.awt.Color;
import java.awt.Graphics;

import quest4.*;
import quest4.map.*;

public class Editor extends Entity {
	int x, y;
	
	int restTime;
	
	Camera c;

	private Palette palette;

	public Editor(int x, int y, Map m, Camera c) {
		super(0, 0, m);
		restTime = 0;
		
		palette = new Palette();
		
		this.c = c;
	}

	public void tick(Input i) {
		
		if (restTime > 0) { restTime--;}
		else {
	    	if (i.buttons[Input.W])	{ y--; restTime = 7;} 
			if (i.buttons[Input.A]) { x--; restTime = 7;}
			if (i.buttons[Input.D]) { x++; restTime = 7;}
			if (i.buttons[Input.S]) { y++; restTime = 7;}
			
	    	if (i.buttons[Input.I])	{ palette.move(0, -1); restTime = 7;} 
			if (i.buttons[Input.J]) { palette.move(-1, 0); restTime = 7;}
			if (i.buttons[Input.L]) { palette.move(1, 0); restTime = 7;}
			if (i.buttons[Input.K]) { palette.move(0, 1); restTime = 7;}
		}
		
		if (i.clicked(Input.LEFTCLICK)) {
			y = ((int)i.mouse.y) / Tile.TILE_HEIGHT;
			x = ((int)i.mouse.x) / Tile.TILE_WIDTH;
		}

		if (y < 0) {y = 0;}
		if (x < 0) {x = 0;}
		if (y >= Map.HEIGHT) {y = Map.HEIGHT - 1;}
		if (x >= Map.WIDTH) {x = Map.WIDTH - 1;}
		
		if (i.clicked(Input.SPACE))  {
			map.tiles[x][y].value = palette.selection().value;
			System.err.println(Integer.toHexString(map.tiles[x][y].value));
		}
		
		if (i.clicked(Input.ENTER))  { 
			c.free = !c.free;
		}
		palette.refresh();
	}
	
	public void render(Graphics g, Camera c) {
		g.setColor(Color.RED);
		g.drawRect( x * Tile.TILE_WIDTH - 1, y * Tile.TILE_HEIGHT - 1, 
					Tile.TILE_WIDTH + 2, Tile.TILE_HEIGHT + 2);
		
		palette.render( g, c);
	}

	public Vect getCenter() {
		return new Vect( (x + 0.5) * Tile.TILE_WIDTH, (y + 0.5) * Tile.TILE_HEIGHT);
	}

}
