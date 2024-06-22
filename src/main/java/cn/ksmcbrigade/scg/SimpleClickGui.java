package cn.ksmcbrigade.scg;

import cn.ksmcbrigade.scg.gui.ClickGui;
import cn.ksmcbrigade.scg.modules.AntiKnockBack;
import cn.ksmcbrigade.scg.modules.HighReach;
import cn.ksmcbrigade.scg.modules.NoFireAnimation;
import cn.ksmcbrigade.vmr.uitls.ModuleUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Mod(SimpleClickGui.MODID)
@Mod.EventBusSubscriber(value = Dist.CLIENT,modid = SimpleClickGui.MODID)
public class SimpleClickGui {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "scg";

    public static boolean rainbowBackground = false;

    public SimpleClickGui() throws IOException {
        MinecraftForge.EVENT_BUS.register(this);
        File file = new File("config/scg-config.json");
        if(!file.exists()){
            JsonObject object = new JsonObject();
            object.addProperty("rainbowBackground",rainbowBackground);
            Files.writeString(file.toPath(),object.toString());
        }
        rainbowBackground = JsonParser.parseString(Files.readString(file.toPath())).getAsJsonObject().get("rainbowBackground").getAsBoolean();

        ModuleUtils.add(new HighReach());
        ModuleUtils.add(new AntiKnockBack());
        ModuleUtils.add(new NoFireAnimation());
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event){
        Minecraft MC = Minecraft.getInstance();
        if(MC.player!=null && InputConstants.isKeyDown(MC.getWindow().getWindow(), GLFW.GLFW_KEY_RIGHT_CONTROL)){
            MC.setScreen(new ClickGui());
        }
    }

    /*@SubscribeEvent
    public static void onKnockBack(LivingKnockBackEvent event){
        if(!event.getEntity().equals(Minecraft.getInstance().player)){
            return;
        }
        if(ModuleUtils.enabled("hack.name.anti_kb")){
            event.setRatioX(event.getOriginalRatioX()*0);
            event.setStrength(event.getOriginalStrength()*0);
            event.setRatioZ(event.getOriginalRatioZ()*0);
        }
    }*/
}
