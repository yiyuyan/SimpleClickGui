package cn.ksmcbrigade.scg.mixin;

import cn.ksmcbrigade.vmr.uitls.ModuleUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingMixin extends Entity {
    public LivingMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Shadow public abstract double getAttributeValue(Attribute p_21134_);

    @Inject(method = "knockback",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getAttributeValue(Lnet/minecraft/world/entity/ai/attributes/Attribute;)D",shift = At.Shift.BEFORE), cancellable = true)
    public void knockback(double p_147241_, double p_147242_, double p_147243_, CallbackInfo ci){
        if(Minecraft.getInstance().player==null) return;
        if(this.getId()!=Minecraft.getInstance().player.getId()) return;
        if(ModuleUtils.enabled("hack.name.anti_kb")){
            p_147241_ = 0;
            p_147242_ = 0;
            p_147243_ = 0;
            p_147241_ *= 1.0 - this.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
            if (!(p_147241_ <= 0.0)) {
                this.hasImpulse = true;
                Vec3 vec3 = this.getDeltaMovement();
                Vec3 vec31 = (new Vec3(p_147242_, 0.0, p_147243_)).normalize().scale(p_147241_);
                this.setDeltaMovement(vec3.x / 2.0 - vec31.x, this.onGround() ? Math.min(0.4, vec3.y / 2.0 + p_147241_) : vec3.y, vec3.z / 2.0 - vec31.z);
            }
            ci.cancel();
        }
    }

    @Inject(method = "animateHurt",at = @At("HEAD"))
    public void hurtAni(float p_265265_, CallbackInfo ci){

    }
}
