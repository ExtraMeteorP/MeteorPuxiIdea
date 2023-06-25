package com.meteor.meteorpuxiidea.client;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;

public class VecHelper {

    public static AABB boxForRange(Vec3 v, double range) {
        return boxForRange(v, range, range, range);
    }

    public static AABB boxForRange(Vec3 v, double rangeX, double rangeY, double rangeZ) {
        return new AABB(v.x - rangeX, v.y - rangeY, v.z - rangeZ, v.x + rangeX, v.y + rangeY, v.z + rangeZ);
    }

    public static float toRadians(float degrees) {
        return (float) (degrees / 180F * Math.PI);
    }

    public static Quaternionf rotateX(float degrees) {
        return new Quaternionf().rotateX(toRadians(degrees));
    }

    public static Quaternionf rotateY(float degrees) {
        return new Quaternionf().rotateY(toRadians(degrees));
    }

    public static Quaternionf rotateZ(float degrees) {
        return new Quaternionf().rotateZ(toRadians(degrees));
    }

}
