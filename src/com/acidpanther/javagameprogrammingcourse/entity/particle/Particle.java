package com.acidpanther.javagameprogrammingcourse.entity.particle;

import com.acidpanther.javagameprogrammingcourse.entity.Entity;
import com.acidpanther.javagameprogrammingcourse.graphics.Screen;
import com.acidpanther.javagameprogrammingcourse.graphics.Sprite;

public class Particle extends Entity{
	
	private Sprite sprite;
	
	private int life;
	private int time = 0;
	
	protected double xx, yy, zz;
	protected double xa, ya, za;
	
	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (random.nextInt(20) - 10);
		sprite = Sprite.particle_normal;
		
		this.xa = random.nextGaussian() + 1.8;
		if(xa < 0) xa = 0.1;
		this.ya = random.nextGaussian();
		this.zz = 2.0;
	}
	
	public void update() {
		time++;
		if(time >= Integer.MAX_VALUE - 1) time = 0;
		
		if(time < life) {
			za -= 0.1;
			if(zz < 0) {
				zz = 0;
				za *= -0.5;
				xa *= 0.4;
				ya *= 0.4;
			}
			
			xx += xa;
			yy += ya;
			zz += za;
		} else {
			remove();
		}
		
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int) xx - 5, (int) yy - (int) zz, sprite, true);
	}
	
}
