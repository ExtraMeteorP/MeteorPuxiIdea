package com.meteor.meteorpuxiidea.common.network;

import com.meteor.meteorpuxiidea.common.lib.LibMisc;
import com.meteor.meteorpuxiidea.common.network.client.AllPaintPacket;
import com.meteor.meteorpuxiidea.common.network.server.SelectPaintPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class NetworkHandler {

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(LibMisc.MOD_ID, "main"),
            () -> "0",
            "0"::equals,
            "0"::equals);

    public static void init() {
        int i = 0;
        CHANNEL.registerMessage(i++, SelectPaintPacket.class, SelectPaintPacket::encode, SelectPaintPacket::decode,
                makeServerBoundHandler(SelectPaintPacket::handle));

        CHANNEL.registerMessage(i++, AllPaintPacket.class, AllPaintPacket::encode, AllPaintPacket::decode,
                makeClientBoundHandler(AllPaintPacket.Handler::handle));
    }

    private static <T> BiConsumer<T, Supplier<NetworkEvent.Context>> makeServerBoundHandler(TriConsumer<T, MinecraftServer, ServerPlayer> handler) {
        return (m, ctx) -> {
            handler.accept(m, ctx.get().getSender().getServer(), ctx.get().getSender());
            ctx.get().setPacketHandled(true);
        };
    }

    private static <T> BiConsumer<T, Supplier<NetworkEvent.Context>> makeClientBoundHandler(Consumer<T> consumer) {
        return (m, ctx) -> {
            consumer.accept(m);
            ctx.get().setPacketHandled(true);
        };
    }

}
