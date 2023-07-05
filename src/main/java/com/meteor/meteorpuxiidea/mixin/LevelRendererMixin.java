package com.meteor.meteorpuxiidea.mixin;

import com.meteor.meteorpuxiidea.client.WorldOverlays;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.RenderBuffers;

import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {

    @Shadow
    @Final
    private RenderBuffers renderBuffers;
    @Shadow
    @Nullable
    private ClientLevel level;

    @Inject(
            method = "renderLevel",
            at = @At(
                    shift = At.Shift.AFTER,
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem;applyModelViewMatrix()V",
                    ordinal = 1 // after debugRenderer, before a long sequence of endBatch calls
            )
    )
    private void renderOverlays(PoseStack ps, float partialTicks, long unknown, boolean drawBlockOutline,
                                Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f projMat, CallbackInfo ci) {
        // Called from our own mixin instead of e.g. Forge's render world last event,
        // because that event is too late for Fabulous mode
        WorldOverlays.renderWorldLast(camera, partialTicks, ps, renderBuffers, level);
    }

}
