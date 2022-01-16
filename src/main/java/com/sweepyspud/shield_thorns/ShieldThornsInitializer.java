package com.sweepyspud.shield_thorns;

import com.sweepyspud.shield_thorns.config.ShieldThornsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.fabricmc.api.ModInitializer;

public class ShieldThornsInitializer implements ModInitializer{
    public static final Logger LOGGER = LogManager.getLogger("Shield Thorns");

    @Override
    public void onInitialize(){
        AutoConfig.register(ShieldThornsConfig.class, GsonConfigSerializer::new);
        LOGGER.info("Shield Thorns are getting ready...");
    }
}
