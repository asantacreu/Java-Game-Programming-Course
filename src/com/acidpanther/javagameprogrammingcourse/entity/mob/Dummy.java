package com.acidpanther.javagameprogrammingcourse.entity.mob;

import com.acidpanther.javagameprogrammingcourse.graphics.Screen;

public class Dummy extends Mob{

	public Dummy(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		//sprite = Sprite.player_fprward;
	}
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		screen.renderMob(x, y, sprite, 0);
	}
	
}
