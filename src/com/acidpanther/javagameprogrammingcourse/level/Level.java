package com.acidpanther.javagameprogrammingcourse.level;

import com.acidpanther.javagameprogrammingcourse.graphics.Screen;

import java.util.ArrayList;
import java.util.List;

import com.acidpanther.javagameprogrammingcourse.entity.Entity;
import com.acidpanther.javagameprogrammingcourse.entity.Spawner;
import com.acidpanther.javagameprogrammingcourse.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	
	private List<Entity> entities = new ArrayList<Entity>();
	
	public static Level spawn = new SpawnLevel("/levels/spawn.png");
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		
		//add(new Spawner(16 * 16, 6 * 16, Spawner.Type.PARTICLE, 50, this));
	}

	protected void generateLevel() {
		
	}
	
	protected void loadLevel(String path) {
	}
	
	
	public void update() {
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
	}
	
	private void time() {
	} 
	
	public boolean tileCollision(double x, double y, double xa, double ya, int size) {
		boolean solid = false;
		
		for(int c = 0; c < 4; c++) {
			int xt = (((int)x + (int)xa) + c % 2 * size / 2 - 5) / 16;
			int yt = (((int)y + (int)ya) + c / 2 * size / 2 + 5) / 16;
			if(getTile(xt, yt).solid()) {
				solid = true;
			}
		}
		
		return solid;
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
		
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
	}
	
	public void add(Entity e) {
		entities.add(e);
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
