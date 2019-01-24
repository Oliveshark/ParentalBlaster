package com.oliveshark.blaster.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.oliveshark.blaster.util.Vector2d;

public abstract class AbstractEntity extends Actor {

    protected final Texture texture;
    protected final Vector2d<Double> position = new Vector2d<>(0d, 0d);

    protected boolean alive = true;

    public AbstractEntity(String path) {
        texture = new Texture(Gdx.files.internal(path));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, position.getX().intValue(), position.getY().intValue());
    }

    public Vector2d<Double> getPosition() {
        return position;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public boolean remove() {
        texture.dispose();
        return super.remove();
    }
}
