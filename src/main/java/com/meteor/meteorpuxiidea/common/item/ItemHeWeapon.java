package com.meteor.meteorpuxiidea.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Collections;
import java.util.List;

public class ItemHeWeapon extends Item {

    public ItemHeWeapon(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(level instanceof ServerLevel serverLevel){
            List<ServerPlayer> players = serverLevel.players();
            if(players.size() <= 1){
                player.sendSystemMessage(Component.translatable("meteorpuxiideamisc.searchfail"));
                return InteractionResultHolder.fail(itemstack);
            }
            Collections.shuffle(players);
            ServerPlayer target = players.get(0);
            if(target.getName().getString().equals(player.getName().getString())){
                target = players.get(1);
            }
            target.sendSystemMessage(Component.translatable("meteorpuxiideamisc.warn", (int)target.getX(), (int)target.getY(), (int)target.getZ(), target.getName().getString()));
            player.sendSystemMessage(Component.translatable("meteorpuxiideamisc.searchsuccess", (int)target.getX(), (int)target.getY(), (int)target.getZ(), target.getName().getString()));
            player.getCooldowns().addCooldown(this, 600);
        }
        return InteractionResultHolder.pass(itemstack);
    }
}