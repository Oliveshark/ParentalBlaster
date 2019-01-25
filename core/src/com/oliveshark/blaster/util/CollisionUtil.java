package com.oliveshark.blaster.util;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.math.Rectangle;

public class CollisionUtil {

	public static boolean AABB(Rectangle a, Rectangle b) {
		return b.overlaps(a);
	}

	public static boolean AABB(int w, int h, Rectangle b) {
		Rectangle a = new Rectangle(0, 0, w, h);
		return b.overlaps(a);
	}

	public static boolean onScreen(Graphics graphics, Rectangle b) {
		return AABB(graphics.getWidth(), graphics.getHeight(), b);
	}
}
