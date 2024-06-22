package cn.ksmcbrigade.scg.mixin;

import cn.ksmcbrigade.vmr.uitls.ModuleUtils;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "displayFireAnimation",at = @At("RETURN"),cancellable = true)
    public void fire(CallbackInfoReturnable<Boolean> cir){
        if(ModuleUtils.enabled("hack.name.no_fire_ani")) cir.setReturnValue(false);
    }
}
