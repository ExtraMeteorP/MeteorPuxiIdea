package com.meteor.meteorpuxiidea.common.item;

import com.meteor.meteorpuxiidea.client.MiscellaneousModels;
import com.meteor.meteorpuxiidea.client.VecHelper;
import com.meteor.meteorpuxiidea.client.renderer.RenderPaint;
import com.meteor.meteorpuxiidea.common.ItemNBTHelper;
import com.meteor.meteorpuxiidea.common.PlayerHelper;
import com.meteor.meteorpuxiidea.common.entity.EntityPaint;
import com.meteor.meteorpuxiidea.common.entity.ModEntities;
import com.meteor.meteorpuxiidea.common.lib.LibMisc;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class ItemSpray extends Item {
    private static final String TAG_PAINTTYPE = "painttype";
    public static final int iconTypes = 3;

    public ItemSpray(Properties prop) {
        super(prop);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(player.isCrouching()){
            int type = ItemNBTHelper.getInt(itemstack, TAG_PAINTTYPE, 0);
            type = (type + 1) % iconTypes;
            ItemNBTHelper.setInt(itemstack, TAG_PAINTTYPE, type);
        }
        return InteractionResultHolder.pass(itemstack);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        if(ctx.getLevel() != null && !ctx.getPlayer().isCrouching()){
            List<EntityPaint> paints = ctx.getLevel().getEntitiesOfClass(EntityPaint.class, VecHelper.boxForRange(ctx.getClickLocation(), 3d));
            int maxDepth = 0;
            for(EntityPaint paint : paints){
                maxDepth = Math.max(maxDepth, paint.getPaintDepth());
            }
            EntityPaint paint = new EntityPaint(ctx.getLevel());
            paint.setPos(ctx.getClickLocation());
            paint.setPaintDepth(maxDepth + 1);
            paint.setPaintType(ItemNBTHelper.getInt(ctx.getItemInHand(), TAG_PAINTTYPE, 0));
            paint.setPaintDirection(ctx.getClickedFace());
            paint.setPaintRotation(ctx.getPlayer().getYRot());
            if(!ctx.getLevel().isClientSide()){
                ctx.getLevel().addFreshEntity(paint);
            }
        }
        return InteractionResult.PASS;
    }

}
