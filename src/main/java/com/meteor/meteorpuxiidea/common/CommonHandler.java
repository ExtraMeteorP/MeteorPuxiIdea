package com.meteor.meteorpuxiidea.common;

import com.meteor.meteorpuxiidea.common.helper.PaintHelper;
import com.meteor.meteorpuxiidea.common.network.NetworkHandler;
import com.meteor.meteorpuxiidea.common.network.client.AllPaintPacket;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
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

        bus.addListener((LivingEvent.LivingTickEvent evt) -> {
            if(evt.getEntity() instanceof Player player){
                Level level = player.level();
                if(level.isClientSide()){
                    Vec3 vec = player.getViewVector(1.0f).xRot((float) (Math.PI * 0.5f)).scale(0.13d);
                    double mx = vec.x;
                    double my = vec.y;
                    double mz = vec.z;
                    //level.addParticle(ParticleTypes.END_ROD, player.getX(), player.getY() + 0.5f, player.getZ(), mx, my, mz);
                }
            }
        });
    }

}
