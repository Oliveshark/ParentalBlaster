package com.oliveshark.blaster.entities;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.oliveshark.blaster.entities.comp.Component;

public class Entity extends Actor {

    boolean alive = true;

    final Set<Component> components = new HashSet<>();

    @Override
    public void draw(Batch batch, float parentAlpha) {
    	super.draw(batch, parentAlpha);
    	for (Component comp : components) {
    		comp.draw(batch);
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

		for (Component comp : components) {
			comp.act(delta);
		}
	}

	public Rectangle getBounds() {
    	return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	public Set<Component> getComponents() {
    	return components;
	}

	@Override
	public boolean remove() {
    	for (Component comp : components) {
    		comp.dispose();
		}
		return super.remove();
	}
}
