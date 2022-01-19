package com.sweepyspud.shield_thorns.mixin;

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

@Mixin(ThornsEnchantment.class)
public class ThornsEnchantmentMixin{

    //Make thorns compatible with shield
    @Inject(at = @At("HEAD"), method = "isAcceptableItem", cancellable = true)
    private void isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> returnValue){
        if (stack.getItem() instanceof ShieldItem) {
            returnValue.setReturnValue(true);
        }
    }
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"), method = "onUserDamaged", locals = LocalCapture.PRINT, cancellable = true)
    private void onUserDamaged(LivingEntity user, Entity attacker, int level, CallbackInfo ci, Random random, Map.Entry<EquipmentSlot, ItemStack> entry){
            if(entry != null){
                if(entry.getValue().getItem() instanceof ShieldItem){
                    System.out.println("works");
                    ci.cancel();
                }
            }
    }
}
