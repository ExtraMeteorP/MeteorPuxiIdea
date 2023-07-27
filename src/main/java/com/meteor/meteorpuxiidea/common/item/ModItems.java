package com.meteor.meteorpuxiidea.common.item;

import com.meteor.meteorpuxiidea.common.helper.ItemNBTHelper;
import com.meteor.meteorpuxiidea.common.lib.LibItemName;
import com.meteor.meteorpuxiidea.common.lib.LibMisc;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, LibMisc.MOD_ID);

    public static final RegistryObject<Item> SPRAY = register(LibItemName.SPRAY,
            () -> new ItemSpray(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1).durability(30)));

    public static final RegistryObject<Item> SPRAY_CREATIVE = register(LibItemName.SPRAY_CREATIVE,
            () -> new ItemSprayCreative(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));

    public static final RegistryObject<Item> RATING_PISTOL = register(LibItemName.RATING_PISTOL,
            () -> new ItemRatingPistol(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));

    public static final RegistryObject<Item> PAINT_COLLECTION = register(LibItemName.PAINT_COLLECTION,
            () -> new ItemPaintCollection(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));

    public static final RegistryObject<Item> PAINT_COLLECTION_CREATIVE = register(LibItemName.PAINT_COLLECTION_CREATIVE,
            () -> new ItemPaintCollectionCreative(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));


    private static RegistryObject<Item> register(String name, Supplier<Item> item) {
        return REGISTER.register(name, item);
    }

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            event.accept(SPRAY.get());
            event.accept(SPRAY_CREATIVE.get());
            event.accept(RATING_PISTOL.get());
            for(int i = 4; i < ItemSpray.iconTypes; i++){
                ItemStack stack = new ItemStack(PAINT_COLLECTION.get());
                ItemNBTHelper.setInt(stack, "collectionId", i);
                event.accept(stack);
            }
            event.accept(PAINT_COLLECTION_CREATIVE.get());
        }
    }

}
