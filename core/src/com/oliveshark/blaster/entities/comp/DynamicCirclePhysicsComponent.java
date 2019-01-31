package com.oliveshark.blaster.entities.comp;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.oliveshark.blaster.entities.Entity;

public class DynamicCirclePhysicsComponent extends DynamicPhysicsComponent {

	public DynamicCirclePhysicsComponent(Entity entity) {
		super(entity);
	}

	@Override
	protected Shape createShape() {
		CircleShape shape = new CircleShape();
		if (entity.getWidth() != entity.getHeight()) {
			throw new IllegalStateException("It's not a good idea to create a circle shape from a non square entity");
		}
		shape.setRadius(entity.getWidth() / 2);
		return shape;
	}
}
