package com.meteor.meteorpuxiidea.client.gui;

import com.google.common.collect.Lists;
import com.meteor.meteorpuxiidea.common.helper.ItemNBTHelper;
import com.meteor.meteorpuxiidea.common.helper.PlayerHelper;
import com.meteor.meteorpuxiidea.common.helper.PaintHelper;
import com.meteor.meteorpuxiidea.common.item.ItemSpray;
import com.meteor.meteorpuxiidea.common.network.NetworkHandler;
import com.meteor.meteorpuxiidea.common.network.server.SelectPaintPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class ScreenPaintSelect extends Screen {
    private final int COUNT = 8;
    private final List<ButtonPaint> dataList = Lists.newArrayList();
    protected Button prevButton;
    protected Button nextButton;

    private int curPage = 0;
    private int maxPage;

    public ScreenPaintSelect() {
        super(Component.translatable("meteorpuxiidea.screen.paintselect"));
    }

    @Override
    protected void init() {
        super.init();
        this.dataList.clear();

        Player player = Minecraft.getInstance().player;
        List<Integer> paintList = PaintHelper.getAllPaint(player);

        ItemStack stack = PlayerHelper.getFirstHeldItemClass(player, ItemSpray.class);
        int selected = -1;
        if(!stack.isEmpty()){
            selected = ItemNBTHelper.getInt(stack, ItemSpray.TAG_PAINTTYPE, 0);
        }

        if(selected != -1){
            int index = paintList.indexOf(selected);
            if(index != -1){
                curPage = index/COUNT;
            }
        }

        maxPage = paintList.size()/COUNT;

        for(int i = 0; i < COUNT; i++){
            int j = i / (COUNT / 2);
            int k = i - j * (COUNT / 2);
            ButtonPaint paint = new ButtonPaint(this.width/2 - 80 * 2 + 16 + 80 * k, this.height/7 + 20 + j * 80, 64, 64, Component.translatable("gui.paint"),(button)-> {
                ButtonPaint b = (ButtonPaint) button;
                NetworkHandler.CHANNEL.sendToServer(new SelectPaintPacket(b.getPaintId()));
                this.onClose();
            }, 0, false);
            this.dataList.add(paint);
            this.addRenderableWidget(paint);
        }

        prevButton = new ButtonPage(this.width/2 - 80 + 5, this.height/6*5, 22, 22, Component.translatable("gui.prev"), (button) -> {
            pageDown();
        }, true);

        nextButton = new ButtonPage(this.width/2 + 80, this.height/6*5, 22, 22, Component.translatable("gui.next"), (button) -> {
            pageUp();
        }, false);

        reload();
        this.addRenderableWidget(prevButton);
        this.addRenderableWidget(nextButton);

        updateButton();
    }

    private void reload(){
        Player player = Minecraft.getInstance().player;
        List<Integer> paintList = PaintHelper.getAllPaint(player);
        ItemStack stack = PlayerHelper.getFirstHeldItemClass(player, ItemSpray.class);
        int selected = -1;
        if(!stack.isEmpty()){
            selected = ItemNBTHelper.getInt(stack, ItemSpray.TAG_PAINTTYPE, 0);
        }
        for(int i = 0; i < COUNT; i++){
            ButtonPaint buttonPaint = this.dataList.get(i);
            int id = curPage * COUNT + i;
            if(id >= paintList.size()){
                buttonPaint.visible = false;
                continue;
            }
            int paintId = paintList.get(id);
            buttonPaint.visible = true;
            buttonPaint.setPaintId(paintId);
            buttonPaint.setSelected(selected == paintId);
        }
    }

    private void pageUp(){
        if(curPage < maxPage){
            curPage++;
        }
        reload();
        updateButton();
    }

    private void pageDown(){
        if(curPage > 0){
            curPage--;
        }
        reload();
        updateButton();
    }

    private void updateButton(){
        prevButton.visible = curPage != 0;
        nextButton.visible = curPage != maxPage;
    }

    @Override
    public boolean keyPressed(int p_96552_, int p_96553_, int p_96554_) {
        if ((p_96552_ == GLFW.GLFW_KEY_E || p_96552_ == 256) && this.shouldCloseOnEsc()) {
            this.onClose();
            return true;
        }
        return super.keyPressed(p_96552_,p_96553_,p_96554_);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mx, int my, float pTicks) {
        guiGraphics.fillGradient(0, 0, this.width, this.height, -1072689136, -804253680);
        guiGraphics.drawCenteredString(this.font, String.format("%d / %d", this.curPage + 1, this.maxPage + 1), this.width / 2 + 10, this.height/6*5, 16777215);
        super.render(guiGraphics, mx, my, pTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

}
