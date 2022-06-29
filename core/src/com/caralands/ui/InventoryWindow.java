package com.caralands.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.caralands.world.World;

import java.util.HashMap;

public class InventoryWindow extends Window {

    private Texture inventoryWindowTexture;

    private SpriteBatch spriteBatch;
    private HashMap<Integer, Slot> slots;

    public InventoryWindow(float posX, float posY){
        super(posX, posY, "Backpack");
        inventoryWindowTexture = new Texture(Gdx.files.internal("ui/ui-window.png"));
        spriteBatch = World.uiSpriteBatch;

        float width = inventoryWindowTexture.getWidth();
        float height = inventoryWindowTexture.getHeight();
        setSize(width, height);

        slots = new HashMap<>();
        slots.put(1, new Slot(40, 360, this));
        slots.put(2, new Slot(180, 360, this));
        slots.put(3, new Slot(320, 360, this));
        slots.put(4, new Slot(40, 240, this));
        slots.put(5, new Slot(180, 240, this));
        slots.put(6, new Slot(320, 240, this));
        slots.put(7, new Slot(40, 120, this));
        slots.put(8, new Slot(180, 120, this));
        slots.put(9, new Slot(320, 120, this));

    }

    @Override
    public void render(){
        if(isVisible){
            spriteBatch.begin();
            spriteBatch.draw(inventoryWindowTexture, getPosition().x, getPosition().y);
            for(Slot slot: slots.values()){
                slot.render();
            }
            spriteBatch.end();
        }

    }

    @Override
    public void update(){

        for(int i = 1; i <= slots.size(); i++){
            slots.get(i).setSlotItem(World.entityPlayer.getInventory().getItem(i-1));
        }

        for(Slot slot: slots.values()){
            slot.update();
        }

    }

    public HashMap<Integer, Slot> getSlots(){
        return slots;
    }

}
