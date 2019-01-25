package com.oliveshark.blaster.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class AbstractEntity extends Actor {

    protected final Sprite sprite;
    protected boolean alive = true;

    public AbstractEntity(String path) {
        sprite = new Sprite(new Texture(Gdx.files.internal(path)));
        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
    	super.draw(batch, parentAlpha);
    	sprite.draw(batch);

    	if (getDebug()) {
			ShapeRenderer shape = new ShapeRenderer();
			shape.setColor(Color.RED);
			shape.setAutoShapeType(true);
			shape.begin();
			drawDebug(shape);
			drawDebugBounds(shape);
			shape.end();
		}
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

	@Override
	public void act(float delta) {
		super.act(delta);
		sprite.setOrigin(getOriginX(), getOriginY());
		sprite.setRotation(getRotation());
		sprite.setScale(getScaleX(), getScaleY());
		sprite.setPosition(getX(), getY());
	}

	@Override
    public boolean remove() {
        return super.remove();
    }
}
