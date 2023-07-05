package com.meteor.meteorpuxiidea.common.item;

import com.meteor.meteorpuxiidea.client.VecHelper;
import com.meteor.meteorpuxiidea.common.helper.ItemNBTHelper;
import com.meteor.meteorpuxiidea.common.entity.EntityPaint;
import com.meteor.meteorpuxiidea.common.helper.PaintHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;


import java.util.List;

public class ItemSpray extends Item {
    public static final String TAG_PAINTTYPE = "painttype";
    public static final int iconTypes = 16;

    public ItemSpray(Properties prop) {
        super(prop);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(!level.isClientSide() && player.isCrouching()){
            List<Integer> paints = PaintHelper.getAllPaint(player);
            int type = ItemNBTHelper.getInt(itemstack, TAG_PAINTTYPE, 0);
            int index = paints.indexOf(type);
            index = (index + 1) % paints.size();
            ItemNBTHelper.setInt(itemstack, TAG_PAINTTYPE, paints.get(index));
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
