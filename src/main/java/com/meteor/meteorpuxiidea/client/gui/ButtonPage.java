package com.meteor.meteorpuxiidea.client.gui;

import com.meteor.meteorpuxiidea.common.lib.LibMisc;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ButtonPage extends Button {

    public static final ResourceLocation WIDGETS_LOCATION = new ResourceLocation(LibMisc.MOD_ID, "textures/gui/pageicon.png");
    private boolean prev;

    protected ButtonPage(int p_259075_, int p_259271_, int p_260232_, int p_260028_, Component p_259351_, OnPress p_260152_, boolean prev) {
        super(p_259075_, p_259271_, p_260232_, p_260028_, p_259351_, p_260152_, Button.DEFAULT_NARRATION);
        this.prev = prev;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int p_93677_, int p_93678_, float p_93679_) {
        int i = this.isHoveredOrFocused() ? 1 : 0;
        guiGraphics.blit(WIDGETS_LOCATION, this.getX(), this.getY(), prev ? 24 : 0 , i*22, 14, 22);
    }

}
