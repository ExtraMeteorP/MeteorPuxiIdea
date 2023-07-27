package com.meteor.meteorpuxiidea.common.item;

import com.meteor.meteorpuxiidea.common.helper.ItemNBTHelper;
import com.meteor.meteorpuxiidea.common.helper.PaintHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ItemPaintCollection extends Item {

    public ItemPaintCollection(Properties prop) {
        super(prop);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        int collectionId = ItemNBTHelper.getInt(itemstack, "collectionId", 4);
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

    @NotNull
    @Override
    public Component getName(@NotNull ItemStack stack) {
        return Component.translatable("item.meteorpuxiidea.paintcollection" + ItemNBTHelper.getInt(stack, "collectionId", 4));
    }

}
