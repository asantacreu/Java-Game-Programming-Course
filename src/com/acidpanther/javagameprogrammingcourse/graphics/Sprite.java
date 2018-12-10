package com.acidpanther.javagameprogrammingcourse.graphics;

public class Sprite {
	
	public final int SIZE;
	private int xSheetIndex, ySheetIndex;
	
	public int[] pixels;
	
	private SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x000000);
	
	//Spawn Sprites:
	public static Sprite spawnGrass = new Sprite(16, 0, 0, SpriteSheet.spawnLevel);
	public static Sprite spawnHedge = new Sprite(16, 1, 0, SpriteSheet.spawnLevel);
	public static Sprite spawnWater = new Sprite(16, 2, 0, SpriteSheet.spawnLevel);
	public static Sprite spawnWall1 = new Sprite(16, 0, 1, SpriteSheet.spawnLevel);
	public static Sprite spawnWall2 = new Sprite(16, 0, 2, SpriteSheet.spawnLevel);
	public static Sprite spawnFloor = new Sprite(16, 1, 1, SpriteSheet.spawnLevel);
	
	//Player Sprites:
	public static Sprite player_up = new Sprite(32, 0, 5, SpriteSheet.tiles);
	public static Sprite player_down = new Sprite(32, 2, 5, SpriteSheet.tiles);
	public static Sprite player_right = new Sprite(32, 1, 5, SpriteSheet.tiles);
	
	public static Sprite player_up_1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite player_up_2 = new Sprite(32, 0, 7, SpriteSheet.tiles);
	
	public static Sprite player_down_1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
	public static Sprite player_down_2 = new Sprite(32, 2, 7, SpriteSheet.tiles);
	
	public static Sprite player_right_1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite player_right_2 = new Sprite(32, 1, 7, SpriteSheet.tiles);
	
	//Projectile Sprites:
	public static Sprite projectileWizard = new Sprite(16, 0, 0, SpriteSheet.projectileWizard);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.xSheetIndex = x * size;
		this.ySheetIndex = y * size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int size, int colour) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColour(colour);
	}
	
	private void setColour(int colour) {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = colour;
		}
	}
	
	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for(int x = 0; x < SIZE; x++) {
				pixels[x+y*SIZE] = sheet.pixels[(xSheetIndex + x) + (ySheetIndex + y) * sheet.SIZE];
			}
		}
	}
	
}
