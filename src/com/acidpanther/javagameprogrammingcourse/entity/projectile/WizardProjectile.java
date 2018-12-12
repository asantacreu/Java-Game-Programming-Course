package com.acidpanther.javagameprogrammingcourse.entity.projectile;

import com.acidpanther.javagameprogrammingcourse.graphics.Sprite;

public class WizardProjectile extends Projectile {

	public static final int FIRE_RATE = 15;
	
	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 100;
		speed = 4;
		damage = 20;
		
		sprite = Sprite.projectileWizard;
		
		nx = Math.cos(angle) * speed;
		ny = Math.sin(angle) * speed;
	}
}
