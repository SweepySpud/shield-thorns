package com.sweepyspud.shield_thorns.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Enchantments.class)
public class EnchantmentsMixin {

    //Modifies the arguments where a ThornsEnchantment instance is defined in the Enchantments
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/ThornsEnchantment;<init>(Lnet/minecraft/enchantment/Enchantment$Rarity;[Lnet/minecraft/entity/EquipmentSlot;)V"))
    private static void setThornsEquipmentSlots(Args args) {
        //.values of an enum returns all values in enum, in EquipmentSlot it includes all armor slots + mainhand/offhand instead of the default behavior of only all armor slots
        args.set(1, EquipmentSlot.values());

    }
}
