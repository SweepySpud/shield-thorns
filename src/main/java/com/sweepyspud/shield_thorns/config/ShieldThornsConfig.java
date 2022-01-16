package com.sweepyspud.shield_thorns.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "shield_thorns")
public class ShieldThornsConfig implements ConfigData{
    public boolean enableShieldBlockDamage = true;
}
