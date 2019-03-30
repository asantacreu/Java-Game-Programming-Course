package com.acidpanther.javagameprogrammingcourse.entity.mob;

import com.acidpanther.javagameprogrammingcourse.graphics.AnimatedSprite;
import com.acidpanther.javagameprogrammingcourse.graphics.Screen;
import com.acidpanther.javagameprogrammingcourse.graphics.SpriteSheet;

public class Chaser extends Mob {
	
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	
	private AnimatedSprite animSprite = null;
	
	private int xa = 0;
	private int ya = 0;
	
	public Chaser(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		animSprite = down;
	}
	
	private void move() {
		xa = 0;
		ya = 0;
		
		Player player = level.getClientPlayer();
		if(x < player.getX()) {
			xa++;
		}else if(x > player.getX()) {
			xa--;
		}
		
		if(y < player.getY()) {
			ya++;
		}else if(y > player.getY()) {
			ya--;
		}
		
		walking = ((xa != 0) || (ya != 0)); 
		if(walking) {
			move(xa, ya);
		}
	}
	
	public void update() {
		move();
		
		if(walking){
			animSprite.update();
		} 
		else {
			animSprite.setFrame(0);
		}
		
		if(xa > 0) animSprite = right;
		else if(xa < 0) animSprite = left;
		if(ya > 0) animSprite = down;
		else if(ya < 0) animSprite = up;
		
				
	}
	
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob(x - 16, y - 16, this);
	}
	
	public int getCustomColor(int color) {
		if(color == 0xff472BBF) {
			color = 0xffBA0015;
		}
		return color;
	}
}
