package com.brandon3055.brandonscore.client.gui.modulargui;

import com.brandon3055.brandonscore.client.gui.modulargui.modularelements.ElementButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brandon3055 on 30/08/2016.
 */
public abstract class ModularGuiContainer extends GuiContainer implements IModularGui<ModularGuiContainer> {

    protected List<GuiElementBase> elements = new ArrayList<GuiElementBase>();

    public ModularGuiContainer(Container container) {
        super(container);
    }

    public void initElements() {
        for (GuiElementBase element : elements) {
            element.initElement();
        }
    }

    //region IModularGui

    @Override
    public ModularGuiContainer getScreen() {
        return this;
    }

    @Override
    public int xSize() {
        return xSize;
    }

    @Override
    public int ySize() {
        return ySize;
    }

    @Override
    public int guiLeft() {
        return guiLeft;
    }

    @Override
    public int guiTop() {
        return guiTop;
    }

    @Override
    public int screenWidth() {
        return width;
    }

    @Override
    public int screenHeight() {
        return height;
    }

    @Override
    public Minecraft getMinecraft() {
        return mc;
    }

    @Override
    public List<GuiElementBase> getElements() {
        return elements;
    }

    @Override
    public void elementButtonAction(ElementButton button) {
    }

    //endregion

    //region Mouse & Key

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (GuiElementBase element : elements) {
            if (element.isEnabled() && element.mouseClicked(mouseX, mouseY, mouseButton)) {
                return;
            }
        }

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        for (GuiElementBase element : elements) {
            if (element.isEnabled()) {
                element.mouseReleased(mouseX, mouseY, state);
            }
        }

        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        for (GuiElementBase element : elements) {
            if (element.isEnabled()) {
                element.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
            }
        }

        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        for (GuiElementBase element : elements) {
            if (element.isEnabled() && element.keyTyped(typedChar, keyCode)) {
                return;
            }
        }

        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void handleMouseInput() throws IOException {
        for (GuiElementBase element : elements) {
            if (element.isEnabled()) {
                element.handleMouseInput();
            }
        }

        super.handleMouseInput();
    }

    //endregion

    //region Render

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        renderBackgroundLayer(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(-guiLeft(), -guiTop(), 0.0F);

        renderForegroundLayer(mouseX, mouseY, mc.getRenderPartialTicks());

        GlStateManager.popMatrix();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        renderOverlayLayer(mouseX, mouseY, partialTicks);
    }


    public void renderBackgroundLayer(int mouseX, int mouseY, float partialTicks) {
        for (GuiElementBase element : elements) {
            if (element.isEnabled()) {
                element.renderBackgroundLayer(mc, mouseX, mouseY, partialTicks);
            }
        }
    }

    public void renderForegroundLayer(int mouseX, int mouseY, float partialTicks) {
        for (GuiElementBase element : elements) {
            if (element.isEnabled()) {
                element.renderForegroundLayer(mc, mouseX, mouseY, partialTicks);
            }
        }
    }

    public void renderOverlayLayer(int mouseX, int mouseY, float partialTicks) {
        for (GuiElementBase element : elements) {
            if (element.isEnabled()) {
                element.renderOverlayLayer(mc, mouseX, mouseY, partialTicks);
            }
        }
    }
    //endregion
}