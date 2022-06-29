package com.caralands.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.caralands.world.World;

import java.util.ArrayList;
import java.util.HashMap;

public class DragAndDropSystem {

    private ArrayList<Window> windows;

    private Slot pickedUpSlot;
    private Vector3 originalSlotPosition, currentSlotPosition, mousePosition;

    public DragAndDropSystem(){
        windows = new ArrayList<>();
        originalSlotPosition = new Vector3();
        currentSlotPosition = new Vector3();
        mousePosition = new Vector3();
    }

    public void update(){

        for(Window window: windows){

            // Picks up slot if it contains a slot item.
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && pickedUpSlot == null && window.isMouseInWindow()){

                if(window instanceof InventoryWindow){
                    HashMap<Integer, Slot> slots = ((InventoryWindow) window).getSlots();
                    for(Slot slot: slots.values()){
                        if(slot.isMouseInSlot() && slot.getSlotItem() != null){
                            pickedUpSlot = slot;
                            originalSlotPosition = slot.getIconPosition();
                        }
                    }
                }


            }

            // Drops slot
            if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT) && pickedUpSlot != null){
                pickedUpSlot.setIconPosition(new Vector3(originalSlotPosition.x, originalSlotPosition.y, 0));
                pickedUpSlot = null;
            }

        }


        if(pickedUpSlot != null){
            mousePosition.x = Gdx.input.getX();
            mousePosition.y = Gdx.input.getY();
            World.cameraUi.unproject(mousePosition);
            currentSlotPosition.x = mousePosition.x;
            currentSlotPosition.y = mousePosition.y;
            pickedUpSlot.setIconPosition(currentSlotPosition);
        }


    }



    public void addWindow(Window window){
        windows.add(window);
    }

}
