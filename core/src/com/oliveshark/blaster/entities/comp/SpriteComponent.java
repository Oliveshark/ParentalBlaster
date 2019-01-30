package com.oliveshark.blaster.entities.comp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.oliveshark.blaster.entities.Entity;

public class SpriteComponent implements Component {

	private final Sprite sprite;
	private final Entity entity;

	public SpriteComponent(Entity entity, String path) {
		this.entity = entity;
		this.sprite = new Sprite(new Texture(Gdx.files.internal(path)));
	}

	@Override
	public void act(float deltatime) {
		sprite.setOrigin(entity.getOriginX(), entity.getOriginY());
		sprite.setRotation(entity.getRotation());
		sprite.setScale(entity.getScaleX(), entity.getScaleY());
		sprite.setBounds(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
	}

	@Override
	public void draw(Batch batch) {
		sprite.draw(batch);
	}

	@Override
	public void dispose() {
		// PASS
	}

	public Sprite getSprite() {
		return sprite;
	}
}
