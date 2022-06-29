package com.caralands.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.caralands.entities.collision.EntityCollisionHelper;
import com.esotericsoftware.spine.*;
import com.esotericsoftware.spine.utils.TwoColorPolygonBatch;


public class EntityPlayer extends Entity {

    private float lastPosX, lastPosY, playerSpeed = 6;

    private TextureAtlas skeletonAtlas;
    private Skeleton skeleton;
    private SkeletonBounds skeletonBounds;

    private Slot slot_weapon, slot_weapon_bounds;

    private AnimationState animationState;

    public Vector3 vector3CameraPosition;

    private Inventory inventory;

    private boolean isRunning = false;

    /**
     *
     * @param posX X spawn position
     * @param posY Y spawn position
     */
    public EntityPlayer(float posX, float posY){
        this.posX = posX;
        this.posY = posY;
        this.lastPosX = posX;
        this.lastPosY = posY;
        this.attackDamage = 10;

        skeletonAtlas = new TextureAtlas(Gdx.files.internal("spine/humanoids/character-standard.atlas"));
        SkeletonJson json = new SkeletonJson(skeletonAtlas);
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal("spine/humanoids/character-standard.json"));
        AnimationStateData animationStateData = new AnimationStateData(skeletonData);
        animationStateData.setMix("walk", "idle", 0.2f);
        animationStateData.setMix("idle", "walk", 0.2f);
        animationState = new AnimationState(animationStateData);
        animationState.setAnimation(0 , "idle", true);
        skeleton = new Skeleton(skeletonData);
        skeleton.setScale(0.2f,0.2f);
        skeleton.setSkin(skeletonData.getDefaultSkin());

        skeletonBounds = new SkeletonBounds();
        skeletonBounds.update(skeleton, true);

        this.width = skeleton.getData().getWidth() * skeleton.getScaleX();
        this.height = skeleton.getData().getHeight() * skeleton.getScaleY();

        slot_weapon = skeleton.findSlot("weapon");
        slot_weapon_bounds = skeleton.findSlot("weapon-bounds");

        slot_weapon.setAttachment(null);
        slot_weapon_bounds.setAttachment(null);

        vector3CameraPosition = new Vector3();

        inventory = new Inventory();
        inventory.setInventoryBackpackSize(27);

    }


    @Override
    public void render(TwoColorPolygonBatch polygonBatch, SkeletonRenderer skeletonRenderer) {
        polygonBatch.begin();
        skeletonRenderer.draw(polygonBatch, skeleton);
        polygonBatch.end();

    }

    @Override
    public void renderDebug(SkeletonRendererDebug debug){
        debug.draw(skeleton);
    }

    @Override
    public void update() {
        animationState.update(Gdx.graphics.getDeltaTime());
        skeleton.updateWorldTransform();
        skeletonBounds.update(skeleton, true);
        animationState.apply(skeleton);
        lastPosX = posX;
        lastPosY = posY;

        if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D)
        || Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S)){
            if(!isRunning){
                isRunning = true;
                animationState.setAnimation(0, "walk", true);
            }
        }else{
            if(isRunning){
                isRunning = false;
                animationState.setAnimation(0, "idle", true);
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            if(skeleton.getScaleX() < 0){
                skeleton.setScaleX(skeleton.getScaleX()*-1);
            }
            posX -= playerSpeed;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            if(skeleton.getScaleX() > 0){
                skeleton.setScaleX(skeleton.getScaleX()*-1);
            }
            posX += playerSpeed;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            posY += playerSpeed;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            posY -= playerSpeed;
        }

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            animationState.setAnimation(1, "swing", false);
            animationState.addEmptyAnimation(1, 0.2f, 0);
            isAttacking = true;
        }

        if(animationState.getCurrent(1) != null){
            if(animationState.getCurrent(1).isComplete()){
                isAttacking = false;
            }
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

    public Inventory getInventory(){
        return inventory;
    }

    @Override
    public void doCollisions(Entity entity) {

        try{
            if(EntityCollisionHelper.intersectsFullBounds(this, entity) && entity.isSolid){
                posX = lastPosX;
                posY = lastPosY;

            }
        }catch (Exception e){

        }


        skeleton.setPosition(posX, posY);
        vector3CameraPosition.set(posX, posY + height/2, 0);
    }

    @Override
    public void doCollisions() {

    }

    @Override
    public void onDeath() {

    }

    @Override
    public void pickup() {

    }


}
