package com.oliveshark.blaster.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Vector2dTest {

    private Vector2d intVector;

    @BeforeEach
    public void setUp() throws Exception {
        intVector = new Vector2d<Integer>(0, 0);
    }

    @Test
    public void addY() {
        intVector.addY(10);
        Assertions.assertEquals(intVector.getY(), 10);
        intVector.addY(-10);
        intVector.addY(-10);
        Assertions.assertEquals(intVector.getY(), -10);
    }

    @Test
    public void addX() {
        intVector.addX(10);
        Assertions.assertEquals(intVector.getX(), 10);
        intVector.addX(-10);
        intVector.addX(-10);
        Assertions.assertEquals(intVector.getX(), -10);
    }

    @Test
    public void getX() {
        Assertions.assertEquals(intVector.getX(), 0);
    }

    @Test
    public void setX() {
        intVector.setX(10);
        Assertions.assertEquals(intVector.getX(), 10);
    }

    @Test
    public void getY() {
        Assertions.assertEquals(intVector.getY(), 0);
    }

    @Test
    public void setY() {
        intVector.setY(10);
        Assertions.assertEquals(intVector.getY(), 10);
    }

    @Test
    public void toStringTest() {
        intVector.setX(10);
        intVector.setY(-10);
        Assertions.assertEquals(intVector.toString(), "10x-10");
    }
}