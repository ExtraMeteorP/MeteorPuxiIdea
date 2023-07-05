package com.meteor.meteorpuxiidea.common.network.client;

import com.meteor.meteorpuxiidea.common.network.Packet;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

import java.util.ArrayList;
import java.util.List;

public class AllPaintPacket implements Packet {

    private List<Integer> data;
    public AllPaintPacket(List<Integer> data){
        this.data = data;
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        int length = data.size();
        buf.writeInt(length);
        for(Integer i : data){
            buf.writeInt(i);
        }
    }

    public static AllPaintPacket decode(FriendlyByteBuf buf) {
        int length = buf.readInt();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < length; i++){
            list.add(buf.readInt());
        }
        return new AllPaintPacket(list);
    }


    public static class Handler {
        public static void handle(AllPaintPacket packet) {
            Minecraft.getInstance().execute(() -> {
                CompoundTag persistentData = Minecraft.getInstance().player.getPersistentData();
                String tagName = "PaintTypesUnlock";
                persistentData.putIntArray(tagName, packet.data);
            });
        }
    }
}
