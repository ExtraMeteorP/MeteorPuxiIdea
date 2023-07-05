package com.meteor.meteorpuxiidea.client;

import com.meteor.meteorpuxiidea.client.renderer.PaintPreview;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.world.level.Level;

public final class WorldOverlays {

    public static void renderWorldLast(Camera camera, float tickDelta, PoseStack matrix, RenderBuffers buffers, Level level) {
        PaintPreview.onRenderWorldLast(camera, matrix, buffers);
    }

    private WorldOverlays() {}

}
