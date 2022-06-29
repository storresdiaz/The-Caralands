package com.caralands.entities.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.caralands.entities.Entity;
import com.esotericsoftware.spine.*;
import com.esotericsoftware.spine.utils.TwoColorPolygonBatch;

public class EntityObject extends Entity {
    public TextureAtlas skeletonAtlas;
    public Skeleton skeleton;
    public SkeletonBounds skeletonBounds;

    public AnimationState animationState;

    public EntityObject(float posX, float posY, String objectName){
        super.posX = posX;
        super.posY = posY;
        skeletonAtlas = new TextureAtlas(Gdx.files.internal("spine/objects/"+objectName+".atlas"));
        SkeletonJson json = new SkeletonJson(skeletonAtlas);
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal("spine/objects/"+objectName+".json"));
        AnimationStateData animationStateData = new AnimationStateData(skeletonData);
        animationState = new AnimationState(animationStateData);
        skeleton = new Skeleton(skeletonData);
        skeleton.setScale(0.4f,0.4f);
        skeletonBounds = new SkeletonBounds();
        skeletonBounds.update(skeleton, true);
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
    public void update(){
        animationState.update(Gdx.graphics.getDeltaTime());
        animationState.apply(skeleton);
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

    @Override
    public void pickup() {

    }
}
