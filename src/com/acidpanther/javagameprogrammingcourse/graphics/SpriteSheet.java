package com.acidpanther.javagameprogrammingcourse.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public final int WIDTH, HEIGHT;
	public int[] pixels;
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/spritesheet.png", 256);
	public static SpriteSheet spawnLevel = new SpriteSheet("/textures/sheets/spawn_lvl.png", 48);
	public static SpriteSheet projectileWizard = new SpriteSheet("/textures/sheets/projectiles/wizard.png", 48);
	
	public static SpriteSheet player = new SpriteSheet("/textures/sheets/player_sheet.png", 128, 96);
	public static SpriteSheet player_down = new SpriteSheet(player, 0, 0, 1, 3, 32);
	public static SpriteSheet player_up = new SpriteSheet(player, 1, 0, 1, 3, 32);
	public static SpriteSheet player_left = new SpriteSheet(player, 2, 0, 1, 3, 32);
	public static SpriteSheet player_right = new SpriteSheet(player, 3, 0, 1, 3, 32);
	
	public static SpriteSheet dummy = new SpriteSheet("/textures/sheets/king_cherno.png", 128, 96);
	public static SpriteSheet dummy_down = new SpriteSheet(dummy, 0, 0, 1, 3, 32);
	public static SpriteSheet dummy_up = new SpriteSheet(dummy, 1, 0, 1, 3, 32);
	public static SpriteSheet dummy_left = new SpriteSheet(dummy, 2, 0, 1, 3, 32);
	public static SpriteSheet dummy_right = new SpriteSheet(dummy, 3, 0, 1, 3, 32);
	
	private Sprite[] sprites;
	
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		SIZE = -1;
		WIDTH = (width * spriteSize);
		HEIGHT = (height * spriteSize);
		pixels = new int[WIDTH * HEIGHT];
		
		int xx = (x * spriteSize);
		int yy = (y * spriteSize);
		for(int y0 = 0; y0 < HEIGHT; y0++) {
			int yp = (yy + y0);
			for(int x0 = 0; x0 < WIDTH; x0++) {
				int xp = (xx + x0);
				pixels[x0 + y0 * WIDTH] = sheet.pixels[xp + yp * sheet.WIDTH];
			
			}
		}
		int iframe = 0;
		sprites = new Sprite[width * height];
		for(int ya=0; ya < height; ya++) {
			for(int xa=0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for(int y0=0; y0 < spriteSize; y0++) {
					for(int x0=0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * WIDTH];
					}
				}
				sprites[iframe++] = new Sprite(spritePixels, spriteSize, spriteSize);
			}
		}
	}
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		this.SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		pixels = new int[WIDTH * HEIGHT];
		
		load();
	}
	
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		this.SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		
		load();
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);	
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	public Sprite[] getSprites() {
		return sprites;
	}
	
}
