package com.meteor.meteorpuxiidea.common.item;

import com.meteor.meteorpuxiidea.common.helper.PaintHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemPaintCollection extends Item {

    private int collectionId;

    public ItemPaintCollection(Properties prop, int collectionId) {
        super(prop);
        this.collectionId = collectionId;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        boolean unlock = PaintHelper.unlockPaint(player, collectionId);
        if(unlock){
            if(!level.isClientSide())
                player.sendSystemMessage(Component.translatable("meteorpuxiideamisc.unlockPaintFail"));
        }else{
            if(!level.isClientSide())
                player.sendSystemMessage(Component.translatable("meteorpuxiideamisc.unlockPaintSuccess"));
            return InteractionResultHolder.consume(itemstack);
        }
        return InteractionResultHolder.pass(itemstack);
    }

}
