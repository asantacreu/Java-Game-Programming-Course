package com.acidpanther.javagameprogrammingcourse.entity.mob;

import java.util.List;

import com.acidpanther.javagameprogrammingcourse.graphics.AnimatedSprite;
import com.acidpanther.javagameprogrammingcourse.graphics.Screen;
import com.acidpanther.javagameprogrammingcourse.graphics.SpriteSheet;

public class Chaser extends Mob {
	
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	
	private AnimatedSprite animSprite = null;
	
	private double xa = 0;
	private double ya = 0;
	private double speed = 0.8;
	
	public Chaser(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		animSprite = down;
	}
	
	private void move() {
		xa = 0;
		ya = 0;
		
		List<Player> players = level.getPlayers(this, 50);
		if(players.size() > 0) {
			Player player = players.get(0);
			if(x < player.getX()) {
				xa+=speed;
			}else if(x > player.getX()) {
				xa-=speed;
			}
			
			if(y < player.getY()) {
				ya+=speed;
			}else if(y > player.getY()) {
				ya-=speed;
			}
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
		screen.renderMob((int) (x - 16), (int) (y - 16), this);
	}
	
	public int getCustomColor(int color) {
		if(color == 0xff472BBF) {
			color = 0xffBA0015;
		}
		return color;
	}
}
