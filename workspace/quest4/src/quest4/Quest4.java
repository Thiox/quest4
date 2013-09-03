package quest4;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import quest4.map.*;
import quest4.entity.*;

import java.awt.image.BufferedImage;

public class Quest4 extends Component implements Runnable, KeyListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final boolean debugmode = false;
	
	public int SCREEN_WIDTH=800;	//screen default size
	public int SCREEN_HEIGHT=480;

    private boolean running = false;
    private Map map;
    private Camera camera;
    private Input input = new Input();
    
	public Quest4() {
		setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent arg0) {}
            public void focusLost(FocusEvent arg0) {
                input.releaseAllKeys();
        }});
        
	}
	
	public void start() {
		running = true;
		new Thread(this).start();
	}
	
	public void stop() {
		running = false;
	}
	
	public void run() {
        requestFocus();
        
        map = new Map();
        
        //Player player = new Player(400, 240, map);
        
        camera = new Camera( SCREEN_WIDTH, SCREEN_HEIGHT, null);
        
        input.setCamera(camera);

        Editor editor = new Editor(0, 0, map, camera);
        camera.setFocus(editor);
        
        Image image = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        
        long lastTime = System.nanoTime();
        long unprocessedTime = 0;
        
		try {
            Thread.sleep(500);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
		
		while (running) {
			Graphics g = image.getGraphics();
			
	        long now = System.nanoTime();
	        unprocessedTime += now - lastTime;
	        lastTime = now;
	
	        int max = 10;
	        while (unprocessedTime > 0) {
	            unprocessedTime -= 1000000000 / 60;
	            
	            //tick statements
	            
	            //player.tick(input);
	            editor.tick(input);
	            camera.tick(input);
	            map.render(g, camera);
	            //player.render(g, camera);

	            editor.render(g, camera);
	            input.tick();
	            
	            if (max-- == 0) {
	                unprocessedTime = 0;
	                break;
	            }
	            
	            try {
	                g = getGraphics();
	                g.drawImage(image, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
	                g.dispose();
	            } catch (Throwable e) {}
	            
	        }
		}
	}
	
    public void keyPressed(KeyEvent ke) { input.set(ke.getKeyCode(), true);}

    public void keyReleased(KeyEvent ke) { input.set(ke.getKeyCode(), false);}

    public void keyTyped(KeyEvent ke) {}
	
	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		input.setMouse( e.getLocationOnScreen(), e.getButton(), true);
	}

	public void mouseReleased(MouseEvent e) {
		input.setMouse( e.getLocationOnScreen(), e.getButton(), false);
	}
    
	public static void main(String[] args) {
		JFrame j = new JFrame("Quest 4");
		Quest4 q = new Quest4();
		
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.add(q);
		j.pack();
		j.setVisible(true);
		
		q.start();
	}

}
