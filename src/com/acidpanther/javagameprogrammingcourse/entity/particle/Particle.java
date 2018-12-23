package com.acidpanther.javagameprogrammingcourse.entity.particle;

import java.util.ArrayList;
import java.util.List;

import com.acidpanther.javagameprogrammingcourse.entity.Entity;
import com.acidpanther.javagameprogrammingcourse.graphics.Screen;
import com.acidpanther.javagameprogrammingcourse.graphics.Sprite;

public class Particle extends Entity{
	
	private List<Particle> particles = new ArrayList<Particle>();
	private Sprite sprite;
	
	private int life;
	
	protected double xx, xa, yy, ya;
	
	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life;
		sprite = Sprite.particle_normal;
		
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
	}
	
	public Particle(int x, int y, int life, int amount) {
		this(x, y, life);
		
		for(int i = 0; i < amount - 1; i++) {
			particles.add(new Particle(x, y, life));
		}

		particles.add(this);
	}
	
	public void update() {
		xx += xa;
		yy += ya;
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) yy, sprite, true);
	}
	
}
