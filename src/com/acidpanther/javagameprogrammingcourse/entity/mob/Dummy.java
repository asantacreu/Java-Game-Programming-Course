package com.acidpanther.javagameprogrammingcourse.entity.mob;

import com.acidpanther.javagameprogrammingcourse.graphics.AnimatedSprite;
import com.acidpanther.javagameprogrammingcourse.graphics.Screen;
import com.acidpanther.javagameprogrammingcourse.graphics.SpriteSheet;

public class Dummy extends Mob{

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	
	private AnimatedSprite animSprite = null;
	
	private int time = 0;
	private int xa = 0;
	private int ya = 0;
	
	public Dummy(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		animSprite = down;
	}
	
	public void update() {
		time++;
		
		if(time % (random.nextInt(50) + 30) == 0) {
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;
			if(random.nextInt(4) == 0) {
				xa = 0;
				ya = 0;
			}
		}
		
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
		
		walking = ((xa != 0) || (ya != 0)); 
		if(walking) {
			move(xa, ya);
		}
	}
	
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob(x, y, this);
	}
	
}
