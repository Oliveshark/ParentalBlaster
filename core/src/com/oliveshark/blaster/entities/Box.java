package com.oliveshark.blaster.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import static com.oliveshark.blaster.util.CollisionUtil.AABB;

public class Box extends AbstractEntity {

	public Box(String path, Rectangle bounds) {
		super(path);
		setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
		setOrigin(getWidth()/2, getHeight()/2);
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (!AABB(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), sprite.getBoundingRectangle())) {
			alive = false;
		}
	}
}
