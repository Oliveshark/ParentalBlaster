package com.oliveshark.blaster.entities;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.oliveshark.blaster.util.Vector2d;

public class Target extends AbstractEntity {

    private Vector2d<Double> velocity = new Vector2d<>(0d, 0d);

    public Target(String path) {
        super(path);
        createPhysicsBody();
        updateBounds();
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                alive = false;
                return true;
            }
        });
    }

    private void createPhysicsBody() {
        // TODO(Linus): This needs doing
    }

    public void setVelocity(double x, double y) {
        velocity.setX(x);
        velocity.setY(y);
        updateBounds();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        position.addX(velocity.getX() * delta);
        position.addY(velocity.getY() * delta);
        updateBounds();
    }

    private void updateBounds() {
        setBounds(position.getX().intValue(), position.getY().intValue(), texture.getWidth(), texture.getHeight());
    }
}
