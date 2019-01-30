package com.oliveshark.blaster.entities.comp;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.oliveshark.blaster.Box2d;
import com.oliveshark.blaster.entities.Entity;


public class DynamicPhysicsComponent extends AbstractPhysicsComponent {

	public DynamicPhysicsComponent(Entity entity) {
		this.entity = entity;
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set((entity.getX() + entity.getWidth()/2), (entity.getY() + entity.getHeight()/2));

		body = Box2d.world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox((entity.getWidth())/2, (entity.getHeight())/2);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0.4f;

		body.createFixture(fixtureDef);

		shape.dispose();

		body.applyLinearImpulse(new Vector2(100, 100), body.getWorldCenter(), true);
		body.applyLinearImpulse(new Vector2(-10, 10), new Vector2(1, 1), true);
	}

	@Override
	public void act(float deltatime) {
		entity.setPosition(body.getPosition().x - (entity.getWidth()/2), body.getPosition().y - entity.getHeight()/2);
		entity.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
	}
}
