package com.meteor.meteorpuxiidea.client.renderer;

import com.meteor.meteorpuxiidea.client.MiscellaneousModels;
import com.meteor.meteorpuxiidea.client.VecHelper;
import com.meteor.meteorpuxiidea.common.ItemNBTHelper;
import com.meteor.meteorpuxiidea.common.PlayerHelper;
import com.meteor.meteorpuxiidea.common.item.ItemSpray;
import com.meteor.meteorpuxiidea.common.lib.LibMisc;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class PaintPreview {

    public static final RenderType PREVIEW_LAYER = new PreviewLayer();

    public static void onRenderWorldLast(Camera camera, float partialTicks, PoseStack ms) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if(player == null){
            return;
        }
        ItemStack stack = PlayerHelper.getFirstHeldItemClass(player, ItemSpray.class);
        if (stack.isEmpty()) {
            return;
        }
        BlockHitResult result = (BlockHitResult) player.pick(8, 1, false);
        if(result != null && player.getEyePosition().closerThan(result.getLocation(), player.getBlockReach())){

            MultiBufferSource.BufferSource bufferSource = mc.renderBuffers().bufferSource();
            VertexConsumer buffer = bufferSource.getBuffer(PREVIEW_LAYER);
            Direction direction = result.getDirection();
            Vec3 vec = result.getLocation();

            ms.pushPose();

            double renderPosX = camera.getPosition().x();
            double renderPosY = camera.getPosition().y();
            double renderPosZ = camera.getPosition().z();

            ms.translate(vec.x - renderPosX,  vec.y - renderPosY, vec.z - renderPosZ);
            ms.pushPose();
            renderPaint(ItemNBTHelper.getInt(stack, "painttype", 0), player.getYRot(), 10, direction, ms, buffer);
            ms.popPose();
            ms.popPose();

            bufferSource.endBatch(PREVIEW_LAYER);
        }
    }

    public static void renderPaint(int paintType, float paintRotation, int paintDepth, Direction direction, PoseStack ms, VertexConsumer buffer){
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
        ms.translate(-0.5, -0.5, -0.06);
        ms.scale(1f, 1f, 0.2f);

        Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(ms.last(), buffer, null, model, 1, 1, 1, 0xF000F0, OverlayTexture.NO_OVERLAY);

        ms.popPose();
        ms.popPose();
    }

    private static class PreviewLayer extends RenderType {
        public PreviewLayer() {
            super(LibMisc.MOD_ID + "preview", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, true, true,
                    () -> {
                        Sheets.translucentCullBlockSheet().setupRenderState();
                        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.4F);
                    }, () -> {
                        Sheets.translucentCullBlockSheet().clearRenderState();
                    });
        }
    }
}
