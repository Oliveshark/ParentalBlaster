package com.oliveshark.blaster.entities.comp;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface Component {

	default void act(float deltatime) {
	}

	default void draw(Batch batch) {

	}

	void dispose();
}
