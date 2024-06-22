package cn.ksmcbrigade.scg.mixin;

import cn.ksmcbrigade.scg.gui.ClickGui;
import cn.ksmcbrigade.vmr.BuiltInModules.RainbowGui;
import cn.ksmcbrigade.vmr.uitls.ModuleUtils;
import com.google.gson.JsonElement;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.awt.*;

@Mixin(AbstractButton.class)
public abstract class ButtonMixin extends AbstractWidget {

    @Shadow @Final protected static WidgetSprites SPRITES;

    @Shadow public abstract void renderString(GuiGraphics p_283366_, Font p_283054_, int p_281656_);

    public ButtonMixin(int p_93629_, int p_93630_, int p_93631_, int p_93632_, Component p_93633_) {
        super(p_93629_, p_93630_, p_93631_, p_93632_, p_93633_);
    }


    /**
     * @author KSmc_brigade
     * @reason re
     */
    @Overwrite
    public void renderWidget(@NotNull GuiGraphics p_281670_, int p_281473_, int p_283021_, float p_282518_){

        ResourceLocation $$4 = SPRITES.get(this.active, this.isHoveredOrFocused());

        if(!(Minecraft.getInstance().screen instanceof ClickGui)) {
            Minecraft minecraft = Minecraft.getInstance();
            p_281670_.setColor(1.0F, 1.0F, 1.0F, this.alpha);
            RenderSystem.enableBlend();
            RenderSystem.enableDepthTest();
            p_281670_.blitSprite($$4, this.getX(), this.getY(), this.getWidth(), this.getHeight());
            p_281670_.setColor(1.0F, 1.0F, 1.0F, 1.0F);
            int i = this.getFGColor();
            this.renderString(p_281670_, minecraft.font, i | Mth.ceil(this.alpha * 255.0F) << 24);
            return;
        }

        Color color;
        if(!ModuleUtils.enabled("RainbowGui")){
            color = new Color(255,255,255,0);
        }
        else{
            JsonElement co = RainbowGui.getColor().get("c");
            color = co==null?new Color(255,255,255,0):new Color(co.getAsInt());
        }

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, $$4);
        RenderSystem.setShaderColor(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, 0);
        p_281670_.setColor(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, 0);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();

        p_281670_.blitSprite($$4, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        p_281670_.setColor(1F,1F,1F,1F);
        int i = this.getFGColor();
        this.renderString(p_281670_, Minecraft.getInstance().font, i | Mth.ceil(this.alpha * 255.0F) << 24);
    }
}
