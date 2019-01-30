package com.oliveshark.blaster;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.oliveshark.blaster.stage.GameStage;

public class Game extends ApplicationAdapter {

	private GameStage currentStage;

	@Override
	public void create () {
		setupBox2d(); // Do this first

	    currentStage = new GameStage();
	    currentStage.build();
	    Gdx.input.setInputProcessor(currentStage);
	}

	private void setupBox2d() {
		Box2d.world = new World(new Vector2(0, -98f), true);
	}

	@Override
	public void render () {
	    Box2d.world.step(Gdx.graphics.getDeltaTime(), 6 ,2);

		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		currentStage.act(Gdx.graphics.getDeltaTime());

		currentStage.draw();
	}
	
	@Override
	public void dispose () {
		currentStage.dispose();
		Box2d.world.dispose();
	}
}
