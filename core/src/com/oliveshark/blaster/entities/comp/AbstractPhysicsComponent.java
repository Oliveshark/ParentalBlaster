package com.oliveshark.blaster.entities.comp;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.physics.box2d.Body;
import com.oliveshark.blaster.Box2d;
import com.oliveshark.blaster.entities.AbstractEntity;

import box2dLight.Light;
import box2dLight.PointLight;

public abstract class AbstractPhysicsComponent implements Component {

	protected AbstractEntity entity;
	protected Body body;
	private final List<Light> lights = new ArrayList<>();

	public Body getBody() {
		return body;
	}

	@Override
	public void dispose() {
		clearLights();
		Box2d.world.destroyBody(body);
	}

	public void addLight(PointLight light, float x, float y) {
		light.attachToBody(body, x, y);
	}

	public void addLight(Light light) {
		light.attachToBody(body);
	}

	public void clearLights() {
		lights.forEach(Light::remove);
		lights.clear();
	}
}
