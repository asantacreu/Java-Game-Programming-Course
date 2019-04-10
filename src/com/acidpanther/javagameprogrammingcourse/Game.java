package com.acidpanther.javagameprogrammingcourse;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.acidpanther.javagameprogrammingcourse.entity.mob.Player;
import com.acidpanther.javagameprogrammingcourse.graphics.Screen;
import com.acidpanther.javagameprogrammingcourse.input.Keyboard;
import com.acidpanther.javagameprogrammingcourse.input.Mouse;
import com.acidpanther.javagameprogrammingcourse.level.Level;
import com.acidpanther.javagameprogrammingcourse.level.TileCoordinate;


public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private static int width = 300;
	private static int height = width / 16 * 9;
	
	private static int scale = 3;
	
	public static String title = "Java GP Course";
	
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false;
	
	private Screen screen;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public Game() {
		Dimension size = new Dimension(getScreenWidth(), getScreenHeight());
		setPreferredSize(size);
		
		screen = new Screen(width, height);
		frame = new JFrame();
		
		key = new Keyboard();
		addKeyListener(key);
		
		Mouse mouse  = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
		level = Level.spawn;
		
		TileCoordinate playerSpawn = new TileCoordinate(10, 6);
		player = new Player(playerSpawn.x(), playerSpawn.y(), key);
		level.add(player);
	}
	
	public static int getScreenWidth() {
		return (width * scale);
	}
	
	public static int getScreenHeight() {
		return (height * scale);
	}
	
	public synchronized void start() {
		running = true;
		
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = (1000000000.0 / 60.0);
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while(running) {
			long now = System.nanoTime();
			delta += ((now - lastTime) / ns);
			lastTime = now;
			while(delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " | " + "ups: " + updates + " fps: " + frames);
				frames = 0;
				updates = 0;
			}
		}
	}

	private void update() {
		key.update();
		level.update();
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		double xScroll = player.getX() - screen.width / 2;
		double yScroll = player.getY() - screen.height / 2;
		level.render((int) xScroll, (int) yScroll,  screen);
		
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("Java GameProgramming Course");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
	
}
