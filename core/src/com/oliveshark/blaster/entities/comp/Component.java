package com.oliveshark.blaster.entities.comp;

public interface Component {

	default void act(float deltatime) {
	}

	default void dispose() {
	}
}
