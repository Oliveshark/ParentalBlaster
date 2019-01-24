package com.oliveshark.blaster.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionTest {

    private Position position;

    @BeforeEach
    public void setUp() throws Exception {
        position = new Position();
    }

    @Test
    public void addY() {
        position.addY(10);
        Assertions.assertEquals(position.getY(), 10);
        position.addY(-10);
        position.addY(-10);
        Assertions.assertEquals(position.getY(), -10);
    }

    @Test
    public void addX() {
        position.addX(10);
        Assertions.assertEquals(position.getX(), 10);
        position.addX(-10);
        position.addX(-10);
        Assertions.assertEquals(position.getX(), -10);
    }

    @Test
    public void getX() {
        Assertions.assertEquals(position.getX(), 0);
    }

    @Test
    public void setX() {
        position.setX(10);
        Assertions.assertEquals(position.getX(), 10);
    }

    @Test
    public void getY() {
        Assertions.assertEquals(position.getY(), 0);
    }

    @Test
    public void setY() {
        position.setY(10);
        Assertions.assertEquals(position.getY(), 10);
    }

    @Test
    public void toStringTest() {
        position.setX(10);
        position.setY(-10);
        Assertions.assertEquals(position.toString(), "10x-10");
    }
}