package com.oliveshark.blaster.entities.comp;

import com.oliveshark.blaster.entities.AbstractEntity;

public interface Component {

	void act(AbstractEntity entity);

	void dispose();
}
