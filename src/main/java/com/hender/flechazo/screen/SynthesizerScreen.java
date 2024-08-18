package com.hender.flechazo.screen;

import com.hender.Hender;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SynthesizerScreen extends HandledScreen<SynthesizerScreenHandler> {
    private static final Identifier TEXTURES = Identifier.of(Hender.MOD_ID, "textures/gui/synthesizer_gui.png");
    public SynthesizerScreen(SynthesizerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURES);
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        context.drawTexture(TEXTURES, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgress1(context, x, y);
        renderProgress2(context, x, y);
        renderProgress3(context, x, y);
    }

    private void renderProgress3(DrawContext context, int x, int y) {
        if (handler.isCrafting() && handler.isRaining()){
            context.drawTexture(TEXTURES, x + 133, y + 21, 176, 50, handler.getScaledProgress3(), handler.getScaledProgress4());
        }
    }

    private void renderProgress2(DrawContext context, int x, int y) {
        if (handler.isCrafting() && handler.isRaining()){
            context.drawTexture(TEXTURES, x + 24, y + 24, 176, 36, handler.getScaledProgress2(), 12);
        }
    }

    private void renderProgress1(DrawContext context, int x, int y) {

        if (handler.isCrafting() && handler.isRaining()){
            context.drawTexture(TEXTURES, x + 7, y + 51,176 ,22, 18, handler.getScaledProgress1());
        }
    }
}
