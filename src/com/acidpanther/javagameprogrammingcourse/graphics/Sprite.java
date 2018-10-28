package com.acidpanther.javagameprogrammingcourse.graphics;

public class Sprite {
	
	private final int SIZE;
	private int xSheetIndex, ySheetIndex;
	
	public int[] pixels;
	
	private SpriteSheet sheet;
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.xSheetIndex = x * size;
		this.ySheetIndex = y * size;
		this.sheet = sheet;
		load();
	}
	
	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for(int x = 0; x < SIZE; x++) {
				pixels[x+y*SIZE] = sheet.pixels[(xSheetIndex + x) + (ySheetIndex + y) * sheet.SIZE];
			}
		}
	}
	
}
