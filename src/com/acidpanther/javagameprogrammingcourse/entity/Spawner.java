package com.acidpanther.javagameprogrammingcourse.entity;

import java.util.ArrayList;
import java.util.List;

import com.acidpanther.javagameprogrammingcourse.entity.particle.Particle;
import com.acidpanther.javagameprogrammingcourse.level.Level;

public class Spawner extends Entity {
	
	public enum Type {
		MOB, PARTICLE;
	}
	
	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		for(int i = 0; i < amount; i++) {
			if(type == Type.PARTICLE) {
				level.add(new Particle(x, y, 50));
			}
		}
	}
}
