package com.meteor.meteorpuxiidea.common.item;

import com.meteor.meteorpuxiidea.common.lib.LibItemName;
import com.meteor.meteorpuxiidea.common.lib.LibMisc;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, LibMisc.MOD_ID);

    public static final RegistryObject<Item> SPRAY = register(LibItemName.SPRAY,
            () -> new ItemSpray(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1)));

    public static final RegistryObject<Item> RATING_PISTOL = register(LibItemName.RATING_PISTOL,
            () -> new ItemRatingPistol(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));

    public static final RegistryObject<Item> PAINT_COLLECTION_4 = registerPaintCollection(4);
    public static final RegistryObject<Item> PAINT_COLLECTION_5 = registerPaintCollection(5);
    public static final RegistryObject<Item> PAINT_COLLECTION_6 = registerPaintCollection(6);
    public static final RegistryObject<Item> PAINT_COLLECTION_7 = registerPaintCollection(7);
    public static final RegistryObject<Item> PAINT_COLLECTION_8 = registerPaintCollection(8);
    public static final RegistryObject<Item> PAINT_COLLECTION_9 = registerPaintCollection(9);
    public static final RegistryObject<Item> PAINT_COLLECTION_10 = registerPaintCollection(10);
    public static final RegistryObject<Item> PAINT_COLLECTION_11 = registerPaintCollection(11);
    public static final RegistryObject<Item> PAINT_COLLECTION_12 = registerPaintCollection(12);
    public static final RegistryObject<Item> PAINT_COLLECTION_13 = registerPaintCollection(13);
    public static final RegistryObject<Item> PAINT_COLLECTION_14 = registerPaintCollection(14);
    public static final RegistryObject<Item> PAINT_COLLECTION_15 = registerPaintCollection(15);
    private static RegistryObject<Item> register(String name, Supplier<Item> item) {
        return REGISTER.register(name, item);
    }

    private static RegistryObject<Item> registerPaintCollection(int id){
        return register(LibItemName.PAINT_COLLECTION + id, () -> new ItemPaintCollection(new Item.Properties().rarity(Rarity.RARE).stacksTo(1), id));
    }

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            event.accept(SPRAY.get());
            event.accept(RATING_PISTOL.get());
            event.accept(PAINT_COLLECTION_4.get());
            event.accept(PAINT_COLLECTION_5.get());
            event.accept(PAINT_COLLECTION_6.get());
            event.accept(PAINT_COLLECTION_7.get());
            event.accept(PAINT_COLLECTION_8.get());
            event.accept(PAINT_COLLECTION_9.get());
            event.accept(PAINT_COLLECTION_10.get());
            event.accept(PAINT_COLLECTION_11.get());
            event.accept(PAINT_COLLECTION_12.get());
            event.accept(PAINT_COLLECTION_13.get());
            event.accept(PAINT_COLLECTION_14.get());
            event.accept(PAINT_COLLECTION_15.get());
        }
    }

}
