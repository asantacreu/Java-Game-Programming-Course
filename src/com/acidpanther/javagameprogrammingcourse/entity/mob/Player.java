package com.acidpanther.javagameprogrammingcourse.entity.mob;

import com.acidpanther.javagameprogrammingcourse.graphics.Screen;
import com.acidpanther.javagameprogrammingcourse.graphics.Sprite;
import com.acidpanther.javagameprogrammingcourse.input.Keyboard;

public class Player extends Mob{
	
	private Keyboard input;
	private Sprite sprite;
	private int flipSprite;
	
	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_up;
	}
	
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_up;
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
		updateSprite();
		
		int halfPlayerSize = (sprite.SIZE / 2);
		screen.renderPlayer(x - halfPlayerSize, y - halfPlayerSize, sprite, flipSprite);
	}
	
	private void updateSprite() {
		flipSprite = 0;
		
		switch(dir) {
			case 0: 
				sprite = Sprite.player_down;
				break;
			case 1: 
				sprite = Sprite.player_right;
				break;
			case 2: 
				sprite = Sprite.player_up;
				break;
			case 3: 
				sprite = Sprite.player_right;
				flipSprite = 1;
				break;
		}
	}
	
}
