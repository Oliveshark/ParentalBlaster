package com.oliveshark.blaster.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.oliveshark.blaster.entities.comp.StaticPhysicsComponent;

import static com.oliveshark.blaster.util.CollisionUtil.AABB;

public class FixedObject extends AbstractEntity {

	public FixedObject(String path, Rectangle bounds) {
		super(path);
		setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
		components.add(new StaticPhysicsComponent(this));
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (!AABB(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), sprite.getBoundingRectangle())) {
			alive = false;
		}
	}
}
