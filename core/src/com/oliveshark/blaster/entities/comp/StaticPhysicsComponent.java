package com.oliveshark.blaster.entities.comp;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.oliveshark.blaster.entities.Entity;

public class StaticPhysicsComponent extends AbstractPhysicsComponent {

	public StaticPhysicsComponent(Entity entity) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set((entity.getX() + entity.getWidth()/2), (entity.getY() + entity.getHeight()/2));

		PolygonShape shape = new PolygonShape();
		shape.setAsBox((entity.getWidth()/2), (entity.getHeight()/2));
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;

		createPhysicsBody(bodyDef, fixtureDef);

		shape.dispose();
	}
}
