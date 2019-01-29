package com.oliveshark.blaster.stage;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.oliveshark.blaster.entities.Box;
import com.oliveshark.blaster.entities.comp.DynamicPhysicsComponent;
import com.oliveshark.blaster.entities.comp.StaticPhysicsComponent;
import com.oliveshark.blaster.entities.comp.TouchToKillComponent;

public class GameStage extends Stage {

    private Timer spawnTimer = new Timer();

    public void build() {
		addStaticBoxes();
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
        Box t = new Box("box.png", new Rectangle(0, 200, 64, 64));
        t.getComponents().add(new DynamicPhysicsComponent(t));
        t.getComponents().add(new TouchToKillComponent(t));
        addActor(t);
    }

    private void addStaticBoxes() {
		addStaticBoxAt(new Rectangle(100, 0, 356, 64));
		addStaticBoxAt(new Rectangle(456, 0, 64, 256));
	}

	private void addStaticBoxAt(Rectangle rect) {
    	Box box = new Box("box.png", rect);
    	box.getComponents().add(new StaticPhysicsComponent(box));
    	addActor(box);
	}

    @Override
    public void act(float delta) {
        super.act(delta);

        for (Actor actor : getActors()) {
            if (actor instanceof Box) {
				if (((Box) actor).isAlive()) {
					continue;
				}
				actor.remove();
			}
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        spawnTimer.stop();
    }
}
