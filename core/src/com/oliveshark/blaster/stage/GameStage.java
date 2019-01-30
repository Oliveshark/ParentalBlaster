package com.oliveshark.blaster.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.oliveshark.blaster.Box2d;
import com.oliveshark.blaster.entities.OnScreenEntity;
import com.oliveshark.blaster.entities.comp.AbstractPhysicsComponent;
import com.oliveshark.blaster.entities.comp.DynamicPhysicsComponent;
import com.oliveshark.blaster.entities.comp.SpriteComponent;
import com.oliveshark.blaster.entities.comp.StaticPhysicsComponent;
import com.oliveshark.blaster.entities.comp.TouchToKillComponent;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import static com.oliveshark.blaster.Box2d.PPM;

public class GameStage extends Stage {

    private Timer spawnTimer = new Timer();
    private RayHandler rayHandler;

    public GameStage() {
		super(new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth()/PPM, Gdx.graphics.getHeight()/PPM,
				new OrthographicCamera(Gdx.graphics.getWidth()/PPM, Gdx.graphics.getHeight()/PPM)));
	}

    public void build() {
		setupRayHandler();
		addStaticBoxes();
        addNewTarget();
        initSpawnTimer();
    }

	private void setupRayHandler() {
		RayHandler.setGammaCorrection(true);
		RayHandler.useDiffuseLight(true);
		rayHandler = new RayHandler(Box2d.world);
		rayHandler.setAmbientLight(0, 0, 0, 0.2f);
		rayHandler.setBlurNum(3);
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
        OnScreenEntity target = new OnScreenEntity(new Rectangle(0, 3, 2, 2));

		AbstractPhysicsComponent comp = new DynamicPhysicsComponent(target);
		PointLight light = new PointLight(rayHandler, 128, null, 16, 0, 0);
		light.setColor(
				MathUtils.random(),
				MathUtils.random(),
				MathUtils.random(),
				1
				);
		comp.addLight(light, 0, 0);

        target.getComponents().add(comp);
        target.getComponents().add(new TouchToKillComponent(target));
        target.getComponents().add(new SpriteComponent(target, "box.png"));

		addActor(target);
    }

    private void addStaticBoxes() {
		addStaticBoxAt(new Rectangle(6, 0, 8, 2));
		addStaticBoxAt(new Rectangle(14, 0, 2, 8));
	}

	private void addStaticBoxAt(Rectangle rect) {
    	OnScreenEntity box = new OnScreenEntity(rect);
    	box.getComponents().add(new StaticPhysicsComponent(box));
    	box.getComponents().add(new SpriteComponent(box, "box.png"));
    	addActor(box);
	}

	@Override
	public void draw() {
		Camera camera = getViewport().getCamera();
		camera.update();

		if (!getRoot().isVisible()) return;

		Batch batch = getBatch();
		batch.setProjectionMatrix(camera.combined);
		batch.disableBlending();
		batch.begin();
		{
			getRoot().draw(batch, 1);
		}
		batch.end();

		rayHandler.setCombinedMatrix((OrthographicCamera) camera);
		rayHandler.updateAndRender();
	}

	@Override
    public void act(float delta) {
        super.act(delta);

        for (Actor actor : getActors()) {
            if (actor instanceof OnScreenEntity) {
				if (((OnScreenEntity) actor).isAlive()) {
					continue;
				}
				actor.remove();
			}
        }
    }

    @Override
    public void dispose() {
        super.dispose();
		rayHandler.dispose();
        spawnTimer.stop();
    }
}
