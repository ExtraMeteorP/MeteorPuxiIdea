package com.meteor.meteorpuxiidea.common.network;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;

public interface Packet {

    default FriendlyByteBuf toBuf() {
        var ret = new FriendlyByteBuf(Unpooled.buffer());
        encode(ret);
        return ret;
    }

    void encode(FriendlyByteBuf buf);
}
