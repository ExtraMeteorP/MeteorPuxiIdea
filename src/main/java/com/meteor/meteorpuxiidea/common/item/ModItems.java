package com.meteor.meteorpuxiidea.common.item;

import com.meteor.meteorpuxiidea.common.handler.PuxiSounds;
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

    public static final RegistryObject<Item> BASEBALL_BAT = register(LibItemName.BASEBALL_BAT,
            () -> new ItemBaseballBat(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1).durability(251)));

    public static final RegistryObject<Item> HE_WEAPON = register(LibItemName.HE_WEAPON,
            () -> new ItemHeWeapon(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));

    public static final RegistryObject<Item> PAINT_COLLECTION = register(LibItemName.PAINT_COLLECTION,
            () -> new ItemPaintCollection(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));

    public static final RegistryObject<Item> PAINT_COLLECTION_CREATIVE = register(LibItemName.PAINT_COLLECTION_CREATIVE,
            () -> new ItemPaintCollectionCreative(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));

    public static final RegistryObject<Item> RECORD_SPACE_WALK = register(LibItemName.RECORD_SPACE_WALK,
            () -> new ItemRecord(1, PuxiSounds.SPACE_WALK, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1), 115));

    public static final RegistryObject<Item> RECORD_WILD_FIRE = register(LibItemName.RECORD_WILD_FIRE,
            () -> new ItemRecord(1, PuxiSounds.WILD_FIRE, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1), 218));

    private static RegistryObject<Item> register(String name, Supplier<Item> item) {
        return REGISTER.register(name, item);
    }

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            event.accept(SPRAY.get());
            event.accept(SPRAY_CREATIVE.get());
            event.accept(RATING_PISTOL.get());
            event.accept(BASEBALL_BAT.get());
            event.accept(HE_WEAPON.get());
            for(int i = 4; i < ItemSpray.iconTypes; i++){
                ItemStack stack = new ItemStack(PAINT_COLLECTION.get());
                ItemNBTHelper.setInt(stack, "collectionId", i);
                event.accept(stack);
            }
            event.accept(PAINT_COLLECTION_CREATIVE.get());
            event.accept(RECORD_SPACE_WALK.get());
            event.accept(RECORD_WILD_FIRE.get());
        }
    }

}
