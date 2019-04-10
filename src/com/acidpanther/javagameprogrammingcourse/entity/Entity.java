package com.acidpanther.javagameprogrammingcourse.entity;

import java.util.Random;

import com.acidpanther.javagameprogrammingcourse.graphics.Screen;
import com.acidpanther.javagameprogrammingcourse.level.Level;

public abstract class Entity {

	protected double x, y;
	
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void init(Level level) {
		this.level = level;
	}
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
}
