package cn.ksmcbrigade.scg.mixin;

import cn.ksmcbrigade.scg.gui.ClickGui;
import cn.ksmcbrigade.vmr.module.Module;
import cn.ksmcbrigade.vmr.uitls.ModuleUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.AccessibilityOptionsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModuleUtils.class)
public class ModuleMixin {
    @Inject(remap = false,method = "set",at = @At(value = "INVOKE", target = "Lcn/ksmcbrigade/vmr/module/Module;setEnabled(Z)V",shift = At.Shift.AFTER))
    private static void set(String name, boolean enabled, CallbackInfo ci){
        if(Minecraft.getInstance().screen instanceof ClickGui){
           Minecraft.getInstance().setScreen(new ClickGui());
        }

        if(Minecraft.getInstance().screen instanceof AccessibilityOptionsScreen last){
            Minecraft.getInstance().setScreen(new AccessibilityOptionsScreen(last.lastScreen,last.options));
        }
    }
}
