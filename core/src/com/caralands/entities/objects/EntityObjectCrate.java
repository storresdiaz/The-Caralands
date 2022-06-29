package com.caralands.entities.objects;

import com.caralands.assets.Sounds;
import com.caralands.entities.Entity;
import com.caralands.entities.collision.EntityCollisionHelper;

public class EntityObjectCrate extends EntityObject {

    public EntityObjectCrate(float posX, float posY) {
        super(posX, posY, "object-crate");
        skeleton.setScale(0.6f, 0.6f);
        isDamageable = true;
        isSolid = false;
        health = 20;
    }

    @Override
    public void doCollisions(Entity entity){

            if (EntityCollisionHelper.fullboundsIntersectsWeapon(this, entity) && entity.isAttacking) {
                health -= entity.attackDamage;
                entity.isAttacking = false;

                animationState.setAnimation(0, "hit", false);

                if(health > 0){
                    Sounds.impact_wood_hard.play();
                }

            }

    }

    @Override
    public void onDeath(){
        Sounds.impact_wood_break.play();
    }
}
