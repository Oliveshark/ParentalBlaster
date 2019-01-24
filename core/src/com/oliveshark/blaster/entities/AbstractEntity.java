package com.oliveshark.blaster.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.oliveshark.blaster.util.Position;

public abstract class AbstractEntity extends Actor {

    protected final Texture texture;
    protected final Position pos = new Position();

    public AbstractEntity(String path) {
        texture = new Texture(Gdx.files.internal(path));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, pos.getX(), pos.getY());
    }
}
