package com.oliveshark.blaster.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.oliveshark.blaster.entities.comp.StaticPhysicsComponent;
import com.oliveshark.blaster.util.CollisionUtil;

public class FixedObject extends AbstractEntity {

	public FixedObject(String path, Rectangle bounds) {
		super(path);
		setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
		components.add(new StaticPhysicsComponent(this));
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (!CollisionUtil.AABB(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), sprite.getBoundingRectangle())) {
			alive = false;
		}
	}
}
