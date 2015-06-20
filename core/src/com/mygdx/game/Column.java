package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public class Column implements Updatable {
	public static final float GAP_MIN = 180;
	public static final float GAP_MAX = 220;
	public static final float dx = 300;
	
	private float x;
	private float upperY;
	private float lowerY;
	private float upperHeight;
	private float lowerHeight;
	
	private float width;
	
	public Column(float x, float width, float height) {
		float gap = GAP_MIN + (float) Math.random() * (GAP_MAX - GAP_MIN);

		this.x = x;
		this.width = width;
		
		lowerY = 0;
		lowerHeight = (float)Math.random() * (height - gap * 2) + gap;
		upperY = lowerY + lowerHeight + gap;
		upperHeight = height - upperY;
	}
	
	public Rectangle getUpperRectangle() {
		return new Rectangle(x, upperY, width, upperHeight);
	}
	
	public Rectangle getLowerRectangle() {
		return new Rectangle(x, lowerY, width, lowerHeight);
	}

	@Override
	public void update(float timeDelta) {
		x -= dx * timeDelta;
	}
}
