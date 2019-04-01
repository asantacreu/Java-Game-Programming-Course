package com.acidpanther.javagameprogrammingcourse.level;

import com.acidpanther.javagameprogrammingcourse.graphics.Screen;

import java.util.ArrayList;
import java.util.List;

import com.acidpanther.javagameprogrammingcourse.entity.Entity;
import com.acidpanther.javagameprogrammingcourse.entity.mob.Player;
import com.acidpanther.javagameprogrammingcourse.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Player> players = new ArrayList<Player>();
	
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
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for(int i = 0; i < players.size(); i++) {
			players.get(i).update();
		}
		clearEntities();
	}
	
	private void clearEntities() {
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i).isRemoved()){
				entities.remove(i);
			}		
		}
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).isRemoved()){
				players.remove(i);
			}		
		}
	}
	
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		
		for(int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 4;
			int yt = (y - c / 2 * size + yOffset) >> 4;
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
		for(int i = 0; i < players.size(); i++) {
			players.get(i).render(screen);
		}
	}
	
	public void add(Entity e) {
		e.init(this);
		if(e instanceof Player) {
			players.add((Player) e);
		} else {
			entities.add(e);
		}
	}
	
	public List<Player> getPlayer() {
		return players;
	}
	
	public Player getPlayerAt(int index) {
		return players.get(index);
	}
	
	public Player getClientPlayer() {
		return players.get(0);
	}
	
	public List<Player> getPlayers(Entity e, int radius) {
		List<Player> result = new ArrayList<Player>();
		
		int ex = e.getX();
		int ey = e.getY();
		for(int i = 0; i< players.size(); i++) {
			Player player = players.get(i);
			int x = player.getX();
			int y = player.getY();
			
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt(dx*dx + dy*dy);
			if(distance <= radius) {
				result.add(player);
			}
		}
		
		return result;
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
