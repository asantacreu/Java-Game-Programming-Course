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
		
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 2.0;
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
			move(xx + xa, (yy + ya) + (zz + za));
			
		} 
		else {
			remove();
		}
		
	}
	
	private void move(double x, double y) {
		if(collision(x, y)) {
			xa *= -0.5;
			ya *= -0.5;
			za *= -0.5;
		}
		
		xx += xa;
		yy += ya;
		zz += za;
	}
	
	public boolean collision(double x, double y) {
		boolean solid = false;
		
		for(int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * 16) / 16;
			double yt = (y - c / 2 * 16) / 16;
			
			int ix;
			if(c % 2 == 0) {
				ix = (int) Math.floor(xt);
			}
			else {
				ix = (int) Math.ceil(xt);
			}
			
			int iy;
			if(c / 2 == 0) {
				iy = (int) Math.floor(yt);
			}
			else {
				iy = (int) Math.ceil(yt);
			}
			if(level.getTile(ix, iy).solid()) {
				solid = true;
			}
		}
		
		return solid;
	}

	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) yy - (int) zz, sprite, true);
	}
	
}
