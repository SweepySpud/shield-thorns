package com.sweepyspud.shield_thorns.mixin;

import com.sweepyspud.shield_thorns.ShieldThornsInitializer;
import com.sweepyspud.shield_thorns.config.ShieldThornsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.enchantment.ThornsEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;
import java.util.Random;

//Make thorns compatible with shield/cancel vanilla behavior
@Mixin(ThornsEnchantment.class)
public class ThornsEnchantmentMixin {



    //Inject when the function damaging attacker is invoked, capture map entry that contains the current item with thorns as local variable
    //Cancel vanilla behavior of damaging attacker for default, use our own implementation instead unless otherwise specified in config
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"), method = "onUserDamaged", locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void onUserDamaged(LivingEntity user, Entity attacker, int level, CallbackInfo ci, Random random, Map.Entry<EquipmentSlot, ItemStack> entry) {
        if (entry != null && ShieldThornsInitializer.isInitialized) {
            ShieldThornsConfig config = AutoConfig.getConfigHolder(ShieldThornsConfig.class).getConfig();
            if (entry.getValue().getItem() instanceof ShieldItem && config.enableShieldBlockDamage) {
                ci.cancel();
            }
        }
    }

    //Make thorns applicable on shields
    @Inject(at = @At("HEAD"), method = "isAcceptableItem", cancellable = true)
    private void isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> returnValue) {
        if (stack.getItem() instanceof ShieldItem) {
            returnValue.setReturnValue(true);
        }
    }
}
