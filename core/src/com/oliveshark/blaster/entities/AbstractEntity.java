package com.oliveshark.blaster.entities;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.oliveshark.blaster.entities.comp.Component;

public abstract class AbstractEntity extends Actor {

    final Sprite sprite;
    boolean alive = true;

    final Set<Component> components = new HashSet<>();

    AbstractEntity(String path) {
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
		components.forEach(comp -> comp.act(delta));

		sprite.setOrigin(getOriginX(), getOriginY());
		sprite.setRotation(getRotation());
		sprite.setScale(getScaleX(), getScaleY());
		sprite.setBounds(getX(), getY(), getWidth(), getHeight());
	}

	public Set<Component> getComponents() {
    	return components;
	}

	@Override
	public boolean remove() {
    	components.stream().forEach(Component::dispose);
		return super.remove();
	}
}
