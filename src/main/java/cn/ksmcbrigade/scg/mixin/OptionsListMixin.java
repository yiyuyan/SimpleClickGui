package cn.ksmcbrigade.scg.mixin;

import cn.ksmcbrigade.scg.gui.ClickGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OptionsList.class)
public abstract class OptionsListMixin extends AbstractWidget {
    public OptionsListMixin(int p_93629_, int p_93630_, int p_93631_, int p_93632_, Component p_93633_) {
        super(p_93629_, p_93630_, p_93631_, p_93632_, p_93633_);
    }

    @Inject(method = "getRowWidth",at = @At("RETURN"),cancellable = true)
    public void width(CallbackInfoReturnable<Integer> cir){
        if(Minecraft.getInstance().screen instanceof ClickGui) cir.setReturnValue(cir.getReturnValue()/2);
    }
}
