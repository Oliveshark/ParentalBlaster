package com.oliveshark.blaster.entities.comp;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.oliveshark.blaster.entities.Entity;


public abstract class DynamicPhysicsComponent extends AbstractPhysicsComponent {

	public DynamicPhysicsComponent(Entity entity) {
		super(entity);
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set((entity.getX() + entity.getWidth()/2), (entity.getY() + entity.getHeight()/2));

		Shape shape = createShape();
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0.4f;

		createPhysicsBody(bodyDef, fixtureDef);

		shape.dispose();

		body.applyLinearImpulse(new Vector2(100, 100), body.getWorldCenter(), true);
		body.applyLinearImpulse(new Vector2(-10, 10), new Vector2(1, 1), true);
	}

	protected abstract Shape createShape();

	@Override
	public void act(float deltatime) {
		entity.setPosition(body.getPosition().x - (entity.getWidth()/2), body.getPosition().y - entity.getHeight()/2);
		entity.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
	}
}
