package com.meteor.meteorpuxiidea.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemRatingPistol extends Item {
    public ItemRatingPistol(Properties prop) {
        super(prop);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity livingEntity, InteractionHand hand) {
        if(livingEntity instanceof Player){
            if (player.level().isClientSide) {
                return InteractionResult.SUCCESS;
            }
            Player target = (Player) livingEntity;
            String name = target.getName().getString();
            int hashcode = name.hashCode();
            int rating = Math.abs(hashcode % 100);
            if(name.equals("ExtraMeteorP")){
                rating = 110;
            }
            player.sendSystemMessage(Component.translatable("meteorpuxiideamisc.rating", name, rating));
        }
        return InteractionResult.PASS;
    }

}
