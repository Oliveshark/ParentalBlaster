package com.oliveshark.blaster.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.oliveshark.blaster.Box2d;
import com.oliveshark.blaster.util.CollisionUtil;
import com.oliveshark.blaster.util.Vector2d;

import static com.oliveshark.blaster.Box2d.PPM;

public class Target extends AbstractEntity {

    private Vector2d<Double> velocity = new Vector2d<>(0d, 0d);
    private Body body;

    public Target(String path) {
        super(path);
        createPhysicsBody();
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                alive = false;
                return true;
            }
        });
        setOrigin(getWidth()/2, getHeight()/2);
    }

    private void createPhysicsBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(getX()/PPM, getY()/PPM);

		body = Box2d.world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox((getWidth()/ PPM)/2, (getHeight()/ PPM)/2);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0.4f;

		body.createFixture(fixtureDef);

		shape.dispose();
    }

    public void applyImpulse(float x, float y) {
    	body.applyLinearImpulse(x, y, (getWidth()/PPM), (getHeight()/PPM), true);
	}

	public void applyForce(float x, float y) {
    	body.applyForce(x, y, 1, 1, true);
	}

	@Override
    public void act(float delta) {
        setPosition(body.getPosition().x * PPM, body.getPosition().y * PPM);
        setRotation(body.getAngle() * MathUtils.radiansToDegrees);
		super.act(delta);

		if (!CollisionUtil.AABB(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), sprite.getBoundingRectangle())) {
			alive = false;
		}
    }

	@Override
	public boolean remove() {
		Box2d.world.destroyBody(body);
		return super.remove();
	}
}
