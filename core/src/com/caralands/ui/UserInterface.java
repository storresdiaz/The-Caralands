package com.caralands.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.caralands.world.World;

public class UserInterface {

    private InventoryWindow inventoryWindow;
    private CharacterWindow characterWindow;

    private DragAndDropSystem dragAndDropSystem;

    private static BitmapFont font;

    private static SpriteBatch spriteBatch;

    private boolean isVisible = true;

    public UserInterface(){
        inventoryWindow = new InventoryWindow(100, 100);
        characterWindow = new CharacterWindow(1200, 100);

        dragAndDropSystem = new DragAndDropSystem();
        dragAndDropSystem.addWindow(inventoryWindow);

        font = new BitmapFont();
        font.getData().setScale(2);
        spriteBatch = World.uiSpriteBatch;
    }

    public void render(){

        if(isVisible){
            inventoryWindow.render();
            characterWindow.render();
        }

    }

    public void update(){

        if(isVisible){
            inventoryWindow.update();
            characterWindow.update();
            dragAndDropSystem.update();
        }

    }

    public static void drawTextFollowMouse(String text){

        Vector3 mousePosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        World.cameraUi.unproject(mousePosition);

            spriteBatch.begin();
                font.draw(spriteBatch, text, mousePosition.x + 2, mousePosition.y + 20);
            spriteBatch.end();

    }

}
