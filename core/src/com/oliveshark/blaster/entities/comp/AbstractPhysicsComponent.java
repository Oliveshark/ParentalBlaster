package com.oliveshark.blaster.entities.comp;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.physics.box2d.Body;
import com.oliveshark.blaster.Box2d;
import com.oliveshark.blaster.entities.Entity;

import box2dLight.Light;
import box2dLight.PointLight;

public abstract class AbstractPhysicsComponent implements Component {

	Entity entity;
	Body body;
	private final List<Light> lights = new ArrayList<>();

	public Body getBody() {
		return body;
	}

	public void addLight(PointLight light, float x, float y) {
		light.attachToBody(body, x, y);
	}

	public void addLight(Light light) {
		light.attachToBody(body);
	}

	private void clearLights() {
		for (Light light : lights) {
			light.remove();
		}
		lights.clear();
	}

	@Override
	public void dispose() {
		clearLights();
		Box2d.world.destroyBody(body);
	}
}
