package com.oliveshark.blaster.entities.comp;

import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.oliveshark.blaster.entities.Entity;

public class DynamicRectanglePhysicsComponent extends DynamicPhysicsComponent {

	public DynamicRectanglePhysicsComponent(Entity entity) {
		super(entity);
	}

	@Override
	protected Shape createShape() {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(entity.getWidth()/2, entity.getHeight()/2);
		return shape;
	}
}
