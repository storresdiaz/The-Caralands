package com.caralands.entities.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.caralands.entities.Entity;
import com.caralands.ui.UserInterface;
import com.caralands.world.World;
import com.esotericsoftware.spine.*;
import com.esotericsoftware.spine.utils.TwoColorPolygonBatch;

public abstract class EntityWeapon extends Entity {

    public float posX, posY, attackDamage, currentWear = 100;
    public TextureAtlas skeletonAtlas;

    public Skeleton skeleton;

    public SkeletonBounds skeletonBounds;

    public EntityWeapon(float posX, float posY, String objectName){
        this.posX = posX;
        this.posY = posY;
        skeletonAtlas = new TextureAtlas(Gdx.files.internal("spine/weapons/"+objectName+".atlas"));
        SkeletonJson json = new SkeletonJson(skeletonAtlas);
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal("spine/weapons/"+objectName+".json"));
        skeleton = new Skeleton(skeletonData);
        skeleton.setScale(0.4f,0.4f);
        skeletonBounds = new SkeletonBounds();
        skeletonBounds.update(skeleton, true);
        this.width = skeleton.getData().getWidth() * skeleton.getScaleX();
        this.height = skeleton.getData().getHeight() * skeleton.getScaleY();
        System.out.println(height);
    }

    @Override
    public void render(TwoColorPolygonBatch polygonBatch, SkeletonRenderer skeletonRenderer) {
        polygonBatch.begin();
        skeletonRenderer.draw(polygonBatch, skeleton);
        polygonBatch.end();

        if (isPointInBounds(Gdx.input.getX(), Gdx.input.getY()) && this.isPickupable){

            UserInterface.drawTextFollowMouse(this.name);

            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                pickup();
            }

        }

    }

    @Override
    public void update(){
        skeleton.setPosition(posX, posY);
        skeleton.updateWorldTransform();
        skeletonBounds.update(skeleton, true);

        if(health <= 0){
            isAlive = false;
            onDeath();
        }

    }

    @Override
    public SkeletonBounds getSkeletonBounds() {
        return skeletonBounds;
    }

    @Override
    public Skeleton getSkeleton(){
        return skeleton;
    }


    @Override
    public void doCollisions(Entity entity) {

    }

    @Override
    public void doCollisions() {

    }

    @Override
    public void onDeath() {

    }

    public boolean isPointInBounds(float posX, float posY){

        Vector3 position = new Vector3(posX, posY, 0);

        World.camera.unproject(position);


        if(position.x > this.posX - this.width / 2 && position.x < this.posX + this.width / 2){
            if(position.y < this.posY + this.height / 2 && position.y > this.posY - this.height / 2){
                return true;
            }
        }

        return false;

    }
}
