package com.caralands.entities.weapons;

import com.caralands.entities.Entity;
import com.caralands.entities.EntityPlayer;
import com.caralands.world.World;

public class EntityWeaponSheetMetal extends EntityWeapon {

    public EntityWeaponSheetMetal(float posX, float posY) {
        super(posX, posY, "weapon-sword-sheetmetal");
        this.attackDamage = 15;
        this.currentWear = 75;
        this.name = "Sheet Metal Sword";
        this.isPickupable = true;
        skeleton.setScale(0.20f, 0.20f);
    }

    @Override
    public void pickup(){
        World.entityPlayer.getInventory().addItemFirstOpening(this);
    }

}
