package com.meteor.meteorpuxiidea.client.gui;

import com.meteor.meteorpuxiidea.common.lib.LibMisc;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ButtonPaint extends Button {

    public static final ResourceLocation WIDGETS_LOCATION = new ResourceLocation(LibMisc.MOD_ID, "textures/gui/paintslot.png");
    private int paintId;
    private boolean isSelected;

    public ButtonPaint(int p_259075_, int p_259271_, int p_260232_, int p_260028_, Component p_259351_, OnPress p_260152_, int paintId, boolean isSelected) {
        super(p_259075_, p_259271_, p_260232_, p_260028_, p_259351_, p_260152_, Button.DEFAULT_NARRATION);
        this.paintId = paintId;
        this.isSelected = isSelected;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int p_93677_, int p_93678_, float p_93679_) {
        ResourceLocation PAINT_LOCATION = new ResourceLocation(LibMisc.MOD_ID, "textures/item/paint_" + paintId + ".png");
        if(this.isSelected){
            guiGraphics.blit(WIDGETS_LOCATION, this.getX(), this.getY(), 0, 0, 64, 64);
        }
        guiGraphics.pose().pushPose();
        float s = 0.20f;
        guiGraphics.pose().scale(s, s, s);
        guiGraphics.blit(PAINT_LOCATION, (int) (this.getX()/s) + 28, (int) (this.getY()/s) + 28, 0, 0, 256, 256);
        guiGraphics.pose().popPose();
    }

    public void setPaintId(int i){
        this.paintId = i;
    }

    public int getPaintId(){
        return this.paintId;
    }

    public void setSelected(boolean b) {
        this.isSelected = b;
    }

}
