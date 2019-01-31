package com.oliveshark.blaster.entities.comp;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.oliveshark.blaster.entities.Entity;

public class StaticTrianglePhysicsComponent extends StaticPhysicsComponent {

	public StaticTrianglePhysicsComponent(Entity entity) {
		super(entity);
	}

	@Override
	Shape createShape(Entity entity) {
		PolygonShape shape = new PolygonShape();
		shape.set(new Vector2[] {
				new Vector2(0, entity.getHeight()/2),
				new Vector2(-entity.getWidth()/2, -entity.getHeight()/2),
				new Vector2(entity.getWidth()/2, -entity.getHeight()/2),
		});
		return shape;
	}
}
