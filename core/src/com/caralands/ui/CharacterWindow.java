package com.caralands.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.caralands.world.World;

import java.util.HashMap;

public class CharacterWindow extends Window {

    private Texture characterWindowTexture;
    private SpriteBatch spriteBatch;
    private HashMap<Integer, Slot> slots;

    private float width, height;

    public CharacterWindow(float posX, float posY) {
        super(posX, posY, "Character");
        characterWindowTexture = new Texture(Gdx.files.internal("ui/ui-window-character.png"));
        spriteBatch = World.uiSpriteBatch;

        width = characterWindowTexture.getWidth();
        height = characterWindowTexture.getHeight();

        slots = new HashMap<>();
        slots.put(1, new Slot(60, 120, this)); // left hand
        slots.put(2, new Slot(320, 120, this)); // right hand
    }

    @Override
    public void render(){
        if(isVisible){
            spriteBatch.begin();
            spriteBatch.draw(characterWindowTexture, getPosition().x, getPosition().y);
            for(Slot slot: slots.values()){
                slot.render();
                slot.update();
            }
            spriteBatch.end();
        }
    }

    @Override
    public void update(){

    }

}
