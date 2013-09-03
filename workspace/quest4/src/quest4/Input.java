package quest4;

import java.awt.Point;
import java.awt.event.*;

import quest4.map.*;


public class Input {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public static final int JUMP = 4;
    public static final int SHOOT = 5;

    public static final int SPACE = 6;
    public static final int ENTER = 7;
    
    public static final int W = 10;
    public static final int S = 11;
    public static final int A = 12;
    public static final int D = 13;
    
    public static final int I = 14;
    public static final int K = 15;
    public static final int J = 16;
    public static final int L = 17;

    public static final int LEFTCLICK = 60;
    public static final int RIGHTCLICK = 61;
    
    public static final int ESCAPE = 63;

    public boolean[] buttons = new boolean[64];
    public boolean[] oldButtons = new boolean[64];
    
    public Vect mouse;
    public Camera camera;
    
    public Input() {
    	mouse = new Vect();
    }
    
    public void setCamera(Camera c) {
    	this.camera = c;
    }

    public void setMouse(Point p, int key, boolean down) {
    	int button = -1;
    	
    	if (down) {
    		mouse.set(p.x, p.y);
    		mouse.add(camera.p.inverse());
    	}
    	
    	if (key == MouseEvent.BUTTON1) button = LEFTCLICK;
    	if (key == MouseEvent.BUTTON2) button = RIGHTCLICK;
    	
    	if (button >= 0) {
            buttons[button] = down;
        	System.out.printf("%f, %f\n", (mouse.x - 0.5) / Tile.TILE_WIDTH, (mouse.y - 0.5) / Tile.TILE_HEIGHT);
        }
    }
    
    public void set(int key, boolean down) {
        int button = -1;

        if (key == KeyEvent.VK_UP) button = UP;
        if (key == KeyEvent.VK_LEFT) button = LEFT;
        if (key == KeyEvent.VK_DOWN) button = DOWN;
        if (key == KeyEvent.VK_RIGHT) button = RIGHT;

        if (key == KeyEvent.VK_W) button = W;
        if (key == KeyEvent.VK_A) button = A;
        if (key == KeyEvent.VK_S) button = S;
        if (key == KeyEvent.VK_D) button = D;
        
        if (key == KeyEvent.VK_I) button = I;
        if (key == KeyEvent.VK_J) button = J;
        if (key == KeyEvent.VK_K) button = K;
        if (key == KeyEvent.VK_L) button = L;
        

        if (key == KeyEvent.VK_Z) button = JUMP;
        if (key == KeyEvent.VK_X) button = SHOOT;
        if (key == KeyEvent.VK_C) button = SHOOT;
        if (key == KeyEvent.VK_SPACE) button = SPACE;
        if (key == KeyEvent.VK_ENTER) button = ENTER;

        if (key == KeyEvent.VK_ESCAPE) button = ESCAPE;

        if (button >= 0) {
            buttons[button] = down;
        }
        //System.err.println(button); 	//***********
    }
    
    public boolean clicked(int button) {
    	//doesn't waste time checking, USE CAREFULLY
    	return (buttons[button] && !oldButtons[button]);
    }

    public void tick() {
        for (int i = 0; i < buttons.length; i++) {
            oldButtons[i] = buttons[i];
        }
    }

    public void releaseAllKeys() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = false;
        }
    }
}
