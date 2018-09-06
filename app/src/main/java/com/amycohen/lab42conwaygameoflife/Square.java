package com.amycohen.lab42conwaygameoflife;

/*
Java Docs on drawing graphics:

https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html#drawRect(int,%20int,%20int,%20int)

drawRect(int x, int y, int width, int height)
 */

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.Set;

public class Square {
    private float xx;
    private float yy;
    private float width;
    private float height;
    private int color;

    private Set<Square> squares;

    public Square(float xx, float yy, float width, float height, int color) {
        this.xx = xx;
        this.yy = yy;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void setX(float xx) {
        this.xx = xx;
    }

    public void setY(float yy) {
        this.yy = yy;
    }

    public float getX() {
        return this.xx;
    }

    public float getY() {
        return this.yy;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public int getColor() {
        return this.color;
    }

    

    public void draw(Canvas canvas) {
        Paint brush = new Paint(Paint.ANTI_ALIAS_FLAG);
        brush.setColor(this.color);
        canvas.drawRect(this.xx, this.yy, this.width, this.height, brush);
    }

//    public void addSquare(float xx, float yy, float width, float height, int color){
////        squares.add(this.xx, this.yy, this.width, this.height, this.color);
//        this.xx = xx;
//        this.yy = yy;
//        this.width = width;
//        this.height = height;
//        this.color = color;
//    }

    public boolean contains(float xx, float yy) {
        float dx = this.xx - xx;
        float dy = this.yy - yy;

//        double distance
        return dx > width && dy > height;
    }

    @Override
    public String toString(){
        String color = "";
        if (this.color == -1) {
            color = "white";
        } else {
            color = "black";
        }
        return "[X: " + (int)this.xx + ", Y: " + (int)this.yy + ", W: " + (int)this.width + ", H: " + (int)this.height + ", C: " + color;
    }

}
