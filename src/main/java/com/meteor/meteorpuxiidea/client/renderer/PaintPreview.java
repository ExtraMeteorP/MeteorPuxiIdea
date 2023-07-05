package com.meteor.meteorpuxiidea.client.renderer;

import com.meteor.meteorpuxiidea.common.helper.ItemNBTHelper;
import com.meteor.meteorpuxiidea.common.helper.PlayerHelper;
import com.meteor.meteorpuxiidea.common.item.ItemSpray;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public final class PaintPreview {

    public static void onRenderWorldLast(Camera camera, PoseStack ms, RenderBuffers buffers) {
        MultiBufferSource.BufferSource bufferSource = buffers.bufferSource();

        Player player = Minecraft.getInstance().player;

        ItemStack stack = PlayerHelper.getFirstHeldItemClass(player, ItemSpray.class);
        if (stack.isEmpty()) {
            return;
        }

        BlockHitResult result = (BlockHitResult) player.pick(8, 1, false);
        if(result != null && player.getEyePosition().closerThan(result.getLocation(), player.getBlockReach())){

            Direction direction = result.getDirection();
            Vec3 vec = result.getLocation();

            ms.pushPose();

            double renderPosX = Minecraft.getInstance().getEntityRenderDispatcher().camera.getPosition().x();
            double renderPosY = Minecraft.getInstance().getEntityRenderDispatcher().camera.getPosition().y();
            double renderPosZ = Minecraft.getInstance().getEntityRenderDispatcher().camera.getPosition().z();

            ms.translate(-renderPosX, -renderPosY, -renderPosZ);
            ms.translate(vec.x, vec.y, vec.z);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.4F);
            RenderPaint.renderPaint(ItemNBTHelper.getInt(stack, "painttype", 0), player.getYRot(), 10, direction, ms, bufferSource);
            ms.popPose();
        }
    }

}
