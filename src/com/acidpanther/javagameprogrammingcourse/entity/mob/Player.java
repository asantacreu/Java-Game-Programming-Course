package com.acidpanther.javagameprogrammingcourse.entity.mob;

import com.acidpanther.javagameprogrammingcourse.graphics.Screen;
import com.acidpanther.javagameprogrammingcourse.graphics.Sprite;
import com.acidpanther.javagameprogrammingcourse.input.Keyboard;
import com.acidpanther.javagameprogrammingcourse.input.Mouse;

public class Player extends Mob{
	
	private Keyboard input;
	private Sprite sprite;
	private int flipSprite;
	private int anim = 0;
	private boolean walking = false;
	
	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_down;
	}
	
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_down;
	}
	
	public void update() {
		int xa = 0;
		int ya = 0;
		if(anim< 7500) { 
			anim++;
		}
		else { 
			anim  = 0;
		}
		if(input.up) ya--;
		if(input.down) ya++;
		if(input.left) xa--;
		if(input.right) xa++;
		
		walking = ((xa != 0) || (ya != 0)); 
		if(walking) {
			move(xa, ya);
		}
		
		updateShooting();
	}

	private void updateShooting() {
		if(Mouse.getButton() == 1) {
			double dx = (Mouse.getX() - 300/2);
			double dy = (Mouse.getY() - 168/2);
			double theta = Math.atan2(dy, dx);
			shoot(x, y, theta);
		}
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
				sprite = Sprite.player_up;
				if(walking) {
					if(anim % 20 > 10) {
						sprite = Sprite.player_up_1;
					}
					else {
						sprite = Sprite.player_up_2;
					}
				}
				break;
			case 1: 
				sprite = Sprite.player_right;
				if(walking) {
					if(anim % 20 > 10) {
						sprite = Sprite.player_right_1;
					}
					else {
						sprite = Sprite.player_right_2;
					}
				}
				break;
			case 2: 
				sprite = Sprite.player_down;
				if(walking) {
					if(anim % 20 > 10) {
						sprite = Sprite.player_down_1;
					}
					else {
						sprite = Sprite.player_down_2;
					}
				}
				break;
			case 3: 
				sprite = Sprite.player_right;
				if(walking) {
					if(anim % 20 > 10) {
						sprite = Sprite.player_right_1;
					}
					else {
						sprite = Sprite.player_right_2;
					}
				}
				flipSprite = 1;
				break;
		}
	}
	
}
