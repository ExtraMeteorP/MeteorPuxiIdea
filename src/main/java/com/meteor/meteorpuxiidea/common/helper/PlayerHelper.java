package com.meteor.meteorpuxiidea.common.helper;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class PlayerHelper {

    public static ItemStack getFirstHeldItem(LivingEntity living, Item item) {
        return getFirstHeldItem(living, s -> s.is(item));
    }

    public static ItemStack getFirstHeldItem(LivingEntity living, Predicate<ItemStack> pred) {
        ItemStack main = living.getMainHandItem();
        ItemStack offhand = living.getOffhandItem();
        if (!main.isEmpty() && pred.test(main)) {
            return main;
        } else if (!offhand.isEmpty() && pred.test(offhand)) {
            return offhand;
        } else {
            return ItemStack.EMPTY;
        }
    }

    public static ItemStack getFirstHeldItemClass(LivingEntity living, Class<?> template) {
        return getFirstHeldItem(living, s -> template.isAssignableFrom(s.getItem().getClass()));
    }

}
