package com.acidpanther.javagameprogrammingcourse.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.acidpanther.javagameprogrammingcourse.entity.Entity;
import com.acidpanther.javagameprogrammingcourse.entity.projectile.Projectile;
import com.acidpanther.javagameprogrammingcourse.graphics.Screen;
import com.acidpanther.javagameprogrammingcourse.graphics.Sprite;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected boolean moving = false;
	protected boolean walking = false;
	
	protected enum Direction {
		UP, DOWN, LEFT, RIGHT
	}
	
	protected Direction dir;
	
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	
	public void move(double xa, double ya) {
		if(xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if(xa > 0) dir = Direction.RIGHT;
		if(xa < 0) dir = Direction.LEFT;
		if(ya > 0) dir = Direction.DOWN;
		if(ya < 0) dir = Direction.UP;
		
		while(xa != 0) {
			if(Math.abs(xa) > 1) {
				if(!collision(abs(xa), ya)) {
					x += abs(xa);
				}
				xa -= abs(xa);
			}else {
				if(!collision(abs(xa), ya)) {
					x += xa;
				}
				xa = 0;
			}
		}
		
		while(ya != 0) {
			if(Math.abs(ya) > 1) {
				if(!collision(xa, abs(ya))) {
					y += abs(ya);
				}
				ya -= abs(ya);
			}else {
				if(!collision(xa, abs(ya))) {
					y += ya;
				}
				ya = 0;
			}
		}
	}
	
	private int abs(double value) {
		if(value < 0) {
			return -1;
		}
		else {
			//Are we sure that when value == 0 we want return 1??
			return 1;
		}
				
	}
	
	public void update() {
		clearProjectiles();
		for(int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
	}
	
	private void clearProjectiles() {
		for(int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			if(p.isRemoved()) {
				projectiles.remove(i);
			}
		}
	}
	
	protected void shoot(Projectile p) {
		projectiles.add(p);
	}
	
	public void render(Screen screen) {
		for(int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
	}
	
	private boolean collision(double xa, double ya) {
		boolean solid = false;
		
		for(int c = 0; c < 4; c++) {
			double xt = ((x + xa) - c % 2 * 16) / 16;
			double yt = ((y + ya) - c / 2 * 16) / 16;
			
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
			
			if(level.getTile(ix, iy).solid()) solid = true;
		}
		
		return solid;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public int getCustomColor(int color) {
		return color;
	}
	
}
