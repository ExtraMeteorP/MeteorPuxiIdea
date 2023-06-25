package com.meteor.meteorpuxiidea.common.item;

import com.meteor.meteorpuxiidea.common.lib.LibItemName;
import com.meteor.meteorpuxiidea.common.lib.LibMisc;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, LibMisc.MOD_ID);

    public static final RegistryObject<Item> SPRAY = register(LibItemName.SPRAY, () -> new ItemSpray(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1)));

    private static RegistryObject<Item> register(String name, Supplier<Item> item) {
        return REGISTER.register(name, item);
    }

}
