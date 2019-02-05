package com.oliveshark.blaster.entities.comp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.oliveshark.blaster.entities.Entity;

import static com.oliveshark.blaster.util.CollisionUtil.AABB;

public class RemoveWhenOffScreenComponent implements Component {

    private Entity entity;

    public RemoveWhenOffScreenComponent(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void act(float deltatime) {
        if (!AABB(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), entity.getBounds())) {
            entity.setAlive(false);
        }
    }

    @Override
    public void draw(Batch batch) {
        // PASS
    }

    @Override
    public void dispose() {
        // PASS
    }
}
