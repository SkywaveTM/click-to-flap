package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class Renderer {
	public static final Color BIRD_COLOR = new Color(0.2f, 0.3f, 0.5f, 1f);
	public static final Color COLUMN_COLOR = Color.WHITE;
	
	private static Renderer SINGLETON;
	
	private ShapeRenderer shape;
	private SpriteBatch batch;
	private BitmapFont font;
	
	private Renderer() {
		shape = new ShapeRenderer();
		batch = new SpriteBatch();
		font = new BitmapFont();
	}
	
	public static Renderer getInstance() {
		if (SINGLETON == null) {
			SINGLETON = new Renderer();
		}
		
		return SINGLETON;
	}
	
	public void clear() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	public void drawBird(Bird bird) {
		drawRectangle(bird.getRectangle(), BIRD_COLOR);
	}
	
	public void drawColumn(Column column) {
		drawRectangle(column.getUpperRectangle(), COLUMN_COLOR);
		drawRectangle(column.getLowerRectangle(), COLUMN_COLOR);
	}
	
	public void drawString(String text, float x, float y) {
		batch.begin();
		font.draw(batch, text, x, y);
		batch.end();
	}
	
	public void drawRectangle(Rectangle rectangle, Color color) {
		shape.setColor(color);
		shape.begin(ShapeType.Filled);
		shape.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		shape.end();
	}
}
