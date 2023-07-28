package com.meteor.meteorpuxiidea;

import com.meteor.meteorpuxiidea.client.ClientHandler;
import com.meteor.meteorpuxiidea.common.CommonHandler;
import com.meteor.meteorpuxiidea.common.entity.ModEntities;
import com.meteor.meteorpuxiidea.common.handler.PuxiSounds;
import com.meteor.meteorpuxiidea.common.item.ModItems;
import com.meteor.meteorpuxiidea.common.lib.LibMisc;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod(LibMisc.MOD_ID)
public class MeteorPuxiIdea {

    public MeteorPuxiIdea(){
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        bind(Registries.SOUND_EVENT, PuxiSounds::init);
        ModItems.REGISTER.register(eventBus);
        ModEntities.REGISTER.register(eventBus);

        eventBus.addListener(ModItems::addCreative);
    }

    private static <T> void bind(ResourceKey<Registry<T>> registry, Consumer<BiConsumer<T, ResourceLocation>> source) {
        FMLJavaModLoadingContext.get().getModEventBus().addListener((RegisterEvent event) -> {
            if (registry.equals(event.getRegistryKey())) {
                source.accept((t, rl) -> event.register(registry, rl, () -> t));
            }
        });
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(CommonHandler::setup);
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(ClientHandler::setup);
    }

}
