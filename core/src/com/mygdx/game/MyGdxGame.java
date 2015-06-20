package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class MyGdxGame extends ApplicationAdapter {
	public static float INIT_BIRD_X = 50;
	public static float INIT_BIRD_Y = 200;
	public static float INIT_COLUMN_TIMER = 3;
	public static float COLUMN_TIMER_MULTIPLIER = 0.15f;
	public static float COLUMN_WIDTH = 50;
	
	private Bird bird;
	private List<Column> columns;
	private int score;
	private Rectangle screenRectangle;
	
	private Renderer renderer;
	private float columnTimer;
	
	@Override
	public void create() {
		renderer = Renderer.getInstance();
		columns = new ArrayList<Column>();
		screenRectangle = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		reset();
	}
	
	private void reset() {
		bird = new Bird(INIT_BIRD_X, INIT_BIRD_Y);
		columns.clear();
		score = 0;
		columnTimer = 0;
	}
	
	private void update(float delta) {
		Rectangle birdRectangle = bird.getRectangle();
		
		// Check out of bound
		if (!screenRectangle.contains(birdRectangle)) {
			reset();
			return;
		}
		
		// Check crash
		for (Column column : columns) {
			if (column.getUpperRectangle().overlaps(birdRectangle) ||
					column.getLowerRectangle().overlaps(birdRectangle)) {
				reset();
				return;
			}
		}
		
		// Generate Column
		columnTimer -= delta;
		if (columnTimer < 0) {
			columnTimer += INIT_COLUMN_TIMER - score * COLUMN_TIMER_MULTIPLIER;
			
			Column newColumn = new Column(screenRectangle.width, COLUMN_WIDTH, screenRectangle.height);
			columns.add(newColumn);
		}
		
		// Remove Column and update score
		for (int i = 0 ; i < columns.size(); i++) {
			Rectangle columnRectangle = columns.get(i).getLowerRectangle();
			
			if (columnRectangle.x + columnRectangle.width < 0) {
				columns.remove(i);
				score++;
				break;
			}
		}
		
		// Check click
		if (Gdx.input.justTouched()) {
			bird.flap();
		}
		
		// Update objects
		bird.update(delta);
		for (Column column : columns) {
			column.update(delta);
		}
	}
	
	private void draw() {
		renderer.clear();
		
		renderer.drawBird(bird);
		for (Column column : columns) {
			renderer.drawColumn(column);
		}
		renderer.drawString("Score: " + score, 0, screenRectangle.height);
	}
	
	@Override
	public void render() {
		update(Gdx.graphics.getRawDeltaTime());
		draw();
	}
}
