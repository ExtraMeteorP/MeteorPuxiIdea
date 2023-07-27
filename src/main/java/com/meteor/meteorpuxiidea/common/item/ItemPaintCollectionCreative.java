package com.meteor.meteorpuxiidea.common.item;

import com.meteor.meteorpuxiidea.common.helper.PaintHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemPaintCollectionCreative extends Item {

    public ItemPaintCollectionCreative(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        for(int i = 4; i < ItemSpray.iconTypes; i++)
            PaintHelper.unlockPaint(player, i);
        return InteractionResultHolder.pass(itemstack);
    }

}
