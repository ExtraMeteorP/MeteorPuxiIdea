package com.meteor.meteorpuxiidea.client;

import com.meteor.meteorpuxiidea.client.renderer.PaintPreview;
import com.meteor.meteorpuxiidea.client.renderer.RenderPaint;
import com.meteor.meteorpuxiidea.common.entity.ModEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public class ClientHandler {

    public static void setup() {
        registerEntityRenderers();

        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener((RenderLevelStageEvent evt)->{
            if(evt.getStage() == RenderLevelStageEvent.Stage.AFTER_CUTOUT_BLOCKS){
                PaintPreview.onRenderWorldLast(evt.getCamera(), evt.getPartialTick(), evt.getPoseStack());
            }
        });
    }

    private static void registerEntityRenderers() {
        EntityRenderers.register(ModEntities.PAINT.get(), RenderPaint::new);
    }
}
