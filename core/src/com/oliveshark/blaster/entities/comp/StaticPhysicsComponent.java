package com.oliveshark.blaster.entities.comp;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.oliveshark.blaster.Box2d;
import com.oliveshark.blaster.entities.AbstractEntity;

import static com.oliveshark.blaster.Box2d.PPM;

public class StaticPhysicsComponent implements Component {

	private Body body;

	public StaticPhysicsComponent(AbstractEntity entity) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set((entity.getX() + entity.getWidth()/2)/PPM, (entity.getY() + entity.getHeight()/2)/PPM);

		body = Box2d.world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox((entity.getWidth()/2)/PPM, (entity.getHeight()/2)/PPM);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;

		body.createFixture(fixtureDef);

		shape.dispose();
	}

	@Override
	public void act(AbstractEntity entity) {
		// PASS
	}

	@Override
	public void dispose() {

	}
}
