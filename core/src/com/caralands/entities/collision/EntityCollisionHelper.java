package com.caralands.entities.collision;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.FloatArray;
import com.caralands.assets.Sounds;
import com.caralands.entities.Entity;
import com.esotericsoftware.spine.attachments.BoundingBoxAttachment;

public class EntityCollisionHelper {

    public static boolean intersectsFullBounds(Entity entityFirst, Entity entityTarget){
        try{
            BoundingBoxAttachment entityFirstBounds = (BoundingBoxAttachment) entityFirst.getSkeleton().getAttachment("bounds", "fullbounds");
            BoundingBoxAttachment entityTargetBounds = (BoundingBoxAttachment) entityTarget.getSkeleton().getAttachment("bounds", "fullbounds");
            FloatArray entityFirstVertices = new FloatArray();
            FloatArray entityTargetVertices = new FloatArray();

            entityFirstVertices = entityFirst.getSkeletonBounds().getPolygon(entityFirstBounds);
            entityTargetVertices = entityTarget.getSkeletonBounds().getPolygon(entityTargetBounds);

            if (Intersector.intersectPolygons(entityFirstVertices, entityTargetVertices)) {
                return true;
            }

        }catch (Exception e){
            return false;
        }

        return false;
    }

    public static boolean fullboundsIntersectsWeapon(Entity entityFirst, Entity entityAttacker){
        try{
            BoundingBoxAttachment entityFirstBounds = (BoundingBoxAttachment) entityFirst.getSkeleton().getAttachment("bounds", "fullbounds");
            BoundingBoxAttachment entityAttackerWeapon = (BoundingBoxAttachment) entityAttacker.getSkeleton().findSlot("weapon-bounds").getAttachment();

            FloatArray entityFirstVertices = new FloatArray();
            FloatArray entityAttackerVertices = new FloatArray();

            entityFirstVertices = entityFirst.getSkeletonBounds().getPolygon(entityFirstBounds);
            entityAttackerVertices = entityAttacker.getSkeletonBounds().getPolygon(entityAttackerWeapon);

            if (Intersector.intersectPolygons(entityFirstVertices, entityAttackerVertices)) {
                return true;
            }
        }catch (Exception e){
            return false;
        }

        return false;
    }
}
