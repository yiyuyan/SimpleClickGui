package cn.ksmcbrigade.scg.modules;

import cn.ksmcbrigade.vmr.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public class NoFireAnimation extends Module {
    public NoFireAnimation() {
        super("hack.name.no_fire_ani");
    }

    @Override
    public void playerTick(Minecraft MC, @Nullable Player player) {
        if(MC.cameraEntity==null) return;
        MC.cameraEntity.setSecondsOnFire(0);
        MC.cameraEntity.setRemainingFireTicks(0);
        MC.cameraEntity.setSharedFlagOnFire(false);
        MC.cameraEntity.clearFire();
    }
}
