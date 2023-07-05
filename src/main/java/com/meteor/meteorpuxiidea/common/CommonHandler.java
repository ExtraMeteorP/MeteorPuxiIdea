package com.meteor.meteorpuxiidea.common;

import com.meteor.meteorpuxiidea.common.helper.PaintHelper;
import com.meteor.meteorpuxiidea.common.network.NetworkHandler;
import com.meteor.meteorpuxiidea.common.network.client.AllPaintPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.PacketDistributor;

import java.util.List;

public class CommonHandler {

    public static void setup() {
        NetworkHandler.init();
        registerEvents();
    }

    private static void registerEvents() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener((PlayerEvent.PlayerLoggedInEvent evt) -> {
            List<Integer> list = PaintHelper.getAllPaint(evt.getEntity());
            if(evt.getEntity() instanceof ServerPlayer serverPlayer){
                NetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new AllPaintPacket(list));
            }
        });
    }

}
