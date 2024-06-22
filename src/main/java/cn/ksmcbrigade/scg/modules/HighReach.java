package cn.ksmcbrigade.scg.modules;

import cn.ksmcbrigade.vmr.module.Config;
import cn.ksmcbrigade.vmr.module.Module;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class HighReach extends Module {

    public static double addedReach = 3;

    public HighReach() throws IOException {
        super("hack.name.high_reach",false,-1,new Config(new File("HighReach"),data()),false);
    }

    @Override
    public void enabled(Minecraft MC) throws IOException {
        File pathFile = new File(Config.configDir,getConfig().file.getPath()+".json");
        addedReach = JsonParser.parseString(Files.readString(pathFile.toPath())).getAsJsonObject().get("added").getAsDouble();
        getConfig().setData(data());
        getConfig().save(true);
    }

    public static JsonObject data(){
        JsonObject object = new JsonObject();
        object.addProperty("added",addedReach);
        return object;
    }
}
