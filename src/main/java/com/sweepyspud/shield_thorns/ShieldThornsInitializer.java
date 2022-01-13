package com.sweepyspud.shield_thorns;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;

public class ShieldThornsInitializer implements ModInitializer
{
    public static final Logger LOGGER = LogManager.getLogger("modid");

    @Override
    public void onInitialize()
    {
        LOGGER.info("Example is getting ready...");
    }
}
