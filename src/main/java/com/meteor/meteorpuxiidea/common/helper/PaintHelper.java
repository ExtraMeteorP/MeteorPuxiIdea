package com.meteor.meteorpuxiidea.common.helper;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class PaintHelper {

    public static boolean unlockPaint(Player player, int paintType){
        CompoundTag persistentData = player.getPersistentData();
        String tagName = "PaintTypesUnlock";
        int[] arr = persistentData.getIntArray(tagName);
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        boolean unlock = list.contains(paintType);
        if(!unlock){
            list.add(paintType);
            persistentData.putIntArray(tagName, list);
        }
        return unlock;
    }

    public static List<Integer> getAllPaint(Player player){
        CompoundTag persistentData = player.getPersistentData();
        String tagName = "PaintTypesUnlock";
        if(!persistentData.contains(tagName)){
            List<Integer> newList = new ArrayList<>();
            newList.add(0);
            newList.add(1);
            newList.add(2);
            newList.add(3);
            persistentData.putIntArray(tagName, newList);
        }
        int[] arr = persistentData.getIntArray(tagName);
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList()).stream().sorted().toList();
        return list;
    }

}
