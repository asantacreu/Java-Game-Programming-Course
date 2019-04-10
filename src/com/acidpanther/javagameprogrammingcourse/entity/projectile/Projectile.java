package com.acidpanther.javagameprogrammingcourse.entity.projectile;

import com.acidpanther.javagameprogrammingcourse.entity.Entity;
import com.acidpanther.javagameprogrammingcourse.entity.spawner.ParticleSpawner;
import com.acidpanther.javagameprogrammingcourse.graphics.Screen;
import com.acidpanther.javagameprogrammingcourse.graphics.Sprite;

public abstract class Projectile extends Entity {

	protected final double xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	protected double nx, ny;
	protected double distance;
	protected double speed;
	protected double range;
	protected double damage;
	
	
	public Projectile(double x, double y, double dir) {
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
		if(level.tileCollision((int)(x + nx), (int)(y + ny), 7, 5, 4)) {
			level.add(new ParticleSpawner((int) x, (int) y, 44, 50, level));
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
