package com.acidpanther.javagameprogrammingcourse.entity.spawner;

import com.acidpanther.javagameprogrammingcourse.entity.particle.Particle;
import com.acidpanther.javagameprogrammingcourse.entity.spawner.Spawner.Type;
import com.acidpanther.javagameprogrammingcourse.level.Level;

public class ParticleSpawner extends Spawner {
	
	public ParticleSpawner(int x, int y, int life, int amount, Level level) {
		super(x, y, Type.PARTICLE, amount, level);
		for(int i = 0; i < amount; i++) {
			level.add(new Particle(x, y, life));
		}
	}

}
