package cn.ksmcbrigade.scg.mixin;

import cn.ksmcbrigade.scg.gui.ClickGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractSelectionList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractSelectionList.class)
public abstract class AbstractSelectionListMixin {
    @Shadow public abstract int getRowWidth();

    @Inject(method = "getRowLeft",at = @At("RETURN"),cancellable = true)
    public void left(CallbackInfoReturnable<Integer> cir){
        if(Minecraft.getInstance().screen instanceof ClickGui) cir.setReturnValue(cir.getReturnValue()-this.getRowWidth()-5);
    }
}
