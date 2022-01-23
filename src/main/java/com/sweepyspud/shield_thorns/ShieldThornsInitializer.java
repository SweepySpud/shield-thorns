package com.sweepyspud.shield_thorns;

import com.sweepyspud.shield_thorns.config.ShieldThornsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.fabricmc.api.ModInitializer;

public class ShieldThornsInitializer implements ModInitializer{
    //static{ AutoConfig.register(ShieldThornsConfig.class, GsonConfigSerializer::new);}

    public static boolean isInitialized = false;
    public static final Logger LOGGER = LogManager.getLogger("Shield Thorns");

    @Override
    public void onInitialize(){
        LOGGER.info("Shield Thorns are getting ready...");
        AutoConfig.register(ShieldThornsConfig.class, GsonConfigSerializer::new);
        isInitialized = true;
    }
}
