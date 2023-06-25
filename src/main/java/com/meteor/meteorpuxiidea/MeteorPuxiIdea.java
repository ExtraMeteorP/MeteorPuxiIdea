package com.meteor.meteorpuxiidea;

import com.meteor.meteorpuxiidea.client.ClientHandler;
import com.meteor.meteorpuxiidea.common.CommonHandler;
import com.meteor.meteorpuxiidea.common.entity.ModEntities;
import com.meteor.meteorpuxiidea.common.item.ModItems;
import com.meteor.meteorpuxiidea.common.lib.LibMisc;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(LibMisc.MOD_ID)
public class MeteorPuxiIdea {

    public MeteorPuxiIdea(){
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.REGISTER.register(eventBus);
        ModEntities.REGISTER.register(eventBus);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(CommonHandler::setup);
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(ClientHandler::setup);
    }

}
