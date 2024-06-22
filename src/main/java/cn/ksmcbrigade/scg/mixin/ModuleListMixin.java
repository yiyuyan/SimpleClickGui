package cn.ksmcbrigade.scg.mixin;

import cn.ksmcbrigade.scg.SimpleClickGui;
import cn.ksmcbrigade.vmr.BuiltInModules.ModulesList;
import cn.ksmcbrigade.vmr.BuiltInModules.RainbowGui;
import cn.ksmcbrigade.vmr.module.Module;
import cn.ksmcbrigade.vmr.uitls.ModuleUtils;
import com.google.gson.JsonElement;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.awt.*;

import static cn.ksmcbrigade.vmr.BuiltInModules.RainbowGui.now;

@Mixin(ModulesList.class)
public class ModuleListMixin {
    @Inject(remap = false,method = "renderGame",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;m_280488_(Lnet/minecraft/client/gui/Font;Ljava/lang/String;III)I",shift = At.Shift.BEFORE),locals = LocalCapture.CAPTURE_FAILSOFT)
    public void renderBackground(GuiGraphics pose, CallbackInfo ci, Minecraft MC, Font font, Module[] mods, int i, JsonElement color){
        Window window = MC.getWindow();

        int colors = Color.BLACK.getRGB();
        if(!ModuleUtils.enabled("RainbowGui")){
            return;
        }
        else if(SimpleClickGui.rainbowBackground){
            int sz = now+(RainbowGui.down?-2:2);
            if(sz<0){
                sz = 0;
            }
            if(sz==RainbowGui.colors.size()){
                sz-=2;
            }
            try {
                colors = RainbowGui.colors.get(sz==-1?0:sz==RainbowGui.colors.size()?(RainbowGui.colors.size()-1):sz).getRGB();
            }
            catch (Exception e){
                colors = RainbowGui.colors.get(RainbowGui.colors.size()-2).getRGB();
            }
        }

        pose.fill(window.getGuiScaledWidth()-font.width(I18n.get(mods[i].name))-2-2,2 + i * font.lineHeight,window.getGuiScaledWidth(),2 + (i+1) * font.lineHeight,colors);
    }
}
