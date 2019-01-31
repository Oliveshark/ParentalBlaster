package com.oliveshark.blaster.entities.comp;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.oliveshark.blaster.Box2d;
import com.oliveshark.blaster.entities.Entity;

import box2dLight.Light;
import box2dLight.PointLight;

public abstract class AbstractPhysicsComponent implements Component {

	Entity entity;
	Fixture fixture;
	Body body;

	private final List<Light> lights = new ArrayList<>();

	public AbstractPhysicsComponent(Entity entity) {
		this.entity = entity;
	}

	public Body getBody() {
		return body;
	}

	public void addLight(PointLight light, float x, float y) {
		light.attachToBody(body, x, y);
		lights.add(light);
	}

	public void addLight(Light light) {
		light.attachToBody(body);
		lights.add(light);
	}

	void createPhysicsBody(BodyDef bodyDef, FixtureDef fixtureDef) {
		if (body != null) {
			destroyBody();
		}
		body = Box2d.world.createBody(bodyDef);
		fixture = body.createFixture(fixtureDef);
		fixture.setUserData(entity);
	}

	private void clearLights() {
		for (Light light : lights) {
			light.remove();
		}
		lights.clear();
	}

	private void destroyBody() {
		Box2d.world.destroyBody(body);
		body = null;
		fixture = null;
	}

	@Override
	public void dispose() {
		destroyBody();
		clearLights();
	}
}
