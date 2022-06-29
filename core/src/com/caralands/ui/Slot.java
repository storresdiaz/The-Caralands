package com.caralands.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.caralands.entities.Entity;
import com.caralands.world.World;

public class Slot {

    private Entity slotItem;

    private Vector3 position, iconPosition;
    private SpriteBatch spriteBatch;

    private float width, height;

    private Texture slotTexture, slotHighlightTexture;

    private Window parentWindow;

    /**
     * A slot that can be added into a window.
     *
     * @param posX x Position relative to parent window
     * @param posY y Position relative to parent window
     * @param parentWindow the Parent window
     */
    public Slot(float posX, float posY, Window parentWindow){
        this.parentWindow = parentWindow;
        spriteBatch = World.uiSpriteBatch;
        position = new Vector3(posX, posY, 0);
        iconPosition = new Vector3(posX + parentWindow.getPosition().x, posY + parentWindow.getPosition().y, 0);

        slotTexture = new Texture(Gdx.files.internal("ui/ui-slot.png"));
        slotHighlightTexture = new Texture((Gdx.files.internal("ui/icons/icon-highlight.png")));
        width = slotTexture.getWidth();
        height = slotTexture.getHeight();
    }

    public void render(){
            spriteBatch.draw(slotTexture, position.x + parentWindow.getPosition().x, position.y + parentWindow.getPosition().y);

            if(slotItem != null){
                spriteBatch.draw(slotItem.icon, iconPosition.x, iconPosition.y);
            }

            if(isMouseInSlot()){
                spriteBatch.draw(slotHighlightTexture, position.x + parentWindow.getPosition().x, position.y + parentWindow.getPosition().y);
            }

    }

    public void update(){

        if(isMouseInSlot()){
            if(slotItem !=null){
                UserInterface.drawTextFollowMouse(slotItem.name);
            }
        }

    }


    public void setSlotItem(Entity entity){
        slotItem = entity;
    }

    public Entity getSlotItem(){
        return slotItem;
    }

    public Vector3 getPosition(){
        return position;
    }

    public void setPosition(Vector3 position){
        this.position = position;
    }

    public Vector3 getIconPosition(){
        return iconPosition;
    }

    public void setIconPosition(Vector3 position){
        this.iconPosition = position;
    }

    public boolean isMouseInSlot(){

        Vector3 mousePosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        World.cameraUi.unproject(mousePosition);

        if(mousePosition.x > position.x + parentWindow.getPosition().x && mousePosition.x < position.x + parentWindow.getPosition().x + width
                && mousePosition.y > position.y + parentWindow.getPosition().y && mousePosition.y < position.y + parentWindow.getPosition().y + height){
            return true;
        }


        return false;
    }


}
