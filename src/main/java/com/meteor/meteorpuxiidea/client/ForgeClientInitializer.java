package com.meteor.meteorpuxiidea.client;

import com.meteor.meteorpuxiidea.common.lib.LibMisc;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LibMisc.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeClientInitializer {

    @SubscribeEvent
    public static void onModelRegister(ModelEvent.RegisterAdditional evt) {
        var resourceManager = Minecraft.getInstance().getResourceManager();
        MiscellaneousModels.INSTANCE.onModelRegister(resourceManager, evt::register);
    }

    @SubscribeEvent
    public static void onModelBake(ModelEvent.ModifyBakingResult evt) {
        MiscellaneousModels.INSTANCE.onModelBake(evt.getModelBakery(), evt.getModels());
    }

}
