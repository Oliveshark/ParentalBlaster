package com.oliveshark.blaster.entities.comp;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.oliveshark.blaster.entities.Entity;

public class CirclePhysicsComponent extends DynamicPhysicsComponent {

	public CirclePhysicsComponent(Entity entity) {
		super(entity);
	}

	@Override
	protected Shape createShape(Entity entity) {
		CircleShape shape = new CircleShape();
		if (entity.getWidth() != entity.getHeight()) {
			throw new IllegalStateException("It's not a good idea to create a circle shape from a non square entity");
		}
		shape.setRadius(entity.getWidth() / 2);
		return shape;
	}
}
