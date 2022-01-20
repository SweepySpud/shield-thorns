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

    //Make thorns compatible with shield
    /*@Inject(at = @At("HEAD"), method = "isAcceptableItem", cancellable = true)
    private void isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> returnValue){
        if (stack.getItem() instanceof ShieldItem) {
            returnValue.setReturnValue(true);
        }
    }
    }*/

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/ThornsEnchantment;<init>(Lnet/minecraft/enchantment/Enchantment$Rarity;[Lnet/minecraft/entity/EquipmentSlot;)V"))
    private static void setThornsEquipmentSlots(Args args) {
        System.out.println("mixin works");
        //args.set(0, Enchantment.Rarity.VERY_RARE);
        args.set(1, EquipmentSlot.values());

    }
}
