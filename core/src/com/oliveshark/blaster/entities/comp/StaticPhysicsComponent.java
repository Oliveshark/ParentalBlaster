package com.oliveshark.blaster.entities.comp;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.oliveshark.blaster.entities.Entity;

public abstract class StaticPhysicsComponent extends AbstractPhysicsComponent {

	public StaticPhysicsComponent(Entity entity) {
		super(entity);

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set((entity.getX() + entity.getWidth()/2), (entity.getY() + entity.getHeight()/2));


		Shape shape = createShape(entity);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;

		createPhysicsBody(bodyDef, fixtureDef);

		shape.dispose();
	}

	abstract Shape createShape(Entity entity);
}
