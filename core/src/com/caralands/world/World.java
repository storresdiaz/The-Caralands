package com.caralands.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.caralands.assets.Sounds;
import com.caralands.entities.Entity;
import com.caralands.entities.EntityPlayer;
import com.caralands.entities.weapons.EntityWeaponSheetMetal;
import com.caralands.ui.UserInterface;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.esotericsoftware.spine.SkeletonRendererDebug;
import com.esotericsoftware.spine.utils.TwoColorPolygonBatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class World {

    public static OrthographicCamera camera, cameraUi;

    public static SpriteBatch spriteBatch, uiSpriteBatch;
    private StretchViewport viewport, viewportUi;
    public static EntityPlayer entityPlayer;
    private static ArrayList<Entity> entities;

    private TwoColorPolygonBatch polygonBatch;

    private SkeletonRenderer skeletonRenderer;

    private SkeletonRendererDebug skeletonRendererDebug;

    private UserInterface userInterface;

    /**
     * The main holder for everything the world contains including global cameras and access to the user interface
     * for classes to use.
     *
     * Initialize an instance of this in a Screen then render+update.
     *
     */
    public World(){

        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        cameraUi = new OrthographicCamera();
        cameraUi.setToOrtho(false, 1920, 1080);
        cameraUi.position.set(960, 540, 0);

        viewport = new StretchViewport(1920, 1080);
        viewport.setCamera(camera);

        viewportUi = new StretchViewport(1920, 1080);
        viewportUi.setCamera(cameraUi);

        spriteBatch = new SpriteBatch();
        uiSpriteBatch = new SpriteBatch();


        polygonBatch = new TwoColorPolygonBatch();

        skeletonRenderer = new SkeletonRenderer();
        skeletonRenderer.setPremultipliedAlpha(true);

        skeletonRendererDebug = new SkeletonRendererDebug();
        skeletonRendererDebug.setPremultipliedAlpha(true);

        entityPlayer = new EntityPlayer(100, 100);
        entities = new ArrayList<>();
        entities.add(entityPlayer);
        entities.add(new EntityWeaponSheetMetal(300, 200));

        userInterface = new UserInterface();

        Sounds.loadSounds();

    }

    public void render(){
        camera.update();
        camera.position.lerp(entityPlayer.vector3CameraPosition, 0.1f);
        cameraUi.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        uiSpriteBatch.setProjectionMatrix(cameraUi.combined);
        polygonBatch.setProjectionMatrix(camera.combined);
        skeletonRendererDebug.getShapeRenderer().setProjectionMatrix(camera.combined);

        renderEntities();
        userInterface.render();
    }

    private void renderEntities(){

        for(Iterator<Entity> entityIterator = entities.iterator(); entityIterator.hasNext();){
            Entity entity = entityIterator.next();
            entity.render(polygonBatch, skeletonRenderer);
            //entity.renderDebug(skeletonRendererDebug);

            for(Iterator<Entity> entityIteratorTarget = entities.iterator(); entityIteratorTarget.hasNext();){
                Entity entityTarget = entityIteratorTarget.next();
                if(entityTarget != entity) {
                    entity.doCollisions(entityTarget);
                }
            }

            if(entities.size() == 1){
                entity.doCollisions(null);
            }

            entity.update();

            if(!entity.isAlive){
                entityIterator.remove();
            }

        }


        // Z index sorting for rendering
        Collections.sort(entities, new Comparator<Entity>() {
            @Override
            public int compare(Entity o1, Entity o2) {
                return (int) (o2.getPosY() - o1.getPosY());
            }
        });

    }

    public void update(){
        userInterface.update();
    }

    public void resize(int width, int height){
        viewport.update(width, height);
        viewportUi.update(width, height);
    }

}
