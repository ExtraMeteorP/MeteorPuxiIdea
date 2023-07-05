package com.meteor.meteorpuxiidea.client;

import com.meteor.meteorpuxiidea.client.gui.ScreenPaintSelect;
import com.meteor.meteorpuxiidea.common.helper.PlayerHelper;
import com.meteor.meteorpuxiidea.common.item.ItemSpray;
import com.meteor.meteorpuxiidea.common.lib.LibMisc;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = LibMisc.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeClientInitializer {

    public static KeyMapping PAINT_SELECT;

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent e) {
        initKeybindings(e::register);
    }

    @SubscribeEvent
    public static void clientInit(FMLClientSetupEvent evt) {
        var bus = MinecraftForge.EVENT_BUS;
        bus.addListener((InputEvent.Key e) ->{
            Minecraft mc = Minecraft.getInstance();
            if(mc.player == null){
                return;
            }
            if(!PlayerHelper.getFirstHeldItemClass(mc.player, ItemSpray.class).isEmpty()
                    && PAINT_SELECT.matches(e.getKey(), e.getScanCode())){
                mc.setScreen(new ScreenPaintSelect());
            }
        });
    }

    public static void initKeybindings(Consumer<KeyMapping> consumer) {
        PAINT_SELECT = new KeyMapping("key.meteorpuxiidea.paintselect", GLFW.GLFW_KEY_C, LibMisc.MOD_ID);
        consumer.accept(PAINT_SELECT);
    }

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
