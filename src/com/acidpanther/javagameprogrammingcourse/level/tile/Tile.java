package com.acidpanther.javagameprogrammingcourse.level.tile;

import com.acidpanther.javagameprogrammingcourse.graphics.Screen;
import com.acidpanther.javagameprogrammingcourse.graphics.Sprite;
import com.acidpanther.javagameprogrammingcourse.level.tile.spawnLevel.SpawnGrassTile;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile flower = new FlowerTile(Sprite.flower);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public static Tile spawnGrass = new SpawnGrassTile(Sprite.spawnGrass);
	public static Tile spawnHedge = new SpawnHedgeTile(Sprite.spawnHedge);
	public static Tile spawnWater = new SpawnWaterTile(Sprite.spawnWater);
	public static Tile spawnWall1 = new SpawnWallTile(Sprite.spawnWall1);
	public static Tile spawnWall2 = new SpawnWallTile(Sprite.spawnWall2);
	public static Tile spawnFloor = new SpawnFloorTile(Sprite.spawnFloor);
	
	public final static int colorspawnGrass = 0xff00ff00;
	public final static int colorspawnHedge = 0; //unused
	public final static int colorspawnWater = 0; //unused
	public final static int colorspawnWall1 = 0xff808080;
	public final static int colorspawnWall2 = 0xff303030;
	public final static int colorspawnFloor = 0xff724715;
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
	}
	
	public boolean solid() {
		return false;
	}
}
