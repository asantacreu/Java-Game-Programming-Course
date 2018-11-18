package com.acidpanther.javagameprogrammingcourse.level.tile;

import com.acidpanther.javagameprogrammingcourse.graphics.Screen;
import com.acidpanther.javagameprogrammingcourse.graphics.Sprite;

public class SpawnFloorTile extends Tile {

	public SpawnFloorTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

}
