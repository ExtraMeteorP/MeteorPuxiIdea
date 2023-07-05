package com.meteor.meteorpuxiidea.client.renderer;

import com.meteor.meteorpuxiidea.client.MiscellaneousModels;
import com.meteor.meteorpuxiidea.client.VecHelper;
import com.meteor.meteorpuxiidea.common.entity.EntityPaint;
import com.meteor.meteorpuxiidea.common.lib.LibMisc;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;

public class RenderPaint extends EntityRenderer<EntityPaint> {
    public RenderPaint(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(EntityPaint tEntity, float yaw, float partialTicks, PoseStack ms, MultiBufferSource buffers, int light) {
        renderPaint(tEntity.getPaintType(), tEntity.getPaintRotation(), tEntity.getPaintDepth(), tEntity.getPaintDirection(), ms, buffers);
    }

    public static void renderPaint(int paintType, float paintRotation, int paintDepth, Direction direction, PoseStack ms, MultiBufferSource buffers){
        float scale = 1f;
        ms.scale(scale, scale, scale);
        float depth = 0.03f - paintDepth * 0.0001f;
        ms.pushPose();
        switch (direction){
            case UP :
                ms.mulPose(VecHelper.rotateX(90f));
                ms.translate(0, 0, depth);
                ms.mulPose(VecHelper.rotateZ(paintRotation));
                break;
            case DOWN:
                ms.mulPose(VecHelper.rotateX(-90f));
                ms.translate(0, 0, depth);
                ms.mulPose(VecHelper.rotateZ(-paintRotation));
                break;
            case EAST:
                ms.mulPose(VecHelper.rotateY(270f));
                ms.translate(0, 0, depth);
                break;
            case WEST:
                ms.mulPose(VecHelper.rotateY(90f));
                ms.translate(0, 0, depth);
                break;
            case NORTH:
                ms.mulPose(VecHelper.rotateY(0f));
                ms.translate(0, 0, depth);
                break;
            case SOUTH:
                ms.mulPose(VecHelper.rotateY(180f));
                ms.translate(0, 0, depth);
                break;
        }
        BakedModel model = MiscellaneousModels.INSTANCE.iconModels[paintType];

        ms.pushPose();
        ms.mulPose(VecHelper.rotateY(180f));
        ms.translate(-0.5, -0.5, -0.075);
        ms.scale(1f, 1f, 0.2f);
        VertexConsumer buffer = buffers.getBuffer(Sheets.translucentItemSheet());
        Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(ms.last(), buffer, null, model, 1, 1, 1, 0xF000F0, OverlayTexture.NO_OVERLAY);

        ms.popPose();
        ms.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(EntityPaint e) {
        return InventoryMenu.BLOCK_ATLAS;
    }

}
