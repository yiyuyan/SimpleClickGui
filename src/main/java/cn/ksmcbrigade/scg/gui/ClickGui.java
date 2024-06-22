package cn.ksmcbrigade.scg.gui;

import cn.ksmcbrigade.vmr.module.Module;
import cn.ksmcbrigade.vmr.uitls.ModuleUtils;
import com.mojang.blaze3d.platform.Monitor;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.awt.*;
import java.util.ArrayList;

public class ClickGui extends Screen {

    private OptionsList options;

    public ClickGui() {
        super(Component.nullToEmpty("ClickGui"));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void init() {
        if(this.minecraft==null) this.minecraft = Minecraft.getInstance();
        this.options = this.addRenderableWidget(new OptionsList(this.minecraft,this.width,this.height-32,12,22));
        this.options.setRenderBackground(false);

        ArrayList<OptionInstance<?>> optionInstances = new ArrayList<>();
        for(Module module:ModuleUtils.getAll(false)){
            optionInstances.add(OptionInstance.createBoolean(module.getName(),module.enabled,(b)-> {
                try {
                    module.setEnabled(b.booleanValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));
        }
        this.options.addSmall(optionInstances.toArray(new OptionInstance[0]));
    }

    @Override
    public void render(GuiGraphics p_281549_, int p_281550_, int p_282878_, float p_282465_) {

        if(this.options!=null){
            this.options.render(p_281549_,p_281550_,p_282878_,p_282465_);
            Minecraft MC = Minecraft.getInstance();

            p_281549_.drawCenteredString(MC.font,"All Modules",this.width/2,6,Color.WHITE.getRGB());
        }
    }
}
