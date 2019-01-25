package com.oliveshark.blaster.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.math.Rectangle;

public class CollisionUtilTest {

	private Rectangle a;
	private Rectangle b;
	private Rectangle c;
	private Rectangle d;

	@BeforeEach
	public void setUp() {
		a = new Rectangle(0, 0, 50, 50);
		b = new Rectangle(30, 30, 50, 50);
		c = new Rectangle(50, 50, 50, 50);
		d = new Rectangle(60, 60, 10, 10);
	}

	@Test
	public void AABB() {
		Assertions.assertTrue(CollisionUtil.AABB(a, b));
		Assertions.assertTrue(!CollisionUtil.AABB(a, c));
		Assertions.assertTrue(CollisionUtil.AABB(c, d));
		Assertions.assertTrue(CollisionUtil.AABB(d, c));
		Assertions.assertTrue(CollisionUtil.AABB(b, c));
		Assertions.assertTrue(CollisionUtil.AABB(b, d));
	}
}
