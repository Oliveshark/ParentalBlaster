package com.oliveshark.blaster.stage;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Timer;
import com.oliveshark.blaster.entities.FixedObject;
import com.oliveshark.blaster.entities.Target;

public class GameStage extends Stage {

    Timer spawnTimer = new Timer();

    public void build() {
		addStaticBlocks();
        addNewTarget();
        initSpawnTimer();
    }

    private void initSpawnTimer() {
        spawnTimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                addNewTarget();
            }
        }, 3, 3);
        spawnTimer.start();
    }

    private void addNewTarget() {
        Target t = new Target("box.png");
        t.setTouchable(Touchable.enabled);
        addActor(t);
    }

    private void addStaticBlocks() {
		addActor(new FixedObject("box.png", new Rectangle(200, 0, 256, 64)));
		addActor(new FixedObject("box.png", new Rectangle(456, 0, 64, 256)));
	}

    @Override
    public void act(float delta) {
        super.act(delta);

        for (Actor actor : getActors()) {
            if (actor instanceof Target) {
                if (!((Target) actor).isAlive()) {
                    actor.remove();
                }
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        spawnTimer.stop();
    }
}
