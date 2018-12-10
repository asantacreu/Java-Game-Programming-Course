package com.acidpanther.javagameprogrammingcourse.graphics;

import java.util.Random;

import com.acidpanther.javagameprogrammingcourse.entity.mob.Player;
import com.acidpanther.javagameprogrammingcourse.entity.projectile.Projectile;
import com.acidpanther.javagameprogrammingcourse.level.tile.Tile;

public class Screen {
	public int width, height;
	public int[] pixels;
	private final int MAP_SIZE = 64;
	private final int MAP_SIZE_MASK = MAP_SIZE - 1;
	private int xOffset, yOffset;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	private Random random = new Random();
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		
		for(int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for(int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if(xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y *tile.sprite.SIZE];
			}
		}
	}
	
	public void renderProjectile(int xp, int yp, Projectile p) {
		xp -= xOffset;
		yp -= yOffset;
		
		for(int y = 0; y < p.getSpriteSize(); y++) {
			int ya = y + yp;
			for(int x = 0; x < p.getSpriteSize(); x++) {
				int xa = x + xp;
				if(xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				int col = p.getSprite().pixels[x + y *p.getSpriteSize()];
				if(col != 0xffff00ff) {
					pixels[xa + ya * width] = col;
				}
			}
		}
	}
	
	public void renderPlayer(int xp, int yp, Sprite sprite, int flip) {
		xp -= xOffset;
		yp -= yOffset;
		
		for(int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;
			int ys = y;
			if(flip ==2 || flip ==3) {
				ys = ((sprite.SIZE - 1) - y);
			}
			for(int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				int xs = x;
				if(flip == 1 || flip == 3) {
					xs = ((sprite.SIZE - 1) - x);
				}
					
				if(xa < - sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				int col = sprite.pixels[xs + ys * sprite.SIZE];
				if(col != 0xffff00ff) {
					pixels[xa + ya * width] = col;
				}
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}

