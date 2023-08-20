package com.meteor.meteorpuxiidea.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class PerspectiveBakedModel implements BakedModel {

    private final BakedModel bakedModel2d;
    private final BakedModel bakedModel3d;

    public PerspectiveBakedModel(BakedModel bakedModel2d, BakedModel bakedModel3d) {
        this.bakedModel2d = bakedModel2d;
        this.bakedModel3d = bakedModel3d;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState p_235039_, @Nullable Direction p_235040_, RandomSource p_235041_) {
        return Collections.emptyList();
    }

    @Override
    public boolean useAmbientOcclusion() {
        return bakedModel2d.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return bakedModel2d.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return bakedModel2d.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return bakedModel2d.getParticleIcon();
    }

    @Override
    public ItemOverrides getOverrides() {
        return ItemOverrides.EMPTY;
    }

    @Override
    public BakedModel applyTransform(ItemDisplayContext transformType, PoseStack poseStack, boolean applyLeftHandTransform)
    {
        if(transformType == ItemDisplayContext.GUI || transformType == ItemDisplayContext.FIXED){
            return bakedModel2d.applyTransform(transformType, poseStack, applyLeftHandTransform);
        }else{
            return bakedModel3d.applyTransform(transformType, poseStack, applyLeftHandTransform);
        }
    }
}
