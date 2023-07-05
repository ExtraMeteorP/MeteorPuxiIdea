package com.meteor.meteorpuxiidea.common.network.server;

import com.meteor.meteorpuxiidea.common.helper.ItemNBTHelper;
import com.meteor.meteorpuxiidea.common.helper.PlayerHelper;
import com.meteor.meteorpuxiidea.common.item.ItemSpray;
import com.meteor.meteorpuxiidea.common.network.Packet;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class SelectPaintPacket implements Packet {

    private int paintId;

    public SelectPaintPacket(int paintId){
        this.paintId = paintId;
    }
    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(paintId);
    }

    public static SelectPaintPacket decode(FriendlyByteBuf buf) {
        return new SelectPaintPacket(buf.readInt());
    }

    public void handle(MinecraftServer server, ServerPlayer player) {
        server.execute(() -> {
            ItemStack stack = PlayerHelper.getFirstHeldItemClass(player, ItemSpray.class);
            if(!stack.isEmpty()){
                ItemNBTHelper.setInt(stack, ItemSpray.TAG_PAINTTYPE, paintId);
            }
        });
    }

}
