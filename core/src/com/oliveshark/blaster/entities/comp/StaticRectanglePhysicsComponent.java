package com.oliveshark.blaster.entities.comp;

import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.oliveshark.blaster.entities.Entity;

public class StaticRectanglePhysicsComponent extends StaticPhysicsComponent {

	public StaticRectanglePhysicsComponent(Entity entity) {
		super(entity);
	}

	@Override
	Shape createShape(Entity entity) {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(entity.getWidth()/2, entity.getHeight()/2);
		return shape;
	}
}
