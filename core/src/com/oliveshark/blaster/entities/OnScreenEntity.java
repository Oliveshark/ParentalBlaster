package com.oliveshark.blaster.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import static com.oliveshark.blaster.util.CollisionUtil.AABB;

/**
 * An entity that 'dies' if it's not on screen
 */
public class OnScreenEntity extends Entity {

	public OnScreenEntity(Rectangle bounds) {
		setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
		setOrigin(getWidth()/2, getHeight()/2);
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (!AABB(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), getBounds())) {
			alive = false;
		}
	}
}
