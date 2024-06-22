package cn.ksmcbrigade.scg.mixin;

import cn.ksmcbrigade.scg.modules.HighReach;
import cn.ksmcbrigade.vmr.uitls.ModuleUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.extensions.IForgePlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = IForgePlayer.class,remap = false)
public interface IForgePlayerMixin {

    /**
     * @author KSmc_brigade
     * @reason addEntityReach
     */
    @Overwrite
    default double getEntityReach() {
        double range = Minecraft.getInstance().player.getAttributeValue((Attribute) ForgeMod.ENTITY_REACH.get());
        double ret = range == 0.0 ? 0.0 : range + (double)(Minecraft.getInstance().player.isCreative() ? 3 : 0);
        if(ModuleUtils.enabled("hack.name.high_reach")) ret+=HighReach.addedReach;
        return ret;
    }

    /**
     * @author KSmc_brigade
     * @reason addBlockReach
     */
    @Overwrite
    default double getBlockReach() {
        double reach = Minecraft.getInstance().player.getAttributeValue((Attribute)ForgeMod.BLOCK_REACH.get());
        double ret = reach == 0.0 ? 0.0 : reach + (Minecraft.getInstance().player.isCreative() ? 0.5 : 0.0);
        if(ModuleUtils.enabled("hack.name.high_reach")) ret+=HighReach.addedReach;
        return ret;
    }
}
