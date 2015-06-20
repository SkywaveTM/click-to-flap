package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public class Bird implements Updatable {
	public static final float INIT_SIZE = 30;
	public static final float GRAVITY = 30;
	public static final float FLAP_POWER = 13;
	
	private float x;
	private float y;
	private float dy;
	private float width;
	private float height;
	
	public Bird(float x, float y) {
		this.x = x;
		this.y = y;
		dy = 0;
		width = INIT_SIZE;
		height = INIT_SIZE;
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
	
	public void flap() {
		dy = FLAP_POWER;
	}
	
	@Override
	public void update(float timeDelta) {
		y += dy;
		dy -= GRAVITY * timeDelta;
	}
}
