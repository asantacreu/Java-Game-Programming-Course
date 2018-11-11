package com.acidpanther.javagameprogrammingcourse.entity.mob;

import com.acidpanther.javagameprogrammingcourse.graphics.Screen;
import com.acidpanther.javagameprogrammingcourse.graphics.Sprite;
import com.acidpanther.javagameprogrammingcourse.input.Keyboard;

public class Player extends Mob{
	
	private Keyboard input;
	
	
	public Player(Keyboard input) {
		this.input = input;
	}
	
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
	}
	
	public void update() {
		int xa = 0;
		int ya = 0;
		if(input.up) ya--;
		if(input.down) ya++;
		if(input.left) xa--;
		if(input.right) xa++;
		
		if((xa != 0) || (ya != 0)) move(xa, ya);
	}
	
	public void render(Screen screen) {
		Sprite playerSprite = Sprite.player;
		int halfPlayerSize = (playerSprite.SIZE / 2);
		screen.renderPlayer(x - halfPlayerSize, y - halfPlayerSize, playerSprite);
	}
	
}
