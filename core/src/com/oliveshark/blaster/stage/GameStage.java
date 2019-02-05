package com.oliveshark.blaster.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.oliveshark.blaster.Box2d;
import com.oliveshark.blaster.entities.Entity;
import com.oliveshark.blaster.entities.comp.*;

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

    private static final String[] roundTexturePaths = {
			"pixel_moon_alpha.png",
			"pixel_planet_alpha.png",
			"pixel_planet_beta.png",
			"pixel_planet_delta.png",
			"pixel_planet_epsilon.png",
			"pixel_planet_gamma.png",
			"pixel_planet_iota.png",
			"pixel_planet_kappa.png",
			"pixel_planet_lambda.png",
			"pixel_planet_mu.png",
			"pixel_planet_nu.png",
			"pixel_planet_omicron.png",
			"pixel_planet_phi.png",
			"pixel_planet_rho.png",
			"pixel_planet_sigma.png"
    };


	private void addNewTarget() {
		Entity target = new Entity();
		target.setBounds(0, 3, 2, 2);
		target.setCenterOrigin();

		AbstractPhysicsComponent comp;
		boolean boxShape = MathUtils.random(1) == 1;
		if (boxShape) {
			comp = new DynamicRectanglePhysicsComponent(target);
		} else {
			comp = new DynamicCirclePhysicsComponent(target);
		}

		PointLight light = new PointLight(rayHandler, 128, null, 16, 0, 0);
		light.setColor(
				MathUtils.random(),
				MathUtils.random(),
				MathUtils.random(),
				1
				);
		comp.addLight(light, 0, 0);

        target.getComponents().add(comp);
		target.getComponents().add(new RemoveWhenOffScreenComponent(target));
        target.getComponents().add(new TouchToKillComponent(target));
        if (boxShape) {
			target.getComponents().add(new SpriteComponent(target, "box.png"));
		} else {
			target.getComponents().add(new SpriteComponent(target, roundTexturePaths[MathUtils.random(roundTexturePaths.length - 1)]));
		}

		addActor(target);
    }

    private void addStaticBoxes() {
		addStaticBoxAt(new Rectangle(6, 0, 12, 2));
		addStaticBoxAt(new Rectangle(18, 0, 2, 12));

		Entity entity = new Entity();
		entity.setBounds(6, 2, 6, 2);
		entity.setCenterOrigin();
		AbstractPhysicsComponent comp = new StaticTrianglePhysicsComponent(entity);
		PointLight light = new PointLight(rayHandler, 128, Color.WHITE, 16, 0, 0);
		comp.addLight(light, 0, 0);
		entity.getComponents().add(comp);
		addActor(entity);
	}

	private void addStaticBoxAt(Rectangle rect) {
    	Entity box = new Entity();
    	box.setBounds(rect);
    	box.setCenterOrigin();
    	box.getComponents().add(new StaticRectanglePhysicsComponent(box));
    	box.getComponents().add(new SpriteComponent(box, "box.png"));
    	addActor(box);
	}

	@Override
	public void draw() {
		super.draw();

		rayHandler.setCombinedMatrix((OrthographicCamera) getViewport().getCamera());
		rayHandler.updateAndRender();
	}

	@Override
    public void act(float delta) {
        super.act(delta);

        for (Actor actor : getActors()) {
            if (actor instanceof Entity) {
				if (((Entity) actor).isAlive()) {
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
