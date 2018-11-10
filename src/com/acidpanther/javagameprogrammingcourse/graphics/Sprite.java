package com.acidpanther.javagameprogrammingcourse.graphics;

public class Sprite {
	
	public final int SIZE;
	private int xSheetIndex, ySheetIndex;
	
	public int[] pixels;
	
	private SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x1B87E0);
	
	public static Sprite player0 = new Sprite(16, 0, 10, SpriteSheet.tiles);
	public static Sprite player1 = new Sprite(16, 1, 10, SpriteSheet.tiles);
	public static Sprite player2 = new Sprite(16, 0, 11, SpriteSheet.tiles);
	public static Sprite player3 = new Sprite(16, 1, 11, SpriteSheet.tiles);
	
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
