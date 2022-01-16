package com.sweepyspud.shield_thorns.mixin;

import net.minecraft.enchantment.ThornsEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ThornsEnchantment.class)
public class ThornsEnchantmentMixin{

    //Make thorns compatible with shield
    @Inject(at = @At("HEAD"), method = "isAcceptableItem", cancellable = true)
    private void isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> returnValue){
        if (stack.getItem() instanceof ShieldItem) {
            returnValue.setReturnValue(true);
        }
    }
}
