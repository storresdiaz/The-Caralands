package com.caralands.entities;

import java.util.HashMap;

public class Inventory {

    private HashMap<Integer, Entity> inventoryBackpack, inventoryEquipped;
    private int inventoryBackpackSize = 27;

    public Inventory(){
            inventoryBackpack = new HashMap<>();
            inventoryEquipped = new HashMap<>();
    }

    public HashMap getItems(){
        return inventoryBackpack;
    }

    public HashMap getEquippedItems(){
        return inventoryEquipped;
    }

    public Entity getItem(int position){
        return inventoryBackpack.get(position);
    }

    public Entity getEquippedItem(int position){
        return inventoryEquipped.get(position);
    }

    /**
     * Adds an Entity to the position on the Inventory Backpack HashMap.
     * @param position The position to add the Entity at.
     * @param entity The entity to be added.
     * @return Returns true if the position is empty and the position is not greater than the inventory's size.
     */
    public boolean addItem(int position, Entity entity){
        if(inventoryBackpack.get(position) == null && position <= inventoryBackpackSize && position > 0){
            inventoryBackpack.put(position, entity);
            return true;
        }

        return false;
    }

    /**
     * Method for adding an item at the first available slot in the Inventory Backpack.
     *
     * @param entity The entity to add.
     * @return Returns true if an open position is found and adds the item.
     */
    public boolean addItemFirstOpening(Entity entity){

        for(int i  = 0; i < inventoryBackpackSize; i++){
            if(inventoryBackpack.get(i) == null){
                inventoryBackpack.put(i, entity);
                return true;
            }
        }

        return false;

    }

    /**
     * Adds an Entity to the Equipped Hashmap.
     * @param position The position to add the Item.
     * @param entity The entity to be added to the map.
     * @return Returns true if the position is empty.
     */
    public boolean addEquippedItem(int position, Entity entity){
        if(inventoryEquipped.get(position) == null){
            inventoryEquipped.put(position, entity);
            return true;
        }

        return false;
    }

    public void setInventoryBackpackSize(int size){
        this.inventoryBackpackSize = size;
    }


}
