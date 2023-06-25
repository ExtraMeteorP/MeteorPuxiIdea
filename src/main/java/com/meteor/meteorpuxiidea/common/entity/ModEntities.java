package com.meteor.meteorpuxiidea.common.entity;

import com.meteor.meteorpuxiidea.common.lib.LibMisc;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, LibMisc.MOD_ID);
    public static final RegistryObject<EntityType<EntityPaint>> PAINT = register("paint",
            EntityType.Builder.<EntityPaint>of((type, world) -> new EntityPaint(world), MobCategory.MISC)
                    .sized(0.1F, 0.1F)
                    .fireImmune()
                    .clientTrackingRange(32)
                    .setTrackingRange(32)
                    .updateInterval(40));

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String name, EntityType.Builder<T> builder) {
        return REGISTER.register(name, () -> builder.build(name));
    }

}
