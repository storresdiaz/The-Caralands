package com.caralands.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.caralands.world.World;

public class Window {

    public boolean isVisible = true;

    private String label;

    private Vector3 position;

    private float width, height;

    public Window(float posX, float posY, String label){
        this.label = label;
        position = new Vector3(posX, posY, 0);
    }

    public void render(){

    }

    public void update(){

    }

    public Vector3 getPosition(){
        return position;
    }

    public void setPosition(float posX, float posY){
        position.x = posX;
        position.y = posY;
    }

    public boolean isMouseInWindow(){
        Vector3 mousePosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        World.cameraUi.unproject(mousePosition);

        if(mousePosition.x > getPosition().x && mousePosition.x < getPosition().x + width &&
                mousePosition.y > getPosition().y && mousePosition.y < height){
            return true;
        }

        return false;
    }

    public void setSize(float width, float height){
        this.width = width;
        this.height = height;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

}
