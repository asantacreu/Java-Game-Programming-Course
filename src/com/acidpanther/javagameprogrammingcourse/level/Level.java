package com.acidpanther.javagameprogrammingcourse.level;

import com.acidpanther.javagameprogrammingcourse.graphics.Screen;
import com.acidpanther.javagameprogrammingcourse.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	
	public static Level spawn = new SpawnLevel("/levels/spawn.png");
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
	}

	protected void generateLevel() {
		
	}
	
	protected void loadLevel(String path) {
	}
	
	
	public void update() {
	}
	
	private void time() {
	} 
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		
		int x0 = xScroll >> 4;
		int x1 = ((xScroll + screen.width) >> 4) + 1;
		int y0 = yScroll >> 4;
		int y1 = ((yScroll + screen.height) >> 4) + 1;
		
		for(int y = y0; y < y1; y++) {
			for(int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.voidTile;
		} 
		else if(tiles[x + y * width] == Tile.colorspawnGrass) {
			return Tile.spawnGrass;
		}
		else if(tiles[x + y * width] == Tile.colorspawnHedge) {
			return Tile.spawnHedge;
		}
		else if(tiles[x + y * width] == Tile.colorspawnWater) {
			return Tile.spawnWater;
		}
		else if(tiles[x + y * width] == Tile.colorspawnWall1) {
			return Tile.spawnWall1;
		}
		else if(tiles[x + y * width] == Tile.colorspawnWall2) {
			return Tile.spawnWall2;
		}
		else if(tiles[x + y * width] == Tile.colorspawnFloor) {
			return Tile.spawnFloor;
		}
		return Tile.voidTile;
	}
	
}
