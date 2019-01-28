package com.oliveshark.blaster.entities.comp;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.oliveshark.blaster.Box2d;
import com.oliveshark.blaster.entities.AbstractEntity;

import static com.oliveshark.blaster.Box2d.PPM;

public class DynamicPhysicsComponent implements Component {

	private Body body;

	public DynamicPhysicsComponent(AbstractEntity entity) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set((entity.getX() + entity.getWidth()/2)/PPM, (entity.getY() + entity.getHeight()/2)/PPM);

		body = Box2d.world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox((entity.getWidth()/ PPM)/2, (entity.getHeight()/ PPM)/2);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0.4f;

		body.createFixture(fixtureDef);

		shape.dispose();

		body.applyLinearImpulse(100, 100, (entity.getWidth()/PPM), (entity.getHeight()/PPM), true);
		body.applyForce(-100, 100, 1, 1, true);
	}

	@Override
	public void act(AbstractEntity entity) {
		entity.setPosition(body.getPosition().x * PPM - (entity.getWidth()/2), body.getPosition().y * PPM - entity.getHeight()/2);
		entity.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
	}

	@Override
	public void dispose() {
		Box2d.world.destroyBody(body);
	}
}
