package com.meteor.meteorpuxiidea.client;

import com.meteor.meteorpuxiidea.common.item.ItemSpray;
import com.meteor.meteorpuxiidea.common.lib.LibMisc;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.Map;
import java.util.function.Consumer;

public class MiscellaneousModels {

    public static final MiscellaneousModels INSTANCE = new MiscellaneousModels();
    public BakedModel[] iconModels = new BakedModel[ItemSpray.iconTypes];

    public void onModelRegister(ResourceManager resourceManager, Consumer<ResourceLocation> consumer) {
        for (int i = 0; i < ItemSpray.iconTypes; i++) {
            consumer.accept(new ResourceLocation(LibMisc.MOD_ID, "icon/paint_" + i));
        }
    }

    public void onModelBake(ModelBakery loader, Map<ResourceLocation, BakedModel> map) {
        for (int i = 0; i < ItemSpray.iconTypes; i++) {
            iconModels[i] = map.get(new ResourceLocation(LibMisc.MOD_ID, "icon/paint_" + i));
        }
    }
}
