package com.sweepyspud.shield_thorns.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.Environment;

import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi{
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory(){
        return parent -> AutoConfig.getConfigScreen(ShieldThornsConfig.class, parent).get();
    }
}
