package com.caralands.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonBounds;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.esotericsoftware.spine.SkeletonRendererDebug;
import com.esotericsoftware.spine.utils.TwoColorPolygonBatch;

public abstract class Entity {

    private Skeleton skeleton;
    public boolean isAttacking = false, isDamageable = false, isAlive = true, isPickupable = false, isSolid = false;
    public int health = 100, attackDamage = 0;

    public float posX, posY, width, height;

    public String name = "default";

    public Texture icon;

    public float iconPosX = 0, iconPosY = 0;

    public Entity(){
        icon = new Texture(Gdx.files.internal("ui/icons/icon-sword-sheetmetal.png"));
    }

    public void render(TwoColorPolygonBatch polygonBatch, SkeletonRenderer skeletonRenderer){


    }

    public void renderDebug(SkeletonRendererDebug debug){

    }

    public void update(){

    }

    public float getPosX(){
        return posX;
    }

    public float getPosY(){
        return posY;
    }

    public SkeletonBounds getSkeletonBounds(){ return new SkeletonBounds();}

    public Skeleton getSkeleton(){return skeleton;}

    public abstract void doCollisions(Entity entity);

    public abstract void doCollisions();

    public abstract void onDeath();

    public abstract void pickup();


}
