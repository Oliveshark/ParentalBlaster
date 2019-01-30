package com.oliveshark.blaster.entities.comp;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.oliveshark.blaster.entities.Entity;

public class TouchToKillComponent implements Component {

	public TouchToKillComponent(Entity entity) {
		entity.setTouchable(Touchable.enabled);
		entity.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				entity.setAlive(false);
				return true;
			}
		});
	}

	@Override
	public void dispose() {
		// PASS
	}
}
