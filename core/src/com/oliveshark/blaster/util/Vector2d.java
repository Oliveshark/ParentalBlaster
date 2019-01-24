package com.oliveshark.blaster.util;

public class Vector2d<T extends Number> {

    private T x;
    private T y;

    public Vector2d(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public void addX(T dx) {
        if (dx instanceof Integer) {
            this.x = (T) Integer.valueOf(x.intValue() + dx.intValue());
        } else if (dx instanceof Double) {
            this.x = (T) Double.valueOf(x.doubleValue() + dx.doubleValue());
        } else if (dx instanceof Float) {
            this.x = (T) Float.valueOf(x.floatValue() + dx.floatValue());
        }
    }

    public void addY(T dy) {
        if (dy instanceof Integer) {
            this.y = (T) Integer.valueOf(y.intValue() + dy.intValue());
        } else if (dy instanceof Double) {
            this.y = (T) Double.valueOf(y.doubleValue() + dy.doubleValue());
        } else if (dy instanceof Float) {
            this.y = (T) Float.valueOf(y.floatValue() + dy.floatValue());
        }
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return x + "x" + y;
    }
}
