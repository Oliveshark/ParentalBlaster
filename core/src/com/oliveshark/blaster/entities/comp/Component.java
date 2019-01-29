package com.oliveshark.blaster.entities.comp;

import com.oliveshark.blaster.entities.AbstractEntity;

public interface Component {

	default void act(float deltatime) {
	}

	default void dispose() {
	}
}
