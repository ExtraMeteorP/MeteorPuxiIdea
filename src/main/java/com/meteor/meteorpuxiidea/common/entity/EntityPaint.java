package com.meteor.meteorpuxiidea.common.entity;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class EntityPaint extends Entity {

    private static final String TAG_PERMANENT = "permanent";
    private static final String TAG_DEPTH = "depth";
    private static final String TAG_ROTATION = "rotation";
    private static final String TAG_TYPE = "type";
    private static final String TAG_DIRECTIION = "direction";

    private static final EntityDataAccessor<Boolean> PERMANENT = SynchedEntityData.defineId(EntityPaint.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DEPTH = SynchedEntityData.defineId(EntityPaint.class,
            EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> ROTATION = SynchedEntityData.defineId(EntityPaint.class,
            EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(EntityPaint.class,
            EntityDataSerializers.INT);
    private static final EntityDataAccessor<Direction> DIRECTION = SynchedEntityData.defineId(EntityPaint.class,
            EntityDataSerializers.DIRECTION);

    public EntityPaint(EntityType<? extends Entity> p_38290_, Level p_38291_) {
        super(p_38290_, p_38291_);
    }

    public EntityPaint(Level level) {
        super(ModEntities.PAINT.get(), level);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(PERMANENT, false);
        this.entityData.define(DEPTH, 0);
        this.entityData.define(TYPE, 0);
        this.entityData.define(DIRECTION, Direction.EAST);
        this.entityData.define(ROTATION, 0f);
    }

    @Override
    public void tick(){
        super.tick();
        if(!getPaintPermanent() && tickCount >= 6000){
            this.remove(RemovalReason.KILLED);
        }
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double p_19883_) {
        double d0 = 1D;
        d0 *= 64.0D;
        return p_19883_ < d0 * d0;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        setPaintPermanent(tag.getBoolean(TAG_PERMANENT));
        setPaintDepth(tag.getInt(TAG_DEPTH));
        setPaintType(tag.getInt(TAG_TYPE));
        setPaintDirection(Direction.byName(tag.getString(TAG_DIRECTIION)));
        setPaintRotation(tag.getFloat(TAG_ROTATION));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putBoolean(TAG_PERMANENT, getPaintPermanent());
        tag.putInt(TAG_DEPTH, getPaintDepth());
        tag.putInt(TAG_TYPE, getPaintType());
        tag.putString(TAG_DIRECTIION, getPaintDirection().getName());
        tag.putFloat(TAG_ROTATION, getPaintRotation());
    }

    public boolean getPaintPermanent(){
        return this.entityData.get(PERMANENT);
    }

    public void setPaintPermanent(boolean b){
        this.entityData.set(PERMANENT, b);
    }

    public float getPaintRotation(){
        return this.entityData.get(ROTATION);
    }

    public void setPaintRotation(float f){
        this.entityData.set(ROTATION, f);
    }

    public int getPaintDepth(){
        return this.entityData.get(DEPTH);
    }

    public void setPaintDepth(int i){
        this.entityData.set(DEPTH, i);
    }

    public int getPaintType(){
        return this.entityData.get(TYPE);
    }

    public void setPaintType(int i){
        this.entityData.set(TYPE, i);
    }

    public Direction getPaintDirection(){
        return this.entityData.get(DIRECTION);
    }

    public void setPaintDirection(Direction direction){
        this.entityData.set(DIRECTION, direction);
    }

}
