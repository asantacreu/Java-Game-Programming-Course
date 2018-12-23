package com.acidpanther.javagameprogrammingcourse.entity.projectile;

import com.acidpanther.javagameprogrammingcourse.entity.Entity;
import com.acidpanther.javagameprogrammingcourse.entity.particle.Particle;
import com.acidpanther.javagameprogrammingcourse.graphics.Screen;
import com.acidpanther.javagameprogrammingcourse.graphics.Sprite;
import com.acidpanther.javagameprogrammingcourse.level.tile.Tile;

public abstract class Projectile extends Entity {

	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	protected double nx, ny;
	protected double distance;
	protected double speed;
	protected double range;
	protected double damage;
	
	
	public Projectile(int x, int y, double dir) {
		this.x = x;
		this.y = y;
		xOrigin = x;
		yOrigin = y;
		angle = dir;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public int getSpriteSize() {
		return sprite.getWidth();
	}
	
	public void update() {
		if(level.tileCollision(x, y, nx, ny, 7/*sprite.SIZE*/)) {
			Particle p = new Particle((int) x, (int) y, 50, 500);
			level.add(p);
			remove();
		}else {
			move();	
		}
		
	}
	
	protected void move() {
		x += nx;
		y += ny;
		
		calculateDistance();
		if(distance > range) {
			remove();
		}
	}
	
	private void calculateDistance() {
		distance = Math.sqrt(Math.abs((xOrigin - x)*(xOrigin - x) + (yOrigin - y)*(yOrigin - y)));
	}
	
	public void render(Screen screen) {
		screen.renderProjectile((int)x - 12, (int)y - 2, this);
	}
}
