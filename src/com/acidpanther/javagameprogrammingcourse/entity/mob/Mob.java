package com.acidpanther.javagameprogrammingcourse.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.acidpanther.javagameprogrammingcourse.entity.Entity;
import com.acidpanther.javagameprogrammingcourse.entity.projectile.Projectile;
import com.acidpanther.javagameprogrammingcourse.graphics.Screen;
import com.acidpanther.javagameprogrammingcourse.graphics.Sprite;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	protected boolean walking = false;
	
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	
	public void move(int xa, int ya) {
		if(xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		
		if(xa > 0) dir = 1;
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;
		if(ya < 0) dir = 0;
		
		if(!collision(xa, ya)) {
			x += xa;
			y += ya;
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
	
	private boolean collision(int xa, int ya) {
		boolean solid = false;
		
		for(int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
			int yt = ((y + ya) + c / 2 * 12 + 3) / 16;
			if(level.getTile(xt, yt).solid()) solid = true;
		}
		
		return solid;
	}
	
}
