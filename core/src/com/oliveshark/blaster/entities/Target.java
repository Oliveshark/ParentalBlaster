package com.oliveshark.blaster.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.oliveshark.blaster.entities.comp.DynamicPhysicsComponent;
import com.oliveshark.blaster.util.CollisionUtil;

public class Target extends AbstractEntity {

    public Target(String path) {
        super(path);
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                alive = false;
                return true;
            }
        });
        setOrigin(getWidth()/2, getHeight()/2);
        components.add(new DynamicPhysicsComponent(this));
    }

    public void applyImpulse(float x, float y) {
	}

	public void applyForce(float x, float y) {
	}

	@Override
    public void act(float delta) {
		super.act(delta);

		if (!CollisionUtil.AABB(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), sprite.getBoundingRectangle())) {
			alive = false;
		}
    }

	@Override
	public boolean remove() {
		return super.remove();
	}
}
