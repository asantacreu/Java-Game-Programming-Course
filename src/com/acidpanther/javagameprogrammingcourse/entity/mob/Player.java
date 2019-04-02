package com.acidpanther.javagameprogrammingcourse.entity.mob;

import com.acidpanther.javagameprogrammingcourse.Game;
import com.acidpanther.javagameprogrammingcourse.entity.projectile.WizardProjectile;
import com.acidpanther.javagameprogrammingcourse.graphics.AnimatedSprite;
import com.acidpanther.javagameprogrammingcourse.graphics.Screen;
import com.acidpanther.javagameprogrammingcourse.graphics.SpriteSheet;
import com.acidpanther.javagameprogrammingcourse.input.Keyboard;
import com.acidpanther.javagameprogrammingcourse.input.Mouse;

public class Player extends Mob{
	
	private Keyboard input;
	private int anim = 0;
	private boolean walking = false;
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);
	
	private AnimatedSprite animSprite = null;
	
	private int fireRate = 0;
	
	public Player(Keyboard input) {
		this.input = input;
		animSprite = down;
	}
	
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		animSprite = down;
	}
	
	public void update() {
		super.update();
		if(walking){
			animSprite.update();
		} 
		else {
			animSprite.setFrame(0);
		}
		
		int xa = 0;
		int ya = 0;
		if(anim< 7500) { 
			anim++;
		}
		else { 
			anim  = 0;
		}
		if(input.up) {
			ya-=2;
			animSprite = up;
		}
		else if(input.down) {
			ya+=2;
			animSprite = down;
		}
		
		if(input.left) {
			xa-=2;
			animSprite = left;
		}
		else if(input.right) {
			xa+=2;
			animSprite = right;
		}
		
		walking = ((xa != 0) || (ya != 0)); 
		if(walking) {
			move(xa, ya);
		}
		updateShooting();
	}

	private void updateShooting() {
		if(fireRate > 0) {
			fireRate--;
		}
		
		if(Mouse.getButton() == 1 && fireRate <= 0) {
			double dx = (Mouse.getX() - (Game.getScreenWidth() / 2));
			double dy = (Mouse.getY() - (Game.getScreenHeight() / 2));
			double theta = Math.atan2(dy, dx);
			
			fireRate = WizardProjectile.FIRE_RATE;
			
			WizardProjectile projectile = new WizardProjectile(x, y , theta);
			projectile.init(level);
			shoot(projectile);
		}
	}
	
	public void render(Screen screen) {
		super.render(screen);
		
		sprite = animSprite.getSprite();
		int halfPlayerSize = (sprite.getWidth() / 2);
		screen.renderMob(x - halfPlayerSize, y - halfPlayerSize, this);
	}
	
}
