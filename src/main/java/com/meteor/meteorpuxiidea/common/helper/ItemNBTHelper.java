package com.meteor.meteorpuxiidea.common.helper;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public final class ItemNBTHelper {

    private static final int[] EMPTY_INT_ARRAY = new int[0];

    // SETTERS ///////////////////////////////////////////////////////////////////

    public static void set(ItemStack stack, String tag, Tag nbt) {
        stack.getOrCreateTag().put(tag, nbt);
    }

    public static void setBoolean(ItemStack stack, String tag, boolean b) {
        stack.getOrCreateTag().putBoolean(tag, b);
    }

    public static void setByte(ItemStack stack, String tag, byte b) {
        stack.getOrCreateTag().putByte(tag, b);
    }

    public static void setShort(ItemStack stack, String tag, short s) {
        stack.getOrCreateTag().putShort(tag, s);
    }

    public static void setInt(ItemStack stack, String tag, int i) {
        stack.getOrCreateTag().putInt(tag, i);
    }

    public static void setIntArray(ItemStack stack, String tag, int[] val) {
        stack.getOrCreateTag().putIntArray(tag, val);
    }

    public static void setLong(ItemStack stack, String tag, long l) {
        stack.getOrCreateTag().putLong(tag, l);
    }

    public static void setFloat(ItemStack stack, String tag, float f) {
        stack.getOrCreateTag().putFloat(tag, f);
    }

    public static void setDouble(ItemStack stack, String tag, double d) {
        stack.getOrCreateTag().putDouble(tag, d);
    }

    public static void setCompound(ItemStack stack, String tag, CompoundTag cmp) {
        if (!tag.equalsIgnoreCase("ench")) // not override the enchantments
        {
            stack.getOrCreateTag().put(tag, cmp);
        }
    }

    public static void setString(ItemStack stack, String tag, String s) {
        stack.getOrCreateTag().putString(tag, s);
    }

    public static void setList(ItemStack stack, String tag, ListTag list) {
        stack.getOrCreateTag().put(tag, list);
    }

    public static void removeEntry(ItemStack stack, String tag) {
        stack.removeTagKey(tag);
    }

    // GETTERS ///////////////////////////////////////////////////////////////////

    public static boolean verifyExistance(ItemStack stack, String tag) {
        return !stack.isEmpty() && stack.hasTag() && stack.getOrCreateTag().contains(tag);
    }

    @Nullable
    public static Tag get(ItemStack stack, String tag) {
        return verifyExistance(stack, tag) ? stack.getOrCreateTag().get(tag) : null;
    }

    public static boolean getBoolean(ItemStack stack, String tag, boolean defaultExpected) {
        return verifyExistance(stack, tag) ? stack.getOrCreateTag().getBoolean(tag) : defaultExpected;
    }

    public static byte getByte(ItemStack stack, String tag, byte defaultExpected) {
        return verifyExistance(stack, tag) ? stack.getOrCreateTag().getByte(tag) : defaultExpected;
    }

    public static short getShort(ItemStack stack, String tag, short defaultExpected) {
        return verifyExistance(stack, tag) ? stack.getOrCreateTag().getShort(tag) : defaultExpected;
    }

    public static int getInt(ItemStack stack, String tag, int defaultExpected) {
        return verifyExistance(stack, tag) ? stack.getOrCreateTag().getInt(tag) : defaultExpected;
    }

    public static int[] getIntArray(ItemStack stack, String tag) {
        return verifyExistance(stack, tag) ? stack.getOrCreateTag().getIntArray(tag) : EMPTY_INT_ARRAY;
    }

    public static long getLong(ItemStack stack, String tag, long defaultExpected) {
        return verifyExistance(stack, tag) ? stack.getOrCreateTag().getLong(tag) : defaultExpected;
    }

    public static float getFloat(ItemStack stack, String tag, float defaultExpected) {
        return verifyExistance(stack, tag) ? stack.getOrCreateTag().getFloat(tag) : defaultExpected;
    }

    public static double getDouble(ItemStack stack, String tag, double defaultExpected) {
        return verifyExistance(stack, tag) ? stack.getOrCreateTag().getDouble(tag) : defaultExpected;
    }

    /**
     * If nullifyOnFail is true it'll return null if it doesn't find any
     * compounds, otherwise it'll return a new one.
     **/
    @Nullable
    @Contract("_, _, !null -> !null")
    public static CompoundTag getCompound(ItemStack stack, String tag, boolean nullifyOnFail) {
        return verifyExistance(stack, tag) ? stack.getOrCreateTag().getCompound(tag) : nullifyOnFail ? null : new CompoundTag();
    }

    @Nullable
    @Contract("_, _, !null -> !null")
    public static String getString(ItemStack stack, String tag, String defaultExpected) {
        return verifyExistance(stack, tag) ? stack.getOrCreateTag().getString(tag) : defaultExpected;
    }

    @Nullable
    @Contract("_, _, !null -> !null")
    public static ListTag getList(ItemStack stack, String tag, int objtype, boolean nullifyOnFail) {
        return verifyExistance(stack, tag) ? stack.getOrCreateTag().getList(tag, objtype) : nullifyOnFail ? null : new ListTag();
    }

}
